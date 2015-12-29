package com.metasocio.controller.PhotoUploader;

import java.io.File;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.metasocio.model.usermanagement.User;
import com.metasocio.service.usermanagement.UserService;

/********************************************************************************
 * Description : Servlet implementation class PhotoUploader extending
 * HttpServlet, Managing image upload operation for a user
 ********************************************************************************/
@WebServlet("/PhotoUploader")
public class PhotoUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhotoUploader() {
		super();
	}

	/***************************************************************************
	 * This method redirect user to profile-picture view to update his profile
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 **************************************************************************/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(
				"./view/usermanagement/profile-picture.jsp").forward(request,
				response);

	}

	/***************************************************************************************
	 * This method sets the path for the selected image and then redirect to
	 * Home controller
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 **************************************************************************************/

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User userObject = (User) session.getAttribute("userObject");
		int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
		final int MAX_REQUEST_SIZE = 1024 * 1024;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Sets the size threshold beyond which files are written directly to disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);
		/* Sets the directory used to temporarily store files that are larger  than
		 *  the configured size threshold. We use temporary directory for java*/
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		// constructs the folder where uploaded file will be stored
		String uploadFolder = "C:/Users/Pooja/Desktop/16-12  final/MetaSocio/WebContent/assets/img/people";
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);

		try {
			String filePath = null;
			// Parse the request
			@SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);
			for (Object item1 : items) {
				FileItem item = (FileItem) item1;
				if (!item.isFormField()) {
					String fileName = userObject.getEmail();
					filePath = uploadFolder + "/" + fileName + ".jpg";
					File uploadedFile = new File(filePath);
					// saves the file to upload directory
					item.write(uploadedFile);
				}
			}
			userObject.setPicture("assets/img/people/" + userObject.getEmail()
					+ ".jpg");

			UserService userService = new UserService();
			userService.updateUserProfile(userObject.getPicture(),
					userObject.getEmail());
			userObject = userService.getUserByEmail(userObject.getEmail());
			session.setAttribute("userObject", userObject);
			response.sendRedirect("Home");
		} catch (Exception e) {
			request.setAttribute("message","Can't update your profile image now.Please try after some time");
			request.getRequestDispatcher("./exception/error.jsp").forward(request, response);
		}
	}

}
