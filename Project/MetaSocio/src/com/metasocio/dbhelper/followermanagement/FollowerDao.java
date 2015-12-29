package com.metasocio.dbhelper.followermanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;

/***********************************************************************
 * Description: This class is directly querying the database regarding 
 * followers or following to get the required information.
 ***********************************************************************/
public class FollowerDao {

	private static FollowerDao iFollowerDao;

	/*******************************************************************
	 * This function is to create and return new instance of FollowerDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static FollowerDao getInstance() {
		iFollowerDao = new FollowerDao(); // Creating instance of this class
		return iFollowerDao; // Returning instance of this class
	}

	/*******************************************************************
	 * This function is to add a person to the following list of a user
	 * 
	 * @param follower
	 *            : logged in user
	 * @param session
	 * @throws MetaSocioException
	 ******************************************************************/
	public void addFollowing(User follower, Session session)
			throws MetaSocioException {
		session.saveOrUpdate(follower);
	}

	/*******************************************************************
	 * This functions is to get the list of person who are following the current
	 * user
	 * 
	 * @param user
	 * @param otherUsersList
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ******************************************************************/
	public List<User> getFollowers(User user, List<User> otherUsersList,
			Session session) throws MetaSocioException {
		List<User> followersList = new ArrayList<User>();
		for (User otherUser : otherUsersList) {
			Set<User> otherUserFollowingSet = otherUser.getUsers();
			Iterator<User> iterator = otherUserFollowingSet.iterator();
			while (iterator.hasNext()) {
				User folowingUser = iterator.next();
				if (folowingUser.getUserId() == user.getUserId()) {
					followersList.add(otherUser);
				}
			}
		}
		return followersList;
	}

	/*********************************************************************
	 * This function is used to filter the list of users to find the list of
	 * people who are not followed by a logged in user
	 * 
	 * @param userId
	 * @param usersList
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ********************************************************************/
	public List<User> getUsersWhomYouAreNotFollowing(int userId,
			List<User> usersList, Session session) throws MetaSocioException {
		// Criteria criteria = session.createCriteria(Friends.class);
		List<User> followingList = new ArrayList<User>();
		Query query;
		String hql = "from User u where u.userId =:userId";
		query = session.createQuery(hql);
		query.setInteger("userId", userId);

		User follower = (User) query.uniqueResult();
		Set<User> userSet = new LinkedHashSet<User>();

		userSet = follower.getUsers();

		Set<Integer> userIdSet = new LinkedHashSet<Integer>();
		if (userSet.isEmpty()) {
			followingList = usersList;
		} else {
			Iterator<User> iterator = userSet.iterator();

			// check values
			while (iterator.hasNext()) {
				User user = new User();
				user = (User) iterator.next();
				userIdSet.add(user.getUserId());
			}

			for (User user : usersList) {
				if (!userIdSet.contains(user.getUserId())) {
					followingList.add(user);
				}
			}
		}
		return followingList;

	}

	/*****************************************************************************
	 * This method is to get the list of people whom the current user is
	 * following
	 * 
	 * @param followerId
	 *            : id of logged in user
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 *****************************************************************************/
	public List<User> getFollowing(int followerId, Session session)
			throws MetaSocioException {
		Query query;
		String hql = "from User where userId=" + followerId;
		query = session.createQuery(hql);
		User user = (User) query.uniqueResult();

		List<User> followingList = new ArrayList<User>();
		Set<User> followingSet = user.getUsers();
		Iterator<User> iterator = followingSet.iterator();
		while (iterator.hasNext()) {

			User following = iterator.next();
			followingList.add(following);
		}
		return followingList;
	}

	/******************************************************************************
	 * This method is remove the person with following id from the following
	 * list of current user
	 * 
	 * @param followerId
	 *            : id of logged in user
	 * @param followingId
	 *            : id of a person whom you want to unfollow
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 *****************************************************************************/
	public boolean unfollow(int followerId, int followingId, Session session)
			throws MetaSocioException {
		String hql1 = "from User where userId=" + followerId;
		Query query1 = session.createQuery(hql1);
		User follower = (User) query1.uniqueResult();
		String hql2 = "from User where userId=" + followingId;
		Query query2 = session.createQuery(hql2);
		User following = (User) query2.uniqueResult();
		follower.getUsers().remove(following);
		try {
			session.saveOrUpdate(follower);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
