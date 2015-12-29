package com.metasocio.controller.searchmanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.metasocio.exception.MetaSocioSystemException;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.searchmanagement.SearchService;
import com.metasocio.service.usermanagement.UserService;

/*********************************************************************************
 * Description : Servlet implementation class NameSearch extending HttpServlet,
 * Managing search by name on key press
 **********************************************************************************/

@WebServlet("/NameSearch")
public class NameSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NameSearch() {
		super();
	}

	/*****************************************************************************
	 * This method gets User list of search result from database and convert
	 * that list into JSON Array then send this array to java script file on
	 * AJAX call.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *****************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyToSearch = request.getParameter("search");
			HttpSession session = request.getSession(false);
			User userObject = (User) session.getAttribute("userObject");
            UserService userService = new UserService();
            List<User> resultantUsers = new ArrayList<User>();// Array list of Users declaration
            List<User> resultantUsersDetails = new ArrayList<User>();// Array list of Users declaration
            if(userService.isEmailExists(userObject.getEmail())){
            	if (!((keyToSearch.trim()).equals(""))) {
            		String email = userObject.getEmail();
    				SearchService searchService = new SearchService();// Creating  object of  SearchService

    				// Retrieving search result by calling method retrieveSearchResult existing in SearchService class
    				resultantUsers = searchService.retrieveUsersByKey(keyToSearch,
    						email);

    				
    				for (int i = 0; i < resultantUsers.size(); i++) {
    					User user = new User();
    					user.setName(resultantUsers.get(i).getName());
    					user.setPicture(resultantUsers.get(i).getPicture());
    					user.setUserId(resultantUsers.get(i).getUserId());
    					resultantUsersDetails.add(user);
    				}

    				// converting list of Users into JSONArray
    				JSONArray searchResultJsonObject = JSONArray
    						.fromObject(resultantUsersDetails);
    				response.setContentType("application/json");
    				response.setCharacterEncoding("UTF-8");
    				response.getWriter().write(
    						new Gson().toJson(searchResultJsonObject));
    			} else {
    				response.getWriter().write("");
    			}
            }
            else {
            	// converting list of Users into JSONArray
				JSONArray searchResultJsonObject = JSONArray
						.fromObject(resultantUsersDetails);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(
						new Gson().toJson(searchResultJsonObject));
			}

			

		} catch (MetaSocioSystemException e) {
			request.setAttribute("message",
					"Can't search now. Please try after sometime. ");
			request.getRequestDispatcher("./view/exception/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
