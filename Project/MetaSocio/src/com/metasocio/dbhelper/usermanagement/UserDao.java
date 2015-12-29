package com.metasocio.dbhelper.usermanagement;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;

/****************************************************************************
 * Description: This class is directly querying the database regarding user to
 * get the required information.
 ****************************************************************************/
public class UserDao {
	private static UserDao iUserDao;

	/*******************************************************************
	 * This function is to create and return new instance of UserDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static UserDao getInstance() {
		iUserDao = new UserDao();
		return iUserDao;
	}

	/*******************************************************************
	 * This method is use to save the user information in the database
	 * 
	 * @param user
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 *******************************************************************/
	public boolean setUserInfo(User user, Session session) throws MetaSocioException {
		try {
			session.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/****************************************************************
	 * This method is to find the list of person who have the
	 * department same as current user
	 * @param department
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ****************************************************************/
	@SuppressWarnings("unchecked")
	public List<User> getUsersHavingSameDepartment(String department,
			Session session) throws MetaSocioException {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("department", department));
		List<User> usersList = new ArrayList<User>();
		usersList = criteria.list();
		return usersList;
	}

	/***********************************
	 * Method to get user by email id
	 * @param email
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 **********************************/
	public User getUserByEmail(String email, Session session)
			throws MetaSocioException {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));

		User user = (User) criteria.uniqueResult();
		return user;
	}

	/*********************************
	 * Method to get user by  id
	 * @param followingId
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ********************************/
	public User getUserById(int followingId, Session session)
			throws MetaSocioException {
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userId", followingId));
		User user = new User();
		user = (User) criteria.uniqueResult();
		return user;
	}

	/*********************************************
	 * Method to get list of all META-SOCIO users
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ********************************************/
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(Session session) throws MetaSocioException {
		List<User> usersList = new ArrayList<User>();
		usersList = session.createQuery("FROM User").list();
		return usersList;
	}

	/*********************************************************
	 * Method to update the user information in the database
	 * @param user
	 * @param session
	 * @throws MetaSocioException
	 ********************************************************/
	public void updateUserInfo(User user, Session session)
			throws MetaSocioException {
		session.update(user);

	}

	/***********************************************************
	 * Method to update the user profile picture in the database
	 * @param picture
	 * @param email
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 **********************************************************/
	public boolean updateUserProfile(String picture, String email,
			Session session) throws MetaSocioException {
		Query updateUserProfile = session
				.createQuery("UPDATE User u SET u.picture =:picture WHERE u.email =:email");
		updateUserProfile.setString("picture", picture);
		updateUserProfile.setString("email", email);
		updateUserProfile.executeUpdate();

		return true;

	}
}
