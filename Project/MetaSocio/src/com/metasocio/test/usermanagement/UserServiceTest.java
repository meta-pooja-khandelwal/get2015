package com.metasocio.test.usermanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.usermanagement.UserService;

/*******************************************************************************
 * Description : This class has methods to test the methods of UserService class
 *******************************************************************************/
public class UserServiceTest {

	private static UserService userService = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService = new UserService();
	}

	/*************************************************************
	 * Method to test getUserByEmail() method existing in UserService
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void getUserByEmailTest() throws MetaSocioException {
		assertNotNull(userService
				.getUserByEmail("pooja.khandelwal@metacube.com"));
	}

	/*************************************************************
	 * Method to test getUserById() method existing in UserService
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void getUserByIdTest() throws MetaSocioException {
		assertNotNull(userService.getUserById(1));
	}

	/*************************************************************
	 * Method to test getUsersHavingSameDepartment() method existing in
	 * UserService
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test(expected = MetaSocioException.class)
	public void getUsersHavingSameDepartmentTest() throws MetaSocioSystemException {
		User user = new User();
		assertNotNull(userService.getUsersHavingSameDepartment(user));
	}

	/*************************************************************
	 * Method to test isEmailExists() method existing in UserService
	 * 
	 * @throws MetaSocioException
	 **************************************************************/
	@Test
	public void isEmailExistsTest() throws MetaSocioException {
		boolean check = userService
				.isEmailExists("pooja.khandelwal@metacube.com");
		assertEquals(true, check);
	}

}
