/**
 * DAOFactory.java
 */

package com.hf.fundamental.dao;

public abstract class DAOFactory {
	public static final int XML = 0;
	public static final int DERBY = 1;
	
	public abstract IdentityDAO getIdentityDAO();
	
	/**
	 * A static method to get the correct DAO factory.
	 * @param: an integer as a type.
	 * @return: a DAO factory.
	 */
	public static DAOFactory getDAOFactory(int type) {
		switch (type) {
		case XML:
			return new XMLDAOFactory();
		case DERBY:
			return new DerbyDAOFactory();
		default:
			return null;
		}
	}
}