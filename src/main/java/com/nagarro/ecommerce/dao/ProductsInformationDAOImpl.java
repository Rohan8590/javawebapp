package com.nagarro.ecommerce.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.nagarro.ecommerce.model.ProductsInformation;

/**
 * Provides the methods that directly interact with the products information
 * database
 * 
 * @author rohanaggarwal
 *
 */
public class ProductsInformationDAOImpl implements ProductsInformationDAO {
	private static SessionsFactory sessionsFactory;
	private static SessionFactory productsSessionFactory;

	/**
	 * Initializes with a session factory from Sessions Factory class if its null
	 */
	public ProductsInformationDAOImpl() {
		if (sessionsFactory == null || productsSessionFactory == null) {
			sessionsFactory = new SessionsFactory();
			productsSessionFactory = sessionsFactory.getSessionFactoryObj();
		}
	}

	/**
	 * Adds a new row in the database
	 */
	public void save(ProductsInformation productsInformation) {
		Session session = productsSessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(productsInformation);
			session.getTransaction().commit();

		} finally {
			closeSession(session);
		}

	}

	/**
	 * To update the data of an existing row
	 */
	public void update(ProductsInformation productsInformation) {
		Session session = productsSessionFactory.openSession();

		try {
			session.beginTransaction();
			session.update(productsInformation);
			session.getTransaction().commit();

		} finally {
			closeSession(session);
		}

	}

	/**
	 * To delete a row with a particular id
	 */
	public void delete(int id) {
		Session session = productsSessionFactory.openSession();
		try {
			session.beginTransaction();
			ProductsInformation productsInformation = getObjectFromId(id);
			session.delete(productsInformation);
			session.getTransaction().commit();
		} finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the object corresponding the an id
	 */
	public ProductsInformation getObjectFromId(int id) {
		Session session = productsSessionFactory.openSession();
		ProductsInformation matchingRow;
		try {
			session.beginTransaction();
			matchingRow = (ProductsInformation) session.get(ProductsInformation.class, id);

		} finally {
			closeSession(session);
		}
		return matchingRow;
	}

	/**
	 * Returns all the product information entered by a user
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ProductsInformation> get(String uname) {
		Session session = productsSessionFactory.openSession();
		List<ProductsInformation> matchingProductsList;
		try {
			Query q = session.createQuery("from ProductsInformation where username=:u");
			q.setParameter("u", uname);

			matchingProductsList = q.list();

		} finally {
			closeSession(session);
		}

		return matchingProductsList;
	}

	/**
	 * Returns the total size of all the images uploaded by the user
	 */
	public long totalUserImageSize(String uname) {
		long totalSize = 0;
		List<ProductsInformation> matchingProductsList = get(uname);
		for (ProductsInformation p : matchingProductsList) {
			totalSize += p.getImage_size();
		}
		return totalSize;
	}

	private void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
	}
}
