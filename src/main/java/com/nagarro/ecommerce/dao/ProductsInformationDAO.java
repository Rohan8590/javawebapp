package com.nagarro.ecommerce.dao;

import java.util.List;

import com.nagarro.ecommerce.model.ProductsInformation;

/**
 * Interface for the methods required to interact with the products information
 * database
 * 
 * @author rohanaggarwal
 *
 */
public interface ProductsInformationDAO {

	/**
	 * Adds a new row in the database
	 * 
	 * @param productsInformation The object of the row to be added
	 */
	public void save(ProductsInformation productsInformation);

	/**
	 * To update the data of an existing row
	 * 
	 * @param productsInformation The object with updated data
	 */
	public void update(ProductsInformation productsInformation);

	/**
	 * To delete a row with a particular id
	 * 
	 * @param id The id corresponding to which the row is to be deleted
	 */
	public void delete(int id);

	/**
	 * Returns the object corresponding the an id
	 * 
	 * @param id The if for which the object is needed
	 * @return The object corresponding the an id
	 */
	public ProductsInformation getObjectFromId(int id);

	/**
	 * Returns all the product information entered by a user
	 * 
	 * @param uname The username of the user
	 * @return All the product information entered by a user
	 */
	public List<ProductsInformation> get(String uname);

	/**
	 * Returns the total size of all the images uploaded by the user
	 * 
	 * @param uname The username of the user
	 * @return The total size of all the images uploaded by the user
	 */
	public long totalUserImageSize(String uname);
}
