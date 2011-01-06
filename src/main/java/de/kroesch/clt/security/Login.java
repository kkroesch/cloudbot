package de.kroesch.clt.security;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(".console.properties"));
		} catch (FileNotFoundException e) {
			env.writer().printf("Password was not set. Cannot authenticate.\n");
		} catch (IOException e) {
			env.writer().printf("Password was not set. Cannot authenticate.\n");
		}
		String correctPassword = prop.getProperty("auth.password");
		if (null == correctPassword) { 
			env.writer().printf("Password was not set. Cannot authenticate.\n");
			return;
		}
		
		String password = args.get(0);
		if (correctPassword.equals(password)) {
			env.writer().printf("OK - Authenticated.\n");
			((InternalEnvironment) env).authority().authenticate("root");
		} else {
			env.writer().printf("?Incorrect credentials.\n");
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
