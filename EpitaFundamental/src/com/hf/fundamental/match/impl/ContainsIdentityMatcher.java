/**
 * ContainsIdentityMatcher.java
 */
package com.hf.fundamental.match.impl;

import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.services.match.Matcher;

/**
 * The {@code ContainsIdentityMatcher} class uses the method {@code String.contains()} to compare the <i>Identities</i>.
 * @author Hoang / Favio
 *
 */
public class ContainsIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		cleanNull(criteria);
		cleanNull(toBeMatched);
		return (criteria.getDisplayName().contains(toBeMatched.getDisplayName())
				|| criteria.getEmail().contains(toBeMatched.getEmail())
				|| criteria.getUid().contains(toBeMatched.getUid())
				)? true: false;		
	}

	/**
	 * The method {@code ClearNull} replaces those fields who point to <b>null</b> to <b>empty String ("")</b>
	 * @param criteria
	 */
	private void cleanNull(Identity criteria) {
		if (criteria.getDisplayName() == null) {
			criteria.setDisplayName("");
		}
		if (criteria.getEmail() == null) {
			criteria.setEmail("");
		}
		if (criteria.getUid() == null) {
			criteria.setUid("");
		}
	}

}
