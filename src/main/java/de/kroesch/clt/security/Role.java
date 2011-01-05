package de.kroesch.clt.security;

public enum Role {
	NOBODY, 
	AUTHENTICATED, 
	ROOT
	;
	
	/**
	 * Returns the role for the user. Simplest implementation.
	 */
	public static Role forUser(String username) {
		return Role.valueOf(username.toUpperCase());
	}
}
