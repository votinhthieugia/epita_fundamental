/**
 * SQLUserDAO.java
 */

package com.hf.fundamental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLUserDAO extends SqlDAO implements UserDAO {

	@Override
	public boolean authenticate(String userName, String password) throws Exception {
		String query = "select count(*) as n from identities where displayName = ? and password = ? limit 1";
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
		
		return hasFound;
	}
}