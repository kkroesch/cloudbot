package de.kroesch.clt.internal;

import java.util.Date;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

/**
 * Output the current date.
 * 
 * Makes sense in distributed environment.
 * 
 * @author karsten
 *
 */
public class Time implements Command {

	private Environment env;

	public void run() {
		env.writer().printf(new Date().toString());
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "version";
	}
}
