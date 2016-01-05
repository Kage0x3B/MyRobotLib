package de.syscy.myrobotlib.robot;

import de.syscy.myrobotlib.util.Color;
import lombok.Getter;

public class RobotCanvas {
	private @Getter Robot robot;

	private @Getter Color color = new Color();
	private @Getter int penSize = 10;

	protected RobotCanvas(Robot robot) {
		this.robot = robot;
	}

	public void setColor(int red, int green, int blue) {
		this.color.set(red, green, blue);

		robot.executeRobotCommand("ZEICHNE stift " + color.toString() + " " + penSize);
	}

	public void setPenSize(int penSize) {
		this.penSize = penSize;

		robot.executeRobotCommand("ZEICHNE stift " + color.toString() + " " + penSize);
	}
	
	public void setText(int x, int y, String text) {
		robot.executeRobotCommand("TEXT " + x + " " + y + " " + text);
	}

	public void clearText() {
		robot.executeRobotCommand("TEXT leer");
	}

	public void drawText(int x, int y, int fontSize, String text) {
		robot.executeRobotCommand("ZEICHNE text" + x + " " + y + " " + fontSize + " " + text);
	}

	public void fill(Color color) {
		robot.executeRobotCommand("ZEICHNE eimer " + color.toString());
	}

	public void fill(int red, int green, int blue) {
		robot.executeRobotCommand("ZEICHNE eimer " + red + " " + green + " " + blue);
	}

	public void rect(int x, int y, int width, int height) {
		robot.executeRobotCommand("ZEICHNE rechteck " + x + " " + y + " " + height + " " + width);
	}

	public void circle(int x, int y, int radius) {
		robot.executeRobotCommand("ZEICHNE kreis " + x + " " + y + " " + radius);
	}

	public void clear() {
		robot.executeRobotCommand("ZEICHNE leer");
	}
}