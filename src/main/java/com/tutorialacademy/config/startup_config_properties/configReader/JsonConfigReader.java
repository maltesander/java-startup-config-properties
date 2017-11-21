package com.tutorialacademy.config.startup_config_properties.configReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonConfigReader implements ConfigReader {

	private String filePath = null;
	private JSONObject jsonObject = null;
	
	/**
	 * Read and parse the JSON file and store in class object
	 * @param filePath
	 */
	public JsonConfigReader( String filePath ) {
		this.filePath = filePath;
		
		JSONParser parser = new JSONParser();

		// parse and keep the JSON object for fast read access
        try {
            jsonObject = (JSONObject) parser.parse( new FileReader( filePath ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Read from the in memory class object; no file access required
	 */
	public String read( String key ) {
		return (String) jsonObject.get( key );
	}

	/**
	 * Write to the class object (changes available at runtime) and persist the data onto the file system
	 */
	@SuppressWarnings("unchecked")
	public void write( String key, String value ) {
		jsonObject.put( key, value );

		try( FileWriter file = new FileWriter( filePath ) ) {
			file.write( jsonObject.toJSONString() );
			file.flush();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

}
