/**
 * SqlDAO.java
 */

package com.hf.fundamental.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlDAO {
	// The jdbc connection.
	protected Connection connection;
	
	// The properties configuration file.
	protected Properties settings;
	
	public SqlDAO() {
		configure();
		
		try {
			connection = getConnection();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the configurations from a properties file.
	 */
	protected void configure() {
		settings = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("config.properties");
			settings.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the jdbc connection from driver manager.
	 */
	protected Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		return DriverManager.getConnection(settings.getProperty("connectionString"), 
										   settings.getProperty("username"), 
										   settings.getProperty("password"));
	}
	
	/**
	 * Close: release the connection.
	 */
	public void close() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}