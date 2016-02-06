package com.hf.fundamental.dao;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The {@code XMLUserDAO} class implements the User authenticate method for {@link User}.
 * 
 * @author Hoang / Favio
 *
 */
public class XMLUserDAO implements UserDAO{
	
	// Root of the XML.
	Document document;
	
	/**
	 * When invoked, it is assumed that the file 'users.xml' <b>must have been created</b> and <b>inserted</b> a user with
	 * his password.
	 */
	public XMLUserDAO() {
		try {
			// Parse the XML into DOM standard.
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(new File("users.xml"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Traverses over the XML file and asks if both <i>userName</i> and <i>password</i> match.
	 * {@inheritDoc}
	 */
	@Override
	public boolean authenticate(String userName, String password) {		
		NodeList identitiesList = document.getElementsByTagName("user");
		int length = identitiesList.getLength();
		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);
			NodeList properties = identity.getElementsByTagName("property");
			int propertiesLength = properties.getLength();
			int control=0;
			for (int j = 0; j < propertiesLength; j++) {
				Element property = (Element) properties.item(j);
				if (property.getTextContent().trim().equals(userName)
					||	property.getTextContent().trim().equals(password)) {
					control++;					
				}						
				if (control == 2) {
					return true;
				}
			}			
		}				
		return false;
	}
}