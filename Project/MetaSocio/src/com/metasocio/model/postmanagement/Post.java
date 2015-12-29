package com.metasocio.model.postmanagement;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

import com.metasocio.model.usermanagement.User;

/**************************
 * POJO class of Post
 **************************/
public class Post {
	// To store post id
	private int postId;
	// To store Post Details
	private String postDetails;
	// to store Date
	private Date datePosted;
	// To store Likes
	private int likes;
	// To store Update At
	private Timestamp updatedAt;
	// To store CreatedBy
	private String createdBy;
	// to store is Delete Field
	private int isDelete;
	private User user;
	private int groupId;
	/**
	 * @return the postId
	 */
	public int getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}
	/**
	 * @return the postDetails
	 */
	public String getPostDetails() {
		return postDetails;
	}
	/**
	 * @param postDetails the postDetails to set
	 */
	public void setPostDetails(String postDetails) {
		this.postDetails = postDetails;
	}
	/**
	 * @return the datePosted
	 */
	public Date getDatePosted() {
		return datePosted;
	}
	/**
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}
	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}
	/**
	 * @return the updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the isDelete
	 */
	public int getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	

}
