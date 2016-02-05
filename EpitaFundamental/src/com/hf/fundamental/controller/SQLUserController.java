/**
 * SQLUserController.java
 */

package com.hf.fundamental.controller;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.UserDAO;

public class SQLUserController implements UserControllerInterface {
	private static SQLUserController instance;
	private UserDAO userDAO;
	
	public static SQLUserController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new SQLUserController(dbType);
		}
		
		return instance;
	}
	
	private SQLUserController(int dbType) throws Exception {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			userDAO = daoFactory.getUserDAO();
		} else {
			throw new Exception();
		}
	}

	@Override
	public boolean authenticate(String username, String password) throws Exception {
		return userDAO.authenticate(username, password);
	}
}