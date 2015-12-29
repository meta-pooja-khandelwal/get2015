package com.metasocio.model.commentmanagement;

import java.sql.Timestamp;
import java.util.Date;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;
import lombok.Data;

/**************************
 * POJO class of Comment
 **************************/
@Data
public class Comment {
	private int commentId;
	private String comments;
	private Date dateCommented;
	private Timestamp updatedAt;
	private String createdBy;
	private int isDelete;
	private Post post;
	private User user;

}
