package com.metasocio.test.postmanagement;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.service.postmanagement.PostService;

/*******************************************************************************
 * Description : This class has methods to test the methods of PostService class
 *******************************************************************************/
public class PostServiceTest {

	/****************************************************************
	 * Method to test retrievePosts() method existing in PostService
	 * 
	 * @throws MetaSocioException
	 *****************************************************************/
	@Test
	public void retrievePostsTest() throws MetaSocioException {
		PostService postService = new PostService();
		assertNotNull(postService.retrievePosts(1, 2, 0));
	}
}
