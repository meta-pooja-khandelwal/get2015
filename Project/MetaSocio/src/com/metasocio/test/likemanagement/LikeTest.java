package com.metasocio.test.likemanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.model.likemanagement.Like;

/*************************************************************************
 * Description : This class has methods to test the methods of Like class
 *************************************************************************/
public class LikeTest {
	private static Like like = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		like = new Like();
	}

	/*****************************************************
	 * Method to test getCounter() method existing in Like
	 *****************************************************/
	@Test
	public void getCounterTest() {
		like.setCounter(20);
		assertEquals(20, like.getCounter());
	}

	/********************************************************
	 * Method to test getCreatedBy() method existing in Like
	 ********************************************************/
	@Test
	public void getCreatedByTest() {
		like.setCreatedBy("Chetna");
		assertEquals("Chetna", like.getCreatedBy());
	}

	/******************************************************
	 * Method to test getIsLiked() method existing in Like
	 ******************************************************/
	@Test
	public void getIsLikedTest() {
		like.setIsLiked(0);
		assertEquals(0, like.getIsLiked());
	}

	/*****************************************************
	 * Method to test getLikerId() method existing in Like
	 *****************************************************/
	@Test
	public void getLikerIdTest() {
		like.setLikerId(5);
		assertEquals(5, like.getLikerId());
	}
	
	/*****************************************************
	 * Method to test getCounter() method existing in Like
	 *****************************************************/
	@Test
	public void getCounterTest1() {
		like.setCounter(9);
		assertNotNull(like.getCounter());
	}

}
