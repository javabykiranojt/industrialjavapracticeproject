package com.kjnext.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.utility.hibernate.HibernateUtil;

public class LoginDao {

	@SuppressWarnings("unchecked")
	public Users loginCheck(Users users) {
	Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			System.out.println("LoginDao: UserName ---> "+ users.getUname());
			System.out.println("LoginDao: Password ---> "+ users.getUpass());
			List<Users> listUsers = hibernateSession.createCriteria(
					Users.class).add(Expression.eq("uname", users.getUname()))
					.add(Expression.eq("upass", users.getUpass())).list();
			if (!listUsers.isEmpty()) {
				Users usersFrmDb = listUsers.get(0);
				System.out.println("111");
				return usersFrmDb;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateSession=null;
		}
		return null;
	}

	public UserDetail loadUser(Integer userId) {
		UserDetail userDetail = (UserDetail) HibernateUtil.getSession().load(
				UserDetail.class, userId);
		return userDetail;
	}

	public void updateLastAccesedTime(Integer userId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			String hql = "update users set lastAccessedTime='"+new java.util.Date()+"' where uId='"+userId+"'" ;
			int rowsUpadated=hibernateSession.createSQLQuery(hql).executeUpdate();
			System.out.println(rowsUpadated);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		}
	}

