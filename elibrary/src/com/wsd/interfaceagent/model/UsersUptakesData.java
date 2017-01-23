package com.wsd.interfaceagent.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Users_uptakes")
public class UsersUptakesData {
	
	@Id @GeneratedValue
	@Column(name = "idUsers_uptakes")
	private int id;
	@Column(name = "Start_time")
	private Date startDate;
	@Column(name = "End_time")
	private Date endDate;
	@ManyToOne
    @JoinColumn(name = "Users_idUsers")
	private UserData userData;
	@ManyToOne
    @JoinColumn(name = "Books_idBooks")
	private BooksData booksData;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public BooksData getBooksData() {
		return booksData;
	}

	public void setBooksData(BooksData booksData) {
		this.booksData = booksData;
	}

	public UsersUptakesData() {
		super();
	}
}
