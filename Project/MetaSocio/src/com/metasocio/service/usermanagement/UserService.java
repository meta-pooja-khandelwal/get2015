package com.metasocio.service.usermanagement;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.usermanagement.UserFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/**********************************************************************************
 * Description : This UserService class is providing services like session opening, 
 * session closing, beginning transaction and commit transactions.Session is opening 
 * a connection between Java and database and transactions is to commit queries related 
 * to user in database.
 ***********************************************************************************/
public class UserService {

	//logger object
	private static Logger logger = Logger.getLogger(UserService.class);

	//error message
	private static final String MSG = "Exception in UserService";

	/*******************************************************************************
	 * Method to check whether the user with given email id exists in database or not
	 * @param email
	 * @return
	 * @throws MetaSocioSystemException
	 ********************************************************************************/
	public boolean isEmailExists(String email) throws MetaSocioSystemException {
		Session session = null;
		boolean isUserExists = false;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;

		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			isUserExists = userFacade.isEmailExists(email, session);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}

		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		return isUserExists;
	}

	/*******************************************************************
	 * This method is use to save the user information in the database
	 * @param user
	 * @return
	 * @throws MetaSocioSystemException
	 * ***************************************************************/
	public boolean setUserInfo(User user) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		boolean check;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			check = userFacade.setUserInfo(user, session);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		return check;

	}

	/**********************************************************
	 * This method is to find the list of person who have the
	 * department same as current user 
	 * @param user
	 * @return
	 * @throws MetaSocioSystemException
	 ***********************************************************/
	public List<User> getUsersHavingSameDepartment(User user)
			throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		List<User> usersOfSameDepartment;
		SessionFactory sessionFactory = null;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			usersOfSameDepartment = userFacade.getUsersHavingSameDepartment(
					user, session);
			transaction.commit();

		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}

		return usersOfSameDepartment;
	}

	/**************************************
	 * Method to get user by email id
	 * @param email
	 * @return
	 * @throws MetaSocioSystemException
	 ***************************************/
	public User getUserByEmail(String email) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		User user;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();

			sessionFactory = cfg.buildSessionFactory();

			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			user = userFacade.getUserByEmail(email, session);
			transaction.commit();

		} catch (Exception e) {

			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		return user;
	}

	/**************************************
	 * Method to get user by  id
	 * @param followingId
	 * @return
	 * @throws MetaSocioSystemException
	 **************************************/
	public User getUserById(int followingId) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		User user;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			user = userFacade.getUserById(followingId, session);
			transaction.commit();
		} catch (Exception e) {

			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
		return user;
	}

	/********************************************************
	 * Method to update the user information in the database
	 * @param user
	 * @throws MetaSocioSystemException
	 ********************************************************/
	public void updateUserInfo(User user) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			userFacade.updateUserInfo(user, session);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	/*************************************************************
	 * Method to update the user profile picture in the database
	 * @param picture
	 * @param email
	 * @throws MetaSocioSystemException
	 **************************************************************/
	public void updateUserProfile(String picture, String email) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			UserFacade userFacade = new UserFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			userFacade.updateUserProfile(picture,email, session);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
}
