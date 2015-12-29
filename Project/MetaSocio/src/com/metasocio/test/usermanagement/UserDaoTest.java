package com.metasocio.test.usermanagement;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.usermanagement.UserDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/*******************************************************************************
 * Description : This class has methods to test the methods of UserDao class
 *******************************************************************************/
public class UserDaoTest {

	private static UserDao userDao = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao = new UserDao();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/*************************************************************
	 * Method to test getUserByEmail() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void getUserByEmailTest() throws MetaSocioException {
		assertNotNull(userDao.getUserByEmail("sanjay.nainani@metacube.com",
				session));
	}

	/*************************************************************
	 * Method to test getUserByEmail() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test(expected = AssertionError.class)
	public void getUserByEmailTest1() throws MetaSocioException {
		assertNotNull(userDao.getUserByEmail("nidhi.sharma@metacube.com",
				session));
	}

	/**********************************************************
	 * Method to test getUserById() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 ************************************************************/
	@Test
	public void getUserByIdTest() throws MetaSocioException {
		assertNotNull(userDao.getUserById(1, session));
	}

	/*********************************************************
	 * Method to test getAllUsers() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 ************************************************************/
	@Test
	public void getAllUsersTest() throws MetaSocioException {
		assertNotNull(userDao.getAllUsers(session));
	}

	/*********************************************************
	 * Method to test getUserById() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 ***********************************************************/
	@Test(expected = AssertionError.class)
	public void getUserByIdTest1() throws MetaSocioException {
		assertNotNull(userDao.getUserById(0, session));
	}

	/***********************************************************************
	 * Method to test getUsersHavingSameDepartmentTest() method existing in
	 * UserDao
	 * 
	 * @throws MetaSocioException
	 ************************************************************************/
	@Test
	public void getUsersHavingSameDepartmentTest() throws MetaSocioException {
		assertNotNull(userDao.getUsersHavingSameDepartment("Java", session));
	}

	/*********************************************************
	 * Method to test setUserInfo() method existing in UserDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************/
	@Test
	public void setUserInfoTest() throws MetaSocioException {
		User user = new User();
		assertEquals(true, userDao.setUserInfo(user, session));
	}
}
