package com.metasocio.authentication.filter;

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

/**************************************************************************
 * Description : Servlet filter implementation class SessionFilter extending
 * Filter, it manages session i.e. if a user is logged in then the session will
 * be present for that user so user will be redirect to requested URI in that
 * case ,otherwise he will be redirected to index page
 ***************************************************************************/
@WebFilter("/*")
public class SessionFilter implements Filter {
	private ServletContext context;

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**************************************************************************
	 * This method manages the session related operations .if a user is logged
	 * in then the session will be present for that user so user will be
	 * redirect to requested URI in that case ,otherwise he will be redirected
	 * to index page
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 ***************************************************************************/
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		this.context.log("Requested Resource::" + uri);
		HttpSession session = req.getSession(false);
		if (session == null
				&& (uri.endsWith("CommentHelper")
						|| uri.endsWith("EmailUtil")
						|| uri.endsWith("Followers")
						|| uri.endsWith("Following")
						|| uri.endsWith("FollowPeople")
						|| uri.endsWith("Unfollow")
						|| uri.endsWith("GroupHelper")
						|| uri.endsWith("GroupHome")
						|| uri.endsWith("Groups")
						|| uri.endsWith("LikeManager")
						|| uri.endsWith("LikeUtility")
						|| uri.endsWith("PhotoUploader")
                        || uri.endsWith("PostUtility")
						|| uri.endsWith("PostHelper")
					    || uri.endsWith("Home")
						|| uri.endsWith("PostLoader")
						|| uri.endsWith("NameSearch")
						|| uri.endsWith("UserProfile")
						|| uri.endsWith("LogOut")
						|| uri.endsWith("followers.jsp")
						|| uri.endsWith("group.jsp")
						|| uri.endsWith("home.jsp")
						|| uri.endsWith("profile.jsp"))) {
			
			this.context.log("Unauthorized access request");
			String message = "Unauthorized access request,Please Login first";
			request.setAttribute("message", message);
			request.getRequestDispatcher("index.jsp").forward(req, res);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

}
