package com.metasocio.dbhelper.postmanagement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;
/******************************************************************
 * Description : This class is directly querying the database 
 * regarding the post to get the required information.
 *****************************************************************/
public class PostDao 
{
	private static PostDao iPostDao;
	
	/*******************************************************************
	 * This function is to create and return new instance of PostDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static PostDao getInstance() 
	{
			iPostDao = new PostDao();
		return iPostDao;
	}
	/***************************************************
	 * This function is to save post in the database
	 * @param newPost: post to be updated
	 * @param session: session to be updated
	 * @throws MetaSocioException
	 **************************************************/
	public void savePost(Post newPost,Session session) throws MetaSocioException
	{
		// save the data to the database
		session.saveOrUpdate(newPost);
		
	}
	/********************************
	 * Method to retrieve post 
	 * @param session
	 * @param maximumResult 
	 * @param startIndex 
	 * @param groupId 
	 * @return
	 * @throws MetaSocioException
	 * Description: retrieve image 
	 *******************************/
	public List<Post> retrievePosts(Session session, int startIndex, int maximumResult, int groupId) throws MetaSocioException
	{
		// Query with session to update
		Query query = session.createQuery("select pm.postId, pm.postDetails, pm.groupId, pm.datePosted, pm.likes,"
				+ "pm.updatedAt, pm.createdBy, pm.isDelete, pm.user from Post pm WHERE pm.isDelete=:isdelete and pm.groupId=:groupId order by pm.datePosted desc ");
		// getting the object with rows 
		query.setInteger("isdelete", 0);
		query.setInteger("groupId", groupId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maximumResult);
		@SuppressWarnings("unchecked")
		List<Object[]> rows=query.list();
		// getting the post with image
		  List<Post> postsWithImage = new ArrayList<Post>();
		for (Object[] row: rows)
		{
			// sets the data to the post table
			Post post=new Post();
		    post.setPostId((int) row[0]);
		    post.setPostDetails((String)row[1]);
		    post.setGroupId((int)row[2]);
		    post.setDatePosted((Timestamp) row[3]);
		    post.setLikes((int) row[4]);
		    post.setUpdatedAt( (Timestamp) row[5]);
		    post.setCreatedBy((String) row[6]);
		    post.setIsDelete((int) row[7]);
		    post.setUser((User) row[8]);
		    postsWithImage.add(post);
		}
		// returns the list with post image
		return postsWithImage;
	}
	/**********************************************       
	 * Method to increment no. of likes on post
	 * @param postId
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 * Description: increments the likes on post
	 **********************************************/
	public int incrementLikesOnPost(int postId,Session session) throws MetaSocioException
	{ 
		// Update like on post
		Query updateLikesOnPost = session.createQuery("UPDATE Post as pm SET pm.likes = pm.likes + 1 WHERE pm.postId = "+postId+"");
		return updateLikesOnPost.executeUpdate();
	}
	/****************************************************
	 * This method is to decrement no. of likes on post
	 * @param postId
	 * @param transaction
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 ****************************************************/
	public int decrementLikesOnPost(int postId,Session session)  throws MetaSocioException 
	{
		// Decrement the likes on post
		Query updateNoOfLikes = session.createQuery("UPDATE Post as pm SET pm.likes = pm.likes - 1 WHERE pm.postId = "+postId+"");
		return updateNoOfLikes.executeUpdate();
	}
	public int deletePostByPostId(int postId, int userId, Timestamp updatedAt, Session session) throws MetaSocioException  {
		Query updateIsDelete = session.createQuery("UPDATE Post as p SET p.isDelete ="+ 1 +",p.updatedAt = '"+updatedAt+"' WHERE p.postId = "+postId+" AND p.user.userId="+userId+""); 		//Preparing query to update required field
		return updateIsDelete.executeUpdate();	
	}
	public int editPostByPostId(int postId, int userId, Timestamp updatedAt, String postDetails, Session session) throws MetaSocioException  {
		Query editPost = session.createQuery("UPDATE Post as p SET p.postDetails=:postDetails,p.updatedAt=:updatedAt WHERE p.postId = "+postId+" AND p.user.userId="+userId+""); 
		editPost.setString("postDetails", postDetails);
		
		editPost.setTimestamp("updatedAt", updatedAt);
		//Preparing query to update required field
		return editPost.executeUpdate();	
	}
}
