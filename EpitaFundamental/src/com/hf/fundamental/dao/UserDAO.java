/**
 * UserDAO.java
 */

package com.hf.fundamental.dao;

/**
 * The {@code UserDAO} interface allows to define <i>reusable behavior for DAOs</i>.
 * <p> 
 * A DAO or Data Access Object is an entity responsible of data
 * access (write or retrieve data) for a given object. 
 * <p>
 * This  Data Access Object is
 * responsible for accessing the {@link com.hf.fundamental.datamodel.User}
 * class.
 * 
 * @author Hoang / Favio
 *
 */
public interface UserDAO {
	
	/**
	 * This method validates whether the userName and password match with the stored data.
	 * @param userName
	 * @param password
	 * @return true if the match is correct
	 * 			and false otherwise.
	 * @throws Exception
	 */
	public boolean authenticate(String userName, String password) throws Exception;
}