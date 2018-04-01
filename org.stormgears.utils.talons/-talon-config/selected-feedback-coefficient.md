[PowerUp](../../index.md) / [org.stormgears.utils.talons](../index.md) / [TalonConfig](index.md) / [selectedFeedbackCoefficient](./selected-feedback-coefficient.md)

# selectedFeedbackCoefficient

`abstract val selectedFeedbackCoefficient: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)

The Feedback Coefficient is a scalar applied to the value of the feedback sensor. Useful when you need to scale
your sensor values within the closed-loop calculations. Default value is 1.

Selected Feedback Sensor register in firmware is the decoded sensor value multiplied by the Feedback Coefficient.

**See Also**

[TalonSRX.configSelectedFeedbackCoefficient](#)

