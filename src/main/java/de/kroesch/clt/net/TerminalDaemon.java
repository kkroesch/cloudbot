package de.kroesch.clt.net;

import static de.kroesch.clt.Settings.PROMPT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;

import javax.management.relation.Role;

import de.kroesch.clt.InternalEnvironment;
import de.kroesch.clt.Parser;
import de.kroesch.clt.internal.Internal;
import de.kroesch.clt.security.AuthCommand;
import de.kroesch.clt.security.Authority;

/**
 * Attaches to a dedicated port and serves Telnet-like interface.
 * 
 * This implementation is single-threaded, because only one master
 * should have access to the bot.
 * 
 * @author Karsten Kroesch <karsten@kroesch.de>
 *
 */
public class TerminalDaemon implements InternalEnvironment {

	private static final int DEFAULT_PORT = 12880;
	
	private static final int TIMEOUT = 10000;

	private final ServerSocket server;
	
	private final Properties properties;
		
	private final Parser parser;
	
	private final Queue<Runnable> commandStack = new ArrayDeque<Runnable>();
	
	private String lastErrorMessage = "OK";
	
	private PrintWriter writer;
	
	private BufferedReader reader;
	
	private Authority authority = new Authority();

	public TerminalDaemon(int port) throws IOException {
		parser = new Parser(this);
		properties = new Properties();
		// TODO: Read properties
		set(PROMPT.key(), "");
		server = new ServerSocket(port);
	}
	
	public TerminalDaemon() throws IOException {
		this(DEFAULT_PORT);
	}

	/*
	 * Create Socket and read commands
	 */
	private void read() throws IOException {
		final Socket socket = server.accept();
		socket.setSoTimeout(TIMEOUT);
		
		writer = new PrintWriter(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer.printf("FreXBot V%s -- The friendly, extensible Bot\n", VERSION);
		writer.printf("Timeout is %d milliseconds.\n", TIMEOUT);
		writer.printf("\n%s> ", get(PROMPT.key()));
		writer.flush();

		String commandLine = "";
		for (;;) {
			boolean timeout = false;
			try {
				commandLine = reader.readLine();
			} catch (SocketTimeoutException e) {
				timeout = true;
			}
			if ("exit".equals(commandLine) || timeout) {
				writer.write("Goodbye\n");
				writer.flush();
				socket.close();
				break;
			}
			Runnable consoleCommand = parser.parseCommand(commandLine);

			if (! (consoleCommand instanceof Internal)) commandStack.add(consoleCommand);
			try {
// TODO:
//				if (consoleCommand instanceof AuthCommand) {
//					AuthCommand cmd = (AuthCommand) consoleCommand;
//					if (! authority.authorize(cmd))
//						throw new SecurityException("Not authorized.");
//				}
				consoleCommand.run();
				lastErrorMessage = "OK";
			} catch(Exception ex) {
				ex.printStackTrace();
				writer.printf("?Error during execution: %s\n", ex.getMessage());
				lastErrorMessage = ex.getMessage();
			}
			writer.printf("\n%s> ", get(PROMPT.key()));
			writer.flush();
		}
	}
	
	public PrintWriter writer() {
		return writer;
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
		writer.printf("%s [%s]: ", question, choices);
		writer.flush();
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Cannot read from socket");
		}
	}
	
	public Authority authority() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		TerminalDaemon daemon = new TerminalDaemon();
		for (;;) {
				daemon.read();
		}
	}
}
