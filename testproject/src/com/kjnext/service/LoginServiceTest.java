package com.kjnext.service;

import com.kjnext.dmart.hibernate.Users;

public class LoginServiceTest {



	public static void main(String[] args) {
		LoginServiceImpl impl=new LoginServiceImpl();
		Users userTo=new Users();
		userTo.setUname("USER12");
		userTo.setUpass("USER1");
		Users users = impl.getLoginDetail(userTo);

		System.out.println(users);
	}
}
