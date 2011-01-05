package de.kroesch.clt.scripting;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.InternalEnvironment;
import de.kroesch.clt.UserHelp;

/**
 * Reads and executes CLT (terminal batch) or JS (Javascript) files.
 * 
 * @author karsten
 *
 */
public class Load implements Command, HasArguments, UserHelp {

	private Environment environment;
	
	private List<String> args;
	
	public void run() {
		if (0 == args.size()) {
			environment.writer().printf("Usage: \n%s\n", help());
			return;
		}
		String file = args.get(0);
		
		if (file.endsWith(".js")) {
			evaluateJavascript(file);
			return;
		}

		if (! file.endsWith(".clt")) {
			environment.writer().printf("Unknown extension for file %s.\n", file);
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				Runnable command = ((InternalEnvironment)environment).parser().parseCommand(line);
				command.run();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setEnvironment(Environment env) {
		this.environment = env;
	}
	
	public String toString() {
		return "load " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
	
	public String help() {
		return "load FILE.[js|clt] - Reads and executes CLT (terminal batch) or JS (Javascript) files.";
	}
	
	private void evaluateJavascript(String file) {
		// JavaScript
		ScriptEngineManager engineMgr = new ScriptEngineManager();
	  ScriptEngine engine = engineMgr.getEngineByName("JavaScript");
	  try {
	  	InputStream is = new FileInputStream(file);
	    Reader reader = new InputStreamReader(is);
	    engine.eval(reader);
	  } catch (ScriptException ex) {
	  	environment.writer().print(ex);
	  	throw new RuntimeException(ex);
	  } catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
