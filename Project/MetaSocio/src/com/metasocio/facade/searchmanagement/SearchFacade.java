package com.metasocio.facade.searchmanagement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.metasocio.dbhelper.searchmanagement.SearchDao;
import com.metasocio.exception.MetaSocioException;
import com.metasocio.facade.followermanagement.FollowerFacade;
import com.metasocio.facade.usermanagement.UserFacade;
import com.metasocio.model.usermanagement.User;
/**
* @author 
* Since : 02 December, 2015
* Description : This SearchFacade class is just an outward appearance of Database helper classes, providing a separation 
* 				 between service and dbHelper classes for Search Management, calling functions of dbHelper.
*/
public class SearchFacade {
	/******************************************************************
	 * This method is to Manage search operations on a particular input.
	 * 
	 * @param transaction
	 * @param session : Creating a session to process 
	 *					query.
	 * @param search : name, on which basis data will retrieve from database 
	 * @param email 
	 * @return : searchResult that is list of related search result 
	 * @throws MetaSocioException Throwing MetaSocioException
	 *        if any exception occurs
	 *********************************************************************/
	public List<User> retrieveUsersByKey(Session session,String search, String email) throws MetaSocioException{
	SearchDao iSearchDao = SearchDao.getInstance();//Getting instance of 'SearchDao' class	
		List<User> searchResult = new ArrayList<User>(); //Creating array list of User type named searchResult
		searchResult = iSearchDao.retrieveUsersByKey(session,search,email);//calling method retrieveSearchResult existing in SearchDao class
		FollowerFacade iFollowerFacade=new FollowerFacade();
		UserFacade iUserFacade=new UserFacade();
		int id=iUserFacade.getUserByEmail(email, session).getUserId();
		List<User> searchUserLiseWhoAreNotFollowingsList=iFollowerFacade.getUsersWhomYouAreNotFollowing(id, searchResult, session);
		
		
		return searchUserLiseWhoAreNotFollowingsList;//returns list of releted search
	}
}
