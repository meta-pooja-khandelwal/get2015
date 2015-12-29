package com.metasocio.test.groupmanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.model.groupmanagement.Group;

/************************************************************************
 * Description : This class has methods to test the methods of Group class
 ************************************************************************/
public class GroupTest {
	private static Group group;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		group = new Group();
	}

	/*********************************************************
	 * Method to test getCreatedBy() method existing in Group
	 *********************************************************/
	@Test
	public void getCreatedByTest() {
		group.setCreatedBy("admin");
		assertEquals("admin", group.getCreatedBy());
	}

	/*********************************************************
	 * Method to test getGroupId() method existing in Group
	 *********************************************************/
	@Test
	public void getGroupIdTest() {
		group.setGroupId(1);
		assertEquals(1, group.getGroupId());
	}

	/*********************************************************
	 * Method to test getGroupName() method existing in Group
	 *********************************************************/
	@Test
	public void getGroupNameTest() {
		group.setGroupName("GET");
		;
		assertEquals("GET", group.getGroupName());
	}

	/*********************************************************
	 * Method to test getImageUrl() method existing in Group
	 *********************************************************/
	@Test
	public void getImageUrlTest() {
		group.setImageUrl("imageUrl");
		assertEquals("imageUrl", group.getImageUrl());
	}

	/*********************************************************
	 * Method to test getIsdelete() method existing in Group
	 *********************************************************/
	@Test
	public void getIsdeleteTest() {
		group.setIsdelete(1);
		assertEquals(1, group.getIsdelete());
	}
}
