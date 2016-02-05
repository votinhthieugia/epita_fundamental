package com.hf.fundamental.services.match;

/**
 * This is an interface to define the Matcher behavior
 * @author tbrou
 *
 * @param <T> any type
 */
public interface Matcher<Object> {

	/**
	 * 
	 * @param criteria
	 * @param toBeMatched
	 * @return true if the criteria matches the toBeMatched instance
	 */
	public boolean match(Object criteria, Object toBeMatched);

}
