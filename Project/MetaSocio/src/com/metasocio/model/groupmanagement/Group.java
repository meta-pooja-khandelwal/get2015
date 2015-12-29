package com.metasocio.model.groupmanagement;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.metasocio.model.usermanagement.User;

/**************************
 * POJO class of Group
 **************************/
public class Group implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int groupId;
	private String groupName;
	private String imageUrl;
	private Date createdAt;
	private Timestamp updatedAt;
	private String createdBy;
	private int isdelete;
	private User user;
	private Set<User> usersSet = new HashSet<User>(0);
	private Set<User> users = new HashSet<User>(0);

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getUsersSet() {
		return usersSet;
	}

	public void setUsersSet(Set<User> usersSet) {
		this.usersSet = usersSet;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", groupName=" + groupName
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", isdelete=" + isdelete + "]";
	}
}
