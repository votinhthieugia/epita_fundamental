/**
 * UserControllerInterface.java 
 */

package com.hf.fundamental.controller;

public interface UserControllerInterface {
	/*
	 * Authenticate an user.
	 * @param:
	 * - username: user username.
	 * - password: user password.
	 * return: true if successful.
	 */
	public boolean authenticate(String username, String password) throws Exception;
}