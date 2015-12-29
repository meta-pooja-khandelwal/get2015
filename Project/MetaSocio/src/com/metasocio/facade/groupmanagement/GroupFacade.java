package com.metasocio.facade.groupmanagement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.metasocio.dbhelper.groupmanagement.GroupDao;
import com.metasocio.dbhelper.usermanagement.UserDao;
import com.metasocio.exception.MetaSocioException;

import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.usermanagement.User;
/**
 * @author 
 * Since : 6 December, 2015
 * Description : This GroupFacade class is just an outward appearance of Database helper classes, providing a separation 
 * 				 between service and dbHelper classes for Group Management, calling functions of dbHelper.
 */
public class GroupFacade {

	/**
	 * This function is to create new group in database
	 * @param group : Group object containing all information regarding group
	 * @param session : Creating a session to process 
	 *					query.
	 * @return : Returning group id of newly created group
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public int createGroup(Group group, Session session)
			throws MetaSocioException {
		GroupDao iGroupDao = GroupDao.getInstance();			//Getting instance of 'GroupDao' class	
		int groupId = iGroupDao.createGroup(group, session);	//Querying database to create new group in database
		return groupId;									//Returning group id of newly created group
	}

	/**
	 * This function is calling Dao to query database to get information of group whose id is provided
	 * @param groupId : Id of group whose information is required
	 * @param session : Creating a session to process 
	 *					query.
	 * @return : Returning group object of group whose id was provided
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public Group getGroupById(int groupId, Session session)
			throws MetaSocioException {
		GroupDao iGroupDao = GroupDao.getInstance();				//Getting instance of 'GroupDao' class
		Group group = iGroupDao.getGroupById(groupId, session);		// calling Dao to query database to get information of group whose id is provided
		return group;												//Returning group object
	}

	/**
	 * This function is calling Dao to query database to update group information like:
	 * a) Updating group when any user joins this group.
	 * b) Updating group when any user lefts this group.
	 * c) Deleting group related informations when admin deletes group and setting isDelete field to 0.
	 * @param group : Group to update
	 * @param session : Creating a session to process 
	 *					query.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public void updateGroup(Group group, Session session) throws MetaSocioException {
		GroupDao groupDao = GroupDao.getInstance();						//Getting instance of 'GroupDao' class
		groupDao.updateGroup(group, session);								// calling Dao to query database to update group information
	}

	/**
	 * This function is calling Dao to query database to return all available groups in database
	 * @param session : Creating a session to process 
	 *					query.
	 * @return : Returning List of all available groups
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public List<Group> getAllGroups(Session session) throws MetaSocioException {
		GroupDao iGroupDao = GroupDao.getInstance();								//Getting instance of 'GroupDao' class
		List<Group> groupList = iGroupDao.getAllGroups(session);					// calling Dao to get all available groups in database
		return groupList;														//Returning list of all available groups
	}
	
	/**
	 * This function is calling Dao to query database to get list of groups in which current user is not member
	 * @param user : member on whose basis filtration is to be done
	 * @param groupList : List of all available groups
	 * @param session : Creating a session to process 
	 *					query.
	 * @return : Returning List of groups in which user is not member
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public List<Group> getGroupsInWhichUserIsNotMember(User user, List<Group> groupList,
			Session session) throws MetaSocioException {
		// CRUD iCrud = CRUD.getInstance();
		GroupDao iGroupDao = GroupDao.getInstance();						//Getting instance of 'GroupDao' class
		List<Group> groupListInWhichUserIsNotMember = iGroupDao.getGroupsInWhichUserIsNotMember(user,
				groupList, session);						//calling Dao to get list of groups in which current user is not member
		return groupListInWhichUserIsNotMember;				//Returning List of groups in which user is not member
	}

	/**
	 * This function is calling Dao to query database to get list of users who are not the members of this group whose group id is provided
	 * @param department : Department of present user
	 * @param groupId : Id of group on which basis list of users is retrieved
	 * @param users : List of all available users
	 * @return : Returning List of users who are not the member of group and of same department of which present user is.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public List<User> getUsersHavingSameDepartmentWhoAreNotGroupMembers(String department,
			int groupId, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();							//Getting instance of 'UserDao' class
		GroupDao iGroupDao = GroupDao.getInstance();						//Getting instance of 'GroupDao' class
		List<User> users = iUserDao.getUsersHavingSameDepartment(department,		
				session);								//calling Dao to get list of users who are not the members of this group whose group id is provided
		List<User> usersHavingSameDepartment = iGroupDao
				.getUsersHavingSameDepartmentWhoAreNotGroupMembers(groupId, users, session);
		return usersHavingSameDepartment;					//Returning List of users who are not the member of group
	}

	
	/**
	 * This function is calling Dao to query database to get list of groups in which user is member
	 * @param userId : User Id of user
	 * @param session : Creating a session to process 
	 *					query.
	 * @return : Returning list of groups in which user is member
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 */
	public List<Group> getMyGroups(int userId, Session session)
			throws MetaSocioException {
		GroupDao iGroupDao = GroupDao.getInstance();					//Getting instance of 'GroupDao' class
		List<Group> myGroupList = iGroupDao.getMyGroups(userId, session);	//calling Dao to query database to get list of groups in which user is member			
		return myGroupList;					//Returning list of groups in which user is member
	}
}
