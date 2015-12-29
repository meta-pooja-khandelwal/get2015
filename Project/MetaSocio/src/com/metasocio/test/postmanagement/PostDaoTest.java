package com.metasocio.test.postmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.postmanagement.PostDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/*************************************************************************
 * Description : This class has methods to test the methods of PostDao class
 *************************************************************************/
public class PostDaoTest {

	private static PostDao postDao;
	private static Configuration cfg;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		postDao = new PostDao();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/*********************************************************************
	 * Method to test decrementLikesOnPost() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void decrementLikesOnPostTest() throws MetaSocioException {
		assertNotNull(postDao.decrementLikesOnPost(1, session));
	}

	/*********************************************************************
	 * Method to test deletePostByPostId() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void deletePostByPostIdTest() throws MetaSocioException {
		Date date = new Date();
		assertNotNull(postDao.deletePostByPostId(1, 1,
				new Timestamp(date.getTime()), session));
	}

	/*********************************************************************
	 * Method to test editPostByPostId() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void editPostByPostIdTest() throws MetaSocioException {
		Date date = new Date();
		assertNotNull(postDao.editPostByPostId(3, 1,
				new Timestamp(date.getTime()), "hello nidhi....", session));
	}

	/*********************************************************************
	 * Method to test incrementLikesOnPost() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void incrementLikesOnPostTest() throws MetaSocioException {
		assertNotNull(postDao.incrementLikesOnPost(1, session));
	}

	/*********************************************************************
	 * Method to test incrementLikesOnPost() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void incrementLikesOnPostTest1() throws MetaSocioException {
		int value = postDao.incrementLikesOnPost(1, session);
		assertEquals(1, value);
	}

	/*********************************************************************
	 * Method to test deletePostByPostId() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void deletePostByPostIdTest1() throws MetaSocioException {
		Date date = new Date();
		int value = postDao.deletePostByPostId(1, 1,
				new Timestamp(date.getTime()), session);
		assertEquals(0, value);
	}

	/*********************************************************************
	 * Method to test retrievePosts() method existing in PostDao
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void retrievePostsTest() throws MetaSocioException {
		assertNotNull(postDao.retrievePosts(session, 1, 2, 0));
	}
}
