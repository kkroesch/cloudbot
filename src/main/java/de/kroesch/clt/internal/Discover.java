package de.kroesch.clt.internal;
import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.InternalEnvironment;
import de.kroesch.clt.security.AuthCommand;
import de.kroesch.clt.security.Role;

/**
 * Find plugins from plugin directory.
 * 
 * @author karsten
 *
 */
public class Discover implements Command, AuthCommand {

	private Environment env;
	
	public void run() {
		((InternalEnvironment)env).parser().discoverPlugins();
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	public String toString() {
		return "discover";
	}

	public boolean asRole(Role role) {
		return Role.ROOT.equals(role);
	}

}
