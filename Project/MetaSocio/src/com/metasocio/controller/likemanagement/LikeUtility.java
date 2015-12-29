package com.metasocio.controller.likemanagement;

/**************************************************************************
 * Description : class LikeUtility , Helper or utility class for LikeManager
 **************************************************************************/

public class LikeUtility {

	/****************************************************************************
	 * This method will create DIV to be shown for like on home or group view
	 * 
	 * @param isLikedByUser
	 *            : true or false
	 * @param postID
	 *            : id on which user likes or dislikes
	 * @param likes
	 *            : total no. of likes on post
	 * @return
	 ***************************************************************************/
	public String createTag(boolean isLikedByUser, String postID, String likes) {
		String likesUpdated = null;
		if (isLikedByUser) {
			likesUpdated = "<a href='#'><i class='fa fa-thumbs-up' id ='like"
					+ postID + "' onClick='loadInfo(" + postID + ")"
					+ "' value='" + postID + "' style='color:orange'></i></a>"
					+ likes;
		} else {
			likesUpdated = "<a href='#'><i class='fa fa-thumbs-up' id ='like"
					+ postID + "' onClick='loadInfo(" + postID + ")"
					+ "' value='" + postID + "' style='color:grey'></i></a>"
					+ likes;
		}
		return likesUpdated;
	}

}
