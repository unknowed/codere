package com.codere.traspasos.filters;

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

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		/*
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		this.context.log(uri.toString());
//		this.context.log(session.getAttribute("usuario").toString());*/
	//	if(session == null && !( uri.endsWith("LoginServlet") || uri.endsWith("home.jsp") )){
		//	this.context.log("Unauthorized access request");
			//res.sendRedirect("home.jsp");
		//}else{
		//	if( session != null) {
		//		if(session.getAttribute("usuario") == null && !( uri.endsWith("LoginServlet") || uri.endsWith("home.jsp") )) {
		//			this.context.log("Unauthorized access request");
		//			res.sendRedirect("home.jsp");				
		//		}
		//	}
			// pass the request along the filter chain
			chain.doFilter(request, response);
		//}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

}
