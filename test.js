var next_red = Math.floor(Math.random() * 25);
var next_green = Math.floor(Math.random() * 25);
var next_blue = Math.floor(Math.random() * 25);

var red = next_red;
var green = next_green;
var blue = next_blue;

var out = java.lang.System.out;

while(true) {
	if(red < next_red) red += 1;
	if(red > next_red) red -= 1;
	
	if(green < next_green) green += 1;
	if(green > next_green) green -= 1;

	if(blue < next_blue) blue += 1;
	if(blue > next_blue) blue -= 1;
	
	if(red == next_red && green == next_green && blue == next_blue) {
		next_red = Math.floor(Math.random() * 25);
		next_green = Math.floor(Math.random() * 25);
		next_blue = Math.floor(Math.random() * 25);
		
		if(Math.random() > 0.5) next_red = Math.round(Math.random()) * 25;
		if(Math.random() > 0.5) next_green = Math.round(Math.random()) * 25;
		if(Math.random() > 0.5) next_blue = Math.round(Math.random()) * 25;
	}
	
	var final_red = red * 10;
	var final_green = green * 10;
	var final_blue = blue * 10;
	
	robot.executeRobotCommand("LED 0 " + final_red + " " + final_green + " " + final_blue + " " + final_red + " " + final_green + " " + final_blue);
}