package com.metasocio.facade.usermanagement;

import java.util.List;

import org.hibernate.Session;

import com.metasocio.dbhelper.usermanagement.UserDao;
import com.metasocio.dbhelper.usermanagement.ValidateUser;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.followermanagement.FollowerFacade;
import com.metasocio.model.usermanagement.User;

public class UserFacade {
	public boolean isEmailExists(String email, Session session)
			throws MetaSocioException {
		boolean isUserExists = false;
		ValidateUser iUser = ValidateUser.getInstance();
		isUserExists = iUser.isEmailExists(email, session);
		return isUserExists;
	}

	public boolean setUserInfo(User user, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		return iUserDao.setUserInfo(user, session);
	}

	public List<User> getUsersHavingSameDepartment(User user, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		List<User> users = iUserDao.getUsersHavingSameDepartment(
				user.getDepartment(), session);
		FollowerFacade iFollowerFacade = new FollowerFacade();
		List<User> userWhomYouAreNotFollowing = iFollowerFacade
				.getUsersWhomYouAreNotFollowing(user.getUserId(), users,
						session);
		return userWhomYouAreNotFollowing;
	}

	public User getUserByEmail(String email, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		User user = iUserDao.getUserByEmail(email, session);
		return user;
	}

	public User getUserById(int followingId, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		User user = iUserDao.getUserById(followingId, session);
		return user;
	}

	public List<User> getAllUsers(Session session) throws MetaSocioException {
		UserDao userDao = UserDao.getInstance();
		List<User> usersList = userDao.getAllUsers(session);

		return usersList;
	}

	public void updateUserInfo(User user, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		iUserDao.updateUserInfo(user, session);

	}

	public void updateUserProfile(String picture, String email, Session session)
			throws MetaSocioException {
		UserDao iUserDao = UserDao.getInstance();
		iUserDao.updateUserProfile(picture, email, session);

	}
}
