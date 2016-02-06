/**
 * SQLUserController.java
 */

package com.hf.fundamental.controller;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.UserDAO;

/**
 * The {@code SQLUserController} class links the {@link ApplicationController} with
 * {@link UserDAO} to manipulate the actions to store
 * @author Hoang / Favio
 *
 */
public class SQLUserController implements UserControllerInterface {
	private static SQLUserController instance;
	private UserDAO userDAO;
	
	/**
	 * 
	 * @param type of storage
	 * @return instance of {@link SQLUserController}
	 * @throws Exception
	 */
	public static SQLUserController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new SQLUserController(dbType);
		}
		
		return instance;
	}
	
	/**
	 * Simgleton implementation
	 * @param type of storage
	 * @throws Exception
	 */
	private SQLUserController(int dbType) throws Exception {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			userDAO = daoFactory.getUserDAO();
		} else {
			throw new Exception();
		}
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public boolean authenticate(String username, String password) throws Exception {
		return userDAO.authenticate(username, password);
	}
}