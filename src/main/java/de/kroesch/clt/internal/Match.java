package de.kroesch.clt.internal;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;

/**
 * «Grep'-like command to match a value against a regular expression.
 * 
 * Variables have to be extended by the Parser implementation.
 * 
 * @author karsten
 *
 */
public class Match implements Command, HasArguments {

	private Environment env;
	
	private List<String> args;
	
	public void run() {
		if (args.size() < 2) return;
		String toMatch = args.get(0);
		String regex = args.get(1);
		boolean matches = toMatch.matches(regex);
		env.writer().printf("%s\n", matches);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	@Override
	public String toString() {
		return "match " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
