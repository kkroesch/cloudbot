package de.kroesch.clt.internal;
import java.io.PrintWriter;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

/**
 * Prints out the command line history.
 * 
 * @author karsten
 *
 */
public class History implements Command {

	private Environment env;
	
	public void run() {
		PrintWriter writer = env.writer();
		
		for (Runnable command : env.history()) {
			writer.printf("%s\n", command.toString());
		}
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	@Override
	public String toString() {
		return "history";
	}
}