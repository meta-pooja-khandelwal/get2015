package com.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Validation
 */
@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password=request.getParameter("password");
		String confirmPassword=request.getParameter("confirmPassword");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		String address=request.getParameter("addressLine");
		String firstName=request.getParameter("firstName");
	    String lastName=request.getParameter("lastName");
		String regex="[a-zA-Z]+";
		String message="";
		//System.out.println("state name"+state);
	    int flag;
		if(!firstName.matches(regex)){
			flag=0;
			message="invlid first name";
		}
		
		else if(!lastName.matches(regex)){
			flag=0;
			message="invlid last name";
		}
		else if (password.length()<8) {
			flag=0;
			message="Password Length should be greater than 8";
		}
		else if(!confirmPassword.equals(password)){
			flag=0;
			message="Password mismatch!!";
		}
		else if(state==null){
			flag=0;
			message="State is not selected";
		}
		else if(city==null){
			flag=0;
			message="City is not selected";
		}
		
		else if(address==null){
			flag=0;
			message="Please fill address field";
		}
		else {
			flag=1;
			message="Registration Successful";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("flag",flag);
		request.getRequestDispatcher("ValidationDispatcher").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
