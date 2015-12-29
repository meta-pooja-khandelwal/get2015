package com.metasocio.service.commentmanagement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.commentmanagement.CommentFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.commentmanagement.Comment;
/***************************************************************************
 * Description : This is service class of manage comments, providing session 
 * and beginning transaction.,committing transaction if successful otherwise 
 * roll back the transaction 
 **************************************************************************/
public class CommentService {

	//logger object
	private static Logger logger = Logger.getLogger(CommentService.class);

	//error message
	private static final String MSG = "Exception in CommentService";

	/*********************************************************************************
	 * Name : shareComment
	 * @param newComment
	 * @throws MetaSocioSystemException
	 * Description : This function is to save comment information in the database
	 *********************************************************************************/
	public void shareComment(Comment newComment) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		try {
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();//getting instance of Configuration
			CommentFacade commentFacade=new CommentFacade();
			sessionFactory = cfg.buildSessionFactory();//building session factory
			session = sessionFactory.openSession();//opening session
			transaction = session.beginTransaction();//begins transaction 
			commentFacade.shareComment(newComment, session);
			transaction.commit();
		}catch (Exception e) {

			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e1);
				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();//closing session
			}
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
	}

	/****************************************************
	 * Name : retrieveComments
	 * @param postId
	 * @return list of comments on post with given id
	 * @throws MetaSocioSystemException
	 * Description : retrieve comments from database
	 ***************************************************/
	public List<Comment> retrieveComments(int postId) throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory=null;
		List<Comment> comments = new ArrayList<Comment>();	// Get comments with image in the ArrayList
		try {
			CommentFacade commentFacade=new CommentFacade();
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();//getting instance of Configuration
			sessionFactory = cfg.buildSessionFactory();//building session factory
			session = sessionFactory.openSession();//opening session
			transaction = session.beginTransaction();//begins transaction 
			comments = commentFacade.retrieveComments(postId,  session);// Calling retrieveCommentListWithImageByPostID method existing in CommentFacade class
			transaction.commit();//	commit transaction
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();//closing session
			}
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		return comments;
	}

	/****************************************************
	 * Name : deleteCommentByCommentId
	 * @param userId 
	 * @param updatedAt 
	 * @throws MetaSocioSystemException
	 * Description : delete comment by id from database
	 ***************************************************/
	public void deleteCommentByCommentId(int commentId, int userId, Timestamp updatedAt)
			throws MetaSocioSystemException {
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory = null;
		try {
			// facade Layer Is Called
			CommentFacade commentFacade = new CommentFacade();
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			commentFacade.deleteCommentByCommentId(commentId, session,userId,updatedAt);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}

	/*****************************************************
	 * Name : editCommentByCommentId
	 * @param commentId
	 * @param commentDetails
	 * @param userId 
	 * @param updatedAt 
	 * @throws MetaSocioSystemException
	 * Description : edit comment by comment Id in database
	 *****************************************************/
	public void editCommentByCommentId(int commentId, String commentDetails, int userId, Timestamp updatedAt)
			throws MetaSocioSystemException {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			// facade Layer Is Called
			CommentFacade commentFacade = new CommentFacade();
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			commentFacade.editCommentByCommentId(commentId, commentDetails,userId,updatedAt,
					session);
			transaction.commit();
		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);
				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
}
