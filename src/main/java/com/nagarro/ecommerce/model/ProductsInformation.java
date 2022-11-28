package com.nagarro.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines the structure for the products information table
 * 
 * @author rohanaggarwal
 *
 */
@Entity
public class ProductsInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	private String username;
	private String title;
	private int quantity;
	private int size;
	private String image;
	private long image_size;

	/**
	 * The default constructor
	 */
	public ProductsInformation() {

	}

	/**
	 * Initializes the new products information object with the data passed in the
	 * parameters
	 * 
	 * @param username   The username of the user
	 * @param title      The title of the product
	 * @param quantity   The quantity of the product
	 * @param size       The size of the product
	 * @param image      The image file name of the product
	 * @param image_size The image size
	 */
	public ProductsInformation(String username, String title, int quantity, int size, String image, long image_size) {
		this.username = username;
		this.title = title;
		this.quantity = quantity;
		this.size = size;
		this.image = image;
		this.image_size = image_size;
	}

	/**
	 * Returns the id corresponding to an object
	 * 
	 * @return The id corresponding to an object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id for an object
	 * 
	 * @param id The id to be set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the username of the user
	 * 
	 * @return The username of the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user
	 * 
	 * @param username The username to be set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the title of the product
	 * 
	 * @return The title of the product
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the product
	 * 
	 * @param title The title to be set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the quantity of the product
	 * 
	 * @return The quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns the size of the product
	 * 
	 * @return The size of the product
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the size of the product
	 * 
	 * @param size The size to be set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the image file name
	 * 
	 * @return The image file name
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image file name
	 * 
	 * @param image The image file name to be set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Returns the image size
	 * 
	 * @return The image size
	 */
	public long getImage_size() {
		return image_size;
	}

	/**
	 * Sets the image size
	 * 
	 * @param image_size The image size to be set
	 */
	public void setImage_size(long image_size) {
		this.image_size = image_size;
	}

}
