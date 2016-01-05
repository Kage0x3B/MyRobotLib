package de.syscy.myrobotlib.robot;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import de.syscy.myrobotlib.util.HTTPUtil;
import lombok.Getter;

public class Robot {
	public static final int TEMP_PROGRAM_ID = 1337;
	public static final int START_ID = 100;

	private AtomicInteger idCounter = new AtomicInteger(START_ID);

	private @Getter RobotProgram tempProgram;
	private @Getter HashMap<String, RobotProgram> standardPrograms = new HashMap<String, RobotProgram>();

	private @Getter RobotCanvas canvas;

	public Robot() {
		tempProgram = new RobotProgram(this, TEMP_PROGRAM_ID);
		tempProgram.setName("TEMP");
		tempProgram.setColor("Red");
		tempProgram.setRepeats(1);
		tempProgram.save();

		loadStandardPrograms();

		canvas = new RobotCanvas(this);
	}

	public void forward() {
		stop();
		standardPrograms.get("forward").execute();
	}

	public void backward() {
		stop();
		standardPrograms.get("backward").execute();
	}

	public void left() {
		stop();
		standardPrograms.get("left").execute();
	}

	public void right() {
		stop();
		standardPrograms.get("right").execute();
	}

	public void stop() {
		standardPrograms.get("stop").execute();
	}

	public void beep() {
		standardPrograms.get("beep").execute();
	}

	public void beep(long millis) {
		executeRobotCommand("BEEP " + millis);
	}
	
	public RobotOutput getOutput(int id) {
		return new RobotOutput(this, id);
	}
	
	public RobotServo getServo(int id) {
		return new RobotServo(this, id);
	}

	public RobotLED getLED(int id) {
		return new RobotLED(this, id);
	}

	public RobotProgram createProgram() {
		return createProgram(idCounter.getAndIncrement());
	}

	public RobotProgram createProgram(String name, String color, int repeats, String program) {
		return createProgram(idCounter.getAndIncrement(), name, color, repeats, program);
	}

	private RobotProgram createProgram(int id, String name, String color, int repeats, String program) {
		RobotProgram robotProgram = createProgram(id);
		robotProgram.setName(name);
		robotProgram.setColor(color);
		robotProgram.setRepeats(repeats);
		robotProgram.setProgram(program);

		return robotProgram;
	}

	private RobotProgram createProgram(int id) {
		return new RobotProgram(this, id);
	}

	public RobotProgram getProgram(int id) {
		return new RobotProgram(this, id);
	}

	/**
	 * Executes a MyRobot Program
	 * 
	 * @param id
	 */
	public void executeRobotProgram(int id) {
		try {
			HTTPUtil.sendGET("http://192.168.4.1/ajax?id=" + id + "&rand=" + Math.random());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void executeRobotCommand(String command) {
		tempProgram.setProgram(command);
		tempProgram.save();
		tempProgram.execute();
	}

	private void loadStandardPrograms() {
		int id = 80;

		standardPrograms.put("forward", createProgram(id++, "forward", "Blue", 1, "Servo 1 180 1"));
		standardPrograms.put("backward", createProgram(id++, "backward", "Blue", 1, "Servo 1 1 180"));
		standardPrograms.put("left", createProgram(id++, "left", "Blue", 1, "Servo 1 1 1"));
		standardPrograms.put("right", createProgram(id++, "right", "Blue", 1, "Servo 1 180 180"));
		standardPrograms.put("stop", createProgram(id++, "stop", "Blue", 1, "Servo 1 0 0"));
		standardPrograms.put("beep", createProgram(id++, "beep", "Blue", 1, "Beep"));
	}
	
	public void prepareStandardPrograms() {
		for(RobotProgram program : standardPrograms.values()) {
			program.save();
		}
	}
}