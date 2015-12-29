package com.metasocio.test.usermanagement;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.usermanagement.ValidateUser;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/*******************************************************************************
 * Description : This class has methods to test the methods of ValidateUser
 * class
 *******************************************************************************/
public class ValidateUserTest {

	private static ValidateUser validateUser = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		validateUser = new ValidateUser();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();

	}

	/****************************************************************
	 * Method to test isEmailExists() method existing in ValidateUser
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void isEmailExistsTest() throws MetaSocioException {
		boolean check = validateUser.isEmailExists(
				"sanjay.nainani@metacube.com", session);
		assertEquals(false, check);
	}

	/*******************************************************************
	 * Method to test isEmailExists() method existing in ValidateUser
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void isEmailExistsTest1() throws MetaSocioException {
		boolean check = validateUser.isEmailExists("s@metacube.com", session);
		assertEquals(false, check);
	}

	/****************************************************************
	 * Method to test isEmailExists() method existing in ValidateUser
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void isEmailExistsTest2() throws MetaSocioException {
		assertNotNull(validateUser.isEmailExists("chetna.sharma@metacube.com",
				session));
	}

}
