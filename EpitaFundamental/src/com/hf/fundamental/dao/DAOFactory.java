/**
 * DAOFactory.java
 */

package com.hf.fundamental.dao;

public abstract class DAOFactory {
	public abstract IdentityDAO getIdentityDAO();
	public abstract UserDAO getUserDAO();
	
	/**
	 * A static method to get the correct DAO factory.
	 * @param: an integer as a type.
	 * @return: a DAO factory.
	 */
	public static DAOFactory getDAOFactory(int type) {
		switch (type) {
		case Storage.XML:
			return new XMLDAOFactory();
		case Storage.DERBY:
			return new DerbyDAOFactory();
		default:
			return null;
		}
	}
}