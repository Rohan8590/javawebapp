package com.nagarro.ecommerce.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.nagarro.ecommerce.model.ProductsInformation;
import com.nagarro.ecommerce.model.User;

/**
 * Creates the session factory object
 * 
 * @author rohanaggarwal
 *
 */
public class SessionsFactory {
	private static SessionFactory sessionFactoryObj;

	/**
	 * Initializes a new session factory object if it's null
	 */
	public SessionsFactory() {
		if (sessionFactoryObj == null) {
			setSessionFactoryObj();
		}
	}

	/**
	 * Returns the session factory object
	 * 
	 * @return The session factory object
	 */
	public SessionFactory getSessionFactoryObj() {
		return sessionFactoryObj;
	}

	/**
	 * Sets the session factory object
	 */
	public static void setSessionFactoryObj() {
		Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(ProductsInformation.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactoryObj = config.buildSessionFactory(registry);
	}

}
