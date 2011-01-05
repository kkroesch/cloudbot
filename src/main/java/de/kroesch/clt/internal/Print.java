package de.kroesch.clt.internal;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;

/**
 * Print an output.
 * 
 * Variables have to be extended by the Parser implementation.
 * 
 * @author karsten
 *
 */
public class Print implements Command, HasArguments {

	private Environment env;
	
	private List<String> args;
	
	public void run() {
		String output = args.get(0);
		if (null == output) output = "\n";
		env.writer().printf("%s\n", output);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	@Override
	public String toString() {
		return "print " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
