import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

// WARNING: Do not read the below code!

// I mean it!
public class JNIHack {
	public static void hack() throws Exception {
		Class<?> clazz = Class.forName("edu.wpi.first.wpilibj.hal.JNIWrapper", false, JNIHack.class.getClassLoader());
//
		Field field = clazz.getDeclaredField("libraryLoaded");
		field.setAccessible(true);

//		Field modifiersField = Field.class.getDeclaredField("modifiers");
//		modifiersField.setAccessible(true);
//		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//
//		field.set(null, true);

		Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
		unsafeConstructor.setAccessible(true);
		Unsafe unsafe = unsafeConstructor.newInstance();
		unsafe.putBoolean(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), false);
	}
}
