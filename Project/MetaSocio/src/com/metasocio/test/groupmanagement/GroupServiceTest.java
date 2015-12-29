package com.metasocio.test.groupmanagement;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.groupmanagement.GroupService;

/***************************************************************************
 * Description : This class has methods to test the methods of GroupService
 * class
 ***************************************************************************/
public class GroupServiceTest {
	private static GroupService groupService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		groupService = new GroupService();
	}

	/***************************************************************
	 * Method to test getGroupById() method existing in GroupService
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void getGroupByIdTest() throws MetaSocioException {

		assertNotNull(groupService.getGroupById(1));
	}

	/**********************************************************************
	 * Method to test getGroupsInWhichUserIsNotMember() method existing in
	 * GroupService
	 * 
	 * @throws MetaSocioException
	 *********************************************************************/
	@Test(expected = MetaSocioSystemException.class)
	public void getGroupsInWhichUserIsNotMemberTest() throws MetaSocioException {
		GroupService groupService = new GroupService();
		User user = new User();
		assertNotNull(groupService.getGroupsInWhichUserIsNotMember(user, null));
	}

	/***************************************************************
	 * Method to test getMyGroups() method existing in GroupService
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void getMyGroupsTest() throws MetaSocioException {
		GroupService groupService = new GroupService();
		assertNotNull(groupService.getMyGroups(1));
	}

	/***************************************************************************
	 * Method to test getUsersHavingSameDepartmentWhoAreNotGroupMembers() method
	 * existing in GroupService
	 * 
	 * @throws MetaSocioException
	 ***************************************************************************/
	@Test
	public void getUsersHavingSameDepartmentWhoAreNotGroupMembersTest()
			throws MetaSocioException {
		GroupService groupService = new GroupService();
		assertNotNull(groupService
				.getUsersHavingSameDepartmentWhoAreNotGroupMembers("Java", 1));
	}

}
