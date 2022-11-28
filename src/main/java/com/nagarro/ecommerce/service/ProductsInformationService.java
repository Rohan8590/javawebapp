package com.nagarro.ecommerce.service;

import java.util.List;

import com.nagarro.ecommerce.model.ProductsInformation;

/**
 * Interface for methods required for program's product information service
 * 
 * @author rohanaggarwal
 *
 */
public interface ProductsInformationService {

	/**
	 * Inserts a new row of product information
	 * 
	 * @param productsInformation
	 */
	public void insert(ProductsInformation productsInformation);

	/**
	 * Returns the object for a particular id
	 * 
	 * @param id The id of the object to search
	 * @return The object corresponding to the id
	 */
	public ProductsInformation get(int id);

	/**
	 * Deletes the row corresponding to an id
	 * 
	 * @param id The id for which the row is to be deleted
	 */
	public void delete(int id);

	/**
	 * Gets a list of all the products information entered by a user
	 * 
	 * @param uname The username of the user
	 * @return The list of all the product information entered by the user
	 */
	public List<ProductsInformation> getList(String uname);

	/**
	 * Updates the row data for a particular row
	 * 
	 * @param productsInformation The object with updated data
	 */
	public void update(ProductsInformation productsInformation);

	/**
	 * Checks if the user has reached his total image size limit or will reach it on
	 * adding the current image
	 * 
	 * @param uname      The username of the user
	 * @param image_size The size of the current image
	 * @return If the user has reached his total image size limit or will reach it
	 *         on adding the current image
	 */
	public boolean exceedstotalimagesize(String uname, long image_size);

	/**
	 * Checks if a file already exists with a given name
	 * 
	 * @param fileToSearch The name of the file
	 * @return If a file already exists with a given name
	 */
	public boolean fileExists(String fileToSearch);

}
