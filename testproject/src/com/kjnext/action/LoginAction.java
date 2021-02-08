package com.kjnext.action;

import java.util.Set;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.Status;
import com.kjnext.service.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Users users;

	UsersService usersService = new UsersServiceImpl();

	LoginServiceImpl loginServiceImpl = new LoginServiceImpl();

	UserDetail userDetail = null;



	public String loginCheck() {
		System.out.println("i AM IN LOGIN CHECK");
		if (users == null) {
			addActionError(getText("error.login"));
			session.put("loginErr", "Error in Login");
			return "error";
		}
		users = loginServiceImpl.getLoginDetail(users);
		if (users == null) {
			addActionError(getText("error.login"));
			session.put("loginErr", "Error in Login");
			return "error";
		}
		Set<UserDetail> setUserDetail= users.getUserDetails();
		for (UserDetail userDetailTemp : setUserDetail) {
			
			userDetail=userDetailTemp;
		}
		request.getSession().setMaxInactiveInterval(12000);
		if(userDetail!=null){
			
			if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
				((org.apache.struts2.dispatcher.SessionMap) session).clear();
			}
			if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
				((org.apache.struts2.dispatcher.SessionMap) session)
						.invalidate();
			}
			session = ActionContext.getContext().getSession();
			if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
				request.getSession().setMaxInactiveInterval(1200);
				session.put("userDetail", userDetail);
				session.put("users", users);
				session.put("usersType", users.getUtype());
				session.put("UserName", userDetail.getFirstName() +" "+userDetail.getLastName() );
				session.put("UserEmpId", userDetail.getUserEmpId());
				session.put("uId", users.getUid());
				session.put("lastAccesedTime", users.getLastAccessedTime());
				session.put("gender", userDetail.getGender());
			}
		}
		System.out.println("user details >>> "+userDetail);
		if(userDetail.getStatus()==null||!Status.ACTIVE.toString().equals(userDetail.getStatus().toString())){
			addActionError(getText("error.login"));
			System.out.println("*****");
			return "error";
		}
		if (users.getUtype().equals("UA")) {
			updateLastAccesedTime(users.getUid());
			return "user";
		} else if (users.getUtype().equals("CA")) {
			updateLastAccesedTime(users.getUid());
			return "client";
		} else if (users.getUtype().equals("AE")){
			updateLastAccesedTime(users.getUid());
			return "admin";
		}
		addActionError(getText("error.login"));
		return "error";
	}

	public String userLogOff() {

		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
			((org.apache.struts2.dispatcher.SessionMap) session)
					.remove("userDetail");
		}

		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
			((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		}

		return SUCCESS;
	}
	
	private void updateLastAccesedTime(Integer userId){
		loginServiceImpl.updateLastAccesedTime(userId);
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	/**
	 * @return the userDetail
	 */
	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	/**
	 * @param userDetail the userDetail to set
	 */
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
}
