package de.kroesch.clt.internal;
import java.io.PrintWriter;

/**
 * Internal command to be executed if an error occurs.
 * 
 * @author karsten
 *
 */
public class Error implements Runnable, Internal {

	private String message;
	
	private PrintWriter writer;
	
	public Error(String message, PrintWriter writer) {
		this.message = message;
		this.writer = writer;
	}

	public void run() {
		writer.printf("Cannot execute: %s\n", message);
	}

	@Override
	public String toString() {
		return "error: " + message;
	}
}
