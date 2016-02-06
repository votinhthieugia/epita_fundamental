package com.hf.fundamental.dao;

import java.util.List;

import com.hf.fundamental.datamodel.Identity;

/**
 * <p>The {@code IdentityDAO} interface allows to <i>define reusable behaviors for DAOs </i> 
 * <p>A DAO or Data Access Object is an entity responsible of data
 * access (write or retrieve data) for a given object.
 * <p> 
 * This Data Access Object is responsible for accessing the 
 * {@link com.hf.fundamental.datamodel.Identity} class.
 * 
 * @author Hoang / Favio
 *
 */
public interface IdentityDAO {

	
	/**
	 * Write an Identity object to storage.
	 * <p>
	 * Writes Identity data and extended attributes to disk. When JDBC is used
	 * as DAO, full extended attribute persistent storage is available in JSON Format.
	 * <p>
	 * 
	 * @param identity
	 * 					Identity Object send from the view layer
	 * @throws Exception
	 * 					Throws Exception
	 */
	public void create(Identity identity) throws Exception;
	
	/**
	 * Reads all identities from storage.
	 * <p>
	 * Reads the identities from storage into a linked list to avoid reading
	 * from storage multiple times.
	 * 
	 * @return List A list of identities.
	 * @throws Exception
	 * 				In case of data access exceptions.
	 */
	public List<Identity> readAll() throws Exception;
	
	/**
	 * Reads the list of Identities from the storage that match criteria
	 * <p>
	 * Method receives an Identity object and after returns the identity list to which the object's 
	 * fields match according to the criteria. 
	 * <p>
	 * @param identity
	 * 				criteria to search
	 * @return a List containing all the matches  
	 * @throws Exception
	 */
	public List<Identity> search(Identity identity) throws Exception;
	
	/**
	 * Update an Identity object from storage.
	 * <p>
	 *
	 * @param identity
	 * 			Identity Object send by the view layer
	 * @throws Exception
	 * 			In case of data access exceptions
	 */
	public void modify(Identity identity) throws Exception;
	
	/**
	 * Delete an Identity object from storage.
	 * <p>	
	 * @param identity object send by the view layer.
	 * @throws Exception
	 * 				In case of data access exceptions.
	 */
	public void delete(Identity identity) throws Exception;
}