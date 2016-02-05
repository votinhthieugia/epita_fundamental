package com.hf.fundamental.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hf.fundamental.dao.UserDAO;
import com.hf.fundamental.datamodel.Identity;

	public class XMLUserDAO implements UserDAO{
		
		// Root of the XML.
		Document document;
		
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
		 * Traverses over the XML file and asks if both userName and password match
		 */
		@Override
		public boolean authenticate(String userName, String password) {		
			ArrayList<Identity> resultList = new ArrayList<>();
			NodeList identitiesList = document.getElementsByTagName("user");
			int length = identitiesList.getLength();
			for (int i = 0; i < length; i++) {
				Element identity = (Element) identitiesList.item(i);
				NodeList properties = identity.getElementsByTagName("property");
				Identity identityInstance = new Identity();
				int propertiesLength = properties.getLength();
				Map<String,String> attributes = new HashMap<>();
				int control=0;
				for (int j = 0; j < propertiesLength; j++) {
					Element property = (Element) properties.item(j);
					String attribute = property.getAttribute("name");
					String value = property.getTextContent().trim();									
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

