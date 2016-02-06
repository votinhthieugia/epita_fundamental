/**
 * XMLUserControler.java
 */
package com.hf.fundamental.controller;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.UserDAO;

/**
 * The {@code XMLUserController} class links the {@code ApplicationController} with {@code UserDAO}
 * @author Hoang / Favio
 *
 */
public class XMLUserController implements UserControllerInterface{
	private static XMLUserController instance;
	private UserDAO userDAO;
	
	/**
	 * 
	 * @param type of storage
	 * @return instance of {@link XMLUserController}
	 * @throws Exception
	 */
	public static XMLUserController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new XMLUserController(dbType);
		}
		
		return instance;
	}
	
	/**
	 * Singleton implementation 
	 * @param type of storage 
	 */
	private XMLUserController(int dbType) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			userDAO = daoFactory.getUserDAO();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean authenticate(String userName, String password) throws Exception {
		return userDAO.authenticate(userName, password);
	}
}