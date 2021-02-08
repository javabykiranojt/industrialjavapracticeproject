package com.kjnext.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.Users;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Kiran Digrase
 *
 */
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = 108L;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {

		System.out.println("AuthenticationInterceptor :intercept(ActionInvocation) ");
		Users user = null;
		UserDetail userDetail = null;
		System.out.println("Action Name :"	+ actionInvocation.getInvocationContext().getName());
		long stTime = new Date().getTime();
		Map session = actionInvocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) actionInvocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		if (session != null && !session.isEmpty()) {
			userDetail = (UserDetail) session.get("userDetail");
			user = (Users) session.get("users");
			System.out.println("userdetail from session >>> " + userDetail);
			System.out.println("user from session >>> " + user);
			
		} else {
			ValidationAware action = (ValidationAware) actionInvocation
					.getAction();
			action.getActionErrors().clear();
			action
					.addActionError(((ActionSupport) actionInvocation
							.getAction())
							.getText("login.errormsg.sessionExpire"));
			return "login";
		}
		// Check the Authentication
		boolean isAuthenticated = false;
		boolean isAuthorised = false;

		if (user != null || userDetail!=null) {

			isAuthenticated = true;
			isAuthorised = true;
		}

		if (!isAuthenticated) {
			return "login";
		}

		if (!isAuthorised) {
			return "unauthorized";
		} else {
			long timeSpan = new Date().getTime()-stTime;
			System.out.println("Exit time is : "+timeSpan+" milliSeconds.");
			return actionInvocation.invoke();
		}

	}

}





