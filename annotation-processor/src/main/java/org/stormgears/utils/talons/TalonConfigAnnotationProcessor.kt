package org.stormgears.utils.talons

import com.squareup.kotlinpoet.*
import com.sun.tools.javac.code.Type // TODO: Update for Java 9+
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic.Kind.ERROR

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class GenerateTalonConfigReconciler

@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.SOURCE)
annotation class Config(
	val prefix: String,
	val timeout: Boolean = true,
	val deep: Boolean = false,
	val pidIdx: Boolean = false,
	val camel: Boolean = true,
	val map: Boolean = false
)

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class ConfigParam

@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.SOURCE)
annotation class PIDSlotConfig(val idx: Int)

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.stormgears.utils.talons.GenerateTalonConfigReconciler")
class TalonConfigAnnotationProcessor : AbstractProcessor() {
	companion object {
		const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
	}

	override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
		val annotatedElements = roundEnv.getElementsAnnotatedWith(GenerateTalonConfigReconciler::class.java)

		if (annotatedElements.isEmpty()) return false

		val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: run {
			processingEnv.messager.printMessage(ERROR, "Can't find the target directory for generated Kotlin files.")
			return false
		}

		annotatedElements.forEach {
			processClass(it).writeTo(File(kaptKotlinGeneratedDir, ""))
		}

		return true
	}

	private fun processClass(clazz: Element): FileSpec {
		val executableElements = clazz.enclosedElements.filter { it is ExecutableElement }.map { it as ExecutableElement }

//		processingEnv.messager.printMessage(NOTE, annotatedGetters.toString())

		val pack = processingEnv.elementUtils.getPackageOf(clazz)

		val generatedClassName = "GeneratedTalonManager"

		return FileSpec.builder(pack.toString(), generatedClassName).apply {
			addType(TypeSpec.classBuilder(generatedClassName).apply {
				addModifiers(KModifier.OPEN)
				primaryConstructor(FunSpec.constructorBuilder()
					.addParameter("talon", ClassName.bestGuess("org.stormgears.utils.talons.IBaseTalon"))
					.addParameter("config", ClassName.bestGuess("org.stormgears.utils.talons.TalonConfig"))
					.build())

				addProperty(PropertySpec.builder(
					"talon",
					ClassName.bestGuess("org.stormgears.utils.talons.IBaseTalon")
				).initializer("talon").build())

				addProperty(PropertySpec.builder(
					"prevConfig",
					ClassName.bestGuess("org.stormgears.utils.talons.TalonConfig").asNullable()
				).mutable(true).initializer("null").build())

				addInitializerBlock(CodeBlock.of("setConfig(config)"))

				addFunction(FunSpec.builder("setConfig").apply {
					addModifiers(KModifier.OPEN)
					buildSetConfig(clazz, executableElements)
					addStatement("this.prevConfig = config")
				}.build())
			}.build())
		}.build()
	}

	private fun FunSpec.Builder.buildSetConfig(clazz: Element, executableElements: List<ExecutableElement>) {
		addParameter("config", clazz.asType().asTypeName())

		addStatement("val prevConfig = this.prevConfig")
		for (el in executableElements) {
			val ann = el.getAnnotation(Config::class.java) ?: continue

			val prefix = ann.prefix
			val propName = el.simpleName.substring(3).decapitalize()
			val talonConfig = if (prefix.isBlank()) propName else prefix + propName.capitalize()

			var argStr = ""
			var codeBlock = ""
			if (ann.deep) {
				val returnType = el.returnType
				val element = processingEnv.typeUtils.asElement(returnType)

//				if (returnType is Type.ClassType) {
//					processingEnv.messager.printMessage(NOTE, returnType.toString())
//					processingEnv.messager.printMessage(NOTE, returnType.isInterface.toString())
//					processingEnv.messager.printMessage(NOTE, returnType.parameterTypes.toString())
//				}

				if (returnType is Type.ClassType) {
					if (returnType.isInterface) {
						val p = "config.$propName"
						// TODO: Generalize
						codeBlock = when (element.simpleName.toString()) {
							"FeedbackDeviceConfig" -> """when ($p) {
							|    is LocalFeedbackDeviceConfig -> talon.$talonConfig(($p as LocalFeedbackDeviceConfig).feedbackDevice, 0, 10)
							|    is RemoteFeedbackDeviceConfig -> talon.$talonConfig(($p as RemoteFeedbackDeviceConfig).remoteFeedbackDevice, 0, 10)
							|}
							|""".trimMargin()
							"LimitSwitchSourceConfig" -> """when ($p) {
							|    is LocalLimitSwitchSourceConfig -> talon.$talonConfig(($p as LocalLimitSwitchSourceConfig).type, ($p as LocalLimitSwitchSourceConfig).normalOpenOrClose, 10)
							|    is RemoteLimitSwitchSourceConfig -> talon.$talonConfig(($p as RemoteLimitSwitchSourceConfig).type, ($p as RemoteLimitSwitchSourceConfig).normalOpenOrClose, ($p as RemoteLimitSwitchSourceConfig).deviceID, 10)
							|}
							|""".trimMargin()
							else -> TODO()
						}
					} else {
//						element.accept()
//						element.getAnnotationsByType(ConfigParam::class.java)[0]
						val args = arrayListOf<String>()
						for (param in element.enclosedElements) {
							val annotation = param.getAnnotation(ConfigParam::class.java) ?: continue
//							processingEnv.messager.printMessage(NOTE, "config.$propName.${param.simpleName}")
							args.add("config.$propName.${param.simpleName}")
						}

						argStr = args.joinToString(", ")
					}
				} else {
					throw IllegalStateException("???")
				}

			} else if (ann.map) {
				// TODO: Diff individual map keys
				codeBlock = """for ((key, value) in config.$propName) {
    |    talon.$talonConfig(key, value${if (ann.timeout) ", 10" else ""})
    |}
""".trimMargin()
			} else {
				argStr = "config.$propName"
			}

			buildIfStatement(propName, codeBlock, talonConfig, argStr, ann)
		}

		for (el in executableElements) {
			val pidAnn = el.getAnnotation(PIDSlotConfig::class.java) ?: continue

			val slotPropName = el.simpleName.substring(3).decapitalize()
			val returnType = el.returnType as Type.ClassType
			val element = processingEnv.typeUtils.asElement(returnType)
			for (c in element.enclosedElements) {
				val ann = c.getAnnotation(Config::class.java) ?: continue

				val prefix = ann.prefix
				val propName = c.simpleName.substring(3).decapitalize()
				val configPropName = "$slotPropName.$propName"
				val talonConfig = if (prefix.isBlank()) propName else prefix + if (ann.camel) propName.capitalize() else propName

				buildIfStatement(configPropName, "", talonConfig, "${pidAnn.idx}, config.$configPropName", ann)
			}
		}
	}

	private fun FunSpec.Builder.buildIfStatement(propName: String, codeBlock: String, talonConfig: String, argStr: String, ann: Config) {
		// FIXME: Thread.sleep is a band-aid, please replace with proper retry logic!
		addStatement("""if ((prevConfig == null) || (prevConfig.$propName != config.$propName)) {
			|    ${if (codeBlock.isBlank()) "talon.$talonConfig($argStr${if (ann.pidIdx) ", 0" else ""}${if (ann.timeout) ", 10" else ""})" else codeBlock}
			|    Thread.sleep(10)
			|}
			|""".trimMargin())
	}

}
