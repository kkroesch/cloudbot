package de.kroesch.clt.internal;
import java.util.List;
import java.util.Properties;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.InternalEnvironment;

/**
 * Get the value of a variable.
 * 
 * @author karsten
 *
 */
public class Get implements Command, HasArguments {

	private Environment env;
	
	private List<String> args;
	
	public void run() {
		if (! args.isEmpty()) {
			write(args.get(0)); 
			return;
		}

		// List variables
		Properties props = ((InternalEnvironment)env).getProperties();
		for (Object var : props.keySet()) {
			write(var.toString());
		}
	}
	
	private void write(String key) {
		String value = env.get(key);
		env.writer().printf("%s: %s\n", key, value);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	public String toString() {
		return "get " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}