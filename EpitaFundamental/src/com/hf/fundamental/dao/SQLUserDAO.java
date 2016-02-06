/**
 * SQLUserDAO.java
 */

package com.hf.fundamental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class SQLUserDAO extends SqlDAO implements UserDAO {

	@Override
	public boolean authenticate(String userName, String password) throws Exception {
		String query = "select * from identities where displayName = ? and password = ?";
		boolean hasFound = false;
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 1;
			statement.setString(index++, userName);
			statement.setString(index++, password);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				hasFound = true;
			}
			
			resultSet.close();
			statement.close();
		}
		
		System.out.println("hasFound" + hasFound);
		return hasFound;
	}

	@Override
	protected void postConfigure() {
		createAdminUser(DataConfiguration.getAdminName(), DataConfiguration.getAdminPassword());
	}

	private void createAdminUser(String username, String password) {
		String query = "select * from identities where displayName = ?";
		boolean hasFound = false;
		
		try {
			int index = 1;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(index++, username);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				hasFound = true;
			}
			
			index = 1;
			
			if (hasFound) {
				query = "update identities set password = ? where displayName = ?";
				statement = connection.prepareStatement(query);
				statement.setString(index++, password);
				statement.setString(index++, username);
				statement.execute();
			} else {
				query = "insert into identities(uid, displayName, password, email) values(?,?,?,?)";
				statement = connection.prepareStatement(query);
				statement.setString(index++, UUID.randomUUID().toString());
				statement.setString(index++, username);
				statement.setString(index++, password);
				statement.setString(index++, "");
				statement.execute();
			}
			
			resultSet.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}