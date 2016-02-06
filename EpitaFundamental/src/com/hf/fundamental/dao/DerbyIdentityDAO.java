/**
 * DerbyIdentityDAO.java
 */

package com.hf.fundamental.dao;

/**
 * The {@code DerbyIdentityDAO} class implements the <i>abstract class</i> {@link SQLIdentityDAO}.
 * @author Hoang / Favio
 *
 */
public class DerbyIdentityDAO extends SQLIdentityDAO {
	
	/**
	 *  Reuse method from super class to set up the data base configuration.
	 */
	@Override
	protected void configure() {
		super.configure();
	}
}