/**
 * Configuration.java
 */

package com.hf.fundamental.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class DataConfiguration {
	private static String adminName = "admin";
	private static String adminPassword = "password";
	private static String dbAdapter = "derby";
	private static String dbUsername = "root";
	private static String dbPassword = "";
	private static String dbName = "identities";
	
	@SuppressWarnings("unchecked")
	public static void load() throws IOException {
		Yaml yaml = new Yaml();
		
//		Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml
//				.load(new FileInputStream(new File("/Users/hoanganhdoan/Documents/workspace/epita_fundamental/EpitaFundamental/resources/data_config.yml")));

		
		File file = new File("data_config.yml");
		InputStream inputStream = new FileInputStream(file);
		Map<String, Map<String, String>> values = (Map<String, Map<String, String>>) yaml.load(inputStream);
		
		for (String key : values.keySet()) {
			Map<String, String> subValues = values.get(key);

			if (key.equals("admin")) {
				for (String subValueKey : subValues.keySet()) {
					switch (subValueKey) {
					case "name":
						adminName = subValues.get(subValueKey);
						break;
					case "password":
						adminPassword = subValues.get(subValueKey);
						break;
					}
				}
			} else if (key.equals("database")) {
				for (String subValueKey : subValues.keySet()) {
					switch (subValueKey) {
					case "adapter":
						dbAdapter = subValues.get(subValueKey);
						break;
					case "dbname":
						dbName = subValues.get(subValueKey);
						break;
					case "username":
						dbUsername = subValues.get(subValueKey);
						break;
					case "password":
						dbPassword = subValues.get(subValueKey);
						break;
					}
				}
			}
		}
		
		System.out.print("dbName" + dbName);
	}
	
	public static String getAdminName() {
		return adminName;
	}
	
	public static String getAdminPassword() {
		return adminPassword;
	}
	
	public static String getAdapter() {
		return dbAdapter;
	}
	
	public static String getDBName() {
		return dbName;
	}
	
	public static String getDBUsername() {
		return dbUsername;
	}
	
	public static String getDBPassword() {
		return dbPassword;
	}
}