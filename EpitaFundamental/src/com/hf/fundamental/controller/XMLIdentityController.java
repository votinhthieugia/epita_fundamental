/**
 * XMLIdentityController
 */
package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.datamodel.Identity;

/**
 * The {@code XMLIdentityController} links the {@code ApplicationController} with {@code IdentityDAO}
 * @author Hoang / Favio
 *
 */
public class XMLIdentityController implements IdentityControllerInterface{

	private static XMLIdentityController instance;
	private IdentityDAO identityDAO;
	
	/**
	 * Singleton implementation
	 * @param type of storage
	 * @throws Exception
	 */
	private XMLIdentityController(int dbType) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			identityDAO = daoFactory.getIdentityDAO();
		} 
	}
	
	/**
	 * 
	 * @param type of storage
	 * @return instance of {@link XMLIdentityController}
	 * @throws Exception
	 */
	public static XMLIdentityController getInstance(int dbType) {
		if (instance == null) {
			instance = new XMLIdentityController(dbType);
		}
		return instance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(Identity newIdentity) throws Exception {
		identityDAO.create(newIdentity);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(Identity identity) throws Exception {
		identityDAO.modify(identity);	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Identity identity) throws Exception {		// 
		identityDAO.delete(identity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Identity> search(Identity identity) throws Exception {		
		return identityDAO.search(identity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Identity> listAll() throws Exception {		
		return identityDAO.readAll();
	}
}