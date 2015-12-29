package com.metasocio.facade.followermanagement;

import java.util.List;

import org.hibernate.Session;

import com.metasocio.dbhelper.followermanagement.FollowerDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.usermanagement.UserFacade;
import com.metasocio.model.usermanagement.User;

public class FollowerFacade {

	public void addFollowing(User follower, Session session)
			throws MetaSocioException {
		// CRUD iCrud = CRUD.getInstance();
		/* FriendDao iFriendDao=FriendDao.getInstance(); */
		FollowerDao iFollowerDao = new FollowerDao();
		iFollowerDao.addFollowing(follower, session);
	}

	public List<User> getFollowers(User user, Session session)
			throws MetaSocioException {
		UserFacade userFacade = new UserFacade();
		FollowerDao followerDao = FollowerDao.getInstance();
		List<User> usersList = userFacade.getAllUsers(session);
		List<User> followersList = followerDao.getFollowers(user,
				usersList, session);
		return followersList;
	}
	

	public List<User> getUsersWhomYouAreNotFollowing(int userId,
			List<User> usersList, Session session) throws MetaSocioException {
		// CRUD iCrud = CRUD.getInstance();
		FollowerDao ifFollowerDao = FollowerDao.getInstance();
		List<User> usersWhomYouAreNotFollowing = ifFollowerDao
				.getUsersWhomYouAreNotFollowing(userId, usersList, session);

		return usersWhomYouAreNotFollowing;
	}

	public List<User> getFollowing(int followerId, Session session)
			throws MetaSocioException {
		FollowerDao followerDao = new FollowerDao();
		List<User> followingList = followerDao.getFollowing(followerId,
				session);

		return followingList;
	}

	public boolean unfollow(int followerId, int followingId, Session session)
			throws MetaSocioException {
		// CRUD iCrud = CRUD.getInstance();
		FollowerDao followerDao = FollowerDao.getInstance();
		return followerDao.unfollow(followerId, followingId, session);
	}

}
