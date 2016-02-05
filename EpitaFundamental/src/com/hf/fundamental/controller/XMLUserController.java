package com.hf.fundamental.controller;

import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.dao.UserDAO;

public class XMLUserController implements UserControllerInterface{

	
	private static XMLUserController instance;
	private UserDAO userDAO;
	
	public static XMLUserController getInstance() throws Exception {
		if (instance == null) {
			instance = new XMLUserController();
		}
		
		return instance;
	}
	@Override
	public boolean authenticate(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
