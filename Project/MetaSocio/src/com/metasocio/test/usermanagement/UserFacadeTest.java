package com.metasocio.test.usermanagement;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.usermanagement.UserFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/*******************************************************************************
 * Description : This class has methods to test the methods of UserFacade class
 *******************************************************************************/
public class UserFacadeTest {

	private static UserFacade userFacade = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userFacade = new UserFacade();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/****************************************************************
	 * Method to test getUserByEmail() method existing in UserFacade
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void getUserByEmailTest() throws MetaSocioException {
		assertNotNull(userFacade.getUserByEmail(
				"pooja.khandelwal@metacube.com", session));
	}

	/****************************************************************
	 * Method to test getAllUsersTest() method existing in UserFacade
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void getAllUsersTest() throws MetaSocioException {
		assertNotNull(userFacade.getAllUsers(session));
	}

	/****************************************************************
	 * Method to test getUsersHavingSameDepartment() method existing in
	 * UserFacade
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test(expected = NullPointerException.class)
	public void getUsersHavingSameDepartmentTest() throws MetaSocioException {
		User user = new User();
		assertNotNull(userFacade.getUsersHavingSameDepartment(user, session));
	}

	/*************************************************************
	 * Method to test getUserById() method existing in UserFacade
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void getUserByIdTest() throws MetaSocioException {
		assertNotNull(userFacade.getUserById(1, session));
	}

	/*************************************************************
	 * Method to test isEmailExists() method existing in UserFacade
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void isEmailExistsTest() throws MetaSocioException {
		assertNotNull(userFacade.isEmailExists("pooja.khandelwal@metacube.com",
				session));
	}

}
