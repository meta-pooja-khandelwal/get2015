package com.metasocio.test.commentmanagement;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.commentmanagement.CommentDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.commentmanagement.Comment;

/*********************************************************************************
 * Description : This class has methods to test the methods of CommentDao class
 *********************************************************************************/
public class CommentDaoTest {

	private static CommentDao commentDao = null;
	private static Session session = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		commentDao = new CommentDao();
		Configuration cfg = ConfigurationFactory.getConfigurationInstance();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/******************************************************************
	 * Method to test saveCommentTest() method existing in CommentDao
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void saveCommentTest() throws MetaSocioException {
		Comment comment = new Comment();
		assertNotNull(commentDao.saveComment(comment, session));
	}

	/*****************************************************************************
	 * Method to test deleteCommentByCommentIdTest() method existing in
	 * CommentDao
	 * 
	 * @throws MetaSocioException
	 ****************************************************************************/
	@Test
	public void deleteCommentByCommentIdTest() throws MetaSocioException {
		Date date = new Date();
		Timestamp updatedAt = new Timestamp(date.getTime());
		assertNotNull(commentDao.deleteCommentByCommentId(0, session, 1,
				updatedAt));
	}

	/*******************************************************************
	 * Method to test retrieveComments() method existing in CommentDao
	 * 
	 * @throws MetaSocioException
	 *******************************************************************/
	@Test
	public void retrieveCommentsTest() throws MetaSocioException {
		assertNotNull(commentDao.retrieveComments(1, session));
	}

	/************************************************************************
	 * Method to test editCommentByCommentId() method existing in CommentDao
	 * 
	 * @throws MetaSocioException
	 ***********************************************************************/
	@Test
	public void editCommentByCommentIdTest() throws MetaSocioException {
		Date date = new Date();
		Timestamp updatedAt = new Timestamp(date.getTime());
		assertNotNull(commentDao.editCommentByCommentId(1, "comment detail", 7,
				updatedAt, session));
	}
}
