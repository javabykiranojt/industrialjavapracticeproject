/**
 * 
 */
package com.kjnext.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.kjnext.utility.hibernate.HibernateUtil;

/**
 * @author Kiran
 * 
 */
public class StartUpLoad extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.dispatcher.FilterDispatcher#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init() throws ServletException {
		HibernateUtil.getSession();
	}
}
