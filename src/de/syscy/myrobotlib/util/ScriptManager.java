package de.syscy.myrobotlib.util;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import de.syscy.myrobotlib.robot.Robot;
import lombok.Getter;

public class ScriptManager {
	private static @Getter ScriptManager instance = new ScriptManager();

    private ScriptEngineManager scriptEngineManager;
    private ScriptEngine scriptEngine;

    public ScriptManager() {
        this.scriptEngineManager = new ScriptEngineManager();
        this.scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    }

    public boolean executeScript(String script, Bindings bindings) {
        try {
            scriptEngine.eval(script, bindings);

            return true;
        } catch (ScriptException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean executeRobotScript(String script, Robot robot) {
        Bindings bindings = new SimpleBindings();
        bindings.put("robot", robot);
        bindings.put("canvas", robot.getCanvas());

        try {
            scriptEngine.eval(script, bindings);

            return true;
        } catch (ScriptException ex) {
            ex.printStackTrace();

            return false;
        }
    }
}