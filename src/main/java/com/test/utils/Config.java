/*
 * 
 */
package com.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class Config.
 */
public class Config {
	public static Properties prop = new Properties();
	public static String propFilePath =System.getProperty("user.dir")+"/src/main/resources/Config.properties";
	/**
	 * Gets the config property. This method return the value of the specified key from the properties file
	 * from the Properties file
	 * 
	 * @param key the key
	 * @return the config property
	 */
	public static String getConfigProperty(String key) {
		FileInputStream input = null;
		try {
			input = new FileInputStream(propFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = prop.getProperty(key);
		return value;
	}

	/**
	 * Sets the config property. This method set the value of the specified key to
	 * the Properties file
	 * 
	 * @param key   the key
	 * @param value the value
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void setConfigProperty(String key, String value) throws IOException {
		FileOutputStream output = new FileOutputStream(propFilePath);
		prop.setProperty(key, value);
		prop.store(output, null);
	}

}
