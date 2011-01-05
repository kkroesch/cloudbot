package de.kroesch.clt.internal;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.InternalEnvironment;

/**
 * Repeats a command periodically.
 * 
 * TODO: Make the timer interval configurable.
 *
 */
public class Repeat implements Command, HasArguments {
	
	private List<String> args;
	private Environment env;

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public void run() {
		if (args.size() < 1) {
			env.writer().print("what?");
			return;
		}
		final Runnable command = ((InternalEnvironment) env).parser().parseCommand(args.get(0));
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				command.run();
			}
		};
		
		int delay = 1000;
		
		new Timer(true).schedule(task, delay);
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
