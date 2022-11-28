package com.nagarro.ecommerce.dao;

/**
 * Creates the DAO class objects for the controller
 * 
 * @author rohanaggarwal
 *
 */
public class Factory {
	private static UserDAO userDAO;
	private static ProductsInformationDAO productsInformationDAO;

	/**
	 * Initializes the DAO references with an object of the corresponding classes if
	 * they're null
	 */
	public Factory() {
		if (userDAO == null) {
			setUserDAO();
		}
		if (productsInformationDAO == null) {
			setProductsInformationDAO();
		}
	}

	/**
	 * Returns the user DAO object
	 * 
	 * @return The user DAO object
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Sets the user DAO object
	 */
	public void setUserDAO() {
		userDAO = new UserDAOImpl();
	}

	/**
	 * Returns the products information DAO object
	 * 
	 * @return The products information DAO object
	 */
	public ProductsInformationDAO getProductsInformationDAO() {
		return productsInformationDAO;
	}

	/**
	 * Sets the products information DAO object
	 */
	public void setProductsInformationDAO() {
		productsInformationDAO = new ProductsInformationDAOImpl();
	}

}