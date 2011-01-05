package de.kroesch.clt.net;

import static de.kroesch.clt.security.Role.ROOT;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;
import de.kroesch.clt.HasArguments;
import de.kroesch.clt.security.AuthCommand;
import de.kroesch.clt.security.Role;

/**
 * Load file from given URL.
 * 
 */
public class Curl implements Command, AuthCommand, HasArguments {

	private Environment env;

	private List<String> args;

	private final int EOF = -1;

	public void run() {
		final URL url;
		try {
			url = new URL(args.get(0));
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL");
		}
		
		// Wait a certain amount of time in Bot environment
		// to avoid load peaks.
		int spraytime = new Random().nextInt(60000);
		env.writer().printf("Waiting %d seconds befor starting download.", spraytime/60);
		env.writer().flush();
		try {
			Thread.sleep(spraytime);
		} catch (InterruptedException e) {}

		try {
			URLConnection conn = url.openConnection();
			conn.connect();
			
			// Where to save the result
			final String file;
			if (args.size() > 1) {
				file = args.get(1);
			} else {
				file = url.getFile();
			}

			InputStream inStream = conn.getInputStream();
			OutputStream outStream = new FileOutputStream(file);
			
			copy(inStream, outStream);

		} catch (IOException e) {
			throw new RuntimeException("Transmission failure. Aborted.");
		}
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public String toString() {
		return "curl " + args;
	}

	public void setArguments(List<String> args) {
		this.args = args;
	}

	public boolean asRole(Role role) {
		return ROOT.equals(role);
	}
	
	private void copy(InputStream inStream, OutputStream outStream) throws IOException {
		byte[] buffer = new byte[0xFFFF];
		for (int len; (len = inStream.read(buffer)) != -1;) {
			outStream.write(buffer, 0, len);
		}
		
		outStream.close();
	}
}
