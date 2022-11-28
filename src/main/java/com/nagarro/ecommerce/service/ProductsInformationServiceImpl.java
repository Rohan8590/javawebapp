package com.nagarro.ecommerce.service;

import java.io.File;
import java.util.List;

import com.nagarro.ecommerce.constants.EcommerceConstants;
import com.nagarro.ecommerce.dao.Factory;
import com.nagarro.ecommerce.dao.ProductsInformationDAO;
import com.nagarro.ecommerce.model.ProductsInformation;

/**
 * Provides methods for the products information service of the program
 * 
 * @author rohanaggarwal
 *
 */
public class ProductsInformationServiceImpl implements ProductsInformationService {
	private Factory factory;
	private ProductsInformationDAO productsInformationDAO;

	/**
	 * Initializes the productsInformationDAO with an object from factory if it's
	 * null
	 */
	public ProductsInformationServiceImpl() {
		if (factory == null) {
			factory = new Factory();
		}
		if (productsInformationDAO == null) {
			productsInformationDAO = factory.getProductsInformationDAO();
		}
	}

	/**
	 * Inserts a new row of product information
	 */
	public void insert(ProductsInformation productsInformation) {
		productsInformationDAO.save(productsInformation);
	}

	/**
	 * Returns the object for a particular id
	 */
	public ProductsInformation get(int id) {
		return productsInformationDAO.getObjectFromId(id);
	}

	/**
	 * Deletes the row corresponding to an id
	 */
	public void delete(int id) {
		productsInformationDAO.delete(id);
	}

	/**
	 * Gets a list of all the products information entered by a user
	 */
	public List<ProductsInformation> getList(String uname) {
		return productsInformationDAO.get(uname);
	}

	/**
	 * Updates the row data for a particular row
	 */
	public void update(ProductsInformation productsInformation) {
		productsInformationDAO.update(productsInformation);
	}

	/**
	 * Checks if the user has reached his total image size limit or will reach it on
	 * adding the current image
	 */
	public boolean exceedstotalimagesize(String uname, long image_size) {
		return productsInformationDAO.totalUserImageSize(uname) + image_size > EcommerceConstants.TOTAL_IMAGE_SIZE_LIMIT;
	}

	/**
	 * Checks if a file already exists with a given name
	 */
	public boolean fileExists(String fileToSearch) {
		boolean isPresent = false;
		File dir = new File(EcommerceConstants.UPLOAD_DIRECTORY);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().equals(fileToSearch)) {
					isPresent = true;
					break;
				}
			}
		}
		return isPresent;
	}

}
