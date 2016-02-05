package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

public class ContainsIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return (criteria.getDisplayName().contains(toBeMatched.getDisplayName())
				|| criteria.getEmail().contains(toBeMatched.getEmail())
				|| criteria.getUid().contains(toBeMatched.getUid())
				)? true: false;		
	}

}
