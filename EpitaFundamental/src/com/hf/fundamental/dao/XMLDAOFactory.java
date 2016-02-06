/**
 * XMLDAOFactory.java
 */

package com.hf.fundamental.dao;

/**
 * The {@code XMLDAOFactory} class allows to <i><b>instantiate</b> concrete objects for DAOs in the
 * application.</i>
 * <p>
 * This factory method allows the DAO functionality and instantiation to be
 * <i>independent of the functional logic.</i>
 * <p>
 * For more on DAO's see {@link com.hf.fundamental.dao.IdentityDAO} and
 * {@link com.hf.fundamental.dao.UserDAO}.
 * 
 * @author Favio / Hoang
 *
 */
public class XMLDAOFactory extends DAOFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IdentityDAO getIdentityDAO() {
		return new XMLIdentityDAO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDAO getUserDAO() {
		return new XMLUserDAO();
	}
}