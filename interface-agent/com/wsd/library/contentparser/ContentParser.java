package com.wsd.library.contentparser;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ContentParser {
	
	public static final String ATTRIBUTE_NAME = "name";
	public static final String NAME_ACTION = "action";
	public static final String CHILD_LOGIN = "login";
	public static final String CHILD_PASS = "password";
	public static final String CHILD_NAME = "name";
	public static final String CHILD_SURNAME = "surname";
	public static final String CHILD_ADDRESS = "address";
	public static final String CHILD_PRICE ="price";
	
	private Document document;
	
	public ContentParser(String document) {
		super();
		this.document = getXMLDocument(document);
	}

	private Document getXMLDocument(String xml) {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc;
		try {
			doc = saxBuilder.build(new StringReader(xml));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	};
	
	private Element getRootElement() {
		Element root = null;
		if (document != null)
			root = document.getRootElement();
		return root;
	}
	
	public String getActionType() {
		Element rootElement = getRootElement();
		if (rootElement != null && rootElement.getName() == NAME_ACTION)
			return rootElement.getAttribute(ATTRIBUTE_NAME).getValue();
		else
			return null;
	}
	
	public String getRootsChildValue(String key) {
		Element rootElement = getRootElement();
		if (rootElement != null && rootElement.getChild(key) != null)
			return document.getRootElement().getChild(key).getValue();
		else
			return null;
	}
	
}
