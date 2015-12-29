package com.metasocio.facade.likemanagement;
import java.util.Date;
import org.hibernate.Session;
import com.metasocio.dbhelper.likemanagement.LikeDao;
import com.metasocio.dbhelper.likemanagement.ValidateUserLike;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.postmanagement.PostFacade;
import com.metasocio.model.likemanagement.Like;
import com.metasocio.model.usermanagement.User;
/**
 * @author 
 * Since : 23 November, 2015
 * Description : This LikeFacade class is just an outward appearance of Database helper classes, providing a separation 
 * 				 between service and dbHelper classes for Like Management, calling functions of dbHelper.
 */
/**
 * @author Pooja
 *
 */
public class LikeFacade {
	/*************************************************************
	 * This function is to Manage all operations regarding likes on
	 * a particular post by a particular user.
	 * @param user : User who is performing likes operation
	 * @param postId : The post on which like operation is applied.
	 * @param session : Creating a session to process 
	 *					query.
	 * @throws MetaSocioException : Throwing MetaSocioException
	 * 								if any exception occurs
	 *************************************************************/
	public void manageLikes(User user, int postId,
			Session session) throws MetaSocioException {
		ValidateUserLike validation = ValidateUserLike.getInstance();		//Getting instance of 'ValidateUserLike' class	
		boolean hasLiked = validation.hasUSerAlreadyLiked(user.getUserId(),postId, session);	
		//Calling a function in dbHelper class to get like status	
		LikeDao likeDao = LikeDao.getInstance();	//Getting instance of 'LikeDao' class	
		PostFacade postFacade=new PostFacade();	//Getting instance of 'PostFacade' class	
		//Checking for user-post combination existence if not exists setting info in 'Like' POJO
		if (hasLiked == false) {		
			Like like = new Like();		//Getting instance of 'Like' POJO class
			like.setLikerId(user.getUserId());		
			like.setPostId(postId);					
			Date date= new java.util.Date();		//Date instance
			like.setCreatedAt(date);
			like.setCreatedBy(user.getName());
			like.setIsLiked(0);
			likeDao.setLike(like, session);		//Calling database helper class function to set Like info in database
			postFacade.incrementLikesOnPost(postId, session);	//Incrementing no. of likes on the post
		} else {
			int hasDeleted = validation.hasUserLikeDeleted(user.getUserId(), postId, session);	
			//Calling post database helper class function to check isLiked field value from database
			//Checking if user liked the post
			if(hasDeleted == 0){
				hasDeleted = 1;		//setting new value to variable
				likeDao.updateIsLiked(hasDeleted, user.getUserId(), postId, session);	 //Calling dbHelper function to update isLiked to new value
				postFacade.decrementLikesOnPost(postId, session);		//Decrementing no.of Likes on post
			}
			else{
				hasDeleted = 0;		//setting new value to variable
				likeDao.updateIsLiked(hasDeleted, user.getUserId(), postId, session);	//Calling dbHelper function to update isLiked to new value
				postFacade.incrementLikesOnPost(postId, session);		//Incrementing no.of Likes on post
			}
		}		
	}
	
	/*************************************************************
	 * @param postId
	 * @param session
	 * @throws MetaSocioException
	 **************************************************************/
	public boolean deleteLikeOnPost(int postId, Session session) throws MetaSocioException {
		LikeDao iLikeDao=LikeDao.getInstance();
		try{ 
		iLikeDao.deleteLikeOnPost(postId, session);
	    	return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	/*************************************************************
	 * @param userId
	 * @param postId
	 * @param session
	 * @return
	 * @throws MetaSocioException
	 ************************************************************/
	public boolean hasUSerAlreadyLiked(int userId, int postId, Session session) throws MetaSocioException {
		ValidateUserLike validateUserLike=ValidateUserLike.getInstance();
		boolean hasAlreadyLiked=validateUserLike.hasUSerAlreadyLiked(userId,postId, session);
		if(hasAlreadyLiked==true){
		int hasDeleted=validateUserLike.hasUserLikeDeleted(userId, postId, session);
		if(hasDeleted==0){
			hasAlreadyLiked=true;
		}else{
			hasAlreadyLiked=false;
		}
		}
		return hasAlreadyLiked;
	}
	
	/*************************************************************
	 * @param postId
	 * @param session
	 * @return
	 * @throws MetaSocioException
	*************************************************************/
	public int LikesOnPostByPostId(int postId, Session session)
			throws MetaSocioException {
		LikeDao iLikeDao = LikeDao.getInstance();
		return  iLikeDao.LikesOnPostByPostId(postId, session);
	}
}
