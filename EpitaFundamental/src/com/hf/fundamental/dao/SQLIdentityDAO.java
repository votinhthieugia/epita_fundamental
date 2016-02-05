/**
 * SQLIdentityDAO.java
 */

package com.hf.fundamental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hf.fundamental.datamodel.Identity;

public class SQLIdentityDAO extends SqlDAO implements IdentityDAO {
	
	/**
	 * Create identity from database row. 
	 */
	private Identity createFromRow(ResultSet resultSet) throws SQLException {
		String displayName = resultSet.getString("displayName");
		String uid = resultSet.getString("uid");
		String email = resultSet.getString("email");
		String attributesString = resultSet.getString("attributes");
		return new Identity(displayName, uid, email, attributesString);
	}
	
	@Override
	public void create(Identity identity) throws Exception {
		String query = "insert into identities(displayName, uid, email, attributes) values (?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 1;
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getUid());
			statement.setString(index++, identity.getEmail());
			statement.setString(index++, identity.getAttributesString());
			statement.execute();
			statement.close();
		}
	}

	@Override
	public List<Identity> readAll() throws Exception {
		ArrayList<Identity> list = new ArrayList<Identity>();
		String query = "select uid, displayName, email, attributes from identities";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				list.add(createFromRow(resultSet));
			}
			
			resultSet.close();
			statement.close();
		}
		
		return list;
	}

	@Override
	public List<Identity> search(Identity identity) throws Exception {
		ArrayList<Identity> list = new ArrayList<Identity>();
		String query = "select uid, displayName, email, attributes from identities where displayName = ? or email = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 1;
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getEmail());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				list.add(createFromRow(resultSet));
			}
			
			resultSet.close();
			statement.close();
		}
		
		return list;
	}

	@Override
	public void modify(Identity identity) throws Exception {
		String query = "update identities set displayName = ?, email = ?, attributes = ? where uid = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			int index = 1;
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getEmail());
			statement.setString(index++, identity.getAttributesString());
			statement.setString(index++, identity.getUid());
			statement.execute();
			statement.close();
		}
	}

	@Override
	public void delete(Identity identity) throws Exception {
		String query = "delete from identities where uid = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, identity.getUid());
			statement.execute();
			statement.close();
		}
	}
}