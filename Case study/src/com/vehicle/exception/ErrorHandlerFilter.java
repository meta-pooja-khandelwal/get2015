package com.vehicle.exception;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class ErrorHandler
 */

@WebFilter("/*")
public class ErrorHandlerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ErrorHandlerFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {

			chain.doFilter(request, response);
		}

		catch (Exception ex) {
			// call ErrorHandler and dispatch to errorPage.jsp
			System.out.println("[" + ex.getMessage() + "]");
			request.setAttribute("errorMessage",
					"Sorry an ecxeption occured ! May be Car does not exist");
			request.getRequestDispatcher("./view/errorPage.jsp").forward(
					request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
