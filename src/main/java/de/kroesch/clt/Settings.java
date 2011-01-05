package de.kroesch.clt;

/**
 * Program settings.
 * 
 * @author karsten
 *
 */
public enum Settings {
	
	PROMPT     ("console.prompt"),
	FORCE_QUIT ("console.force_quit"),
	DAEMON_PORT ("terminal.daemon.port")
	;
	
	private String configKey;

	private Settings(String configKey) {
		this.configKey = configKey;
	}
	public String key() {
		return configKey;
	}
}
