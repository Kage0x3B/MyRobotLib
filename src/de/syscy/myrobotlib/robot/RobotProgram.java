package de.syscy.myrobotlib.robot;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import de.syscy.myrobotlib.util.HTTPUtil;
import lombok.Getter;

public class RobotProgram {
	private static WebClient webClient = new WebClient();
	
	private @Getter Robot robot;

	private final @Getter int id;

	private @Getter String name = "Error";
	private @Getter String color = "Red";

	private @Getter int repeats = 1;
	private @Getter String program = "";
	
	private @Getter boolean saved = false;

	protected RobotProgram(Robot robot, int id) {
		this.robot = robot;
		
		this.id = id;
		
		load();
	}
	
	public void execute() {
		if(!saved) save();
		
		robot.executeRobotProgram(id);
	}

	public void load() {
		try {
			final HtmlPage page = webClient.getPage("http://192.168.4.1/edit?id=" + id);

			HtmlForm formElement = (HtmlForm) page.getBody().getElementsByTagName("form").get(0);

			String name = formElement.getInputByName("text").getValueAttribute();
			String color = formElement.getInputByName("color").getValueAttribute();
			int repeats = 1;

			try {
				repeats = Integer.parseInt(formElement.getInputByName("repeat").getValueAttribute());
			} catch (Exception ex) {
				repeats = 1;
			}

			String program = formElement.getTextAreaByName("content").getText();

			this.name = name == null || name.isEmpty() ? this.name : name;
			this.color = color == null || color.isEmpty() ? this.color : color;
			this.repeats = repeats;
			this.program = program == null || program.isEmpty() ? this.program : program;
			
			//If everything is not empty, everything is of course saved, we are loading it right here!!
			saved = !(name.isEmpty() || color.isEmpty() || program.isEmpty());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		webClient.closeAllWindows();
	}

	public void save() {
		if(saved) return;
		
		try {
			HTTPUtil.sendPOST("http://192.168.4.1/", "action=edit&id=" + id + "&text=" + name + "&color=" + color + "&repeat=" + repeats + "&content=" + program);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setName(String name) {
		this.name = name;
		this.saved = false;
	}
	
	public void setColor(String color) {
		this.color = color;
		this.saved = false;
	}
	
	public void setRepeats(int repeats) {
		this.repeats = repeats;
		this.saved = false;
	}
	
	public void setProgram(String program) {
		this.program = program;
		this.saved = false;
	}
}