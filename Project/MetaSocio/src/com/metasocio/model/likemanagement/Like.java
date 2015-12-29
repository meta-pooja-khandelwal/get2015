package com.metasocio.model.likemanagement;

import java.sql.Timestamp;
import java.util.Date;

/**************************
 * POJO class of Like
 **************************/
public class Like {
	private int counter;
	private int postId;
	private int likerId;
	private Date createdAt;
	private Timestamp updatedAt;
	private String createdBy;
	private int isLiked;
	
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
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
	 * @return the likerId
	 */
	public int getLikerId() {
		return likerId;
	}
	/**
	 * @param likerId the likerId to set
	 */
	public void setLikerId(int likerId) {
		this.likerId = likerId;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	 * @return the isLiked
	 */
	public int getIsLiked() {
		return isLiked;
	}
	/**
	 * @param isLiked the isLiked to set
	 */
	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}
	
	

}
