package com.metasocio.service.followermanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.followermanagement.FollowerFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/***************************************************************************
 * Description : This is service class to manage followers and following, 
 * providing session and beginning transaction.,committing transaction
 * if successful otherwise roll back the transaction 
 **************************************************************************/
public class FollowerService {

	//logger object
	private static Logger logger = Logger.getLogger(FollowerService.class);

	//error message
	private static final String MSG = "Exception in FollowerService";

	/******************************************************************
	 * This method is to add a person to the following list of a user
	 * @param follower : currently logged in user
	 * @throws MetaSocioSystemException
	 ******************************************************************/
	public void addFollowing(User follower) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			FollowerFacade followerFacade=new FollowerFacade();
			followerFacade.addFollowing(follower, session);
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
	}

	/****************************************************************************
	 * This functions is to get the list of person who are following the current
	 * user
	 * @param user
	 * @return
	 * @throws MetaSocioSystemException
	 **************************************************************************/
	public List<User> getFollowers(User user)  throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory=null;
		List<User> followersList;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			FollowerFacade followerFacade=new FollowerFacade();
			followersList=followerFacade.getFollowers(user, session);
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		return followersList;
	}


	/*************************************************************************
	 * This function is used to filter the list of users to find the list of
	 * people who are not followed by a logged in user
	 * @param userId
	 * @param usersList
	 * @return
	 * @throws MetaSocioSystemException
	 *************************************************************************/
	public List<User> getUsersWhomYouAreNotFollowing(int userId,
			List<User> usersList) throws MetaSocioSystemException {
		Session session = null;
		List<User> usersWhomYouAreNotFollowing = null;
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			FollowerFacade followerFacade=new FollowerFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			usersWhomYouAreNotFollowing = followerFacade.getUsersWhomYouAreNotFollowing(userId, usersList, session);
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		return usersWhomYouAreNotFollowing;
	}

	/********************************************************************
	 * This method is to get the list of people whom the current user is
	 * following
	 * @param followerId
	 * @return
	 * @throws MetaSocioSystemException
	 ********************************************************************/
	public List<User> getFollowing(int followerId)  throws MetaSocioSystemException {
		Session session = null;
		List<User> followingList = new ArrayList<User>();
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			FollowerFacade followerFacade =new FollowerFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			followingList = followerFacade.getFollowing(followerId, session);
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		return followingList;
	}

	/************************************************************************
	 * This method is remove the person with following id from the following
	 * list of current user
	 * @param followerId
	 * @param followingId
	 * @return
	 * @throws MetaSocioSystemException
	 ***********************************************************************/
	public void unfollow(int followerId, int followingId) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory=null;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			FollowerFacade followerFacade=new FollowerFacade();
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			followerFacade.unfollow(followerId, followingId, session);
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
	}

}
