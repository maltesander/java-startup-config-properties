package com.tutorialacademy.config.startup_config_properties;

import com.tutorialacademy.config.startup_config_properties.configReader.ConfigReader;
import com.tutorialacademy.config.startup_config_properties.configReader.JsonConfigReader;
import com.tutorialacademy.config.startup_config_properties.configReader.PropertiesConfigReader;
import com.tutorialacademy.config.startup_config_properties.configReader.XmlConfigReader;

public class ConfigReaderRun 
{
	// public static to be accessible anywhere in our code
	// make sure to initialize the config reader before you work with it (e.g. the main entry point, some initialization class) in the code
	// if all data is stored in one config file check for singelton pattern
	// --> this way you can reuse one instance and only read the config file once
	// if one config reader should read multiple config files, keep the existing pattern to initialize more than one instance 
	public static ConfigReader propertyConfigReader = null;
	public static ConfigReader xmlConfigReader 		= null;
	public static ConfigReader jsonConfigReader		= null;

	public static void main( String[] args )
    {
		String path = null;

    	/************************************************************************************************/
    	/* Properties */
    	/************************************************************************************************/
		path = getRelativeResourcePath( "config.properties" );
		
		ConfigReaderRun.propertyConfigReader = new PropertiesConfigReader( path );
    	
    	System.out.println("PropertyConfigReader:");
    	testConfigReader( ConfigReaderRun.propertyConfigReader );
    	System.out.println();
    	
    	/************************************************************************************************/
    	/* XML */
    	/************************************************************************************************/
    	path = getRelativeResourcePath( "config.xml" );
    	
    	ConfigReaderRun.xmlConfigReader = new XmlConfigReader( path );
    	
    	System.out.println("XmlConfigReader:");
    	testConfigReader( ConfigReaderRun.xmlConfigReader );
    	System.out.println();
    	
    	/************************************************************************************************/
    	/* JSON */
    	/************************************************************************************************/
    	path = getRelativeResourcePath( "config.json" );
    	
    	ConfigReaderRun.jsonConfigReader = new JsonConfigReader( path );
    	
    	System.out.println("JsonConfigReader:");
    	testConfigReader( ConfigReaderRun.jsonConfigReader );
    	System.out.println();
    }
	
	// resolve maven specific path for resources
	private static String getRelativeResourcePath( String resource ) {
		if( resource == null || resource.equals("") ) return null;
		
		return ConfigReaderRun.class.getClassLoader().getResource( resource ).getPath();
	}
    
    private static void testConfigReader( ConfigReader reader ) {
    	System.out.println( reader.read( "user" ) );
    	reader.write( "user", "newUser" );
    	System.out.println( reader.read( "user" ) );
    }

}
