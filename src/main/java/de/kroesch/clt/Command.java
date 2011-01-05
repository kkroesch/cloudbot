package de.kroesch.clt;

/**
 * Interface for all commands.
 * 
 * @author karsten
 *
 */
public interface Command extends Runnable {

	void setEnvironment(Environment env);
}
