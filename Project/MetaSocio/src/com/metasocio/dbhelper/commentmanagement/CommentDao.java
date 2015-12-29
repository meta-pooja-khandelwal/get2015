package com.metasocio.dbhelper.commentmanagement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.commentmanagement.Comment;
import com.metasocio.model.usermanagement.User;
/****************************************************************************
 * Description: This class is directly querying the database regarding 
 * comments on post to get the required information.
 ****************************************************************************/
public class CommentDao {
	private static CommentDao iCommentDao; //making static instance of class 
	
	public CommentDao() {

	}
	/*******************************************************************
	 * This function is to create and return new instance of CommentDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static CommentDao getInstance() {
		iCommentDao = new CommentDao();
		return iCommentDao;//Returning instance of this class
	}
	/*************************************************************
	 * This function is to save comment information in the database
	 * @param newComment
	 * @param session
	 * @return 
	 * @throws MetaSocioException
	 ************************************************************/
	public boolean saveComment(Comment newComment,
			Session session) throws MetaSocioException {
		try{
		session.save(newComment);	
		return true;
		}
		catch(Exception e){
			return false;
		}
	}
	/**************************************************************
	 * This function is to retrieve comment list from the database
	 * @param postId
	 * @param session
	 * @return list of comments with user's image whom commented
	 * @throws MetaSocioException
	 **************************************************************/
	public List<Comment> retrieveComments(int postId,
			Session session) throws MetaSocioException {

		// query to retrieve comments from comment table
		Query query = session.createQuery("select cm.commentId, cm.comments, cm.dateCommented, cm.updatedAt, cm.createdBy,"
				+ "cm.isDelete, cm.user from Comment cm where cm.post.postId=:id AND cm.isDelete=:isdelete order by cm.dateCommented desc");
		query.setInteger("id",postId);
		query.setInteger("isdelete",0);


		@SuppressWarnings("unchecked")
		List<Object[]> rows=query.list();


		//declaration of array list named commentsWithImage 
		List<Comment> comments = new ArrayList<Comment>();
		for (Object[] row: rows) {
			// Creating object of Comment class
			Comment comment = new Comment();
			// Setting comment information in comment object
			comment.setCommentId((int) row[0]);
			comment.setComments((String)row[1]);
			comment.setDateCommented((Date) row[2]);
			//  comment.setCreatedAt((Date) row[3]);
			comment.setUpdatedAt( (Timestamp) row[3]);
			comment.setCreatedBy((String) row[4]);
			comment.setIsDelete((int) row[5]);
			// imageHelper.setComment(comment);
			comment.setUser((User) row[6]);
			//adding this object to list commentsWithImage
			comments.add(comment);
		}
		return comments;
	}
	
	/*****************************************************************
	 * This function is to delete comment on post which is deleted
	 * from the database
	 * @param postId
	 * @param session
	 * @throws MetaSocioException
	 ******************************************************************/
	public void deleteCommentOnPost(int postId, Session session) throws MetaSocioException  {
		Query deleteCommentOnPost = session.createQuery("UPDATE Comment as c SET c.isDelete = "+ 1 +" WHERE c.post.postId = "+postId+""); 		//Preparing query to update required field
		deleteCommentOnPost.executeUpdate();	

	}

	/*****************************************************
	 * This function is to delete comment by comment id 
	 * @param commentId
	 * @param session
	 * @param userId 
	 * @param updatedAt 
	 * @return
	 * @throws MetaSocioException
	 *****************************************************/
	public int deleteCommentByCommentId(int commentId, Session session, int userId, Timestamp updatedAt) throws MetaSocioException {
		Query deleteCommentByCommentId = session.createQuery("UPDATE Comment as c SET c.isDelete "
				+ "= "+ 1 +",c.updatedAt='"+updatedAt+"' WHERE c.commentId = "+commentId+" AND c.user.userId ="+userId+""); 		//Preparing query to update required field
		return deleteCommentByCommentId.executeUpdate();	
	}

	/**************************************************
	 * This function is to edit comment by comment id 
	 * @param commentId
	 * @param commentDetails
	 * @param userId 
	 * @param updatedAt 
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 **************************************************/
	public int editCommentByCommentId(int commentId, String commentDetails, int userId, Timestamp updatedAt, Session session) throws MetaSocioException {
		Query editCommentByCommentId = session.createQuery("UPDATE Comment as c SET c.comments=:comments,c.updatedAt=:updatedAt WHERE c.commentId = "+commentId+" AND c.user.userId="+userId+""); 		//Preparing query to update required field
		editCommentByCommentId.setString("comments", commentDetails);
		editCommentByCommentId.setTimestamp("updatedAt",updatedAt);
		//editCommentByCommentId.setTimestamp("updatedAt",updatedAt);
		return editCommentByCommentId.executeUpdate();	

	}

}
