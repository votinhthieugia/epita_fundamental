/**
 * DerbyDAOFactory.java
 */

package com.hf.fundamental.dao;

/**
 * <p>The {@code DerbyDAOFactory} allows to instantiate concrete objects for {@link DerbyDAO} in the
 * application. 
 * <p>It currently supports two types: <b>1. DerbyIdentityDAO and 2. DerbyUserDAO.</b>
 * <p>
 * The {@code DerbyDAOFactory} class allows the {@link DerbyDAO} functionality and instantiation <i>to be
 * independent of the functional logic.</i>
 * 
 * @author Hoang / Favio
 *
 */
public class DerbyDAOFactory extends DAOFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdentityDAO getIdentityDAO() {
		return new DerbyIdentityDAO();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDAO getUserDAO() {
		return new DerbyUserDAO();
	}
}