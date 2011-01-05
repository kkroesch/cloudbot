package de.kroesch.clt.internal;

import static de.kroesch.clt.security.Role.ROOT;
import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.security.AuthCommand;
import de.kroesch.clt.security.Role;

/**
 * Exit from the program.
 */
public class Shutdown implements Command, AuthCommand {

	private Environment env;

	public void run() {
		System.exit(0);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "shutdown";
	}

	public boolean asRole(Role role) {
		return ROOT.equals(role);
	}
}
