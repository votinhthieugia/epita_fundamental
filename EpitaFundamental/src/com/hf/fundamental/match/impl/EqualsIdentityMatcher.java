/**
 * EqualsIdentityMatcher.java
 */
package com.hf.fundamental.match.impl;

import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.services.match.Matcher;

/**
 * The {@code EqualsIdentityMatcher} class uses the method {@code String.equals()} to compare the <i>Identities</i>.
 * @author Hoang / Favio
 *
 */
public class EqualsIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return (criteria.getDisplayName().matches(toBeMatched.getDisplayName())
				|| criteria.getEmail().matches(toBeMatched.getEmail())
				|| criteria.getUid().matches(toBeMatched.getUid())		
				)? true : false; 		
	}
	
}