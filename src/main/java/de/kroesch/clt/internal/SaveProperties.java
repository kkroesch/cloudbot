package de.kroesch.clt.internal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.InternalEnvironment;

/**
 * Store properties and variables to config file.
 * 
 * @author karsten
 *
 */
public class SaveProperties implements Command, Internal {

	private InternalEnvironment env;
	
	public void run() {
		try {
			FileWriter writer = new FileWriter(new File(".console.properties"));
			env.getProperties().store(writer, "");
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
		return "savep";
	}
}
