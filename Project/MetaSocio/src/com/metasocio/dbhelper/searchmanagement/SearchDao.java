package com.metasocio.dbhelper.searchmanagement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.metasocio.exception.MetaSocioException;
import com.metasocio.model.usermanagement.User;
/***********************************************************************
 *  Description : This class is directly
 *  fetching the data from database related to text typed in search box.
 ***********************************************************************/
public class SearchDao {
	private static SearchDao iSearchDao;// Declaring static instance of class
	
	/*******************************************************************
	 * This function is to create and return new instance of SearchDao.
	 * 
	 * @return : Returning instance of this class
	 ******************************************************************/
	public static SearchDao getInstance() {
		iSearchDao = new SearchDao();//Creating instance of this class
		return iSearchDao;//Returning instance of this class
	}
	/******************************************************************
	 * This method contains query that is fetching data of User from 
	 * database of related search.
	 * @param transaction
	 * @param session
	 * @param search
	 * @param email 
	 * @return User List of related search
	 * @throws MetaSocioException
	 *****************************************************************/
	@SuppressWarnings("unchecked")
	public List<User> retrieveUsersByKey(
			Session session, String search, String email) throws MetaSocioException {
		Query query = session.createQuery("FROM User WHERE name like'" + search
				+ "%' ORDER BY name");//Array List of User type 
		List<User> searchResult = new ArrayList<User>();
		
		searchResult = query.list();//Searched Users in list named searchResult
		
		for(int i=0;i<searchResult.size();i++){
			if(searchResult.get(i).getEmail().equalsIgnoreCase(email)){
				searchResult.remove(i);
			}
		}
		return searchResult;
	}
}
