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

/**
 * The {@code SQLDAO} class defines the methods needed to establish and close the connection with a database.
 * <p>
 * It uses a <b>properties file</b> where the data base <b>configuration is provided by the user</b>.
 * @author Hoang / Favio
 *
 */
public class SqlDAO {
	// The jdbc connection.
	protected Connection connection;
	
	// The properties configuration file.
	protected Properties settings;
	
	/**
	 * By creating the instance of SQLDAO, it invokes the method configure to read the configurations 
	 * from a properties file and then it invokes getConnection Method to get the JDBC connection.
	 */
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
	 * Release the connection.
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