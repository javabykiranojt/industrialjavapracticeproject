package com.kjnext.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kjnext.dmart.dao.TaskClientPointsDao;
import com.kjnext.dmart.dao.TaskDao;
import com.kjnext.dmart.dao.Impl.ClientDaoImpl;
import com.kjnext.dmart.dao.Impl.TaskClientPointsDaoImpl;
import com.kjnext.dmart.dao.Impl.TaskDaoImpl;

import com.kjnext.dmart.hibernate.ClientDocuments;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.DocumentService;
import com.kjnext.dmart.service.FeedbackService;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.DocumentServiceImpl;
import com.kjnext.dmart.service.Impl.FeedbackServiceImpl;
import com.kjnext.dmart.service.Impl.TaskServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.Status;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.service.RewardServices;
import com.kjnext.dmart.service.TaskClientPointsServices;
import com.kjnext.dmart.service.Impl.RewardServicesImpl;
import com.kjnext.dmart.service.Impl.TaskClientPointsServicesImpl;
import com.kjnext.utility.OutCome;

public class FeedbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	FeedbackService feedbackService = new FeedbackServiceImpl();
	private ClientDocuments clientDocuments;
	TaskService taskService = new TaskServiceImpl();
	UsersService usersService = new UsersServiceImpl();

	DocumentService documentService = new DocumentServiceImpl();
	TaskClientPointsServices clientPointServices = new TaskClientPointsServicesImpl();
	RewardServices rewardServices = new RewardServicesImpl();
	TaskClientPointsServices tackClientPointServices = new TaskClientPointsServicesImpl();
	TaskClientPointsDao taskClientPointsDao=new TaskClientPointsDaoImpl();
	Tasks tasks;
	TaskDao taskdao=new TaskDaoImpl();
	Clients clients;
	List<UserFeedback> userFeedbackList = null;
	TaskClientPoints taskClientPoints;
	private Integer taskPointId;
	Integer userEmpId;
	Integer clientId;
	Integer billUploadId;
	
	Integer feedbackId;
	UserFeedback feedId;
	Integer imageUploadId;

	Integer OtherDocIfAnyId;

	private UserFeedback userFeedback;

	private File billUpload;

	private String billUploadFileName;

	private String billUploadContentType;

	private File imageUpload;

	private String imageUploadFileName;

	private String imageUploadContentType;

	private File otherDocIfAny;

	private String otherDocIfAnyFileName;

	private String otherDocIfAnyContentType;

	private String navigationFlag;

	private File offlineFeedback;
	
	private String offlineFeedbackFileName;
	
	private Integer offlineFeedbackId;
	Integer clId;
	
	private String pageTitle;
	private List<FeedbackDocuments> feedbackList;
	
	private String actionMsg;
	
	public String userFeedbackInput() {
		
		try {
		System.out.println("*****Inside FeedBack Action UserFeedback Input");   
	    System.out.println(tasks.getTaskId());
		List<Tasks>	data=taskdao.userFeedbackClientNameDate(tasks.getTaskId());
		for (Tasks tasks : data) {
			String dateWithTime=tasks.getScheduleDate().toString();
            String [] dateOnly=dateWithTime.split(" ");
            System.out.println("client id .."+tasks.getClients().getClientId());
            clientDocuments=new ClientDocuments();
            ClientDaoImpl clientDaoImpl=new ClientDaoImpl();
            int DocId=clientDaoImpl.getDocId(tasks.getClients().getUserDetail().getUserEmpId());
            System.out.println(DocId);
            clientDocuments.setDocId(DocId);
            OutCome outCome=new OutCome();
			outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
			outCome.addMessage(new OutCome.Message("<b>Task Name : </b>"+tasks.getTaskUniqueName()+"<br><b>Client: </b>"+tasks.getClients()+"<br><b>Task Assigned Date:</b>"+dateOnly[0]+"<br>"));
			addActionMessage(outCome);
            //so that I can access the selected task attributes in next jsp
            this.tasks = tasks;
		}
		}catch (Exception e) {
				e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * Get Documents and feedback details by user inserted into
	 * 1)Document_Repository 2)User_Feedback 3)Feedback_Documents
	 * 
	 */

	public String addFeedback() throws IOException {
			
		try {
			if (offlineFeedback != null) {
			System.out.println("=feedbackByUserInput=");
			byte[] bytesBillUpload = null;
			byte[] bytesImageUpload = null;
			byte[] bytesOtherDocIfAny = null;
			byte[] bytesOfflineFeedback = null;
			bytesOfflineFeedback = documentService.getBytesFromFile(offlineFeedback);
			if (bytesOfflineFeedback != null) {
				offlineFeedbackId = documentService.storeDocument(bytesOfflineFeedback);
				System.out.println("bytesOfflineFeedback = " + offlineFeedbackId);
			}
			bytesBillUpload = documentService.getBytesFromFile(billUpload);
			if (bytesBillUpload != null) {
				billUploadId = documentService.storeDocument(bytesBillUpload);
				System.out.println("bytesBillUpload = " + billUploadId);
			}
			bytesImageUpload = documentService.getBytesFromFile(imageUpload);
			if (bytesImageUpload != null) {
				imageUploadId = documentService.storeDocument(bytesImageUpload);
				System.out.println("bytesImageUpload = " + imageUploadId);
			}
			bytesOtherDocIfAny = documentService
					.getBytesFromFile(otherDocIfAny);
			if (bytesOtherDocIfAny != null) {
				OtherDocIfAnyId = documentService
						.storeDocument(bytesOtherDocIfAny);
				System.out.println("bytesOtherDocIfAny = " + OtherDocIfAnyId);
			}
			
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
			
			userEmpId = (Integer) session.get("UserEmpId");

			UserDetail userDetail = new UserDetail();
			userDetail.setUserEmpId(userEmpId);
			userFeedback.setUserDetail(userDetail);

			Clients clients = new Clients();
			clients.setClientId(tasks.getClients().getClientId());
			userFeedback.setClients(clients);

			Tasks tasksId = new Tasks();
			tasksId.setTaskId(tasks.getTaskId());
			userFeedback.setTasks(tasksId);
			userFeedback.setCreatedDtm(new Date());
			userFeedback.setCreatedUId(tasks.getCreatedUId());
			System.out.println(tasks.getCreatedUId());
			//userFeedback.setCreatedUId((Integer)session.get("uId"));
			userFeedback.setStatus(Status.FINISHED_USER.toString());
			feedbackService.addFeedback(userFeedback);

			addFeedbackInFeedbackDocument();

			tasks.setStatus(Status.FINISHED_USER.toString());
			tasks.setModifiedUId((Integer) session.get("UserEmpId"));
			tasks.setModifiedDtm(new Date());
			taskService.updateTask(tasks);

			setNavigationFlag("success");
			} else {
				
				userFeedbackInput();
				OutCome outCome=new OutCome();
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message("feedback.updated.byUser.fail"));
				addActionMessage(outCome);
				//addFieldError("offlineFeedback", getText("feedback.updated.byUser.fail"));
			}
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addFeedbackInFeedbackDocument() {

		try {
			if (billUploadId != null) {
				DocumetRepository documetRepository = new DocumetRepository();
				documetRepository.setDocId(billUploadId);
				FeedbackDocuments feedbackDocuments = new FeedbackDocuments();
				feedbackDocuments.setDocumentName(billUploadFileName);
				feedbackDocuments.setDocumetRepository(documetRepository);
				feedbackDocuments.setUserFeedback(userFeedback);
				feedbackDocuments.setCreatedDtm(new Date());
				userEmpId = (Integer) session.get("UserEmpId");
				feedbackDocuments.setCreatedUId(userEmpId);
				feedbackDocuments.setDocumentStatus(Status.BILL_UPLOAD.toString());
				feedbackService.addFeedbackDocument(feedbackDocuments);
			}
			if (imageUploadId != null) {
				DocumetRepository documetRepository1 = new DocumetRepository();
				documetRepository1.setDocId(imageUploadId);
				FeedbackDocuments feedbackDocuments1 = new FeedbackDocuments();
				feedbackDocuments1.setDocumentName(imageUploadFileName);
				feedbackDocuments1.setDocumetRepository(documetRepository1);
				feedbackDocuments1.setUserFeedback(userFeedback);
				feedbackDocuments1.setCreatedDtm(new Date());
				userEmpId = (Integer) session.get("UserEmpId");
				feedbackDocuments1.setCreatedUId(userEmpId);
				feedbackDocuments1.setDocumentStatus(Status.IMAGE_UPLOAD.toString());
				feedbackService.addFeedbackDocument(feedbackDocuments1);
			}
			if (OtherDocIfAnyId != null) {
				DocumetRepository documetRepository2 = new DocumetRepository();
				documetRepository2.setDocId(OtherDocIfAnyId);
				FeedbackDocuments feedbackDocuments2 = new FeedbackDocuments();
				feedbackDocuments2.setDocumentName(otherDocIfAnyFileName);
				feedbackDocuments2.setDocumetRepository(documetRepository2);
				feedbackDocuments2.setUserFeedback(userFeedback);
				feedbackDocuments2.setCreatedDtm(new Date());
				userEmpId = (Integer) session.get("UserEmpId");
				feedbackDocuments2.setCreatedUId(userEmpId);
				feedbackDocuments2.setDocumentStatus(Status.OTHER_DOC_UPLOAD.toString());
				feedbackService.addFeedbackDocument(feedbackDocuments2);
			}
			if (offlineFeedback != null) {
				DocumetRepository documetRepository = new DocumetRepository();
				documetRepository.setDocId(offlineFeedbackId);
				FeedbackDocuments feedbackDocuments = new FeedbackDocuments();
				feedbackDocuments.setDocumentName(offlineFeedbackFileName);
				feedbackDocuments.setDocumetRepository(documetRepository);
				feedbackDocuments.setUserFeedback(userFeedback);
				feedbackDocuments.setCreatedDtm(new Date());
				userEmpId = (Integer) session.get("UserEmpId");
				feedbackDocuments.setCreatedUId(userEmpId);
				feedbackDocuments.setDocumentStatus(Status.OFFLINE_DOC_UPLOAD.toString());
				feedbackService.addFeedbackDocument(feedbackDocuments);
			}
		
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 
	 * to update feedback document
	 */
	public String updateFeedbackInFeedbackDocument() {

		try {			
				if (billUploadId != null) {
					DocumetRepository documetRepository = new DocumetRepository();
					documetRepository.setDocId(billUploadId);
					FeedbackDocuments feedbackDocuments = new FeedbackDocuments();
					feedbackDocuments.setDocumentName(billUploadFileName);
					feedbackDocuments.setDocumetRepository(documetRepository);
					feedbackDocuments.setUserFeedback(userFeedback);
					feedbackDocuments.setCreatedDtm(new Date());
					userEmpId = (Integer) session.get("UserEmpId");
					feedbackDocuments.setModifiedUId(userEmpId);
					feedbackDocuments.setDocumentStatus(Status.BILL_UPLOAD.toString());
					feedbackDocuments.setCreatedUId(userFeedback.getUserDetail().getUserEmpId());
					feedbackService.updateFeedbackDocument(feedbackDocuments);
				}
				if (imageUploadId != null) {
					DocumetRepository documetRepository1 = new DocumetRepository();
					documetRepository1.setDocId(imageUploadId);
					FeedbackDocuments feedbackDocuments1 = new FeedbackDocuments();
					feedbackDocuments1.setDocumentName(imageUploadFileName);
					feedbackDocuments1.setDocumetRepository(documetRepository1);
					feedbackDocuments1.setUserFeedback(userFeedback);
					feedbackDocuments1.setCreatedDtm(new Date());
					userEmpId = (Integer) session.get("UserEmpId");
					feedbackDocuments1.setModifiedUId(userEmpId);
					feedbackDocuments1.setDocumentStatus(Status.IMAGE_UPLOAD.toString());
					feedbackDocuments1.setCreatedUId(userFeedback.getUserDetail().getUserEmpId());
					feedbackService.updateFeedbackDocument(feedbackDocuments1);
				}
				if (OtherDocIfAnyId != null) {
					DocumetRepository documetRepository2 = new DocumetRepository();
					documetRepository2.setDocId(OtherDocIfAnyId);
					FeedbackDocuments feedbackDocuments2 = new FeedbackDocuments();
					feedbackDocuments2.setDocumentName(otherDocIfAnyFileName);
					feedbackDocuments2.setDocumetRepository(documetRepository2);
					feedbackDocuments2.setUserFeedback(userFeedback);
					feedbackDocuments2.setCreatedDtm(new Date());
					userEmpId = (Integer) session.get("UserEmpId");
					feedbackDocuments2.setModifiedUId(userEmpId);
					feedbackDocuments2.setDocumentStatus(Status.OTHER_DOC_UPLOAD.toString());
					feedbackDocuments2.setCreatedUId(userFeedback.getUserDetail().getUserEmpId());
					feedbackService.updateFeedbackDocument(feedbackDocuments2);
				}
				if (offlineFeedback != null) {
					DocumetRepository documetRepository = new DocumetRepository();
					documetRepository.setDocId(offlineFeedbackId);
					FeedbackDocuments feedbackDocuments = new FeedbackDocuments();
					feedbackDocuments.setDocumentName(offlineFeedbackFileName);
					feedbackDocuments.setDocumetRepository(documetRepository);
					feedbackDocuments.setUserFeedback(userFeedback);
					feedbackDocuments.setCreatedDtm(new Date());
					userEmpId = (Integer) session.get("UserEmpId");
					feedbackDocuments.setModifiedUId(userEmpId);
					feedbackDocuments.setDocumentStatus(Status.OFFLINE_DOC_UPLOAD.toString());
					feedbackDocuments.setCreatedUId(userFeedback.getUserDetail().getUserEmpId());
					feedbackService.updateFeedbackDocument(feedbackDocuments);
				}
		
			
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	 * Just setting the extra fields in UserFeedback
	 * @author Manish
	 */
	private List<UserFeedback> setExtraFieldsInUserFeedback(List<UserFeedback> userFeedbackList)
	{
		for (UserFeedback userFeedback: userFeedbackList) {
			
			Integer uid = userFeedback.getModifiedUId();
			String taskStatus = userFeedback.getStatus();
			if (taskStatus.equals("INITIATED_USER")) {
				userFeedback.setTaskUserStatus("Completed By User");
			} else  if(taskStatus.equals("FINISHED_ADMIN")){
				userFeedback.setTaskUserStatus("Accepted By Admin");
			}else  if(taskStatus.equals("REJECTED_ADMIN")){
				userFeedback.setTaskUserStatus("Rejected By Admin");
			}
			
			/*if(userFeedback.getSentStatus().equalsIgnoreCase("y")) {
				userFeedback.setFeedSentStatus("Reported to Client");
			} else {
				userFeedback.setFeedSentStatus("Not Reported to Client");
			}*/
			
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
					userFeedback.setCreatedUIdName(uD.getFirstName());
				}
			}else {
				userFeedback.setCreatedUIdName("-");
			}
		}
		return userFeedbackList;	
	}
	
	public String getAllFinishedFeedbackByUser() {
		pageTitle=getText("user.feedback");
		UserFeedback tempUserFeedback = new UserFeedback();
		userFeedbackList = feedbackService.getAllFinishedTaskByUser(tempUserFeedback);
		userFeedbackList = setExtraFieldsInUserFeedback(userFeedbackList);
				return SUCCESS;
	}

	public String getAllApprovedFeedbackByAdmin() {
		pageTitle=getText("user.approvedfeedback");
		UserFeedback tempUserFeedback = new UserFeedback();
		userFeedbackList = feedbackService.getAllApprovedFeedbackByAdmin(tempUserFeedback);
		userFeedbackList = setExtraFieldsInUserFeedback(userFeedbackList);
		return SUCCESS;
	}

	public String getAllRejectedFeedbackByAdmin() {
		pageTitle=getText("user.rejectedfeedback");
		UserFeedback tempUserFeedback = new UserFeedback();
		userFeedbackList = feedbackService.getAllRejectedFeedbackByAdmin(tempUserFeedback);
		userFeedbackList = setExtraFieldsInUserFeedback(userFeedbackList);
		return SUCCESS;
	}
	
// ************ Start by Sneha
	
	public String getAllReassignedFeedbackByClient() {
		pageTitle=getText("user.reassignedfeedback");
		UserFeedback tempUserFeedback = new UserFeedback();
		userFeedbackList = feedbackService.getAllReassignedFeedbackByClient(tempUserFeedback);
		userFeedbackList = setExtraFieldsInUserFeedback(userFeedbackList);
		return SUCCESS;
	}
	
// ************ End by Sneha	
	
	public String approveFeedback() {
		return SUCCESS;
	}

	public String approveFeedbackInput() {
		
			try {
			
			userFeedback = feedbackService.loadUserFeedback(userFeedback
					.getFeedbackId());
			
			System.out.println("===uSER FEEDBACK CODE BEGINS==");
			
			Integer clId = userFeedback.getClients().getClientId();
			System.out.println("client id :"+clId);
			Integer clPoints= tackClientPointServices.getPoints(clId);
			System.out.println(clPoints);
			if (clPoints !=0 && clPoints!= null) {
				UserRewards userRewards = new UserRewards();
				Integer userEmpId = userFeedback.getUserDetail().getUserEmpId();
				System.out.println(userEmpId);
				System.out.println(rewardServices.verifyIfUserExist(userEmpId));
				if (rewardServices.verifyIfUserExist(userEmpId)) {
					System.out.println("====inside user exist====");
					Integer point = clientPointServices.getPoints(userFeedback.getClients().getClientId())+rewardServices.getPoints(userEmpId);
					System.out.println(point);
					userRewards.setPoint(point.toString());
					
					System.out.println(userEmpId);
					
					Integer rewardId = rewardServices.getUserRewardsId(userEmpId);
					userRewards.setModifiedDtm(userFeedback.getCreatedDtm());
					userRewards.setModifiedUId((Integer)session.get("uId"));
					rewardServices.updateUserRewards(userRewards, rewardId);
					
				}else {
					System.out.println(userFeedback.getClients().getClientId());
					Integer point = clientPointServices.getPoints(userFeedback.getClients().getClientId());
					userRewards.setPoint(point.toString());
					userRewards.setCreatedDtm(new Date());
					
					UserDetail userDetailId = new UserDetail();
					userDetailId.setUserEmpId(userEmpId);
					userRewards.setUserDetail(userDetailId);
					userRewards.setCreatedUId((Integer)session.get("uId"));
					rewardServices.addRewardsToUser(userRewards);
				}
				feedbackService.approveFeedback(userFeedback);
				setNavigationFlag("success");
				//***Amit***
				System.out.println(userFeedback.getFeedbackId());
				 feedId =feedbackService.userFeedbackMail(userFeedback.getFeedbackId());
				
				
			} else {
				OutCome outCome=new OutCome();
				//addActionError("No Client point exists for "+userFeedback.getClients().getClientName()+".Please assign.");
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message("No Client point exists for "+userFeedback.getClients().getClientName()+".Please assign."));
				addActionMessage(outCome);
							}
			
			
			} catch (RuntimeException e) {
			e.printStackTrace();
			}
			
		
		return SUCCESS;
	}

	public String rejectFeedback() {
		userFeedback = feedbackService.loadUserFeedback(userFeedback
				.getFeedbackId());
		return SUCCESS;
	}

	public String rejectFeedbackInput() {

		try {
			System.out.println(userFeedback.getFeedbackId());
			feedbackService.rejectFeedback(userFeedback);
			setNavigationFlag("success");
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addOfflineFeedback()
	{
		try
		{
			//adding doc in "document_repository"
			byte[] bytesOfflineFeedback = null;
			bytesOfflineFeedback = documentService.getBytesFromFile(offlineFeedback);
			if (bytesOfflineFeedback != null) {
				offlineFeedbackId = documentService.storeDocument(bytesOfflineFeedback);
				System.out.println("bytesOfflineFeedback = " + offlineFeedbackId);
			}
			
			//Getting current user
			userEmpId = (Integer) session.get("UserEmpId");
			
			//Loading current task
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
			
			//Storing record in "user_feedback" with all foreign keys
			userFeedback = new UserFeedback();
			
			UserDetail userDetail = new UserDetail();
			userDetail.setUserEmpId(userEmpId);
			userFeedback.setUserDetail(userDetail);

			Clients clients = new Clients();
			clients.setClientId(tasks.getClients().getClientId());
			userFeedback.setClients(clients);

			Tasks tasksId = new Tasks();
			tasksId.setTaskId(tasks.getTaskId());
			userFeedback.setTasks(tasksId);
			
			userFeedback.setCreatedDtm(new Date());
			userFeedback.setCreatedUId((Integer)session.get("uId"));
			userFeedback.setStatus(Status.INITIATED_USER.toString());
			userFeedback.setTaskAssignProof(Status.FEEDBACK_OFFLINE.toString());
			userFeedback.setFeedbackDetails(Status.FEEDBACK_OFFLINE.toString());
			feedbackService.addFeedback(userFeedback);
			
			//Storing record in "feedback_documents" with foreign keys
			addFeedbackInFeedbackDocument();
			
			//Changing status of task
			tasks.setStatus(Status.FINISHED_USER.toString());
			tasks.setModifiedDtm(new Date());
			tasks.setModifiedUId(userEmpId);
			taskService.updateTask(tasks);
			//Amit
			usersService.sendEmailToAllAdminForFeedbackSubmission(tasks);
			
			setNavigationFlag("success");
			
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String  reviewDocumentReportInput() {
		pageTitle=getText("reviewFeedback.pageTitle");
		userFeedback = new FeedbackServiceImpl().loadUserFeedback(getFeedbackId());
		feedbackList= UsersServiceImpl
		.generateFeedbackDocumentReport(getFeedbackId());
		
		
		return SUCCESS; 		
	}
	
	public String updateUserFeedbackbyAdmin() throws IOException{
		try {
			if (offlineFeedback != null) {
			System.out.println("=feedbackByUserInput=");
			byte[] bytesBillUpload = null;
			byte[] bytesImageUpload = null;
			byte[] bytesOtherDocIfAny = null;
			byte[] bytesOfflineFeedback = null;
			bytesOfflineFeedback = documentService.getBytesFromFile(offlineFeedback);
			if (bytesOfflineFeedback != null) {
				offlineFeedbackId = documentService.storeDocument(bytesOfflineFeedback);
				System.out.println("bytesOfflineFeedback = " + offlineFeedbackId);
			}
			bytesBillUpload = documentService.getBytesFromFile(billUpload);
			if (bytesBillUpload != null) {
				billUploadId = documentService.storeDocument(bytesBillUpload);
				System.out.println("bytesBillUpload = " + billUploadId);
			}
			bytesImageUpload = documentService.getBytesFromFile(imageUpload);
			if (bytesImageUpload != null) {
				imageUploadId = documentService.storeDocument(bytesImageUpload);
				System.out.println("bytesImageUpload = " + imageUploadId);
			}
			bytesOtherDocIfAny = documentService
					.getBytesFromFile(otherDocIfAny);
			if (bytesOtherDocIfAny != null) {
				OtherDocIfAnyId = documentService
						.storeDocument(bytesOtherDocIfAny);
				System.out.println("bytesOtherDocIfAny = " + OtherDocIfAnyId);
			}
			
			tasks = taskService.loadTaskOnTaskId(userFeedback.getTasks().getTaskId());
			
			userEmpId = (Integer) session.get("UserEmpId");

			UserDetail userDetail = new UserDetail();
			userDetail.setUserEmpId(userEmpId);
			userFeedback.setUserDetail(userDetail);

			Clients clients = new Clients();
			clients.setClientId(tasks.getClients().getClientId());
			userFeedback.setClients(clients);
			System.out.println(getUserFeedback().getTaskAssignProof());
			userFeedback.setTaskAssignProof(getUserFeedback().getTaskAssignProof());
			System.out.println(getUserFeedback().getFeedbackDetails());
			userFeedback.setFeedbackDetails(getUserFeedback().getFeedbackDetails());
			Tasks tasksId = new Tasks();
			tasksId.setTaskId(tasks.getTaskId());
			userFeedback.setTasks(tasksId);
			userFeedback.setCreatedDtm(new Date());
			userFeedback.setCreatedUId(tasks.getCreatedUId());
			System.out.println(tasks.getCreatedUId());
			//userFeedback.setCreatedUId((Integer)session.get("uId"));
			userFeedback.setStatus(Status.FINISHED_USER.toString());
			feedbackService.updateUserFeedbackbyAdmin(userFeedback);

			updateFeedbackInFeedbackDocument();

			tasks.setStatus(Status.FINISHED_USER.toString());
			tasks.setModifiedUId((Integer) session.get("UserEmpId"));
			tasks.setModifiedDtm(new Date());
			taskService.updateTask(tasks);
			setActionMsg("feedEdited");
			setNavigationFlag("success");
			} else {
				pageTitle=getText("reviewFeedback.pageTitle");
				userFeedback = new FeedbackServiceImpl().loadUserFeedback(userFeedback.getFeedbackId());
				feedbackList= UsersServiceImpl
				.generateFeedbackDocumentReport(userFeedback.getFeedbackId());
				OutCome outCome=new OutCome();
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message("feedback.updated.byAdmin.fail"));
				addActionMessage(outCome);
			}
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	// Getter And Setter.

	public File getBillUpload() {
		return billUpload;
	}

	public void setBillUpload(File billUpload) {
		this.billUpload = billUpload;
	}

	public String getBillUploadContentType() {
		return billUploadContentType;
	}

	public void setBillUploadContentType(String billUploadContentType) {
		this.billUploadContentType = billUploadContentType;
	}

	public String getBillUploadFileName() {
		return billUploadFileName;
	}

	public void setBillUploadFileName(String billUploadFileName) {
		this.billUploadFileName = billUploadFileName;
	}

	public File getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(File imageUpload) {
		this.imageUpload = imageUpload;
	}

	public String getImageUploadContentType() {
		return imageUploadContentType;
	}

	public void setImageUploadContentType(String imageUploadContentType) {
		this.imageUploadContentType = imageUploadContentType;
	}

	public String getImageUploadFileName() {
		return imageUploadFileName;
	}

	public void setImageUploadFileName(String imageUploadFileName) {
		this.imageUploadFileName = imageUploadFileName;
	}

	public File getOtherDocIfAny() {
		return otherDocIfAny;
	}

	public void setOtherDocIfAny(File otherDocIfAny) {
		this.otherDocIfAny = otherDocIfAny;
	}

	public String getOtherDocIfAnyContentType() {
		return otherDocIfAnyContentType;
	}

	public void setOtherDocIfAnyContentType(String otherDocIfAnyContentType) {
		this.otherDocIfAnyContentType = otherDocIfAnyContentType;
	}

	public String getOtherDocIfAnyFileName() {
		return otherDocIfAnyFileName;
	}

	public void setOtherDocIfAnyFileName(String otherDocIfAnyFileName) {
		this.otherDocIfAnyFileName = otherDocIfAnyFileName;
	}

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
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

	public List<UserFeedback> getUserFeedbackList() {
		return userFeedbackList;
	}

	public void setUserFeedbackList(List<UserFeedback> userFeedbackList) {
		this.userFeedbackList = userFeedbackList;
	}

	public File getOfflineFeedback() {
		return offlineFeedback;
	}

	public void setOfflineFeedback(File offlineFeedback) {
		this.offlineFeedback = offlineFeedback;
	}

	public String getOfflineFeedbackFileName() {
		return offlineFeedbackFileName;
	}

	public void setOfflineFeedbackFileName(String offlineFeedbackFileName) {
		this.offlineFeedbackFileName = offlineFeedbackFileName;
	}

	public TaskClientPoints getTaskClientPoints() {
		return taskClientPoints;
	}

	public void setTaskClientPoints(TaskClientPoints taskClientPoints) {
		this.taskClientPoints = taskClientPoints;
	}

	public Integer getTaskPointId() {
		return taskPointId;
	}

	public void setTaskPointId(Integer taskPointId) {
		this.taskPointId = taskPointId;
	}

	public Integer getClId() {
		return clId;
	}

	public void setClId(Integer clId) {
		this.clId = clId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public UserFeedback getFeedId() {
		return feedId;
	}

	public void setFeedId(UserFeedback feedId) {
		this.feedId = feedId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public List<FeedbackDocuments> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackDocuments> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	/**
	 * @return the clientDocuments
	 */
	public ClientDocuments getClientDocuments() {
		return clientDocuments;
	}

	/**
	 * @param clientDocuments the clientDocuments to set
	 */
	public void setClientDocuments(ClientDocuments clientDocuments) {
		this.clientDocuments = clientDocuments;
	}
	
}
