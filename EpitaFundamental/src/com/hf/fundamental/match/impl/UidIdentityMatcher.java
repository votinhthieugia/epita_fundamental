package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

public class UidIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return criteria.getUid().equals(toBeMatched.getUid());
	}

}
