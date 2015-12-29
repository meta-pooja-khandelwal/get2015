package com.metasocio.service.searchmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.facade.searchmanagement.SearchFacade;
import com.metasocio.hibernate.factory.ConfigurationFactory;
import com.metasocio.model.usermanagement.User;
/**********************************************************************************
 * Description : This SearchService class is providing services like session opening, 
 * session closing, beginning transaction and commit transactions.Session is opening 
 * a connection between Java and database and transactions is to commit queries in
 * database.
 ***********************************************************************************/
public class SearchService {
	//logger object
	private static Logger logger = Logger.getLogger(SearchService.class);

	//error message
	private static final String MSG = "Exception in SearchService";

	/***********************************************************************
	 * This function is managing search operations providing
	 * session to the facade layer and transactions will be committed
	 * in this layer, also closing session.
	 * @param search : input on which basis search result will come.
	 * @param email 
	 * @return : searchResult containing related search list of Users 
	 * @throws MetaSocioSystemException : Throwing MetaSocioException
	 * 								if any exception occurs
	 ***********************************************************************/
	public List<User> retrieveUsersByKey(String search, String email)
			throws MetaSocioSystemException {
		Session session = null;//Creating session
		SessionFactory sessionFactory=null;
		Transaction transaction = null;//Creating transaction
		List<User> searchResult = new ArrayList<User>();//Array List of User type

		try {
			SearchFacade searchFacade = new SearchFacade();//Creating instance of 'SearchFacade' class
			Configuration cfg = ConfigurationFactory.getConfigurationInstance();//Getting instance of configuration file
			sessionFactory = cfg.buildSessionFactory();	//Building session factory
			session = sessionFactory.openSession(); //opening session
			transaction = session.beginTransaction(); //beginning transaction

			//passing session and search string to manage search operation in Facade 
			searchResult = searchFacade.retrieveUsersByKey(
					session, search,email);
			transaction.commit();//committing transaction 

		} catch (Exception e) {
			try {
				transaction.rollback();
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("Transaction roll back,["
						+ e.getMessage() + "]", e);
			} catch (Exception e1) {
				logger.error(Thread.currentThread().getStackTrace()[2]
						.getLineNumber());
				logger.error(MSG, e);

				throw new MetaSocioSystemException("[" + e.getMessage()
						+ "];error in transactiopn roll back,["
						+ e1.getMessage() + "]", e);
			}
		} finally {
			if (session != null) {
				session.close(); //closing session if not null
			}
			if(sessionFactory!=null){
				sessionFactory.close();
			}
		}
		return searchResult;
	}
}
