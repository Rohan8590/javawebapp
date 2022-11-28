package com.nagarro.ecommerce.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nagarro.ecommerce.model.User;

/**
 * Provides the methods that directly interact with the user database
 * 
 * @author rohanaggarwal
 *
 */
public class UserDAOImpl implements UserDAO {
	private static SessionsFactory sessionsFactory;
	private static SessionFactory userSessionFactory;

	/**
	 * Initializes with a session factory from Sessions Factory class if its null
	 */
	public UserDAOImpl() {
		if (sessionsFactory == null || userSessionFactory == null) {
			sessionsFactory = new SessionsFactory();
			userSessionFactory = sessionsFactory.getSessionFactoryObj();
		}
	}

	/**
	 * Returns the object for a particuar user
	 */
	public User getUser(String uname) {
		Session session = userSessionFactory.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, new String(uname));
		session.getTransaction().commit();
		session.close();
		return user;
	}

}
