package de.syscy.myrobotlib;

import java.util.logging.Logger;

import de.syscy.myrobotlib.robot.Robot;
import de.syscy.myrobotlib.util.FileUtil;
import de.syscy.myrobotlib.util.ScriptManager;
import lombok.Getter;

public class MyRobotLib {
	private static @Getter Logger logger;
	private static @Getter Robot robot;
	
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s %2$s %5$s%6$s%n");

		logger = Logger.getLogger("MyRobotLib");
		robot = new Robot();
	}
	
	public static void executeScript(String fileName) {
		String script = FileUtil.readFile(fileName);
		
		if(!script.isEmpty()) {
			logger.info("Executing script..");
			
			ScriptManager.getInstance().executeRobotScript(script, robot);
		}
	}
	
	public static void main(String[] args) {
		if(args.length > 0) {
			executeScript(args[0]);
			
			return;
		}
	}
}