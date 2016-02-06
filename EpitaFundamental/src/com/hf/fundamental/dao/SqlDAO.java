/**
 * SqlDAO.java
 */

package com.hf.fundamental.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDAO {
	// The jdbc connection.
	protected Connection connection;
	
	public SqlDAO() {
		configure();
		
		try {
			connection = getConnection();
			System.out.println(connection);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		
		postConfigure();
	}
	
	/**
	 * Implement some data configuration.
	 */
	protected void configure() {
	}
	
	/**
	 * Do something after configuring and get the connection.
	 */
	protected void postConfigure() {
	}
	
	/**
	 * Create the connection string.
	 */
	protected String getConnectionString() {
		String connectionString = "";
		switch (DataConfiguration.getAdapter().toLowerCase()) {
		case "derby":
			connectionString = "jdbc:derby://localhost:1527/" + DataConfiguration.getDBName() + ";create=true";
			break;
		case "mysql":
			connectionString = "jdbc:mysql://localhost:3306/" + DataConfiguration.getDBName() + ";create=true";
			break;
		default:
			break;
		}
		return connectionString;
	}
	
	/**
	 * Get the jdbc connection from driver manager.
	 */
	protected Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		return DriverManager.getConnection(getConnectionString(),
										   DataConfiguration.getDBUsername(), 
										   DataConfiguration.getDBPassword());
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