package de.syscy.myrobotlib.robot;

import lombok.Getter;

public class RobotLED {
	private @Getter Robot robot;

	private final @Getter int id;

	public RobotLED(Robot robot, int id) {
		this.robot = robot;

		this.id = id;
	}

	public void setColor(int r, int g, int b) {
		robot.executeRobotCommand("LED " + id + " " + r + " " + g + " " + b);
	}
	
	public void turnOff() {
		setColor(0, 0, 0);
	}
}