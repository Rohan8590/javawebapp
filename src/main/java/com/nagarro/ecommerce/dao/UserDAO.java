package com.nagarro.ecommerce.dao;

import com.nagarro.ecommerce.model.User;

/**
 * Interface for the methods required to interact with the user database
 * 
 * @author rohanaggarwal
 *
 */
public interface UserDAO {
	
	/**
	 * Returns the object for a particular user
	 * @param uname The username of the user
	 * @return The object for the user
	 */
	User getUser(String uname);
}
