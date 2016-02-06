/**
 * UidIdentityMatcher.java
 */
package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

/**
 * The {@code UidIdentityMatcher} class only considers as a field to compare the {@code uid}s to compare the <i>Identities</i>.
 * @author Hoang / Favio
 *
 */
public class UidIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return criteria.getUid().equals(toBeMatched.getUid());
	}

}
