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
	public void create(Identity identity) {
		String query = "insert into identities(displayName, uid, email, attributes) values (?, ?, ?, ?)";
		
		try {
			int index = 1;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getUid());
			statement.setString(index++, identity.getEmail());
			statement.setString(index++, identity.getAttributesString());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Identity> readAll() {
		ArrayList<Identity> list = new ArrayList<Identity>();
		String query = "select uid, displayName, email, attributes from identities";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				list.add(createFromRow(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Identity> search(Identity identity) {
		ArrayList<Identity> list = new ArrayList<Identity>();
		String query = "select uid, displayName, email, attributes from identities where displayName = ? or email = ?";
		
		try {
			int index = 1;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getEmail());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				list.add(createFromRow(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void modify(Identity identity) {
		String query = "update identities set displayName = ?, email = ?, attributes = ? where uid = ?";
		
		try {
			int index = 1;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(index++, identity.getDisplayName());
			statement.setString(index++, identity.getEmail());
			statement.setString(index++, identity.getAttributesString());
			statement.setString(index++, identity.getUid());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Identity identity) {
		String query = "delete from identities where uid = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, identity.getUid());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}