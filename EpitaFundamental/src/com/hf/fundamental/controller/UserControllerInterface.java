/**
 * UserControllerInterface.java 
 */

package com.hf.fundamental.controller;

/**
 * The {@code UserControllerInterface} 
 * The interface {@code UserControllerInterface} <i>allows to define reusable behaviors </i> 
 * <b>for User Controllers.</b>
 * @author Hoang / Favio
 *
 */
public interface UserControllerInterface {
	/**
	 * Authenticate an user
	 * @param username
	 * @param password
	 * @return true if successful
	 * @throws Exception
	 */
	public boolean authenticate(String username, String password) throws Exception;
}