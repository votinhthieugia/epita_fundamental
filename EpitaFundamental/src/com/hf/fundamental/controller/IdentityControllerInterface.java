/**
 * IdentityControllerInterface.java
 */

package com.hf.fundamental.controller;

import java.util.List;

import com.hf.fundamental.datamodel.Identity;

public interface IdentityControllerInterface {
	/*
	 * Create an identity record.
	 * @param: new identity.
	 */
	public void create(Identity newIdentity) throws Exception;
	
	/*
	 * Update an existing identity record.
	 * @param: an identity.
	 */
	public void update(Identity identity) throws Exception;
	
	/*
	 * Delete an identity record.
	 * @param: existing identity.
	 * return: true if successful.
	 */
	public void delete(Identity identity) throws Exception;

	/*
	 * Search a list of identities by display name or email.
	 * @param: identity
	 * return: list of identities.
	 */
	public List<Identity> search(Identity identity) throws Exception;
	
	/*
	 * List all identities.
	 * return: list of identities.
	 */
	public List<Identity> listAll() throws Exception;
}
