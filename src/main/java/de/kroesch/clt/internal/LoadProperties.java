package de.kroesch.clt.internal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.InternalEnvironment;

/**
 * Load properties and stored variables from config file.
 * 
 * @author karsten
 *
 */
public class LoadProperties implements Command, Internal {

	private InternalEnvironment env;
	
	public void run() {
		try {
			FileReader reader = new FileReader(new File(".console.properties"));
			env.getProperties().load(reader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void setEnvironment(Environment env) {
		this.env = (InternalEnvironment) env;
	}
	
	@Override
	public String toString() {
		return "loadp";
	}
}
