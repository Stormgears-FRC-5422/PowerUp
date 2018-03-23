package org.stormgears.powerup.subsystems.field

interface AutoRoute {
	val pathToLeftScale: Array<Segment>
	val strafeToLeftScale: Array<Segment>
	val pathToRightScale: Array<Segment>
	val strafeToRightScale: Array<Segment>
	val pathToLeftSwitch: Array<Segment>
	val pathToRightSwitch: Array<Segment>
	val pathToCrossLine: Array<Segment>
}
