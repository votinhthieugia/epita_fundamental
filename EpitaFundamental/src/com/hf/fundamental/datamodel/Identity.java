/**
 * Identity.java
 */

package com.hf.fundamental.datamodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

/**
 * The {@code Identity} class defines the fields and methods based on the requirements
 * 
 * @author Hoang / Favio
 *
 */
public class Identity {
	
	String 			   displayName;
	String 			   uid;
	String 			   email;
	Map<String,String> attributes;
		
	public Identity() {
		super();
	}
	
	public Identity(String displayName, String uid, String email, Map<String, String> attributes) {
		super();
		this.displayName = displayName;
		this.uid = uid;
		this.email = email;
		this.attributes = attributes;
	}
	
	public Identity(String displayName, String uid, String email, String attributesString) {
		this.displayName = displayName;
		this.uid = uid;
		this.email = email;
		
		Map<String, String> attributes = new HashMap<String, String>();
		
		if (attributesString != null) {
			try {
				JSONObject attributesJson = new JSONObject(attributesString);
				@SuppressWarnings("unchecked")
				Iterator<String> keys = attributesJson.keys();
				
				while (keys.hasNext()) {
					String key = keys.next();
					attributes.put(key, attributesJson.getString(key));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.attributes = attributes;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	/**
	 * @return attributes <b>string in json string format</b>.
	 */
	public String getAttributesString() {
		JSONObject json = new JSONObject();
		
		try {
			for (Map.Entry<String, String> entry: attributes.entrySet()) {
				json.put(entry.getKey(), entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json.toString();
	}
	
	/**
	 * Check if the identity is valid.
	 */
	public boolean isValid() {
		if (displayName.equals("") || email.equals("")) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Identity [displayName=" + displayName + ", uid=" + uid + ", email=" + email + ", attributes="
				+ attributes + "]";
	}
}
