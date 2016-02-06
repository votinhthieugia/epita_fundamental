/**
 * SQLIdentityController.java
 */

package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.dao.DAOFactory;
import com.hf.fundamental.dao.IdentityDAO;
import com.hf.fundamental.datamodel.Identity;

/**
 * The {@code SQLIdentityController} class links the {@link ApplicationController} with
 * {@link IdentityDAO} to manipulate the actions to store
 * @author Hoang / Favio
 *
 */
public class SQLIdentityController implements IdentityControllerInterface {
	private static SQLIdentityController instance;
	private IdentityDAO identityDAO;
	
	/**
	 * 
	 * @param type of storage
	 * @return instance of {@link SQLIdentityController}
	 * @throws Exception
	 */
	public static SQLIdentityController getInstance(int dbType) throws Exception {
		if (instance == null) {
			instance = new SQLIdentityController(dbType);
		}		
		return instance;
	}
	
	/**
	 * Singleton implementation
	 * @param type of storage
	 * @throws Exception
	 */
	private SQLIdentityController(int dbType) throws Exception {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(dbType);
		
		if (daoFactory != null) {
			identityDAO = daoFactory.getIdentityDAO();
		} else {
			throw new Exception();
		}
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
	public void delete(Identity identity) throws Exception {
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