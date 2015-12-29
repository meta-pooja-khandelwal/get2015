package com.metasocio.service.groupmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.groupmanagement.GroupFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.usermanagement.User;

/***********************************************************************************
 * Description : This GroupService class is providing services like session opening,
 * session closing, beginning transaction and commit transactions. Session is opening 
 * a connection between Java and database and transactions is to commit queries in
 * database.
 ***********************************************************************************/
public class GroupService {

	//logger object
	private static Logger logger = Logger.getLogger(GroupService.class);

	//error message
	private static final String MSG = "Exception in GroupService";

	/**********************************************************************
	 * This function is to create new group in database
	 * 
	 * @param group
	 *            : Group object containing all information regarding group
	 * @return : Returning group id of newly created group
	 * @throws MetaSocioSystemException
	 *             : Throwing MetaSocioException if any exception occurs
	 **********************************************************************/
	public int createGroup(Group group) throws MetaSocioSystemException {
		Session session = null;
		int groupId = 0;				//To store of newly created group
		SessionFactory sessionFactory = null;
		Transaction transaction = null;

		try {
			GroupFacade groupFacade = new GroupFacade();						//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance(); //Getting instance of XML configuration file
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			groupId = groupFacade.createGroup(group, session);
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
		return groupId;			//Returning group id of newly created group
	}

	/**************************************************************************
	 * This function is calling facade and providing session to create queries
	 * to get information of group whose id is provided
	 * 
	 * @param groupId
	 *            : Id of group whose information is required
	 * @return : Returning group object of group whose id was provided
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 **************************************************************************/
	public Group getGroupById(int groupId) throws MetaSocioSystemException {
		Session session = null;
		Group group = new Group();					//Creating instance of group
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {

			GroupFacade groupFacade = new GroupFacade();			//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();		//Getting instance of XML configuration file
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			group = groupFacade.getGroupById(groupId, session);
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

				System.out.println("error in transactiopn roll back,["
						+ e1.getMessage() + "]");
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
		return group;					//Returning group object whose id was provided
	}

	/**********************************************************************************
	 * This function is calling facade and providing session to query database
	 * to update group information like: a) Updating group when any user joins
	 * this group. b) Updating group when any user lefts this group. c) Deleting
	 * group related informations when ADMIN deletes group and setting isDelete
	 * field to 0.
	 * 
	 * @param group : Group to update
	 * @throws MetaSocioException : Throwing MetaSocioException if any exception occurs
	 ***********************************************************************************/
	public void updateGroup(Group group) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			GroupFacade groupFacade = new GroupFacade();				//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance(); //Getting instance of XML configuration file
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			groupFacade.updateGroup(group, session);
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

	/**********************************************************************************
	 * This function is calling facade and providing session to query database
	 * to return all available groups in database
	 * 
	 * @return : Returning List of all available groups
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 *********************************************************************************/
	public List<Group> getAllGroups() throws MetaSocioSystemException {
		Session session = null;
		List<Group> groupList = new ArrayList<Group>();						//Creating list to store all available groups
		Transaction transaction = null;
		SessionFactory sessionFactory = null;										
		try {
			GroupFacade groupFacade = new GroupFacade();				//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();		//Getting instance of XML configuration file
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			groupList = groupFacade.getAllGroups(session);
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
		return groupList;						//Returning list of all available groups
	}

	/***************************************************************************
	 * This function is calling facade and providing session to query database
	 * to get list of groups in which current user is not member
	 * 
	 * @param user
	 *            : member on whose basis filtration is to be done
	 * @param groupList
	 *            : List of all available groups
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning List of groups in which user is not member
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 ***************************************************************************/
	public List<Group> getGroupsInWhichUserIsNotMember(User user,
			List<Group> groupList) throws MetaSocioSystemException {
		Session session = null;
		List<Group> groupListInWhichUserIsNotMember = new ArrayList<Group>();					//Creating List to store groups in which user is not member
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			GroupFacade groupFacade = new GroupFacade();					//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();			//Getting instance of XML configuration file
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			groupListInWhichUserIsNotMember = groupFacade
					.getGroupsInWhichUserIsNotMember(user, groupList, session);
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
		return groupListInWhichUserIsNotMember;				//Returning List of groups in which user is not member
	}

	/*****************************************************************************
	 * This function is calling facade and providing session to query database
	 * to get list of users who are not the members of this group whose group id
	 * is provided
	 * 
	 * @param department
	 *            : Department of present user
	 * @param groupId
	 *            : Id of group on which basis list of users is retrieved
	 * @param users
	 *            : List of all available users
	 * @return : Returning List of users who are not the member of group and of
	 *         same department of which present user is.
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 ****************************************************************************/
	public List<User> getUsersHavingSameDepartmentWhoAreNotGroupMembers(
			String department, int groupId) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		List<User> usersHavingSameDepartmentWhoAreNotGroupMembers = new ArrayList<User>();			//Creating List to store users who are not the member of group
		try {
			GroupFacade groupFacade = new GroupFacade();					//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();			//Getting instance of XML configuration file
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			usersHavingSameDepartmentWhoAreNotGroupMembers = groupFacade.getUsersHavingSameDepartmentWhoAreNotGroupMembers(
					department, groupId,session);
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
		return usersHavingSameDepartmentWhoAreNotGroupMembers;			//Returning List of users who are not the member of group
	}

	/*****************************************************************************
	 * This function is calling Dao to query database to get list of groups in
	 * which user is member
	 * 
	 * @param userId
	 *            : User Id of user
	 * @return : Returning list of groups in which user is member
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 ****************************************************************************/
	public List<Group> getMyGroups(int userId) throws MetaSocioSystemException {
		Session session = null;
		List<Group> myGroupList = new ArrayList<Group>();			//Creating list to store groups in which user is member
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			GroupFacade groupFacade = new GroupFacade();						//Creating instance of group facade
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();		//Getting instance of XML configuration file
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			myGroupList = groupFacade.getMyGroups(userId, session);
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
		return myGroupList;					//Returning list of groups in which user is member
	}
}
