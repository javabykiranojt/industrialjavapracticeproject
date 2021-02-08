package com.kjnext.utility;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.kjnext.dmart.vo.LockFunction;

public class UnlockTaskAfterSessionTimeOut implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void sessionDestroyed(HttpSessionEvent session) {
		//System.out.println("Task >> "+lockFunction.toString());
		int userIdiNSession=0;
		if(session.getSession().getAttribute("UserEmpId")==null){
			session.getSession().getServletContext().removeAttribute(LockFunction.TASK.toString());
			return;
		}
		if(session!=null)
		 userIdiNSession=(Integer) session.getSession().getAttribute("UserEmpId");
		int userIdiNContext=0;
		if(session.getSession().getServletContext().getAttribute(LockFunction.TASK.toString())!=null)
			userIdiNContext=(Integer)session.getSession().getServletContext().getAttribute(LockFunction.TASK.toString());
		
		if(userIdiNContext==userIdiNSession){
			//System.out.println("unlocking for user>> "+getEmployeeNameLabel((Integer) session.get("UserEmpId")));
			session.getSession().getServletContext().removeAttribute(LockFunction.TASK.toString());
		}
	}

}
