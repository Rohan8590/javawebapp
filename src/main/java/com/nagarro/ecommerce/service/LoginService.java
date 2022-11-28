package com.nagarro.ecommerce.service;

/**
 * Interface for methods required for program's login service
 * 
 * @author rohanaggarwal
 *
 */
public interface LoginService {

	/**
	 * To check if the username and password match with the database records
	 * 
	 * @param uname The username entered
	 * @param pass  The passwork entered
	 * @return If the username and password match with the database records
	 */
	public boolean isValidUser(String uname, String pass);

	/**
	 * To check if the username and email match with the database records
	 * 
	 * @param uname The username entered
	 * @param email The email entered
	 * @return If the username and email match with the database records
	 */
	public boolean isValidEmail(String uname, String email);

	/**
	 * To get the password for a user
	 * 
	 * @param uname The username of the user
	 * @return The password of the user
	 */
	public String getPassword(String uname);

}
