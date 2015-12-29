package com.metasocio.test.postmanagement;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.postmanagement.PostFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/*******************************************************************************
 * Description : This class has methods to test the methods of PostFacade class
 *******************************************************************************/
public class PostFacadeTest {

	private static PostFacade postFacade = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		postFacade = new PostFacade();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/****************************************************************
	 * Method to test retrievePosts() method existing in PostFacade
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void retrievePostsTest() throws MetaSocioException {
		assertNotNull(postFacade.retrievePosts(session, 0, 2, 0));
	}

	/*********************************************************************
	 * Method to test deletePostByPostId() method existing in PostFacade
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void deletePostByPostIdTest() throws MetaSocioException {
		Date date = new Date();
		assertNotNull(postFacade.deletePostByPostId(1, 2,
				new Timestamp(date.getTime()), session));
	}

	/******************************************************************
	 * Method to test editPostByPostId() method existing in PostFacade
	 * 
	 * @throws MetaSocioException
	 *******************************************************************/
	@Test
	public void editPostByPostIdTest() throws MetaSocioException {
		Date date = new Date();
		assertNotNull(postFacade.editPostByPostId(1, 2,
				new Timestamp(date.getTime()), "postDetails", session));
	}

	/*********************************************************************
	 * Method to test decrementLikesOnPost() method existing in PostFacade
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void decrementLikesOnPostTest() throws MetaSocioException {
		assertNotNull(postFacade.decrementLikesOnPost(1, session));
	}

	/*********************************************************************
	 * Method to test incrementLikesOnPost() method existing in PostFacade
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void incrementLikesOnPostTest() throws MetaSocioException {
		assertNotNull(postFacade.incrementLikesOnPost(1, session));
	}

}
