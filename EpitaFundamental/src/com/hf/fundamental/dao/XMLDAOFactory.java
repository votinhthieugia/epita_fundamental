/**
 * XMLDAOFactory.java
 */

package com.hf.fundamental.dao;

public class XMLDAOFactory extends DAOFactory {

	@Override
	public IdentityDAO getIdentityDAO() {
		return new XMLIdentityDAO();
	}

	@Override
	public UserDAO getUserDAO() {
		return new XMLUserDAO();
	}
}