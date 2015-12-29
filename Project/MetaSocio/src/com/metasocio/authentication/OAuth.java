package com.metasocio.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.metasocio.model.usermanagement.User;
import com.metasocio.service.usermanagement.UserService;

/**************************************************************************
 * Description : Servlet implementation class OAuth extending HttpServlet, it
 * manages google authentication for email verification
 ***************************************************************************/
@WebServlet("/OAuth")
public class OAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OAuth() {
		super();

	}

	/**************************************************************************
	 * This method will get code generated from google on allow or deny.if code
	 * will null then redirect to index page otherwise redirect to either home
	 * page or profile page
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 ***************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// get code
			String code = request.getParameter("code");
			// if code generated from google is null then redirect the user to index jsp page
			if (code == null) {

				request.setAttribute("message",
						"Please click allow so that we can verify your email.");
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
				;
			}
			// format url parameters to post
			String urlParameters = "code="
					+ code
					+ "&client_id=530623119057-nfsj6k1res5fh0gvpu3epncerdrj1594.apps.googleusercontent.com"
					+ "&client_secret=52d8Fdq7JKiOqoQeZNMwBaIy"
					+ "&redirect_uri=http://localhost:8080/MetaSocio/OAuth"
					+ "&grant_type=authorization_code";
			// post parameters
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String line, outputString = "";
			// get output in outputString
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			// converting output string to JSON object
			JsonObject json = (JsonObject) new JsonParser().parse(outputString);
			// get Access Token from JSON object
			String access_token = json.get("access_token").getAsString();
			// get User Info from google
			url = new URL(
					"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
							+ access_token);
			urlConn = url.openConnection();
			outputString = "";
			// converting User Info retrieved from google to JSON object
			reader = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			// Convert JSON response(user info) into Pojo class GooglePojo
			User userObject = new Gson().fromJson(outputString, User.class);

			if (userObject.getHd().equalsIgnoreCase("metacube.com")) {
				HttpSession session = request.getSession(true);// getting HTTP session
				boolean isUserExists = false;
				UserService userService = new UserService();// creating instance of UserService class
				try {

					/*
					 * check whether user account is already exists in MetaSocio
					 * database or not by calling isEmailExists() method
					 */
					isUserExists = userService.isEmailExists(userObject
							.getEmail());
					/*
					 * if user already exists in MetaSocio database then it will redirects
					 * to Home controller otherwise redirects to profile page
					 */
					if (isUserExists) {
						User user = userService.getUserByEmail(userObject.getEmail());
						session.setAttribute("userObject", user);
						response.sendRedirect("Home");
					} else {
						session.setAttribute("userObject", userObject);
						request.setAttribute("first", "first");
						request.getRequestDispatcher("./view/usermanagement/profile.jsp").forward(request, response);
					}
				} catch (Exception e) {
					request.setAttribute("message","Can't show your timeline");
					request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message","Please enter your metacube email id");
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}

			writer.close();
			reader.close();
		} catch (MalformedURLException e) {
			request.setAttribute("message", "Authentication error");
			request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
		} catch (ProtocolException e) {
			request.setAttribute("message", "Authentication error");
			request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
		} catch (IOException e) {
			request.setAttribute("message", "Authentication error");
			request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
		}
	}
}