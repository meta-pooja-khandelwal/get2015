package com.metasocio.facade.commentmanagement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.metasocio.dbhelper.commentmanagement.CommentDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.commentmanagement.Comment;
/***************************************************************************
 * Name: CommentFacade
 * @author Since: 28 November,2015 
 * Description: Facade Layer to Comment on post
 **************************************************************************/
public class CommentFacade {
	/************************************************************************
	 * Name : ShareComment
	 * @param newComment
	 * @param transaction
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 * Description: Save comments on a post in database
	 ***********************************************************************/
	public boolean shareComment(Comment newComment,
			Session session) throws MetaSocioException {
		// Gets the instance of the Dao
		CommentDao iCommentDao=CommentDao.getInstance();
		// Calling method addComment existing in CommentDao class
		return iCommentDao.saveComment(newComment,session);
	}
	/**************************************************************************
	 * Name : retrieveCommentListWithImageByPostID
	 * @param postId
	 * @param transaction
	 * @param session
	 * @return list of comments with images
	 * @throws MetaSocioException
	 * Description:retrieve comments on home page
	 *****************************************************************************/
	public List<Comment> retrieveComments(int postId,
			 Session session) throws MetaSocioException {
		// Gets the Instance of Dao
		CommentDao iCommentDao = CommentDao.getInstance();// Get comments with image in the ArrayList
		List<Comment> comments = new ArrayList<Comment>();
		comments = iCommentDao.retrieveComments(postId,
				session);// Calling retrieveCommentListWithImageByPostID method existing in CommentDao class
		return comments;
	}
	/** ******************************************************************
	 * @param postId
	 * @param session
	 * @throws MetaSocioException
	 * ******************************************************************/
	public void deleteCommentOnPost(int postId, Session session)  throws MetaSocioException {
		CommentDao iCommentDao = CommentDao.getInstance();
		iCommentDao.deleteCommentOnPost(postId,session);;
	}
	
	/** ******************************************************************
	 * @param commentId
	 * @param session
	 * @param userId 
	 * @param updatedAt 
	 * @return
	 * @throws MetaSocioException
	  *******************************************************************/
	public int deleteCommentByCommentId(int commentId, Session session, int userId, Timestamp updatedAt) throws MetaSocioException {
		CommentDao icoCommentDao = CommentDao.getInstance();
		// decrement likes on post
		return icoCommentDao.deleteCommentByCommentId(commentId,session,userId,updatedAt);
	}
	/********************************************************************
	 * @param commentId
	 * @param commentDetails
	 * @param userId 
	 * @param updatedAt 
	 * @param session
	 * @throws MetaSocioException
	 * ******************************************************************/
	public void editCommentByCommentId(int commentId, String commentDetails, int userId, Timestamp updatedAt, Session session)throws MetaSocioException {
		CommentDao icoCommentDao = CommentDao.getInstance();
		// decrement likes on post
		icoCommentDao.editCommentByCommentId(commentId,commentDetails,userId,updatedAt,session);
	}
}
