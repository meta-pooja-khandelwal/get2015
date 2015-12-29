package com.metasocio.dbhelper.likemanagement;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.likemanagement.Like;
/**************************************************************
 * Description : This class is performing validations and 
 * directly querying the database regarding Likes.
 *************************************************************/
public class ValidateUserLike {
	private static ValidateUserLike iValidateUserLike;		//Declaring static instance of this class
	/************************************
	 * Private Constructor
	 ***********************************/
	public ValidateUserLike() {
	}
	/************************************************************
	 * This function is to create single instance of this class, 
	 * if the instance already exists return the previous one
	 * and if instance is null create a new instance.
	 * 
	 * @return : Returning instance of this class
	 ************************************************************/
	public static ValidateUserLike getInstance() {
		//Checking if instance already exists
			iValidateUserLike = new ValidateUserLike();		//Creating instance of this class
		return iValidateUserLike;		//Returning instance of this class
	}
	/**************************************************************
	 * This function is checking if combination of a particular 
	 * user with post exists already in database.
	 * @param userId & postId : Creating a unique combination for 
	 * 					a particular user likes on particular post 
	 * @param session : Creating a session to process query.
	 * @return : Returning boolean true if combination exists and 
	 * 				false if not.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs.
	 **************************************************************/
	public boolean hasUSerAlreadyLiked(int userId, int postId,
			Session session) throws MetaSocioException {
		boolean hasLiked = false;	//Creating a flag for user existence
		Criteria criteria = session.createCriteria(Like.class); 		//Creating criteria of 'Like' to get required table
		criteria.add(Restrictions.eq("likerId",
				userId)).add(Restrictions.eq("postId", postId));	//Adding restrictions as likerId and postId to criteria
		Like like= new Like();			//Creating instance of 'Like' POJO.
		like = (Like) criteria.uniqueResult();		//Getting unique result from database through this query
		//Checking if Combination exists
		if (like != null) {
			hasLiked = true;		//Setting flag to true if combo exists
		} else {
			hasLiked = false;		//Setting flag to false if combo doesn't exists
		}
		return hasLiked;			//Returning flag for existence of user-post combination
	}
	/********************************************************************
	 * This function is to get like status of user on a particular post 
	 * from database.
	 * @param userId & postId : These parameters together making a unique
	 * 							combination whose like status is required.
	 * @param session : Creating a session to process query.
	 * @return : Returning integer 1 if user has unlike the post and 
	 * 				0 if user already likes the post.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs.
	 *******************************************************************/
	public int hasUserLikeDeleted(int userId, int postId, Session session) throws MetaSocioException {
		Query checkDelete = session.createQuery("Select lk.isLiked from  "
				+ "Like as lk where lk.postId = "+postId+" and lk.likerId=" + userId+"");	//Creating query to get data from database
		int hasDeleted = (int) checkDelete.uniqueResult();		//Getting unique result (status of isLiked)
		return hasDeleted;				//Returning isLiked field of user-post combination from Database 'LikeCounter' table
	}
}
