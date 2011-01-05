package com.oem.cltplugin;

import de.kroesch.clt.Command;
import de.kroesch.clt.Environment;

public class SamplePlugin implements Command {

	private Environment env;
	
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public void run() {
		env.writer().printf("Hi, I'm a sample plugin!\n");
	}

	@Override
	public String toString() {
		return "sample";
	}
}