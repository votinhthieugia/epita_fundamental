/**
 * DAOFactory.java
 */

package com.hf.fundamental.dao;

/**
 * The {@code DAOFactory } class allows to <b>instantiate</b> concrete objects for DAOs in the
 * application.
 * <p>
 * Two types are currently supported 1. xml 2. jdbc.
 * <p>
 * This factory method <i>allows the DAO functionality, and instantiation to be
 * independent of the functional logic</i>.
 * <p>
 * For more on DAO's see {@link fr.epita.awebo.dao.IdentityDAO}.
 * 
 * @author Hoang / Favio
 *
 */
public abstract class DAOFactory {
	
	/**
	 * The method {@code IdentityDAO} returns an instance of a IdentityDAO  class.
	 * @return IdentityDAO
	 */
	public abstract IdentityDAO getIdentityDAO();
	
	/**
	 * The method {@code UserDAO} returns an instance of a UserDAO  class.
	 * @return UserDAO
	 */
	public abstract UserDAO getUserDAO();
	
	/**
	 * The <i>static</i> method {@code DAOFactory} matches the DAO factory according the {@link Storage} type
	 * and returns a {@code DAOFactory}.
	 * @param: type .
	 * @return: {@code DAOFactory}.
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