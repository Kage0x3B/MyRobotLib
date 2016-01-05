package de.syscy.myrobotlib.robot;

import lombok.Getter;

public class RobotOutput {
	private @Getter Robot robot;

	private final @Getter int id;

	public RobotOutput(Robot robot, int id) {
		this.robot = robot;

		this.id = id;
	}
	
	public void set(boolean output) {
		robot.executeRobotCommand("AUSGANG " + id + " " + (output ? "an" : "aus"));
	}

	public void toggle() {
		robot.executeRobotCommand("AUSGANG " + id + " toggle");
	}
}