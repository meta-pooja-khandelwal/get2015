package com.metasocio.service.postmanagement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.postmanagement.PostFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.postmanagement.Post;

/*********************************************************************
 * Description : This PostService class is providing services for post 
 * session opening, session closing, beginning transaction and commit 
 * transactions.Session is opening a connection between Java and 
 * database and transactions is to commit queries in database.
 *******************************************************************/

public class PostService
{
	//logger object
	private static Logger logger = Logger.getLogger(PostService.class);

	//error message
	private static final String MSG = "Exception in PostService";


	/********************************************
	 * Description: Saves post in the database
	 * @param newPost
	 * @throws MetaSocioSystemException
	 ********************************************/
	public void savePost(Post newPost)throws MetaSocioSystemException
	{
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory=null;

		try 
		{
			// Configuration is Called
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			// Facade Layer Is Called
			PostFacade postFacade=new PostFacade();
			// Session Factory Instance is created
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			// Share the Post
			postFacade.savePost(newPost, session);
			transaction.commit();
		} 
		catch (Exception e) 
		{
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
						+ "];error in transaction roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close();
			}
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}

	}

	/***********************************************
	 * Description:retrieve post from the database
	 * @param startIndex
	 * @param maximumResult
	 * @param groupId
	 * @return
	 * @throws MetaSocioSystemException
	 **********************************************/
	public  List<Post> retrievePosts(int startIndex, int maximumResult, int groupId) throws MetaSocioSystemException
	{
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		// List to store  Post With Image
		List<Post> postsList = new ArrayList<Post>();	
		try 
		{
			// facade Layer Is Called
			PostFacade postFacade=new PostFacade();
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			postsList = postFacade.retrievePosts(session,startIndex,maximumResult,groupId);
			transaction.commit();

		}
		catch (Exception e) 
		{
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		// returns the Posts 
		return postsList;
	}

	/***************************************************
	 * Description:method to delete post by its post id
	 * @param postId
	 * @param userId
	 * @param updatedAt
	 * @return
	 * @throws MetaSocioSystemException
	 ****************************************************/
	public  void deletePostByPostId(int postId, int userId, Timestamp updatedAt) throws MetaSocioSystemException
	{
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory =null;
		try 
		{
			// facade Layer Is Called
			PostFacade postFacade=new PostFacade();
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			postFacade.deletePostByPostId(postId,userId, updatedAt, session);
			transaction.commit();

		}
		catch (Exception e) 
		{
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		// returns the Posts

	}

	/*****************************************
	 * Description:method to edit post 
	 * @param postId
	 * @param userId
	 * @param updatedAt
	 * @param postDetails
	 * @return
	 * @throws MetaSocioSystemException
	 ****************************************/
	public  void editPostByPostId(int postId,int userId, Timestamp updatedAt, String postDetails) throws MetaSocioSystemException
	{
		Session session = null;
		Transaction transaction = null;
		SessionFactory sessionFactory=null;
		try 
		{
			// facade Layer Is Called
			PostFacade postFacade=new PostFacade();
			Configuration cfg =ConfigurationFactory.getConfigurationInstance();
			// Session Factory is Called
			sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			 postFacade.editPostByPostId(postId,userId, updatedAt, postDetails, session);
			transaction.commit();

		}
		catch (Exception e) 
		{
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
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}

	}
}
