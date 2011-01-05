package de.kroesch.clt.security;

public class Authority {

	private Role loggedIn;
	
	public void authenticate(String user) {
		loggedIn = Role.forUser(user);
	}
	
	public boolean authorize(AuthCommand command) {
		return command.asRole(loggedIn);
	}
}
