package com.wsd.library.message;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.wsd.library.model.BooksData;

public class ContentParser {
	
	public static final String ATTRIBUTE_NAME = "name";
	public static final String NAME_ACTION = "action";
	public static final String CHILD_LOGIN = "login";
	public static final String CHILD_PASS = "password";
	public static final String CHILD_NAME = "name";
	public static final String CHILD_SURNAME = "surname";
	public static final String CHILD_ADDRESS = "address";
	public static final String CHILD_PRICE ="price";
	public static final String CHILD_TITLE ="title";
	public static final String CHILD_ID ="id";
	public static final String CHILD_AUTHOR ="author";
	public static final String CHILD_ISBN = "isbn";
	public static final String CHILD_YEAR = "year";
	public static final String CHILD_WEIGHT = "weight";
	public static final String CHILD_BOOKID = "bookid";
	public static final String CHILD_USERID = "userid";
	public static final String CHILD_AMOUNT = "amount";
	
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
	
	public List<BooksData> getBooksIdList() {
		Element rootElement = getRootElement();
		List<Element> books = rootElement.getChildren();
		List<BooksData> ret = new ArrayList<BooksData>();
		for (Element book: books) {
			BooksData booksData = new BooksData();
			booksData.setAuthor(book.getChild(CHILD_AUTHOR).getValue());
			booksData.setPrice(new BigDecimal(book.getChild(CHILD_PRICE).getValue()));
			booksData.setTitle(book.getChild(CHILD_TITLE).getValue());
			booksData.setIsbn(book.getChild(CHILD_ISBN).getValue());
			booksData.setYear(Integer.parseInt(book.getChild(CHILD_YEAR).getValue()));
			booksData.setWeight(Integer.parseInt(book.getChild(CHILD_WEIGHT).getValue()));
			ret.add(booksData);
		}
		return ret;
	}
	
	public BooksData getBook() {
		Element rootElement = getRootElement();
		Element book = rootElement.getChildren().get(0);
		BooksData booksData = new BooksData();
		booksData.setAuthor(book.getChild(CHILD_AUTHOR).getValue());
		booksData.setPrice(new BigDecimal(book.getChild(CHILD_PRICE).getValue()));
		booksData.setTitle(book.getChild(CHILD_TITLE).getValue());
		booksData.setIsbn(book.getChild(CHILD_ISBN).getValue());
		booksData.setYear(Integer.parseInt(book.getChild(CHILD_YEAR).getValue()));
		booksData.setWeight(Integer.parseInt(book.getChild(CHILD_WEIGHT).getValue()));
		return booksData;
	}
	
	public int getBooksId() {
		Element rootElement = getRootElement();
		Element book = rootElement.getChildren().get(0);
		return Integer.valueOf(book.getChild(CHILD_ID).getValue());
	}
	
	public String getBooksStatus() {
		Element rootElement = getRootElement();
		Element book = rootElement.getChildren().get(0);
		return book.getAttributeValue("status");
	}

	public String getError() {
		Element rootElement = getRootElement();
		Element book = rootElement.getChildren().get(0);
		return book.getValue();
	}
	
}
