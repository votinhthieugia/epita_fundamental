package com.hf.fundamental.controller;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.dao.UserDAO;

public class XMLUserController implements UserControllerInterface{

	
	private static XMLUserController instance;
	private UserDAO userDAO;
	
	public static XMLUserController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new XMLUserController(dbType);
		}
		
		return instance;
	}
	
	private XMLUserController(int dbType) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			userDAO = daoFactory.getUserDAO();
		}
	}
	
	@Override
	public boolean authenticate(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.authenticate(userName, password);
	}

}
