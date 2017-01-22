package com.wsd.library.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Books")
@XmlRootElement(name = "book")
public class BooksData {
	@Id @GeneratedValue
	@Column(name = "idBooks")
	private int id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Author")
	private String author;
	@Column(name = "Price")
	private BigDecimal price;
	@Column(name = "ISBN")
	private String isbn;
	@Column(name = "Year")
	private int year;
	@Column(name = "Weight")
	private int weight;
	
	public String getIsbn() {
		return isbn;
	}
	
	@XmlElement(name = "isbn")
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	@XmlElement(name = "year")
	public void setYear(int year) {
		this.year = year;
	}

	public int getWeight() {
		return weight;
	}

	@XmlElement(name = "weight")
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	@XmlElement(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement(name = "title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	@XmlElement(name = "author")
	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@XmlElement(name = "price")
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BooksData() {
		super();
	}
}
