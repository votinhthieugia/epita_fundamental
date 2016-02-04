/**
 * DerbyDAOFactory.java
 */

package com.hf.fundamental.dao;

public class DerbyDAOFactory extends DAOFactory {

	@Override
	public IdentityDAO getIdentityDAO() {
		return new DerbyIdentityDAO();
	}
}