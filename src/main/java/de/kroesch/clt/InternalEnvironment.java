package de.kroesch.clt;

import java.util.Properties;

import de.kroesch.clt.security.Authority;

public interface InternalEnvironment extends Environment {
	
	Properties getProperties();
	
	void setLastErrorMessage(String message);

	Parser parser();
	
	Authority authority();
}
