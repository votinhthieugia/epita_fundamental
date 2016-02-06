/**
 * XMLIdentityDAO.java
 */
package com.hf.fundamental.dao;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hf.fundamental.datamodel.Identity;
import com.hf.fundamental.match.impl.ContainsIdentityMatcher;
import com.hf.fundamental.match.impl.EqualsIdentityMatcher;
import com.hf.fundamental.match.impl.UidIdentityMatcher;
import com.hf.fundamental.services.match.Matcher;
import com.hf.fundamental.util.Reflection;

/**
 * The {@code XMLIdentityDAO} class implements the Identity object CRUD methods for {@link com.hf.fundamental.datamodel.Identity}.
 * <p>
 * Uses Matcher implementations {@link com.hf.fundamental.match.impl}.
 *
 * @author Hoang / Favio
 */
public class XMLIdentityDAO implements IdentityDAO{

	// Root of the XML.
	Document document;

	// Matchers used to compare identities according criteria.
	private Matcher<Identity> activeMatchingStrategy = new EqualsIdentityMatcher();
	private Matcher<Identity> activeMatchingStrategyUid = new UidIdentityMatcher();
	private Matcher<Identity> activeMatchingStrategyContain = new ContainsIdentityMatcher();
	

	/**
	 * By invoking the constructor, it will look for the file stored previously and then <b>parse</b> it to 
	 * a DOM format
	 */
	public XMLIdentityDAO(){
		try {
			// Parse the XML into DOM standard.
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(new File("identities.xml"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/**
	 * Append one {@link Identity} into XML File.	
	 * 			
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 * 			An Exception can be thrown when writing the document
	 */
	@Override
	public void create(Identity identity) throws Exception {
		// Create tag <identity>.
		Element newIdentity = document.createElement("identity");
		// Create tag Structure into newIdentity and writes into the XML file.
		appendIdentity(newIdentity, identity);
		document.getElementsByTagName("identities").item(0).appendChild(newIdentity);
		writeXML();	
		document.normalize();
	}

	/**
	 * Writes an element into XML file.	
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	private void writeXML()
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		// Use a Transformer for writing into the XML.
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;		
		transformer = tFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");				    
		DOMSource source = new DOMSource(document);		    
		StreamResult result = new StreamResult(new File("identities.xml"));		
		transformer.transform(source, result);
	}

	/**
	 * Use Reflection to traverse through the Identity attributes and append each one to the node.
	 * @param newIdentity root of the DOM
	 * @param identity instance of Identity
	 */
	private Element appendIdentity(Element newIdentity, Identity identity) {		
		// Obtain the list of attributes contained in identity.
		List<Field> listFields = Reflection.getFields(identity);
		for (Field field : listFields) {
			String type = field.getGenericType().toString();			
			// If the attribute is a Map<>, then use iterator to append the subFields.
			if(type.equals("java.util.Map<java.lang.String, java.lang.String>")){
				Map<String,String> attributes = (Map<String,String>) Reflection.invokeGetter(identity, field.getName());
				for (java.util.Map.Entry<String, String> map : attributes.entrySet()) {														
					appendField(newIdentity, map.getKey(), map.getValue());
				} 			
				// Otherwise, append Field.
			} else {
				appendField(newIdentity, field.getName(), 
						Reflection.invokeGetter(identity, field.getName()).toString());	
			}
		}
		return newIdentity;
	}

	/**
	 * Append a new tag <property> into the newIdentity Element, given an <i>attribute (name and value).</i>
	 * <p>
	 * @param newIdentity root of the DOM
	 * @param nameField name of the property <property name = nameField>
	 * @param valueField value of a given name of property <property name = nameField>valueField</property>
	 */
	private void appendField(Element newIdentity, String nameField, String valueField) {
		Element element = document.createElement("property");
		element.setAttribute("name", nameField);
		element.appendChild(document.createTextNode(valueField));
		newIdentity.appendChild(element);
	}

	/**
	 * Returns all the Identities from XML file.
	 */
	@Override
	public List<Identity> readAll() {
		ArrayList<Identity> resultList = new ArrayList<>();
		NodeList identitiesList = document.getElementsByTagName("identity");
		int length = identitiesList.getLength();
		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);
			NodeList properties = identity.getElementsByTagName("property");
			Identity identityInstance = new Identity();
			int propertiesLength = properties.getLength();
			Map<String,String> attributes = new HashMap<>();
			for (int j = 0; j < propertiesLength; j++) {
				Element property = (Element) properties.item(j);
				String attribute = property.getAttribute("name");
				String value = property.getTextContent().trim();				
				if(j <= 2){ //Because it traverses through the first 3 fields until it reaches the Map with additional fields.
					Reflection.invokeSetter(identityInstance, attribute, value);
				} else {// Map					
					attributes.put(attribute, value);
				}				
			}
			Reflection.invokeSetter(identityInstance, "attributes", attributes);
			resultList.add(identityInstance);			
		}		
		return resultList;
	}

	/**
	 * Search one {@link Identity} and returns it into a List<Identity>.
	 * @return resultList
	 */
	@Override
	public List<Identity> search(Identity criteria) {			
		List<Identity> resultList = new ArrayList<Identity>();
		List<Identity> identitiesList = readAll();			
		for (Identity identity : identitiesList) {			
			if (activeMatchingStrategyContain.match(criteria, identity)){
				// If it is has matched, then add the found identity to the resultList.				
				resultList.add(identity);
			}
		}		
		return resultList;
	}

	/**
	 * Modify one Identity given its </i>unique uid and updates it into the XML file.
	 */
	@Override
	public void modify(Identity criteria) throws Exception{		
		//If the criteria (with unique uid) was found in the xml then delete old, and create one with updated values.
		if(!search(criteria).isEmpty()){			
			delete(criteria);
			create(criteria);
		}
	}

	/**
	 * Deletes an identity from XML file, given its unique uid.
	 */
	@Override
	public void delete(Identity criteria) throws Exception {		
		// If the criteria (with unique uid) was found in the xml then delete it.
		NodeList identitiesList = document.getElementsByTagName("identity");
		int length = identitiesList.getLength();		

		for (int i = 0; i < length; i++) {
			Element identity = (Element) identitiesList.item(i);				
			NodeList properties = identity.getElementsByTagName("property");

			int propertiesLength = properties.getLength();
			for (int j = 0; j < propertiesLength; j++) {
				Element property = (Element) properties.item(j);
				String attribute = property.getAttribute("name");					
				String value = property.getTextContent().trim();
				if (attribute.equals("uid") && value.equals(criteria.getUid())) {						
					property.getParentNode().getParentNode().removeChild(property.getParentNode());
					writeXML();	
					length--;						
				}																		
			}			
		}
	}
}