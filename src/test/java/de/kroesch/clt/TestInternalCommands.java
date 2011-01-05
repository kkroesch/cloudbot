package de.kroesch.clt;

import org.junit.Before;
import org.junit.Test;

import de.kroesch.clt.internal.Get;

public class TestInternalCommands {

	private InternalEnvironment env;
	
	@Before
	public void init() throws Exception {
		env = new TestEnvironment();
		env.writer().printf("init\n");
		env.set("so", "4500");
		env.set("be", "3000");
		env.writer().flush();
	}
	
	@Test
	public void testGet() throws Exception {
		Get get = new Get();
		get.setEnvironment(env);
		get.run();
		env.writer().flush();
	}
}
