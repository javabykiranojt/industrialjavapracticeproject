package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.History;
import com.kjnext.dmart.hibernate.HistoryUserDetail;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;

public interface ReportService {
	
	List<UserFeedback> generateReportAsClient(int uId,int month);
	List<UserFeedback> generateReportAsClient(int uId);
	
	public UserDetail getUidName(Integer id);
	List<UserFeedback> clientTwoMonthReport(int month);

	List<UserFeedback> clientThreeMonthReport(int month);

	List<UserFeedback> clientSixMonthReport(int month);

	List<UserFeedback> clientOneYearReport(int month);
	
	public   List getHistoryUserDetailByUid(Integer uid);
	public List<HistoryUserDetail> getallhistoryuserdetail();
	
//	********************* START BY SNEHA	
	public   List< History > getHistoryByUid(Integer uid);
//********************* END BY SNEHA	

	public List<History> getallhistory();
	public List<History> selecthistory();

}
