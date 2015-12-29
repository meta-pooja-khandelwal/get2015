package com.metasocio.test.followermanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.followermanagement.FollowerDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/*******************************************************************************
 * Description : This class has methods to test the methods of FollowerDao class
 *******************************************************************************/
public class FollowerDaoTest {
	private static User user = null;
	private static FollowerDao followerDao = null;
	private static Session session = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
		followerDao = new FollowerDao();
		Configuration cfg = ConfigurationFactory.getConfigurationInstance();

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/*******************************************************************************
	 * Method to test getUsersWhomYouAreNotFollowing() method existing in
	 * FollowerDao
	 * 
	 * @throws MetaSocioException
	 *******************************************************************************/
	@Test(expected = AssertionError.class)
	public void getUsersWhomYouAreNotFollowingTest() throws MetaSocioException {
		assertNotNull(followerDao.getUsersWhomYouAreNotFollowing(1, null,
				session));
	}

	/***************************************************************
	 * Method to test getFollowers() method existing in FollowerDao
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test(expected = NullPointerException.class)
	public void getFollowersTest() throws MetaSocioException {
		assertNotNull(followerDao.getFollowers(user, null, session));
	}

	/***************************************************************
	 * Method to test unfollow() method existing in FollowerDao
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void unfollowTest() throws MetaSocioException {
		Boolean check = followerDao.unfollow(1, 2, session);
		assertEquals(true, check);
	}

	/***************************************************************
	 * Method to test getFollowing() method existing in FollowerDao
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void getFollowingsTest() throws MetaSocioException {
		assertNotNull(followerDao.getFollowing(1, session));

	}

}
