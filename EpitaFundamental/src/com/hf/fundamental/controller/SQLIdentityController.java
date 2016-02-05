/**
 * SQLIdentityController.java
 */

package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.datamodel.Identity;

public class SQLIdentityController implements IdentityControllerInterface {
	private static SQLIdentityController instance;
	private IdentityDAO identityDAO;
	
	public static SQLIdentityController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new SQLIdentityController(dbType);
		}
		
		return instance;
	}
	
	private SQLIdentityController(int dbType) throws Exception {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			identityDAO = daoFactory.getIdentityDAO();
		} else {
			throw new Exception();
		}
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
	public void delete(Identity identity) throws Exception {
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