package com.remindme.model;

public class User {
	private int id;
	private String userName;
	private String mobileNumber;
	private String email;
	private String password;
	
	public User(int id, String userName, String mobileNumber, String email, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	public User(String userName, String mobileNumber, String email, String password) {
		super();
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", mobileNumber=" + mobileNumber + ", email=" + email
				+ ", password=" + password + "]";
	}	
}
