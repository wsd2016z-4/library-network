package com.wsd.library.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class BooksData {
	@Id @GeneratedValue
	@Column(name = "idBooks")
	private int id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Author")
	private String author;
	@Column(name = "Sygnature")
	private int sygnature;
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

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getSygnature() {
		return sygnature;
	}

	public void setSygnature(int sygnature) {
		this.sygnature = sygnature;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BooksData() {
		super();
	}
}
