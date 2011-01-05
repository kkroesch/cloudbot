package de.kroesch.clt.internal;
import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

/**
 * Test command that throws an exeception. Intended to test exception handling.
 * 
 * @author karsten
 *
 */
public class Crash implements Command, Internal {

	public void run() {
		throw new RuntimeException("TestException");
	}

	public void setEnvironment(Environment env) {
	}
	
	@Override
	public String toString() {
		return "crash";
	}
}
