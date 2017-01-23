package com.wsd.droneagent.message;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ContentParser {

	public static final String ACTION = "action";
	public static final String ACTION_NAME = "name";

	public static final String BOOK = "book";
	public static final String ID = "id";
	public static final String WEIGHT = "weight";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";

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
		if (rootElement != null && rootElement.getName() == ACTION)
			return rootElement.getAttribute(ACTION_NAME).getValue();
		else
			return null;
	}

	public BookInfo getBookInfo() {
		BookInfo result = new BookInfo();

		Element rootElement = getRootElement();
		if (rootElement != null && rootElement.getName() == ACTION)
		{
			for(Element child : rootElement.getChildren())
			{
				if (child.getName() == BOOK)
				{
					for (Element c : child.getChildren())
					{
						if (c.getName() == ID)
							result.id = c.getValue();

						if (c.getName() == WEIGHT)
							result.weight = Integer.parseInt(c.getValue());
					}
				}
			}

			return result;
		}
		else
			return null;
	}

	public WarehouseInfo getWarehouseInfo(String target) {
		WarehouseInfo result = new WarehouseInfo();

		Element rootElement = getRootElement();
		if (rootElement != null && rootElement.getName() == ACTION)
		{
			for(Element child : rootElement.getChildren())
			{
				if (child.getName() == target)
				{
					for (Element c : child.getChildren())
					{
						if (c.getName() == ID)
							result.id = c.getValue();

						if (c.getName() == LATITUDE)
							result.coords.latitude = Double.parseDouble(c.getValue());

						if (c.getName() == LONGITUDE)
							result.coords.longitude = Double.parseDouble(c.getValue());
					}
				}
			}

			return result;
		}
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
