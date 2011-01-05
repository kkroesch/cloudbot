package de.kroesch.clt.storage;
import static de.kroesch.clt.Util.UTIL;

import java.io.File;
import java.util.Arrays;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

/**
 * List local files.
 * 
 * @author karsten
 *
 */
public class Ls implements Command {

	private Environment env;
	
	public void run() {
		File currentDir = new File(".");
		
		String[] files = currentDir.list();
		UTIL.printColumns(Arrays.asList(files), env.writer(), 3);
		env.writer().print("\n");
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}
	
	@Override
	public String toString() {
		return "ls";
	}
}
