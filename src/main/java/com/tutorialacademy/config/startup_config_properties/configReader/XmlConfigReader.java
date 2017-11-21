package com.tutorialacademy.config.startup_config_properties.configReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class XmlConfigReader implements ConfigReader {

	private String filePath = null;
	private Properties properties = null;
	
	/**
	 * Read and parse the XML file and store in class object
	 * @param filePath
	 */
	public XmlConfigReader( String filePath ) {
		this.filePath = filePath;
		
    	properties = new Properties();
		// open config file
    	InputStream in = null;
		try {
			in = new FileInputStream( filePath );
			properties.loadFromXML( in );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		finally {
    		if ( in != null ) {
    			try {
    				in.close();
    			} catch ( IOException e ) {
    				e.printStackTrace();
    			}
    		}
		}
	}
	
	/**
	 * Read from the in memory class object; no file access required
	 */
	public String read( String key ) {
		return properties.getProperty( key );
	}

	/**
	 * Write to the class object (changes available at runtime) and persist the data onto the file system
	 */
	public void write( String key, String value ) {
		properties.setProperty( key, value );
		
		// persist data into property file
		OutputStream output = null;

    	try {
    		output = new FileOutputStream( filePath );

    		// save properties to given path
    		properties.storeToXML( output, null );

    	} catch ( IOException ioe ) {
    		ioe.printStackTrace();
    	} finally {
    		if ( output != null ) {
    			try {
    				output.close();
    			} catch ( IOException e ) {
    				e.printStackTrace();
    			}
    		}

    	}
	}

}
