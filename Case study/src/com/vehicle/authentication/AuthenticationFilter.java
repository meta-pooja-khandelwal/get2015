/**
 * @author Pooja Khandelwal
 * @created date 29/10/2015
 * @name AuthenticationFilter
 * @description this is filter which will accept the request and then perform some operation with this request and then if valid then pass it to requested servlet or jsp page, otherwise send it to login.jsp page 
 */
package com.vehicle.authentication;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vehicle.user.UserType;

/**
 * Servlet Filter implementation class ServletFilter
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private ServletContext context;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("filter 1");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::" + uri);
		HttpSession session = req.getSession(false);
		// System.out.println(session);
		if (session == null
				&& (uri.endsWith("CreateCar")
						|| uri.endsWith("CreateCarController")
						|| uri.endsWith("DeleteCarController")
						|| uri.endsWith("EditCar")
						|| uri.endsWith("EditCarController")
						|| uri.endsWith("CreateCar.jsp") || uri
							.endsWith("EditCar.jsp"))) {
			this.context.log("Unauthorized access request");
			String message = "Unauthorized access request,Please Login first";
			request.setAttribute("message", message);
			request.setAttribute("person", UserType.admin);
			request.getRequestDispatcher("./view/Login.jsp").forward(req, res);
		} else if (session != null) {
			if (session.getAttribute("admin") == null
					&& (uri.endsWith("CreateCar")
							|| uri.endsWith("CreateCarController")
							|| uri.endsWith("DeleteCarController")
							|| uri.endsWith("EditCar")
							|| uri.endsWith("EditCarController")
							|| uri.endsWith("CreateCar.jsp") || uri
								.endsWith("EditCar.jsp"))) {
				// System.out.println("hi");
				this.context.log("Unauthorized access request");
				String message = "Unauthorized access request,Please Login first";
				request.setAttribute("message", message);
				request.setAttribute("person", UserType.admin);
				request.getRequestDispatcher("./view/Login.jsp").forward(req,
						res);
				;
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
