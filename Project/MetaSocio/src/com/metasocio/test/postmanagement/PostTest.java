package com.metasocio.test.postmanagement;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.model.postmanagement.Post;

/*************************************************************************
 * Description : This class has methods to test the methods of Post class
 **************************************************************************/
public class PostTest {
	private static Post post = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		post = new Post();
	}

	/****************************************************
	 * Method to test getPostId() method existing in Post
	 *****************************************************/
	@Test
	public void getPostIdTest() {
		post.setPostId(1);
		assertEquals(1, post.getPostId());
	}

	/*****************************************************
	 * Method to test getGroupId() method existing in Post
	 ******************************************************/
	@Test
	public void getGroupIdTest() {
		post.setGroupId(3);
		;
		assertEquals(3, post.getGroupId());
	}

	/****************************************************
	 * Method to test getLikes() method existing in Post
	 ******************************************************/
	@Test
	public void getLikesTest() {
		post.setLikes(30);
		;
		assertEquals(30, post.getLikes());
	}

	/**********************************************************
	 * Method to test getPostDetails() method existing in Post
	 ************************************************************/
	@Test
	public void getPostDetailsTest() {
		post.setPostDetails("Hello...It's my post");
		;
		assertEquals("Hello...It's my post", post.getPostDetails());
	}

	/*******************************************************
	 * Method to test getCreatedBy() method existing in Post
	 *******************************************************/
	@Test
	public void getCreatedByTest() {
		post.setCreatedBy("Admin");
		assertEquals("Admin", post.getCreatedBy());
	}

	/******************************************************
	 * Method to test getIsDelete() method existing in Post
	 *******************************************************/
	@Test
	public void getIsDeleteTest() {
		post.setIsDelete(1);
		assertEquals(1, post.getIsDelete());
	}

}
