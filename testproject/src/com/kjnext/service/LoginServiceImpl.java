package com.kjnext.service;

import com.kjnext.dao.LoginDao;
import com.kjnext.dmart.hibernate.Users;

public class LoginServiceImpl {
	LoginDao loginDao = new LoginDao();

	public Users getLoginDetail(Users userTo) {
		try {
			System.out
					.println("service: UserName From UserTranObject(UserTo)--->"
							+ userTo.getUname());
			System.out
					.println("service: Password From UserTranObject(UserTo)--->"
							+ userTo.getUpass());
			return loginDao.loginCheck(userTo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDao.loginCheck(userTo);
	}

	public void updateLastAccesedTime(Integer userId) {
		loginDao.updateLastAccesedTime(userId);
	}
}
