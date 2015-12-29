package com.metasocio.test.searchmanagement;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.searchmanagement.SearchFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;

/**************************************************************************
 * Description : This class has methods to test the methods of SearchFacade
 * class
 ***************************************************************************/
public class SearchFacadeTest {

	/**********************************************************************
	 * Method to test retrieveUsersByKey() method existing in SearchFacade
	 * 
	 * @throws MetaSocioException
	 ***********************************************************************/
	@Test
	public void retrieveUsersByKeyTest() throws MetaSocioException {
		SearchFacade facade = new SearchFacade();
		Configuration cfg = ConfigurationFactory.getConfigurationInstance();
		Session session = null;
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		session = sessionFactory.openSession();
		assertNotNull(facade.retrieveUsersByKey(session, "ch",
				"chetna.sharma@metacube.com"));
	}

}
