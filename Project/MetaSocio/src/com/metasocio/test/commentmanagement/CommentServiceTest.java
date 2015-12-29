package com.metasocio.test.commentmanagement;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.service.commentmanagement.CommentService;

/******************************************************************************
 * Description : This class has methods to test the methods of CommentService
 * class
 ******************************************************************************/
public class CommentServiceTest {

	/********************************************************************
	 * Method to test retrieveComments() method existing in CommentService
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test
	public void retrieveCommentsTest() throws MetaSocioException {
		CommentService commentService = new CommentService();
		assertNotNull(commentService.retrieveComments(1));
	}

}
