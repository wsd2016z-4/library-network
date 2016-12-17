package com.wsd.library.model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserData {
	public UserData() {
		super();
	}
	@Id @GeneratedValue
	@Column(name = "idUsers")
	private int id;
	@Column(name = "Login")
	private String userLogin;
	@Column(name = "Password")
	private String userPass;
	@Column(name = "Name")
	private String name;
	@Column(name = "Surname")
	private String surname;
	@Column(name = "Adress")
	private String address;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
