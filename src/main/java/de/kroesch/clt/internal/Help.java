package de.kroesch.clt.internal;

import static de.kroesch.clt.Util.UTIL;

import java.io.PrintWriter;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.InternalEnvironment;
import de.kroesch.clt.Parser;
import de.kroesch.clt.UserHelp;

/**
 * Get help.
 * 
 * @author karsten
 *
 */
public class Help implements Command, HasArguments {

	private Environment env;

	private List<String> args;
	
	public void run() {
		Parser parser = ((InternalEnvironment) env).parser();
		PrintWriter writer = env.writer();
		
		// General help: list all commands
		if (args.size() == 0) {
			List<String> commands = parser.availableCommands();
			writer.print("Commands available:\n");
			UTIL.printColumns(commands, writer, 4);
			writer.printf("\n");
			return;
		}
		
		// Help for specified command if available
		Class<? extends Command> cmdClass = parser.byName(args.get(0));
		try {
			UserHelp uh = (UserHelp) cmdClass.newInstance();
			writer.print(uh.help());
			writer.flush();
		} catch (InstantiationException e) {
			writer.print("No help available");
		} catch (IllegalAccessException e) {
			writer.print("No help available");
		}
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public String toString() {
		return "help";
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
