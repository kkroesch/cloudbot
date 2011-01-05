package de.kroesch.clt;

import java.util.List;

/**
 * This interface must be implemented by commands that have arguments.
 * 
 * @author karsten
 *
 */
public interface HasArguments {

	void setArguments(List<String> args);
}
