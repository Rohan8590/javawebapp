package com.nagarro.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Defines the structure for the user table
 * 
 * @author rohanaggarwal
 *
 */
@Entity
public class User {
	@Id
	private String uname;
	private String pass;
	private String email;

	/**
	 * The default constructor
	 */
	public User() {

	}

	/**
	 * Initializes an user object with the passed parameters
	 * 
	 * @param uname
	 * @param pass
	 */
	public User(String uname, String pass) {
		this.uname = uname;
		this.pass = pass;
	}

	/**
	 * Returns the username of the user
	 * 
	 * @return The username of the user
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * Sets the username of the user
	 * 
	 * @param uname The username to be set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * Returns the password of the user
	 * 
	 * @return The password of the user
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the password of the user
	 * 
	 * @param pass The password to be set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Returns the email of the user
	 * 
	 * @return The email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user
	 * 
	 * @param email The email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
