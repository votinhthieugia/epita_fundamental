/**
 * User.java
 */

package com.hf.fundamental.datamodel;

/**
 * The {@code User} is used to differentiate those <i>identities</i> who have privileges to access the application  
 * @author Hoang / Favio
 *
 */
public class User extends Identity {
	
	String password;

	public User(String password) {
		super();
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [password=" + password + ", displayName=" + displayName + ", uid=" + uid + ", email=" + email
				+ ", attributes=" + attributes + "]";
	}
}