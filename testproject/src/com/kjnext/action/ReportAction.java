package com.kjnext.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.History;
import com.kjnext.dmart.hibernate.HistoryUserDetail;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.FeedbackService;
import com.kjnext.dmart.service.ReportService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.FeedbackServiceImpl;
import com.kjnext.dmart.service.Impl.ReportServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.model.HistoryActivity;
import com.kjnext.model.HistoryUserDetailActivity;
import com.kjnext.utility.OutCome;

public class ReportAction extends BaseAction {
	
	UserFeedback userFeedback=new UserFeedback();
	List<UserFeedback> userFeedbackList=new ArrayList();
	String navigationFlag;
	ReportService reportService=new ReportServiceImpl();
	FeedbackService feedbackService = new FeedbackServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	private Integer frmClientsId;
	List<Clients> clientsList = null;
	ClientService clientService = new ClientServiceImpl();
	List< History > historyList;
	History hist;
	List<String> date=new ArrayList<String>();
	List<UserDetail> historyUidList;
	
	HistoryActivity ha;
	List<HistoryActivity> allActivityList;
	
	List test = new ArrayList();
	
	private String uidName;
	private Integer id ;
	HistoryUserDetailActivity huda;
	ArrayList<HistoryUserDetailActivity>	allActivityListuserdetail;
	List<HistoryUserDetail> historyuderdetailList;
	String Activity;
	StringBuffer historyById;	
	private String historyString;
	private List feedbackId;
	private String pagetTitle;
	private String pagesTitle;
	//amarjit
	private String feedbackString;
	List<Integer> fIdInd = new ArrayList<Integer>();
	List<UserFeedback> unsentFeedbackList=new ArrayList();
	List<UserFeedback> sentFeedbackList=new ArrayList();
	Integer noMonth;
	
	private String actionMsg;
	
	

	public String feedbackReport()
	{	
		pagetTitle=getText("feedback.report");
		if (getActionMsg()!=null) {
			OutCome outCome=new OutCome();
			if (getActionMsg().equals("feedEdited")) {
				
				//addActionError("No Client point exists for "+userFeedback.getClients().getClientName()+".Please assign.");
				outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message("feedback.updated.byAdmin.success"));
				
			}
			addActionMessage(outCome);
		}
		System.out.println(getFeedbackId());
		System.out.println(getFrmClientsId());
		if (getFrmClientsId()!= null && getFrmClientsId()!= 0) {
			userFeedbackList.addAll(feedbackService.getAllFeedback(getFrmClientsId()));
			clientsList = clientService.showAllClients();
			for (UserFeedback userFeedback: userFeedbackList) {
				Integer uid = userFeedback.getModifiedUId();
				String taskStatus = userFeedback.getStatus();
				if (taskStatus.equals("FINISHED_USER")) {
					userFeedback.setTaskUserStatus("Feedback Submitted by Agent");
				} else  if(taskStatus.equals("FINISHED_ADMIN")){
					userFeedback.setTaskUserStatus("Feedback Approved By Admin");
				}else  if(taskStatus.equals("REJECTED_ADMIN")){
					userFeedback.setTaskUserStatus("Feedback Rejected");
				}
				
				
				if (userFeedback.getSentStatus()!=null) {
					if(userFeedback.getSentStatus().equalsIgnoreCase("y")) {
						userFeedback.setFeedSentStatus("Reported to Client");
					} else {
						userFeedback.setFeedSentStatus("Not Reported to Client");
					}
				}else {
					userFeedback.setFeedSentStatus("--");
				}
				
				
				if(uid!=null){
					Users tempUsers =usersService.getUsers(uid);
					Set<UserDetail> set = tempUsers.getUserDetails();
					for (UserDetail uD : set) {
						userFeedback.setModifiedUIdName(uD.getFirstName()+" "+uD.getLastName());
					}
				}else{
					userFeedback.setModifiedUIdName("-");
				}				
				uid = userFeedback.getCreatedUId();
				if(uid!=null){
					Users tempUsers =usersService.getUsers(uid);
					Set<UserDetail> set = tempUsers.getUserDetails();
					for (UserDetail uD : set) {
						userFeedback.setCreatedUIdName(uD.getFirstName()+" "+uD.getLastName());
					}
				}else {
					userFeedback.setCreatedUIdName("-");
				}			
			}
		} else {
			//default action
			UserFeedback usrFeedback = new UserFeedback();
			userFeedbackList.addAll(feedbackService.getAllFinishedTaskByUser(usrFeedback));
			userFeedbackList.addAll(feedbackService.getAllApprovedFeedbackByAdmin(usrFeedback));
			userFeedbackList.addAll(feedbackService.getAllRejectedFeedbackByAdmin(usrFeedback));
			clientsList = clientService.showAllClients();
			for (UserFeedback userFeedback: userFeedbackList) {
				Integer uid = userFeedback.getModifiedUId();
				String taskStatus = userFeedback.getStatus();
				if (taskStatus.equals("FINISHED_USER")) {
					userFeedback.setTaskUserStatus("User Submitted Feedback");
				} else  if(taskStatus.equals("FINISHED_ADMIN")){
					userFeedback.setTaskUserStatus("Approved By Admin");
				}else  if(taskStatus.equals("REJECTED_ADMIN")){
					userFeedback.setTaskUserStatus("Feedback Rejected");
				}
				
				if (userFeedback.getSentStatus()!=null) {
					if(userFeedback.getSentStatus().equalsIgnoreCase("y")) {
						userFeedback.setFeedSentStatus("Reported to Client");
					} else {
						userFeedback.setFeedSentStatus("Not Reported to Client");
					}
				}else {
					userFeedback.setFeedSentStatus("--");
				}
				
				
				if(uid!=null){
					Users tempUsers =usersService.getUsers(uid);
					Set<UserDetail> set = tempUsers.getUserDetails();
					for (UserDetail uD : set) {
						userFeedback.setModifiedUIdName(uD.getFirstName()+" "+uD.getLastName());
					}
				}else {
					userFeedback.setModifiedUIdName("-");
				}				
				uid = userFeedback.getCreatedUId();
				if(uid!=null){
					Users tempUsers =usersService.getUsers(uid);
					Set<UserDetail> set = tempUsers.getUserDetails();
					for (UserDetail uD : set) {
						userFeedback.setCreatedUIdName(uD.getFirstName()+" "+uD.getLastName());
					}
				}else {
					userFeedback.setCreatedUIdName("-");
				}				
			}
		}		
 		return SUCCESS;
	}
	
	public String emailFeedback(){
		String freturn="";
		//OutCome outCome=new OutCome();
		Integer ucount =0;
		Integer scount = 0;
		System.out.println(getFeedbackString());
		String[] fId =getFeedbackString().split(",");
		for (int i = 0; i < fId.length; i++) {
			fIdInd.add(Integer.parseInt(fId[i]));
		}
		for (Integer fidx : fIdInd) {
			UserFeedback userFback = feedbackService.loadUserFeedback(fidx);		    
			if (userFback.getClients().getUserDetail().getUsers()!=null	) {
				freturn= SUCCESS;
				if(userFback.getSentStatus()== null){
					ucount++;
				}else
				if(userFback.getSentStatus().equalsIgnoreCase("y")){
					scount++;
				}else
				if(userFback.getSentStatus().equalsIgnoreCase("n")){
					ucount++;
				}
							
				}
			else{
				setUserFeedback(userFback);
				freturn= "noClientExist"; 	
			}
			userFeedback.setClientname(userFback.getClients().getClientName());
		}
		userFeedback.setAllCount(ucount+scount);
		userFeedback.setUnsentCount(ucount);
		userFeedback.setSentCount(scount);
			
		return freturn;		
	}
	
	public String emailFeedbackToClient(){
		System.out.println(getFeedbackString());
		String[] fId =getFeedbackString().split(",");
		for (int i = 0; i < fId.length; i++) {
			fIdInd.add(Integer.parseInt(fId[i]));
		}
		for (Integer fidx : fIdInd) {
			UserFeedback userFback = feedbackService.loadUserFeedback(fidx);	
			userFback.setSentStatus("Y");
			userFback.setSentDtm(new Date());
			userFback.setModifiedUId((Integer)session.get("uId"));
			feedbackService.updateUserFeedback(userFback, fidx);
		}
		setNavigationFlag("success");
		return SUCCESS;		
	}
	
	public String clientError(){
		setNavigationFlag("success");
		return SUCCESS;	
	}
	
	public String taskReport()
	{
		return SUCCESS;
	}
	public String usersReport()
	{
		return SUCCESS;
	}
	
	
//	************************START BY SNEHA FOR REPORTS - ACTIVITIES
	
	public String activityReport() {
		try{
		pagesTitle = getText("all.activities");
		historyList = reportService.getallhistory();
		
		historyuderdetailList = reportService.getallhistoryuserdetail();

		// historyUidList=new ArrayList<UserDetail>();
		historyUidList = usersService.allUsers();

		List<String> hs = new ArrayList<String>();
		allActivityList = new ArrayList<HistoryActivity>();
		if(historyList!=null){
		for (History Hactivity : historyList) {
			ha = new HistoryActivity();
			Date d = Hactivity.getDtm();
			ha.setDate(d);
			if ((Hactivity.getOldValue() == null)
					&& (Hactivity.getUserDetail() == null)) {

				String ac = " A NEW VALUE " + Hactivity.getNewValue()
						+ " IS ADDED IN " + Hactivity.getColumnName() + " FOR "
						+ Hactivity.getFunction() + " FUNCTIONALITY ";

				ha.setActivityDetails(ac);

			} else if ((Hactivity.getOldValue() == null)
					&& (Hactivity.getUserDetail() != null)) {
				String ac = " " + Hactivity.getUserDetail().getFirstName()
						+ " " + Hactivity.getUserDetail().getLastName()
						+ " HAS ADDED   " + Hactivity.getNewValue() + " IN "
						+ Hactivity.getColumnName() + " FOR "
						+ Hactivity.getFunction() + " FUNCTIONALITY ";
				ha.setActivityDetails(ac);
			} else if ((Hactivity.getOldValue() != null)
					&& (Hactivity.getUserDetail() == null)) {
				String ac = " IN " + Hactivity.getColumnName() + " , "
						+ Hactivity.getOldValue() + " HAS BEEN CHANGED TO"
						+ Hactivity.getNewValue() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY ";
				ha.setActivityDetails(ac);
			} else {
				String ac = Hactivity.getUserDetail().getFirstName() + "  "
						+ Hactivity.getUserDetail().getLastName()
						+ " HAS CHANGED " + Hactivity.getColumnName()
						+ " FROM " + Hactivity.getOldValue() + " TO "
						+ Hactivity.getNewValue() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY . ";
				ha.setActivityDetails(ac);
			}
			allActivityList.add(ha);
		}
		}
		// ================lucky================

		allActivityListuserdetail = new ArrayList<HistoryUserDetailActivity>();
		
		for(HistoryUserDetail check:historyuderdetailList)
		{
			System.out.println(check.getSrNo());
			
		}
		for(HistoryUserDetail Hudactivity : historyuderdetailList) {
			huda = new HistoryUserDetailActivity();
			Date d = Hudactivity.getDtm();
			huda.setDate(d);
			if ((Hudactivity.getOldValue() == null)
					&& (Hudactivity.getUserDetailByUid() != null)
					
					&& (Hudactivity.getUserDetailByUid() != null)) {
					System.out.println(Hudactivity.getSrNo());
				String acud = " A NEW VALUE " + Hudactivity.getNewValue()
						+ " IS ADDED IN " + Hudactivity.getColumnName()
						+ " FOR " + Hudactivity.getFunction()
						+ " ON DATE "
						+ Hudactivity.getDtm();

				huda.setActivityDetails(acud);

			} else if ((Hudactivity.getOldValue() == null)
					&& (Hudactivity.getUserDetailByForUser() != null)) {
				System.out.println(Hudactivity.getSrNo());
				String acud = " "
						+ Hudactivity.getUserDetailByForUser().getFirstName()
						+ " "
						+ Hudactivity.getUserDetailByForUser().getLastName()
						+ " HAS ADDED   " + Hudactivity.getNewValue() + " IN "
						+ Hudactivity.getColumnName() + " FOR "
						+ Hudactivity.getFunction() 
						+ " ON DATE "
						+ Hudactivity.getDtm();

				huda.setActivityDetails(acud);
			} else if ((Hudactivity.getOldValue() != null)
					&& (Hudactivity.getUserDetailByForUser() == null)) {
				System.out.println(Hudactivity.getSrNo());
				String acud = " IN " + Hudactivity.getColumnName() + " , "
						+ Hudactivity.getOldValue() + " HAS BEEN CHANGED TO"
						+ Hudactivity.getNewValue() + " IN "
						+ Hudactivity.getFunction() 
						+ " ON DATE " 
						+ Hudactivity.getDtm();
				huda.setActivityDetails(acud);

			} 
			else{
				UserDetail uidname=(UserDetail) reportService.getUidName(Hudactivity.getUserDetailByUid().getUsers().getUid());
				String acud = uidname.getFirstName()
						+ "  "
						+ " HAS CHANGED "
						+ Hudactivity.getUserDetailByForUser().getFirstName()
						+ "'s"
						+ " "
						+ Hudactivity.getColumnName()
						+ " FROM "
						+ Hudactivity.getOldValue()
						+ " TO "
						+ Hudactivity.getNewValue()
						+ " ON DATE "
						+ Hudactivity.getDtm();
				huda.setActivityDetails(acud);
				System.out.println(acud);
			}
			
			allActivityListuserdetail.add(huda);
		}
		}catch (Exception e) {
		e.printStackTrace();
		}
		return SUCCESS;
	}

	public String showHistoryForId() {
		if (session.get("usersType").toString().equals("UA")) {
			Integer integer = (Integer) session.get("uId");
			historyList = reportService.getHistoryByUid(integer);
			historyuderdetailList = reportService
					.getHistoryUserDetailByUid(integer);
		} else {
			System.out.println(Activity);
			historyList = reportService.getHistoryByUid(Integer
					.parseInt(Activity));
			historyuderdetailList = reportService
					.getHistoryUserDetailByUid(Integer.parseInt(Activity));
		}

		allActivityList = new ArrayList<HistoryActivity>();

		historyUidList = new ArrayList<UserDetail>();
		historyUidList = usersService.allUsers();

		for (History Hactivity : historyList) {
			ha = new HistoryActivity();

			Date d = Hactivity.getDtm();
			ha.setDate(d);

			if ((Hactivity.getOldValue() == null)
					&& (Hactivity.getUserDetail() == null)) {

				String ac = " A NEW VALUE " + Hactivity.getNewValue()
						+ " IS ADDED IN " + Hactivity.getColumnName() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY ";

				ha.setActivityDetails(ac);

			} else if ((Hactivity.getOldValue() == null)
					&& (Hactivity.getUserDetail() != null)) {
				String ac = " " + Hactivity.getUserDetail().getFirstName()
						+ " " + Hactivity.getUserDetail().getLastName()
						+ " HAS ADDED   " + Hactivity.getNewValue() + " IN "
						+ Hactivity.getColumnName() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY ";
				ha.setActivityDetails(ac);
			} else if ((Hactivity.getOldValue() != null)
					&& (Hactivity.getUserDetail() == null)) {
				String ac = " IN " + Hactivity.getColumnName() + " , "
						+ Hactivity.getOldValue() + " HAS BEEN CHANGED TO"
						+ Hactivity.getNewValue() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY. ";
				ha.setActivityDetails(ac);
			} else {
				String ac = Hactivity.getUserDetail().getFirstName() + "  "
						+ Hactivity.getUserDetail().getLastName()
						+ " HAS CHANGED " + Hactivity.getColumnName()
						+ " FROM " + Hactivity.getOldValue() + " TO "
						+ Hactivity.getNewValue() + " IN "
						+ Hactivity.getFunction() + " FUNCTIONALITY. ";
				ha.setActivityDetails(ac);
			}

			allActivityList.add(ha);
		}

		// ==============================lucky================

		allActivityListuserdetail = new ArrayList<HistoryUserDetailActivity>();
		for (HistoryUserDetail Hudactivity : historyuderdetailList) {
			huda = new HistoryUserDetailActivity();
			Date d = Hudactivity.getDtm();
			huda.setDate(d);
			if ((Hudactivity.getOldValue() == null)
					&& (Hudactivity.getUserDetailByForUser() == null)) {

				String acud = " A NEW VALUE " + Hudactivity.getNewValue()
						+ " IS ADDED IN " + Hudactivity.getColumnName()
						+ " FOR " + Hudactivity.getFunction()
						+ " ON DATE "
						+ Hudactivity.getDtm();

				huda.setActivityDetails(acud);

			} else if ((Hudactivity.getOldValue() == null)
					&& (Hudactivity.getUserDetailByForUser() != null)) {
				String acud = " "
						+ Hudactivity.getUserDetailByForUser().getFirstName()
						+ " "
						+ Hudactivity.getUserDetailByForUser().getLastName()
						+ " HAS ADDED   " + Hudactivity.getNewValue() + " IN "
						+ Hudactivity.getColumnName() + " FOR "
						+ Hudactivity.getFunction() + " ON DATE "
						+ Hudactivity.getDtm();
				huda.setActivityDetails(acud);
			} else if ((Hudactivity.getOldValue() != null)
					&& (Hudactivity.getUserDetailByForUser() == null)) {
				String acud = " IN " + Hudactivity.getColumnName() + " , "
						+ Hudactivity.getOldValue() + " HAS BEEN CHANGED TO"
						+ Hudactivity.getNewValue() + " IN "
						+ Hudactivity.getFunction() + " ON DATE "
						+ Hudactivity.getDtm();;
				huda.setActivityDetails(acud);
			} else {
				UserDetail uidname=(UserDetail) reportService.getUidName(Hudactivity.getUserDetailByUid().getUsers().getUid());
				String acud = uidname.getFirstName()
						+ "  "
						+ " HAS CHANGED "
						+ Hudactivity.getUserDetailByForUser().getFirstName()
						+ "'s"
						+ " "
						+ Hudactivity.getColumnName()
						+ " FROM "
						+ Hudactivity.getOldValue()
						+ " TO "
						+ Hudactivity.getNewValue()
						+ " ON DATE "
						+ Hudactivity.getDtm();
				huda.setActivityDetails(acud);

			}
			System.out.println(huda);
			allActivityListuserdetail.add(huda);
		}

		return SUCCESS;
	}
	
	public String addFeedbackByClient(){
		
		return SUCCESS;
	}
	
	public String reassignFeedback(){
		System.out.println("**************************successful**********");
		// userFeedback1 = new UserFeedback();
		
		userFeedback.setStatus("REASSIGNED_BY_CLIENT");
		//System.out.println(userFeedback.getFeedbackId());
		userFeedback.setFeedbackId(userFeedback.getFeedbackId());
		userFeedback.setRemarkByClient(userFeedback.getRemarkByClient());
		//System.out.println(userFeedback.getRemarkByClient());
		
		
		clientService.reassignFeedback(userFeedback);
		setNavigationFlag("success");
		return SUCCESS;	
		
		
	}
//	************************ENT BY SNEHA FOR REPORTS - ACTIVITIES		
	
	
	public String rewardsReport()
	{
		return SUCCESS;
	}
	
	
	
	
	//Client Report
	
	public String clientFeedBackReport(){
		System.out.println((Integer)session.get("uId"));
		userFeedbackList=reportService.generateReportAsClient((Integer)session.get("uId"));
		return SUCCESS;
	}
	
	public String getFeedbackReportMonths()
	{
		System.out.println((Integer)session.get("uId"));
		System.out.println(getNoMonth());
		userFeedbackList=reportService.generateReportAsClient((Integer)session.get("uId"), getNoMonth());
		return "success";
	}
	public String clientTwoMonthReport()
	{
		userFeedbackList=reportService.clientTwoMonthReport(2);
		return "success";
	}
	public String clientThreeMonthReport()
	{
		userFeedbackList=reportService.clientThreeMonthReport(3);
		return "success";
	}
	public String clientSixMonthReport()
	{
		userFeedbackList=reportService.clientSixMonthReport(6);
		return "success";
	}
	public String clientOneYearReport()
	{
		userFeedbackList=reportService.clientOneYearReport(12);
		return "success";
	}
	
	//Getters and setters
	public List<UserFeedback> getUserFeedbackList() {
		return userFeedbackList;
	}

	public void setUserFeedbackList(List<UserFeedback> userFeedbackList) {
		this.userFeedbackList = userFeedbackList;
	}

	public UserFeedback getUserFeedback() {
		return userFeedback;
	}

	public void setUserFeedback(UserFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}
	public String getNavigationFlag() {
		return navigationFlag;
	}
	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	
	public List<History> getHistoryList() {
		return historyList;
	}



	public void setHistoryList(List<History> historyList) {
		this.historyList = historyList;
	}



	public History getHist() {
		return hist;
	}



	public void setHist(History hist) {
		this.hist = hist;
	}



	public List<String> getDate() {
		return date;
	}



	public void setDate(List<String> date) {
		this.date = date;
	}


	public String getActivity() {
		return Activity;
	}



	public void setActivity(String activity) {
		Activity = activity;
	}



	public StringBuffer getHistoryById() {
		return historyById;
	}



	public void setHistoryById(StringBuffer historyById) {
		this.historyById = historyById;
	}



	public String getHistoryString() {
		return historyString;
	}



	public void setHistoryString(String historyString) {
		this.historyString = historyString;
	}
	public List<Clients> getClientsList() {
		return clientsList;
	}



	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}



	public Integer getFrmClientsId() {
		return frmClientsId;
	}



	public void setFrmClientsId(Integer frmClientsId) {
		this.frmClientsId = frmClientsId;
	}



	public List getFeedbackId() {
		return feedbackId;
	}



	public void setFeedbackId(List feedbackId) {
		this.feedbackId = feedbackId;
	}

	public List<HistoryActivity> getAllActivityList() {
		return allActivityList;
	}

	public void setAllActivityList(List<HistoryActivity> allActivityList) {
		this.allActivityList = allActivityList;
	}

	public List<UserDetail> getHistoryUidList() {
		return historyUidList;
	}

	public void setHistoryUidList(List<UserDetail> historyUidList) {
		this.historyUidList = historyUidList;
	}

	public String getFeedbackString() {
		return feedbackString;
	}

	public void setFeedbackString(String feedbackString) {
		this.feedbackString = feedbackString;
	}

	public List<Integer> getfIdInd() {
		return fIdInd;
	}

	public void setfIdInd(List<Integer> fIdInd) {
		this.fIdInd = fIdInd;
	}

	public List<UserFeedback> getUnsentFeedbackList() {
		return unsentFeedbackList;
	}

	public void setUnsentFeedbackList(List<UserFeedback> unsentFeedbackList) {
		this.unsentFeedbackList = unsentFeedbackList;
	}

	public List<UserFeedback> getSentFeedbackList() {
		return sentFeedbackList;
	}

	public void setSentFeedbackList(List<UserFeedback> sentFeedbackList) {
		this.sentFeedbackList = sentFeedbackList;
	}

	public Integer getNoMonth() {
		return noMonth;
	}

	public void setNoMonth(Integer noMonth) {
		this.noMonth = noMonth;
	}

	public String getPagetTitle() {
		return pagetTitle;
	}

	public void setPagetTitle(String pagetTitle) {
		this.pagetTitle = pagetTitle;
	}

	public String getPagesTitle() {
		return pagesTitle;
	}

	public void setPagesTitle(String pagesTitle) {
		this.pagesTitle = pagesTitle;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public List<HistoryUserDetail> getHistoryuderdetailList() {
		return historyuderdetailList;
	}

	public void setHistoryuderdetailList(
			List<HistoryUserDetail> historyuderdetailList) {
		this.historyuderdetailList = historyuderdetailList;
	}

	public HistoryUserDetailActivity getHuda() {
		return huda;
	}

	public void setHuda(HistoryUserDetailActivity huda) {
		this.huda = huda;
	}

	public ArrayList<HistoryUserDetailActivity> getAllActivityListuserdetail() {
		return allActivityListuserdetail;
	}

	public void setAllActivityListuserdetail(
			ArrayList<HistoryUserDetailActivity> allActivityListuserdetail) {
		this.allActivityListuserdetail = allActivityListuserdetail;
	}
}
