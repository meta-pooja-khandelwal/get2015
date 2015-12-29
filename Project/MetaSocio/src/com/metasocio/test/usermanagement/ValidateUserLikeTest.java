package com.metasocio.test.usermanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.likemanagement.ValidateUserLike;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/*******************************************************************************
 * Description : This class has methods to test the methods of ValidateUserLike
 * class
 *******************************************************************************/
public class ValidateUserLikeTest {

	private static ValidateUserLike likeDao = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		likeDao = new ValidateUserLike();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();

	}

	/*************************************************************
	 * Method to test hasUSerAlreadyLikedTest() method existing in
	 * ValidateUserLike
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void hasUSerAlreadyLikedTest() throws MetaSocioException {
		boolean likes = likeDao.hasUSerAlreadyLiked(2, 7, session);
		assertEquals(false, likes);
	}

	/***************************************************************************
	 * Method to test hasUserLikeDeleted() method existing in ValidateUserLike
	 * 
	 * @throws MetaSocioException
	 ****************************************************************************/
	@Test
	public void hasUserLikeDeletedTest() throws MetaSocioException {
		assertNotNull(likeDao.hasUserLikeDeleted(2, 7, session));
	}

}
