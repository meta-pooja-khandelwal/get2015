package com.metasocio.dbhelper.groupmanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.usermanagement.User;

/**************************************************************************
 * Description : This class is directly querying the database regarding groups
 * to get the required information.
 *************************************************************************/
public class GroupDao {

	private static GroupDao iGroupDao; // Declaring static instance of class

	/*******************************************************************
	 * This function is to create and return new instance of GroupDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static GroupDao getInstance() {
			iGroupDao = new GroupDao();
		return iGroupDao; // returning instance
	}

	/********************************************************************
	 * This function is creating new group in database
	 * 
	 * @param group
	 *            : Containing all info, required to create new group
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning group id of newly created group
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 *******************************************************************/
	public int createGroup(Group group, Session session)
			throws MetaSocioException {
		session.save(group); // Query to create group
		// query to get Group id of newly created group
		String hql = "Select g.groupId From Group g WHERE g.groupId =(Select max(groupId)from Group))"; 
		Query query = session.createQuery(hql); // creating query
		// query executed and returning unique result(group id)
		int groupId = (int) query.uniqueResult(); 
		return groupId; // Returning group id of newly created group
	}

	/************************************************************************
	 * This function is to update group information like : a) Updating group
	 * when any user joins this group. b) Updating group when any user lefts
	 * this group. c) Deleting group related informations when admin deletes
	 * group and setting isDelete field to 0.
	 * 
	 * @param group
	 *            : Group to update
	 * @param session
	 *            : Creating a session to process query.
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 ***********************************************************************/
	public void updateGroup(Group group, Session session)
			throws MetaSocioException {
		session.saveOrUpdate(group); // Querying database to update group information
	}

	/***************************************************************************
	 * This function is to get complete information of a particular group on the
	 * basis of group id which is unique
	 * 
	 * @param groupId
	 *            : group id of group whose information is required
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning group
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 **************************************************************************/
	public Group getGroupById(int groupId, Session session)
			throws MetaSocioException {
		Criteria criteria = session.createCriteria(Group.class); // Creating criteria to create query
		criteria.add(Restrictions.eq("groupId", groupId)); // adding group id restriction to criteria
		Group group = new Group(); // creating instance of group
		group = (Group) criteria.uniqueResult(); // getting result in created instance of group
		return group; // Returning complete group information in instance of group
	}

	/**********************************************************************
	 * This function is returning all available groups in database
	 * 
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning List of all available groups
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 *********************************************************************/
	public List<Group> getAllGroups(Session session) throws MetaSocioException {

		String hql = "from Group where isdelete=0"; // writing hql query to get all available groups
		Query query = session.createQuery(hql); // creating query
		@SuppressWarnings("unchecked")
		List<Group> groupsList = query.list(); // Getting list of all available groups in database
		return groupsList; // Returning list of all groups
	}

	/****************************************************************************
	 * This function is to get list of groups in which current user is not
	 * member
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
			List<Group> groupList, Session session) throws MetaSocioException {
		// Creating an instance of list which will contain  group list  in which  user is  not member
		List<Group> groupListInWhichUserIsNotMember = new ArrayList<Group>(); 
		//Creating an instance of list which will contain group id of those groups in which user is member
		List<Integer> groupIdSetOfMember = new ArrayList<Integer>(); 

		// adding group id of those groups to list in which user is member
		for (Group group : user.getGroupsSet()) {
			groupIdSetOfMember.add(group.getGroupId());
		}
		// Adding groups to list in which user is not member
		for (Group group : groupList) {
			if (!(groupIdSetOfMember.contains(group.getGroupId()))) {
				groupListInWhichUserIsNotMember.add(group);
			}
		}
		return groupListInWhichUserIsNotMember; // Returning list of those group in which user is not member
	}

	/******************************************************************************
	 * This function is to get list of users who are not the members of this
	 * group whose group id is provided
	 * 
	 * @param groupId
	 *            : Id of group on which basis list of users is retrieved
	 * @param users
	 *            : List of all available users
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning List of users who are not the member of group
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 *****************************************************************************/
	public List<User> getUsersHavingSameDepartmentWhoAreNotGroupMembers(
			int groupId, List<User> users, Session session)
			throws MetaSocioException {
		List<User> usersWhoAreNotGroupMembers = new ArrayList<User>(); // Creating instance of Users list
		String hql = "from Group g where g.groupId =:groupId"; // writing HQL query to get group of particular group id
		Query query = session.createQuery(hql); // Creating query to get results
		query.setParameter("groupId", groupId); // setting required parameter to query
		Set<User> userSet = new LinkedHashSet<User>(); // Creating set of users

		Group group = (Group) query.uniqueResult(); // Getting unique group from query

		// Checking if group contains any member
		if (!group.getUsersSet().isEmpty()) {
			userSet = group.getUsersSet(); // setting all group members into usersSet

			for (User user : users) {
				if (!userSet.contains(user)) {
					usersWhoAreNotGroupMembers.add(user); // Adding users who are not group members to list
				}
			}
		} else {
			// if group does not contain any member, setting complete user list to list
			usersWhoAreNotGroupMembers = users; 
		}
		return usersWhoAreNotGroupMembers; // Returning List of users who are not the member of group
	}

	/***********************************************************************
	 * This function is to get list of groups in which user is member
	 * 
	 * @param userId
	 *            : User Id of user
	 * @param session
	 *            : Creating a session to process query.
	 * @return : Returning list of groups in which user is member
	 * @throws MetaSocioException
	 *             : Throwing MetaSocioException if any exception occurs
	 **********************************************************************/
	public List<Group> getMyGroups(int userId, Session session)
			throws MetaSocioException {

		List<Group> myGroupList = new ArrayList<Group>(); // Creating instance of Group list
		String hql = "from User where userId=" + userId; // writing HQL query to get group of particular group id
		Query query = session.createQuery(hql); // Creating query to get results
		User user = (User) query.uniqueResult(); // Getting unique user from query
		Set<Group> groupSet = user.getGroupsSet(); // Setting set of groups in which user is member in a groupSet
		Iterator<Group> iterator = groupSet.iterator(); // Creating instance of iterator
		while (iterator.hasNext()) {
			Group group = iterator.next();
			myGroupList.add(group); // Adding groups in which users is member one by one to myGroupList
		}
		return myGroupList; // Returning List of groups in which user is member
	}
}
