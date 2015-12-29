package com.metasocio.test.followermanagement;

import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.followermanagement.FollowerService;

/*******************************************************************************
 * Description : This class has methods to test the methods of FollowerService
 * class
 *******************************************************************************/
public class FollowerServiceTest {
	private static User user = null;
	private static FollowerService followerService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
		followerService = new FollowerService();
	}

	/********************************************************************
	 * Method to test getUsersWhomYouAreNotFollowing() method existing in
	 * FollowerService
	 * 
	 * @throws MetaSocioException
	 ********************************************************************/
	@Test(expected = AssertionError.class)
	public void getUsersWhomYouAreNotFollowingTest() throws MetaSocioException {
		assertNotNull(followerService.getUsersWhomYouAreNotFollowing(1, null));
	}

	/******************************************************************
	 * Method to test getFollowers() method existing in FollowerService
	 * 
	 * @throws MetaSocioException
	 *******************************************************************/
	@Test
	public void getFollowersTest() throws MetaSocioException {
		assertNotNull(followerService.getFollowers(user));
	}

	/******************************************************************
	 * Method to test getFollowing() method existing in FollowerService
	 * 
	 * @throws MetaSocioException
	 *******************************************************************/
	@Test
	public void getFollowingsTest() throws MetaSocioException {
		assertNotNull(followerService.getFollowing(1));

	}

}
