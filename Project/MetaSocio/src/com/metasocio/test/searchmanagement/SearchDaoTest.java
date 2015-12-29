package com.metasocio.test.searchmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.metasocio.dbhelper.searchmanagement.SearchDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;

/*******************************************************************************
 * Description : This class has methods to test the methods of SearchDao class
 *******************************************************************************/
public class SearchDaoTest {

	private static SearchDao searchDao = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		searchDao = new SearchDao();
		cfg = ConfigurationFactory.getConfigurationInstance();
		sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	/*******************************************************************
	 * Method to test retrieveUsersByKey() method existing in SearchDao
	 * 
	 * @throws MetaSocioException
	 ********************************************************************/
	@Test
	public void retrieveUsersByKeyTest() throws MetaSocioException {
		assertNotNull(searchDao.retrieveUsersByKey(session, "ch",
				"chetna.sharma@metacube.com"));
	}

	/*******************************************************************
	 * Method to test retrieveUsersByKey() method existing in SearchDao
	 * 
	 * @throws MetaSocioException
	 ********************************************************************/
	@Test
	public void retrieveUsersByKeyTest1() throws MetaSocioException {
		String expectedResult = "[User [userId=1, name=Chetna, emailId=chetna.sharma@metacube.com, address=null, phoneNo=null, city=null, department=Java, role=Trainee, college=null, course=null, highSchool=null, stream=null, dateOfBirth=null, gender=null, imageURL=null, about=null, nickName=null, relationshipStatus=null, createdAt=2015-12-05 17:48:16.0, updatedAt=2015-12-05 17:48:16.0, createdBy=Chetna, updatedBy=Chetna, isDelete=0]]";
		List<User> listOfUser = searchDao.retrieveUsersByKey(session, "ch",
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
