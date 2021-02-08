package com.kjnext.dmart.dao.Impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.ReportDao;
import com.kjnext.dmart.hibernate.History;
import com.kjnext.dmart.hibernate.HistoryUserDetail;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.utility.hibernate.HibernateUtil;

public class ReportDaoImpl implements ReportDao {
	History history;
	Users users;
	
	
	
	public List<History> showhistroy()
	{
//		 ************************ START BY SNEHA		
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
			List<History> historyList = hibernateSession.createCriteria(History.class).list();
			return historyList;
		}catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		
		
		/*System.out.println("i am in dao layer mathod");
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
		List<History> historyList = (List<History>) hibernateSession.createCriteria(History.class).list();

		//System.out.println("print"+userdetails);
		Iterator itr = (Iterator) historyList.iterator();
		while(((java.util.Iterator<History>) itr).hasNext()) {
	         Object element = ((java.util.Iterator<History>) itr).next();
	         System.out.print(element + " ");
	      }
		return historyList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}*/
	}
	
	public List<History> showHistoryByUid(Integer uid){
		System.out.println("333");
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
			List<History> historyList = hibernateSession.createCriteria(History.class).createCriteria("userDetail").add(Expression.eq("userEmpId", uid)).list();
			
			return historyList;
		}catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		
	}
	
	
// ************************ END BY SNEHA	

	
	 public List<History> selecthistory()
	{
		System.out.println("i am in dao of select  history");
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
		List<History> historyList = (List<History>) hibernateSession.createCriteria(History.class).list();

		//System.out.println("print"+userdetails);
		/*Iterator itr = (Iterator) historyList.iterator();
		while(((java.util.Iterator<History>) itr).hasNext()) {
	         Object element = ((java.util.Iterator<History>) itr).next();
	         System.out.print(element + " ");
	      }*/
		return historyList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		
	}

	/*public List<Users> getUserDetail(Integer uid) {
		UserDetail userDetail = null;
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			(List<Users>)	userDetail = (UserDetail) hibernateSession.load(UserDetail.class, uid);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return  userDetail;
	}
*/
//	 Client Report
	 @SuppressWarnings("unchecked")
	public List<UserFeedback> generateReportAsClient(int uId) {
		 Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			try {				
				String sentStatus ="Y";
				List<UserFeedback> userFeedbackList =hibernateSession.createCriteria(UserFeedback.class).
					add(Expression.eq("sentStatus", sentStatus)).
					createCriteria("clients").createCriteria("userDetail").
					createCriteria("users").add(Expression.eq("uid", uId)).list();
		        		
				return userFeedbackList;
			} catch (Exception e) {
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
		}	
	
	@SuppressWarnings("unchecked")
	public List<UserFeedback> generateReportAsClient(int uId,int month) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
    	
		try
		{	 
			List<UserFeedback> userFeedbackList=null;
			Date start=null;
        	Date end=null;			
			String sentStatus ="Y";			
			if(month >0){
					start=DateUtils.truncate(DateUtils.addMonths(new Date(), -month) , Calendar.MONTH);
					//System.out.println(start);
					end = DateUtils.add(new Date(), 0, month);
					//System.out.println(end);
					
					userFeedbackList =hibernateSession.createCriteria(UserFeedback.class).
					add(Expression.eq("sentStatus", sentStatus)).add(Restrictions.between("sentDtm",start,end)).
					createCriteria("clients").createCriteria("userDetail").
					createCriteria("users").add(Expression.eq("uid", uId)).list();
					
				}else{
	        		 userFeedbackList =(List<UserFeedback>)hibernateSession.createCriteria(UserFeedback.class).
	        		 add(Expression.eq("sentStatus", sentStatus)).
	        		 createCriteria("clients").createCriteria("userDetail").
	        		 createCriteria("users").add(Expression.eq("uid", uId)).list();
						}        					
			return userFeedbackList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}	
	public UserDetail getUidName(Integer uid)
	{
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
			UserDetail uidname=(UserDetail) hibernateSession.get(UserDetail.class,uid);
		//System.out.println(uidString);
		return uidname;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}
	public List<HistoryUserDetail> showhistroyuserdetail()
	{
//		 ************************ START BY LUCKY		
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try{
			List<HistoryUserDetail> HistoryUserDetail = hibernateSession.createCriteria(HistoryUserDetail.class).list();
			return HistoryUserDetail;
		}catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
	}
  public List<HistoryUserDetail> showHistoryUserDetailByUid(Integer uid){
	System.out.println("333");
	Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
	try{
		List<HistoryUserDetail> historyList = hibernateSession.createCriteria(HistoryUserDetail.class).createCriteria("userDetailByUid").add(Expression.eq("userEmpId", uid)).list();
		
		return historyList;
	}catch (Exception e) {
		e.printStackTrace();
		hibernateSession.getTransaction().rollback();
		throw new RuntimeException(e.getMessage());
	}finally{
		hibernateSession=null;
	}
	
}
	
	public List<UserFeedback> generateClientReport(int month) {
		List<UserFeedback> list=null;
		HibernateUtil.closeSession();
    	Session session=HibernateUtil.getSession();
    	session.beginTransaction();
    	Criteria criteria=session.createCriteria(UserFeedback.class);
		try
		{	 
        	Date start=null;
        	Date end=null;
        	// Report for 2 months
			if(month==2)
			{
					start=DateUtils.truncate(DateUtils.addMonths(new Date(), -2) , Calendar.MONTH);
					end = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.MONTH),2);
					criteria.add(Restrictions.between("createdDtm",start,end));
					list=criteria.list();
					System.out.println("client report list="+list);
			       
				return list;
			}
			// Report for 3 months
			else if(month==3)
			{
				
				return list;
			}
			// Report for 6 months
			else if(month==6)
			{
				return list;
			}
			// Report for 1 year
			else
			{
				return list;
			}
		}//end try block
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return list;
	}//end generateClientReport() method.

	public UserDetail getUserDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
