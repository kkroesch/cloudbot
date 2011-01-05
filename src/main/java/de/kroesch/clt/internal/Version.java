package de.kroesch.clt.internal;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

/**
 * Give the version string of the application.
 * 
 * @author karsten
 *
 */
public class Version implements Command {

	private Environment env;

	public void run() {
		env.writer().print(Environment.VERSION);
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "version";
	}
}
