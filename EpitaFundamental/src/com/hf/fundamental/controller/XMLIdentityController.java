package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.datamodel.Identity;

public class XMLIdentityController implements IdentityControllerInterface{

	private static XMLIdentityController instance;
	private IdentityDAO identityDAO;
	
	private XMLIdentityController(int dbType) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			identityDAO = daoFactory.getIdentityDAO();
		} 
	}
	
	public static XMLIdentityController getInstance(int dbType) {
		if (instance == null) {
			instance = new XMLIdentityController(dbType);
		}
		return instance;
	}
	
	@Override
	public void create(Identity newIdentity) throws Exception {
		identityDAO.create(newIdentity);
		
	}

	@Override
	public void update(Identity identity) throws Exception {
		identityDAO.modify(identity);	
	}

	@Override
	public void delete(Identity identity) throws Exception {		// 
		identityDAO.delete(identity);
	}

	@Override
	public List<Identity> search(Identity identity) throws Exception {		
		return identityDAO.search(identity);
	}

	@Override
	public List<Identity> listAll() throws Exception {		
		return identityDAO.readAll();
	}

}
