package com.hf.fundamental.match.impl;

import java.util.Map;

import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.datamodel.Identity;

public class EqualsIdentityMatcher implements Matcher<Identity>{

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return (criteria.getDisplayName().matches(toBeMatched.getDisplayName())
				|| criteria.getEmail().matches(toBeMatched.getEmail())
				|| criteria.getUid().matches(toBeMatched.getUid())
//				|| searchInAttributes(criteria.getAttributes(), toBeMatched.getAttributes())		
				)? true : false; 		
	}

	private boolean searchInAttributes(Map<String,String> criteria, Map<String,String> toBeMatched){
		for (int i = 0; i < criteria.size(); i++) {
			for (int j = 0; j < toBeMatched.size(); j++) {				
			}
		}
		return false;
	}
	
}
//implement delete method