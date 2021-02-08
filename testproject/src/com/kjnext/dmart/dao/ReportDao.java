package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.History;
import com.kjnext.dmart.hibernate.HistoryUserDetail;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;

public interface ReportDao {

//	 Client Report
	List<UserFeedback> generateClientReport(int month);
	
	List<UserFeedback> generateReportAsClient(int uId,int month);
	List<UserFeedback> generateReportAsClient(int uId);
		
	public  List<HistoryUserDetail> showhistroyuserdetail();
	public List<HistoryUserDetail> showHistoryUserDetailByUid(Integer uid);

//		 ********************* START BY SNEHA	
		
		List<History> showhistroy();

		public UserDetail getUidName(Integer uid);
		public List<History> showHistoryByUid(Integer uid);
		
		
// ********************* END BY SNEHA
		//public UserDetail getUserDetail();
		//List<Users> getUserDetail();

		List<History> selecthistory();
}
