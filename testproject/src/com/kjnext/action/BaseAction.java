package com.kjnext.action;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.LockFunction;
import com.kjnext.utility.OutCome;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;

	protected Logger logger = Logger.getLogger(getClass().getName());

	protected Map<String, Object> session = null;

	protected HttpServletRequest request = null;

	protected HttpServletResponse response = null;

	protected ServletContext servletContext = null;

	String navigationFlg;

	protected Boolean LockedByFlg;

	public String getNavigationFlg() {
		return navigationFlg;
	}

	public void setNavigationFlg(String navigationFlg) {
		this.navigationFlg = navigationFlg;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected void addActionMessage(OutCome outCome) {

		if (outCome != null) {

			for (OutCome.Message msg : outCome.getMessages()) {

				List<Object> parameters = msg.getParameters();

				if (msg.isParamlookUp() && (parameters != null)
						&& (!parameters.isEmpty())) {
					int i = 0;
					for (Object param : parameters) {
						parameters.set(i, getText(String.valueOf(param)));
						i++;
					}
				}

				String aMessage = getText(String.valueOf(msg.getMessageCode()),
						parameters);
				String fieldName = msg.getFieldName();

				if (outCome.isFailureResponse()) {
					throw new SecurityException(outCome.getFailureResponse()
							.name());
				} else {
					if (outCome.isSuccess()) {
						addActionMessage(aMessage);
					} else {
						if (fieldName != null) {
							addFieldError(fieldName, aMessage);
						} else {
							addActionError(aMessage);
						}
					}
				}

			}

		}

	}

	UsersService usersService = new UsersServiceImpl();

	String getEmployeeNameLabel(Integer empId) {
		if(empId==null){
			return "-";
		}
		List<UserDetail> list = usersService.getEmployeeNameLabel(empId);
		Object o = list.get(0);
		Object[] objects = (Object[]) o;
		System.out.println(objects[0] + " " + objects[1]);
		return objects[0] + " " + objects[1];
	}

	/**
	 * @return the lockedByFlg
	 */
	public Boolean getLockedByFlg() {
		return LockedByFlg;
	}

	/**
	 * @param lockedByFlg
	 *            the lockedByFlg to set
	 */
	public void setLockedByFlg(Boolean lockedByFlg) {
		LockedByFlg = lockedByFlg;
	}

	public void unLockFunction(LockFunction lockFunction) {
		System.out.println("Task >> "+lockFunction.toString());
		int userIdiNSession=0;
		if(session!=null)
		 userIdiNSession=(Integer) session.get("UserEmpId");
		int userIdiNContext=0;
		
		if(getServletContext().getAttribute(lockFunction.toString())!=null)
			userIdiNContext=(Integer)getServletContext().getAttribute(lockFunction.toString());
		
		if(userIdiNContext==userIdiNSession){
			System.out.println("unlocking for user>> "+getEmployeeNameLabel((Integer) session.get("UserEmpId")));
			getServletContext().removeAttribute(lockFunction.toString());
		}
	}

	public void unLockAll() {
		unLockFunction(LockFunction.TASK);
	}
}
