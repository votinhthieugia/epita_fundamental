/**
 * SQLUserDAO.java
 */

package com.hf.fundamental.dao;

public class SQLUserDAO extends SqlDAO implements UserDAO {

	@Override
	public boolean authenticate(String userName, String password) {
		return false;
	}
}