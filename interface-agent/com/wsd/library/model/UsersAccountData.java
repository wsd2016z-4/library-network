package com.wsd.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_account")
public class UsersAccountData {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRFID_card() {
		return RFID_card;
	}
	public void setRFID_card(int rFID_card) {
		RFID_card = rFID_card;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUserAccountCol1() {
		return userAccountCol1;
	}
	public void setUserAccountCol1(String userAccountCol1) {
		this.userAccountCol1 = userAccountCol1;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	@Id @GeneratedValue
	@Column(name = "idUser_account")
	private int id;
	@Column(name = "RFID_card")
	private int RFID_card;
	@Column(name = "balance")
	private double balance;
	@Column(name = "User_accountcol1")
	private String userAccountCol1;
	@ManyToOne
    @JoinColumn(name = "Users_idUsers")
	private UserData userData;
}
