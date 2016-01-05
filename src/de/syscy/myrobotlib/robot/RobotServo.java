package de.syscy.myrobotlib.robot;

import lombok.Getter;

public class RobotServo {
	private @Getter Robot robot;

	private final @Getter int id;

	public RobotServo(Robot robot, int id) {
		this.robot = robot;

		this.id = id;
	}

	public void setSpeed(int speed1, int speed2) {
		robot.executeRobotCommand("SERVO " + id + " " + speed1 + " " + speed2);
	}
}