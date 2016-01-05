MyRobotLib is a Java library (and Im planning a gui for controlling the robot) for controlling the "MyRobot V7.0" with Java or Javascript code.

Java example:

Robot robot = MyRobotLib.getRobot();
robot.forward();
		
try {
	Thread.sleep(1000);
} catch (InterruptedException ex) {
	ex.printStackTrace();
}

robot.stop();

And you can do the same thing in Javascript, look at test.js.
When you are done writing the Javascript file, execute it with MyRobotLib.executeScript(String fileName)
or with a compiled version of the library, which has a main method which executes the first argument.
In a Javascript script, the variables robot and canvas are already defined so you can use "robot.forward();" for example.
