package com.kjnext.dmart.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.kjnext.dmart.dao.ReportDao;
import com.kjnext.dmart.dao.Impl.ReportDaoImpl;
import com.kjnext.dmart.hibernate.History;
import com.kjnext.dmart.hibernate.HistoryUserDetail;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ReportService;
import com.kjnext.dmart.vo.Status;

public class ReportServiceImpl implements ReportService {
	ReportDao reportDao=new ReportDaoImpl();
	// Client Report
	public List<UserFeedback> clientOneYearReport(int month) {
	
		return reportDao.generateClientReport(month);
	}

	public List<UserFeedback> clientSixMonthReport(int month) {
		return reportDao.generateClientReport(month);
	}

	public List<UserFeedback> clientThreeMonthReport(int month) {
		return reportDao.generateClientReport(month);
	}

	public List<UserFeedback> clientTwoMonthReport(int month) {
		return reportDao.generateClientReport(month);
	}
	
	public List<History> selecthistory()
	{
		System.out.println("i am in select history");
		List< History > historyList= reportDao.selecthistory();
		return historyList;
	}
	
	public List<HistoryUserDetail > getallhistoryuserdetail()	  {
		return reportDao.showhistroyuserdetail();
		  
	  }
public   List getHistoryUserDetailByUid(Integer uid)
	{
		return reportDao.showHistoryUserDetailByUid(uid);
	}

//	 ********************* START BY SNEHA	
	public   List< History > getallhistory()
	  {
		  /*System.out.println("i am in service layer mathod");
		  List< History > historyList= reportDao.showhistroy();
		  System.out.println("i am back from dao");
		  System.out.println("ssss"+historyList);*/
		return reportDao.showhistroy();
		  
	  }
	
	public   List< History > getHistoryByUid(Integer uid){
		return reportDao.showHistoryByUid(uid);
	}
	public UserDetail getUidName(Integer id)
	{	
		return reportDao.getUidName(id);
		
	}
	
// *********************END BY SNEHA

	/*public   List< Users> usersdetails()
	  {
		  System.out.println("i am in service layer userdetails mathod");
		  List<  Users > historyList=(List< Users>) reportDao.getUserDetail();
		  System.out.println("i am back from userdtails dao");
		  System.out.println("ssss"+ userDetail);
		  return   (List<Users>) userDetail;
		  
	  }*/
	  /*public static void main(String[] args) throws NullPointerException
	  {
		  List< History > historyList=adminActivitiesDao.showhistroy();
		
	}*/

	public List<Users> usersdetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UserFeedback> generateReportAsClient(int uId, int month) {
		return reportDao.generateReportAsClient(uId, month);
	}

	
	public List<UserFeedback> generateReportAsClient(int uId) {
		List<UserFeedback> list1 = reportDao.generateReportAsClient(uId);
		List<UserFeedback> list2 = new ArrayList<UserFeedback>();
		for (UserFeedback userFeedback : list1) {
			if (!userFeedback.getStatus().equals(Status.REJECTED_ADMIN.toString())
					|| !userFeedback.getSentStatus().equals(Status.REASSIGNED_BY_CLIENT.toString())) {
				list2.add(userFeedback);
			}
		}
		return list2;
	}

}
