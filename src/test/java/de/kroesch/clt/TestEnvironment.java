package de.kroesch.clt;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Queue;

import de.kroesch.clt.InternalEnvironment;
import de.kroesch.clt.Parser;
import de.kroesch.clt.security.Authority;


public class TestEnvironment implements InternalEnvironment {

	private final PrintWriter writer;
	private final Properties properties = new Properties();
	
	
	
	public TestEnvironment() throws Exception {
		writer = new PrintWriter(new File("test.log"));
	}

	public String expect(String question, String choices) {
		return "y";
	}

	public Queue<Runnable> history() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public String lastError() {
		throw new UnsupportedOperationException("Not implemented");
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

	public Properties getProperties() {
		return properties;
	}

	public Parser parser() {
		throw new UnsupportedOperationException("Not implemented");
	}

	public void setLastErrorMessage(String message) {
		throw new UnsupportedOperationException("Not implemented");
	}

	public Authority authority() {
		// TODO Auto-generated method stub
		return null;
	}
}
