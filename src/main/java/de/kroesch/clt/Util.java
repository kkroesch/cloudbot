package de.kroesch.clt;

import java.io.PrintWriter;
import java.util.List;

/**
 * Utility singleton.
 * 
 * @author karsten
 *
 */
public class Util {
	
	public static final Util UTIL = new Util();

	public void printColumns(List<String> things, PrintWriter writer, int cols) {
		int col = 0;
		for (String thing : things) {
			col ++;
			char delim = (col % cols == 0) ? '\n' : '\t';
			writer.print(thing);
			writer.print(delim);
		}
	}

}
