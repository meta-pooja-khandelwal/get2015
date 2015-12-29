package com.metasocio.test.commentmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.model.commentmanagement.Comment;
import com.metasocio.model.postmanagement.Post;
import com.metasocio.model.usermanagement.User;

/***************************************************************************
 * Description : This class has methods to test the methods of Comment class
 ***************************************************************************/
public class CommentTest {
	private static Comment comment = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comment = new Comment();
	}

	/***********************************************************
	 * Method to test getCommentId() method existing in Comment
	 ***********************************************************/
	@Test
	public void getCommentIdTest() {
		comment.setCommentId(1);
		assertEquals(1, comment.getCommentId());
	}

	/***********************************************************
	 * Method to test getComments() method existing in Comment
	 ***********************************************************/
	@Test
	public void getCommentsTest() {
		comment.setComments("It's my comment");
		assertEquals("It's my comment", comment.getComments());
	}

	/***********************************************************
	 * Method to test getCreatedBy() method existing in Comment
	 ***********************************************************/
	@Test
	public void getCreatedByTest() {
		comment.setCreatedBy("Saurabh");
		assertEquals("Saurabh", comment.getCreatedBy());
	}

	/***********************************************************
	 * Method to test getIsDelete() method existing in Comment
	 ***********************************************************/
	@Test
	public void getIsDeleteTest() {
		comment.setIsDelete(0);
		assertEquals(0, comment.getIsDelete());
	}

	/*******************************************************
	 * Method to test getPost() method existing in Comment
	 ******************************************************/
	@Test
	public void getPostTest() {
		Post post = new Post();
		comment.setPost(post);
		assertNotNull(comment.getPost());
	}

	/*******************************************************
	 * Method to test getUser() method existing in Comment
	 *******************************************************/
	@Test
	public void getUserTest() {
		User user = new User();
		comment.setUser(user);
		assertNotNull(comment.getUser());
	}

}
