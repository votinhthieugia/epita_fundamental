/**
 * IdentityControllerInterface.java
 */

package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.datamodel.Identity;

public interface IdentityControllerInterface {
	/*
	 * Authenticate an identity.
	 * @param:
	 * - username: user username.
	 * - password: user password.
	 * return: true if successful.
	 */
	public boolean authenticate(String username, String password);
	
	/*
	 * Create an identity record.
	 * @param: new identity.
	 * return: the identity after created.
	 */
	public Identity create(Identity newIdentity) throws Exception;
	
	/*
	 * Update an existing identity record.
	 * @param: an identity.
	 * return: the identity after updated.
	 */
	public Identity update(Identity identity) throws Exception;
	
	/*
	 * Delete an identity record.
	 * @param: existing identity.
	 * return: true if successful.
	 */
	public boolean delete(Identity identity) throws Exception;

	/*
	 * Search a list of identities by display name or email.
	 * @param: identity
	 * return: list of identities.
	 */
	public List<Identity> search(Identity identity);
	
	/*
	 * List all identities.
	 * return: list of identities.
	 */
	public List<Identity> listAll();
}
