package com.hf.fundamental.match.impl;

import com.hf.fundamental.services.match.Matcher;
import com.hf.funproject.util.Reflection;
import com.hf.fundamental.datamodel.Identity;

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
