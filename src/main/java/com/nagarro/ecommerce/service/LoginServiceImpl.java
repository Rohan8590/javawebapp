package com.nagarro.ecommerce.service;

import com.nagarro.ecommerce.dao.Factory;
import com.nagarro.ecommerce.dao.UserDAO;
import com.nagarro.ecommerce.model.User;

/**
 * Provides functions for login service of the program
 * 
 * @author rohanaggarwal
 *
 */
public class LoginServiceImpl implements LoginService {
	private Factory factory;
	private UserDAO userDAO;

	/**
	 * Initializes the userDAO with an object from the factory
	 */
	public LoginServiceImpl() {
		if (factory == null) {
			factory = new Factory();
		}
		if (userDAO == null) {
			userDAO = factory.getUserDAO();
		}
	}

	/**
	 * To check if the username and password match with the database records
	 */

	public boolean isValidUser(String uname, String pass) {
		User user = userDAO.getUser(uname);
		if (user != null && user.getPass().equals(pass)) {
			return true;
		}
		return false;
	}

	/**
	 * To check if the username and email match with the database records
	 */
	public boolean isValidEmail(String uname, String email) {
		User user = userDAO.getUser(uname);
		if (user != null && user.getEmail().equals(email)) {
			return true;
		}
		return false;
	}

	/**
	 * To get the password for a user
	 */
	public String getPassword(String uname) {
		User user = userDAO.getUser(uname);
		if (user != null) {
			return user.getPass();
		}
		return null;
	}
}
