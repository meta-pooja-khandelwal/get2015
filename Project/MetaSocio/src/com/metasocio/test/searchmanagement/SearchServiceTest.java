package com.metasocio.test.searchmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.searchmanagement.SearchService;

/***************************************************************************
 * Description : This class has methods to test the methods of SearchService
 * class
 ****************************************************************************/
public class SearchServiceTest {

	private static SearchService searchService = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 searchService = new SearchService();
	}
	
	/**********************************************************************
	 * Method to test retrieveUsersByKey() method existing in SearchService
	 * 
	 * @throws MetaSocioException
	 **********************************************************************/
	@Test
	public void retrieveUsersByKeyTest() throws MetaSocioException {
		assertNotNull(searchService.retrieveUsersByKey("ch",
				"chetna.sharma@metacube.com"));
	}

	/***********************************************************************
	 * Method to test retrieveUsersByKey() method existing in SearchService
	 * 
	 * @throws MetaSocioException
	 ***********************************************************************/
	@Test
	public void retrieveUsersByKeyTest1() throws MetaSocioException {
		String expectedResult = "[User [userId=1, name=Chetna, emailId=chetna.sharma@metacube.com, address=null, phoneNo=null, city=null, department=Java, role=Trainee, college=null, course=null, highSchool=null, stream=null, dateOfBirth=null, gender=null, imageURL=null, about=null, nickName=null, relationshipStatus=null, createdAt=2015-12-05 17:48:16.0, updatedAt=2015-12-05 17:48:16.0, createdBy=Chetna, updatedBy=Chetna, isDelete=0]]";
		List<User> listOfUser = searchService.retrieveUsersByKey("ch",
				"chetna.sharma@metacube.com");
		String listString = "[";

		for (User user : listOfUser) {
			listString += user;
		}
		listString += "]";
		System.out.println(listString);
		assertEquals(expectedResult, listString);
	}

}
