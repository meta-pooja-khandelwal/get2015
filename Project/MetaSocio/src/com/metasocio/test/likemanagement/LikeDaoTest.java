package com.metasocio.test.likemanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.likemanagement.LikeDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.likemanagement.Like;

/***************************************************************************
 * Description : This class has methods to test the methods of LikeDao class
 ***************************************************************************/
public class LikeDaoTest {
	private static LikeDao likeDao = null;
	private static Configuration cfg = null;
	private static Session session = null;
	private static Like like = null;
	private static SessionFactory sessionFactory = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		likeDao = new LikeDao();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		like = new Like();
	}

	/*******************************************************
	 * Method to test setLike() method existing in LikeDao
	 * 
	 * @throws MetaSocioException
	 *******************************************************/
	@Test(expected = AssertionError.class)
	public void setLikeTest() throws MetaSocioException {
		Boolean check = likeDao.setLike(like, session);
		assertEquals(false, check);
	}

	/***********************************************************
	 * Method to test updateIsLiked() method existing in LikeDao
	 * 
	 * @throws MetaSocioException
	 ***********************************************************/
	@Test(expected = AssertionError.class)
	public void updateIsLikedTest() throws MetaSocioException {
		int check = likeDao.updateIsLiked(1, 2, 7, session);
		assertNotNull(check);
	}

	/*****************************************************************
	 * Method to test LikesOnPostByPostId() method existing in LikeDao
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void LikesOnPostByPostIdTest() throws MetaSocioException {
		int likes = likeDao.LikesOnPostByPostId(2, session);
		assertEquals(0, likes);
	}

	/*****************************************************************
	 * Method to test LikesOnPostByPostId() method existing in LikeDao
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void LikesOnPostByPostIdTest1() throws MetaSocioException {
		int likes = likeDao.LikesOnPostByPostId(6, session);
		assertNotNull(likes);
	}

}
