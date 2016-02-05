package com.hf.fundamental.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class FileProperties {

	InputStream inputStream;

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
