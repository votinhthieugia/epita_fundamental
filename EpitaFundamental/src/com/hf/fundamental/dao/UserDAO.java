/**
 * UserDAO.java
 */

package com.hf.fundamental.dao;

public interface UserDAO {
	public boolean authenticate(String userName, String password);
}