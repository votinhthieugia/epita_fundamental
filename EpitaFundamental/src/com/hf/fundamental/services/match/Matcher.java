/**
 * Matcher.java
 */
package com.hf.fundamental.services.match;

/**
 * The {@code Matcher<t>} interface defines the <i>Matcher behavior</i>.
 * @author Hoang / Favio
 * @param <T> any type
 */
public interface Matcher<T> {

	/**	
	 * @param criteria
	 * @param toBeMatched
	 * @return true if the criteria matches the toBeMatched instance
	 */
	public boolean match(T criteria, T toBeMatched);

}
