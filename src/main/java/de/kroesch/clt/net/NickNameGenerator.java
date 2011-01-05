package de.kroesch.clt.net;

import java.util.Random;

/**
 * Generate a random name to address the bot.
 * 
 * @author karsten
 *
 */
public class NickNameGenerator {
	private static final int NUM_CHARS = 9;
	private static String chars = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rnd = new Random();

	public static String generate() {
		char[] buf = new char[NUM_CHARS];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = chars.charAt(rnd.nextInt(chars.length()));
		}
		return new String(buf);
	}
}
