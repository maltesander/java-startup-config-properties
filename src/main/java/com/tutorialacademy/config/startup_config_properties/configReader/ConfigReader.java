package com.tutorialacademy.config.startup_config_properties.configReader;

public interface ConfigReader {
	public String read( String key );
	public void write( String key, String value );
}
