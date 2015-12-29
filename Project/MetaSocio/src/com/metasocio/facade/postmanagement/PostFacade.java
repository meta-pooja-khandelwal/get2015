package com.metasocio.facade.postmanagement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.metasocio.dbhelper.postmanagement.PostDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.commentmanagement.CommentFacade;
import com.metasocio.facade.likemanagement.LikeFacade;
import com.metasocio.model.postmanagement.Post;
/**
 * Name: PostFacade
 * @author Anurag
 * Since: 28 November,2015
 * Description: Facade Layer to Share post
 */
public class PostFacade 
{
	/**
	 * Name: SharePOst
	 * @param newPost
	 * @param transaction
	 * @param session
	 * @throws MetaSocioException
	 * Description: Shares post on the home Page
	 **/
	public void savePost(Post newPost, Session session)
			throws MetaSocioException 
	{
		// Gets the instance of the Dao
		PostDao iPostDao = PostDao.getInstance();
		// Adds the post And calls the addpost
		iPostDao.savePost(newPost, session);
	}
	/**
	 * Name:retrievePostWithImageOnHome
	 * @param transaction
	 * @param session
	 * @param maximumResult 
	 * @param startIndex 
	 * @param groupId 
	 * @return
	 * @throws MetaSocioException
	 * Description:retrieve post from the Session
	 **/
	public List<Post> retrievePosts(Session session, int startIndex, int maximumResult, int groupId) throws MetaSocioException 
	{
		// Gets the Instance of DAO 
		PostDao iPostDao = PostDao.getInstance();
		// Gets the ArrayList
		List<Post> postsWithImage = new ArrayList<Post>();
		postsWithImage = iPostDao.retrievePosts(session,startIndex,maximumResult,groupId);
		// Returns posts with image
		return postsWithImage;
	}
	/**
	 * Name: incrementLikesOn Post
	 * @param postId
	 * @param transaction
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 * Description: Increments like on post and sends the link to Dao
	 */
	public int incrementLikesOnPost(int postId,
			Session session) throws MetaSocioException
	{
		// Gets the Instance 
		PostDao postDao = PostDao.getInstance();
		//dao is called
		return postDao.incrementLikesOnPost(postId,session);
	}
	/**
	 * Name:decrementLikesOnPost
	 * @param postId
	 * @param transaction
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 * Description:decrements likes on post
	 */
	public int decrementLikesOnPost(int postId,Session session)  throws MetaSocioException
	{
		// Gets the Instance
		PostDao postDao = PostDao.getInstance();
		// decrement likes on post
	   return postDao.decrementLikesOnPost(postId,session);
	}

	public int deletePostByPostId(int postId, int userId, Timestamp updatedAt, Session session) throws MetaSocioException {
		PostDao iPostDao = PostDao.getInstance();
		// decrement likes on post
		int check =  iPostDao.deletePostByPostId(postId,userId,updatedAt,session);
	    CommentFacade iCommentFacade=new CommentFacade();
	    iCommentFacade.deleteCommentOnPost(postId,session);
	    LikeFacade iLikeFacade=new LikeFacade();
	    iLikeFacade.deleteLikeOnPost(postId,session);
		return check;
	}
	public int editPostByPostId(int postId, int userId, Timestamp updatedAt, String postDetails, Session session) throws MetaSocioException {
		PostDao iPostDao = PostDao.getInstance();
		// decrement likes on post
	   return iPostDao.editPostByPostId(postId,userId,updatedAt, postDetails,session);
	}
}
