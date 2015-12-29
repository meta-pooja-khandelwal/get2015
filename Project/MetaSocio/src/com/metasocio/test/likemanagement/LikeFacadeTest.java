package com.metasocio.test.likemanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.likemanagement.LikeFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/******************************************************************************
 * Description : This class has methods to test the methods of LikeFacade class
 ******************************************************************************/
public class LikeFacadeTest {
	private static LikeFacade likeFacade = null;
	private static Configuration cfg = null;
	private static Session session = null;
	private static SessionFactory sessionFactory = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		likeFacade = new LikeFacade();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/*********************************************************************
	 * Method to test hasUSerAlreadyLiked() method existing in LikeFacade
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void hasUSerAlreadyLikedTest() throws MetaSocioException {
		Boolean check = likeFacade.hasUSerAlreadyLiked(1, 1, session);
		assertEquals(false, check);
	}

	/*********************************************************************
	 * Method to test LikesOnPostByPostId() method existing in LikeFacade
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void LikesOnPostByPostIdTest() throws MetaSocioException {
		assertNotNull(likeFacade.LikesOnPostByPostId(7, session));
	}

}
