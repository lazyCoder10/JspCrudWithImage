package com.binarydream.test.users;

import java.io.InputStream;
import java.util.Date;

public class User {
	private int userId;
	private String firstName ;
	private String lastName;
	private Date dateOfBirth;
	private String userEmail;
	private String userImage;
	
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String userName) {
		this.firstName = userName;
	}
	
	/*
	 * overriding toString method helps to 
	 * get userInfo for updating that user's info
	 * */
	@Override
	    public String toString() {
	        return "User [userid=" + userId + ", firstName=" + firstName
	                + ", lastName=" + lastName + ", dob=" + dateOfBirth + ", email="
	                + userEmail + "]";
	    }
	
}
