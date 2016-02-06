/**
 * StartsWithIdentityMatcher.java
 */
package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

/**
 * The {@code StartWithIdentityMatcher} class uses the method {@code String.startsWith()} to compare the <i>Identities</i>.
 * @author Hoang / Favio
 *
 */
public class StartsWithIdentityMatcher implements Matcher<Identity> {

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return toBeMatched.getEmail().startsWith(criteria.getEmail())
				|| toBeMatched.getDisplayName().startsWith(	criteria.getDisplayName());
	}

}
