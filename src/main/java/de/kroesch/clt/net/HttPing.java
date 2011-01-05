package de.kroesch.clt.net;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;

/**
 * Loads an URL to update status.
 * 
 * This is intended to give the botmaster a signal that this bot is up and running.
 * 
 */
public class HttPing implements Command, HasArguments {

	private Environment env;

	private List<String> args;
	
	public void run() {
		final URL url;
		try {
			url = new URL(args.get(0));
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL");
		}

		try {
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "FreXBot/0.8.1");
			conn.getContent();
		} catch (IOException e) {
			throw new RuntimeException("Transmission failure. Aborted.");
		}
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Override
	public String toString() {
		return "curl " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}
}
