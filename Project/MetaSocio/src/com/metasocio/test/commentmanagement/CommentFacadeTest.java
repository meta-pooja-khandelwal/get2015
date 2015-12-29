package com.metasocio.test.commentmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.commentmanagement.CommentFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.commentmanagement.Comment;

/*********************************************************************************
 * Description : This class has methods to test the methods of CommentFacade
 * class
 *********************************************************************************/
public class CommentFacadeTest {
	private static CommentFacade commentFacade = null;
	private static Session session = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		commentFacade = new CommentFacade();
		Configuration cfg = ConfigurationFactory.getConfigurationInstance();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/********************************************************************
	 * Method to test retrieveComments() method existing in CommentFacade
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void retrieveCommentsTest() throws MetaSocioException {

		assertNotNull(commentFacade.retrieveComments(1, session));
	}

	/********************************************************************
	 * Method to test shareComment() method existing in CommentFacade
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void shareCommentTest() throws MetaSocioException {
		Comment comment = new Comment();
		assertNotNull(commentFacade.shareComment(comment, session));
	}

	/********************************************************************
	 * Method to test shareComment() method existing in CommentFacade
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void shareCommentTest1() throws MetaSocioException {
		Comment comment = new Comment();
		assertEquals(true, commentFacade.shareComment(comment, session));
	}

	/****************************************************************************
	 * Method to test deleteCommentByCommentId() method existing in
	 * CommentFacade
	 * 
	 * @throws MetaSocioException
	 ****************************************************************************/
	@Test
	public void deleteCommentByCommentIdTest() throws MetaSocioException {
		Date date = new Date();
		Timestamp updatedAt = new Timestamp(date.getTime());
		assertNotNull(commentFacade.deleteCommentByCommentId(13, session, 2,
				updatedAt));
	}

}
