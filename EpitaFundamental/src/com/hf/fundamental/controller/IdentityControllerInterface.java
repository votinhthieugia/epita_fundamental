/**
 * IdentityControllerInterface.java
 */

package com.hf.fundamental.controller;

import java.util.List;
import com.hf.fundamental.datamodel.Identity;

/**
 * The interface {@code IdentityControllerInterface} <i>allows to define reusable behaviors </i> 
 * <b>for Identity Controllers.</b>
 * @author Hoang / Favio
 *
 */
public interface IdentityControllerInterface {
	
	/**
	 * Create an <i>identity</i> record.
	 * @param newIdentity
	 * @throws Exception
	 */
	public void create(Identity newIdentity) throws Exception;
		
	/**
	 * Update an <i>existing identity</i> record.
	 * @param identity
	 * @throws Exception
	 */
	public void update(Identity identity) throws Exception;
	
	/**
	 * Delete an <i>identity</i> record.
	 * @param identity 
	 * @throws Exception
	 */
	public void delete(Identity identity) throws Exception;
	
	/**
	 * Search a <i>list of identities</i> by <b>displayName or email</b>.
	 * @param identity
	 * @return list of matched identities
	 * @throws Exception
	 */
	public List<Identity> search(Identity identity) throws Exception;
		
	/**
	 * List all <i/>identities</i>.
	 * @return List<Identity> list of identities
	 * @throws Exception
	 */
	public List<Identity> listAll() throws Exception;
}