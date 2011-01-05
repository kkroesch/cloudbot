package de.kroesch.clt;
import java.io.PrintWriter;
import java.util.Queue;

/**
 * Interface for hosting commands.
 * 
 * This is the drop-in interface for commands to communicate with the runtime.
 * 
 * @author karsten
 *
 */
public interface Environment {
	
	/**
	 * Version string as represented in the application.
	 */
	final String VERSION = "1.0.11";

	/**
	 * Standard YES/NO prompt text.
	 */
	static String YESNO = "Y,n";

	/**
	 * Output channel.
	 * 
	 * @return The default output channel for command output.
	 */
	PrintWriter writer();
	
	/**
	 * Store variables.
	 * 
	 * @param key
	 * @param value
	 */
	void set(String key, String value);
	
	/**
	 * Get variables.
	 * 
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * @return When there was an error, it can be obtained from environment.
	 */
	String lastError();
	
	/**
	 * Command history.
	 */
	Queue<Runnable> history();
	
	/**
	 * Let a command retrieve an input from user.
	 * 
	 * @param question
	 * @param choices
	 * @return User's input.
	 */
	String expect(String question, String choices);
	
}
