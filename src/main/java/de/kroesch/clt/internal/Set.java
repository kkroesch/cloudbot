package de.kroesch.clt.internal;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;

/**
 * Store a local variable into environment.
 * 
 * @author karsten
 *
 */
public class Set implements Command, HasArguments {

	private Environment env;
	
	private List<String> args;
	
	public void run() {
		String key = args.get(0);
		String value = args.get(1);
		env.writer().printf("%s: %s\n", key, value);
		env.set(key, value);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	@Override
	public String toString() {
		return "set " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
