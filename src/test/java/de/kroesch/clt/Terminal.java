package de.kroesch.clt;

import static de.kroesch.clt.Settings.PROMPT;
import java.io.Console;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;

import de.kroesch.clt.internal.Internal;
import de.kroesch.clt.security.AuthCommand;
import de.kroesch.clt.security.Authority;


public class Terminal implements InternalEnvironment {
	
	private final Properties properties;
	
	private final Console console;
	
	private final Parser parser;
	
	private String lastErrorMessage = "OK";
	
	private Queue<Runnable> commandStack = new ArrayDeque<Runnable>();
	
	private Authority authority = new Authority();
	
	public Terminal() {
		console = System.console();
		if (console == null) throw new RuntimeException("Must be started from command line.");
		
		parser = new Parser(this);
		properties = new Properties();
		set(PROMPT.key(), "");
	}
	
	private void read() {
		String commandLine = "";
		console.printf("\n%s> ", get(PROMPT.key()));
		while (true) {
			commandLine = console.readLine();
			
			Runnable consoleCommand = parser.parseCommand(commandLine);
			
			if (! (consoleCommand instanceof Internal)) commandStack.add(consoleCommand);
			try {
//				if (consoleCommand instanceof AuthCommand) {
//					AuthCommand cmd = (AuthCommand) consoleCommand;
//					if (! authority.authorize(cmd))
//						throw new SecurityException("Not authorized.");
//				}
				consoleCommand.run();
				lastErrorMessage = "OK";
			} catch(Exception ex) {
				console.printf("Error during execution: %s\n", ex.getMessage());
				lastErrorMessage = ex.getMessage();
			}
			console.printf("\n%s> ", get(PROMPT.key()));
		}
	}

	public PrintWriter writer() {
		return console.writer();
	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public void set(String key, String value) {
		properties.setProperty(key, value);
	}

	public Queue<Runnable> history() {
		return commandStack;
	}

	public String lastError() {
		return lastErrorMessage;
	}

	public void setLastErrorMessage(String message) {
		this.lastErrorMessage = message;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public Parser parser() {
		return parser;
	}

	public String expect(String question, String choices) {
		console.printf("%s [%s]: ", question, choices);
		return console.readLine();
	}
	
	public static void main(String[] args) {
		new Terminal().read();
	}

	public Authority authority() {
		return authority;
	}
}