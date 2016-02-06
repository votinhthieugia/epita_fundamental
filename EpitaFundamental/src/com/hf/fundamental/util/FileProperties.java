/**
 * FileProperties.java
 */
package com.hf.fundamental.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code FilePropertiesClass} manages the <b>configuration file</b> of the project
 * @author Favio
 *
 */
public class FileProperties {

	// To read the document.
	InputStream inputStream;

	/**
	 * The {@code getPropValues} method reads the configuration file and through a {@link InputStream},
	 * loads the content into a {@link Properties} object
	 * @return properties
	 */
	public Properties getPropValues() {		 
		Properties prop = new Properties();
		String propFileName = "config.properties";
		try{
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			}			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return prop;
	}
}
