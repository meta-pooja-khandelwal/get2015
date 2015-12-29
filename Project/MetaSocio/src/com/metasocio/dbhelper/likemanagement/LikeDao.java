package com.metasocio.dbhelper.likemanagement;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.likemanagement.Like;
import com.metasocio.model.postmanagement.Post;
/******************************************************************
 * Description : This class is directly querying the database 
 * regarding likes on post to get the required information.
 *****************************************************************/
public class LikeDao {
	private static LikeDao iLikeDao;		//Declaring static instance of class 
							
	/*******************************************************************
	 * This function is to create and return new instance of LikeDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static LikeDao getInstance() {
			iLikeDao = new LikeDao(); 		//Creating instance of this class
		return iLikeDao;		//Returning instance of this class
	}
	/************************************************************
	 * This function is to save Like information
	 * in the database.Ex. Liker ID, post id etc.
	 * @param like : Setting data of POJO 'Like' in 
	 * 				 the related table.
	 * @param session : Creating a session to process 
	 *					query.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 *************************************************************/
	public boolean setLike(Like like, Session session)  throws MetaSocioException {
		try{
			session.save(like);	//Query to save 'Like' data in database
			return true;
		}		
		catch(Exception e){
			return false;
		}
	}
	/********************************************************************************
	 * This function is to update 'isLiked' field in 'LikeCounter' table, if
	 * if user has already liked the post (isLiked = 0), then update the field to 
	 * Unlike (isLiked = 1) and vice-versa.
	 * 
	 * @param hasDeleted : This is the value which is to set in isLIkeed field. 
	 * @param userId & postId : These two parameters are making a unique combination
	 * 							on which field is to update.
	 * @param session : Creating a session to process 
	 *					query.
	 * @return 
	 * @throws MetaSocioException : Throwing MetaSocioException
	 ********************************************************************************/
	public int updateIsLiked(int hasDeleted, int userId, int postId,
			Session session)  throws MetaSocioException {
		Query updateIsLiked = session.createQuery("UPDATE Like as lk SET lk.isLiked "
				+ "= "+ hasDeleted +" WHERE lk.postId = "+postId+" and lk.likerId=" + userId+""); 		//Preparing query to update required field
		return updateIsLiked.executeUpdate();				//Executing updates		
	}
	public int deleteLikeOnPost(int postId, Session session) throws MetaSocioException {
		Query updateIsLiked = session.createQuery("UPDATE Like as lk SET lk.isLiked "
				+ "= "+ 1 +" WHERE lk.postId = "+postId+""); 		//Preparing query to update required field
		return updateIsLiked.executeUpdate();	
	}
	
	/*******************************************************
	 * This method is to get total no. of likes on a post 
	 * @param postId
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 *******************************************************/
	public int LikesOnPostByPostId(int postId, Session session) throws MetaSocioException {
		Criteria criteria = session.createCriteria(Post.class);
		criteria.add(Restrictions.eq("postId", postId));
		Post post = new Post();
		post = (Post) criteria.uniqueResult();
		int noOfLikes = post.getLikes();
		return noOfLikes;
	}
}
