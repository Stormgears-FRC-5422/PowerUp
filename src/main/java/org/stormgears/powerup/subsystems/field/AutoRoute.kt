package org.stormgears.powerup.subsystems.field

interface AutoRoute {
	val pathToLeftScale: Array<Segment>
	val pathToRightScale: Array<Segment>
	val pathToLeftSwitch: Array<Segment>
	val pathToRightSwitch: Array<Segment>
	val pathToCrossLine: Array<Segment>
}
