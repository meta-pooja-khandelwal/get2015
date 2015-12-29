package com.metasocio.test.groupmanagement;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.groupmanagement.GroupFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.groupmanagement.Group;
import com.metasocio.model.usermanagement.User;

/*******************************************************************************
 * Description : This class has methods to test the methods of GroupFacade class
 *******************************************************************************/
public class GroupFacadeTest {
	static GroupFacade groupFacade = null;
	static Group group = null;
	static Session session = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		groupFacade = new GroupFacade();
		group = new Group();
		Configuration cfg = ConfigurationFactory.getConfigurationInstance();

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/***************************************************************
	 * Method to test createGroup() method existing in GroupFacade
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void createGroupTest() throws MetaSocioException {
		assertNotNull(groupFacade.createGroup(group, session));
	}

	/**************************************************************
	 * Method to test getAllGroups() method existing in GroupFacade
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void getAllGroupsTest() throws MetaSocioException {
		assertNotNull(groupFacade.getAllGroups(session));
	}

	/***************************************************************
	 * Method to test getGroupById() method existing in GroupFacade
	 * 
	 * @throws MetaSocioException
	 ***************************************************************/
	@Test
	public void getGroupByIdTest() throws MetaSocioException {
		assertNotNull(groupFacade.getGroupById(1, session));
	}

	/**********************************************************************
	 * Method to test getGroupsInWhichUserIsNotMember() method existing in
	 * GroupFacade
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test(expected = NullPointerException.class)
	public void getGroupsInWhichUserIsNotMemberTest() throws MetaSocioException {
		User user = new User();
		assertNotNull(groupFacade.getGroupsInWhichUserIsNotMember(user, null,
				session));
	}

	/*************************************************************
	 * Method to test getMyGroups() method existing in GroupFacade
	 * 
	 * @throws MetaSocioException
	 *************************************************************/
	@Test
	public void getMyGroupsTest() throws MetaSocioException {
		assertNotNull(groupFacade.getMyGroups(1, session));
	}

	/****************************************************************************
	 * Method to test getUsersHavingSameDepartmentWhoAreNotGroupMembers() method
	 * existing in GroupFacade
	 * 
	 * @throws MetaSocioException
	 ****************************************************************************/
	@Test
	public void getUsersHavingSameDepartmentWhoAreNotGroupMembersTest()
			throws MetaSocioException {
		assertNotNull(groupFacade
				.getUsersHavingSameDepartmentWhoAreNotGroupMembers("Java", 1,
						session));
	}
}
