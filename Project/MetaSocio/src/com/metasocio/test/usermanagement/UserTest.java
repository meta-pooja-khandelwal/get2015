package com.metasocio.test.usermanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.model.usermanagement.User;

/*************************************************************************
 * Description : This class has methods to test the methods of User class
 **************************************************************************/
public class UserTest {

	private static User user = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user = new User();
	}

	/***************************************************
	 * Method to test getName() method existing in User
	 ****************************************************/
	@Test
	public void getNameTest() {
		user.setName("Chetna");
		assertEquals("Chetna", user.getName());
	}

	/****************************************************
	 * Method to test getUserId() method existing in User
	 ******************************************************/
	@Test
	public void getUserIdTest() {
		user.setUserId(1);
		assertEquals(1, user.getUserId());
	}

	/****************************************************
	 * Method to test getAbout() method existing in User
	 *****************************************************/
	@Test
	public void getAboutTest() {
		user.setAbout("about");
		;
		assertEquals("about", user.getAbout());
	}

	/******************************************************
	 * Method to test getAddress() method existing in User
	 ******************************************************/
	@Test
	public void getAddressTest() {
		user.setAddress("Jaipur");
		;
		assertEquals("Jaipur", user.getAddress());
	}

	/**************************************************
	 * Method to test getCity() method existing in User
	 ***************************************************/
	@Test
	public void getCityTest() {
		user.setCity("Jaipur");
		assertEquals("Jaipur", user.getCity());
	}

	/*******************************************************
	 * Method to test getCreatedBy() method existing in User
	 ********************************************************/
	@Test
	public void getCreatedByTest() {
		user.setCreatedBy("admin");
		assertEquals("admin", user.getCreatedBy());
	}

	/*********************************************************
	 * Method to test getDepartment() method existing in User
	 *********************************************************/
	@Test
	public void getDepartmentTest() {
		user.setDepartment("Java");
		assertEquals("Java", user.getDepartment());
	}

	/****************************************************
	 * Method to test getGender() method existing in User
	 *****************************************************/
	@Test
	public void getGenderTest() {
		user.setGender("Female");
		assertEquals("Female", user.getGender());
	}

	/******************************************************
	 * Method to test getCollege() method existing in User
	 ******************************************************/
	@Test
	public void getCollegeTest() {
		user.setCollege("JECRC");
		assertEquals("JECRC", user.getCollege());
	}

	/**************************************************** 
	 * Method to test getEmail() method existing in User
	 *****************************************************/
	@Test
	public void getEmailTest() {
		user.setEmail("c@metacube.com");
		assertEquals("c@metacube.com", user.getEmail());
	}

	/****************************************************
	 * Method to test getCourse() method existing in User
	 *****************************************************/
	@Test
	public void getCourseTest() {
		user.setCourse("OOPS");
		assertEquals("OOPS", user.getCourse());
	}

	/*****************************************************
	 * Method to test getPicture() method existing in User
	 ******************************************************/
	@Test
	public void getPictureTest() {
		user.setPicture("imageURL");
		;
		assertEquals("imageURL", user.getPicture());
	}

	/*******************************************************
	 * Method to test getNickName() method existing in User
	 *******************************************************/
	@Test
	public void getNickNameTest() {
		user.setNickName("Jimmy");
		assertEquals("Jimmy", user.getNickName());
	}

	/*****************************************************
	 * Method to test getPhoneNo() method existing in User
	 ******************************************************/
	@Test
	public void getPhoneNoTest() {
		user.setPhoneNo("9879654232");
		assertEquals("9879654232", user.getPhoneNo());
	}

	/*************************************************************
	 * Method to test getRelationshipStatus() method existing in User
	 **************************************************************/
	@Test
	public void getRelationshipStatusTest() {
		user.setRelationshipStatus("Single");
		assertEquals("Single", user.getRelationshipStatus());
	}

	/**************************************************
	 * Method to test getRole() method existing in User
	 ***************************************************/
	@Test
	public void getRoleTest() {
		user.setRole("Trainee");
		assertEquals("Trainee", user.getRole());
	}

	/*******************************************************
	 * Method to test getIsDelete() method existing in User
	 ********************************************************/
	@Test
	public void getIsDeleteTest() {
		user.setIsDelete(0);
		;
		assertEquals(0, user.getIsDelete());
	}
}
