package com.kjnext.interceptor;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * @author Kiran Digrase
 *
 */
public class LoggingInterceptor implements Interceptor {

	private static final long serialVersionUID = 106L;

	private static Logger logger = Logger.getLogger(LoggingInterceptor.class);

	public void destroy() {
		logger.debug("destroy");
	}

	public void init() {
		logger.debug("init");

	}

	
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("Enter LoggingInterceptor invoking :"
				+ invocation.getAction().getClass().getName() + "."
				+ invocation.getInvocationContext().getName());
			long stTime = new Date().getTime();
		String result = null;
		try {
			
			result = invocation.invoke();
		} catch (Throwable e) {
			logger.error("LoggingInterceptor error :" + e.getMessage(), e);
			ServletActionContext.getRequest().setAttribute(
					"com.project.customization.applicationError.exception", e);
			return "applicationError";
		}
		System.out.println("Exit LoggingInterceptor from :"
				+ invocation.getAction().getClass().getName() + "."
				+ invocation.getInvocationContext().getName());
		long timeSpan = new Date().getTime()-stTime;
		System.out.println("Exit time is : "+timeSpan+" milliSeconds.");
		return result;
	}

}




