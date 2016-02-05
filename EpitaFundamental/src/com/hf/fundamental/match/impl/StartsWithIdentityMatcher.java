package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

public class StartsWithIdentityMatcher implements Matcher<Identity> {

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return toBeMatched.getEmail().startsWith(criteria.getEmail())
				|| toBeMatched.getDisplayName().startsWith(	criteria.getDisplayName());
	}

}
