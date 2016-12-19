package com.wsd.library.message;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ContentParser {

	public static final String NAME_ACTION = "action";

	public static final String ATTRIBUTE_NAME = "name";

	private Document document;

	public ContentParser(String document) throws Exception {
		super();
		this.document = getXMLDocument(document);
	}

	private Document getXMLDocument(String xml) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		return saxBuilder.build(new StringReader(xml));
	}

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
