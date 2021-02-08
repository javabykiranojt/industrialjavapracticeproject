package com.kjnext.dmart.vo;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.Users;

public class SearchCriteriaUsers {
	
	UserDetail userDetail;
	
	Users users;

	/**
	 * @return the userDetail
	 */
	public UserDetail getUserDetail() {
		return userDetail;
	}

	/**
	 * @param userDetail the userDetail to set
	 */
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	/**
	 * @return the users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Users users) {
		this.users = users;
	}

}
