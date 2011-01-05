package de.kroesch.clt.security;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.InternalEnvironment;

/**
 * Simple login mechanism. Only provide password.
 * Login decides which role.
 * 
 */
public class Login implements Command, HasArguments {

	private List<String> args;
	private Environment env;
	
	public void run() {
		String password = args.get(0);
		if ("enigma".equals(password)) {
			env.writer().printf("OK - Authenticated.\n");
			((InternalEnvironment) env).authority().authenticate("root");
		} else {
			env.writer().printf("?Incorrect credentials. Incident will be reported.\n");
		}
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	public String toString() {
		return "login";
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
