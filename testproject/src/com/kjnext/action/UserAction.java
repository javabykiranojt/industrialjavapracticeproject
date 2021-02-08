package com.kjnext.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.FileOperations;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.DataSingleton;
import com.kjnext.dmart.service.Impl.FeedbackServiceImpl;
import com.kjnext.dmart.service.Impl.FileOperationsImpl;
import com.kjnext.dmart.service.Impl.TaskServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.SearchCriteriaTask;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	TaskService taskService = new TaskServiceImpl();

	UsersService usersService = new UsersServiceImpl();

	List<Tasks> tasksList = null;
	List<String> tasksNamesList = null;

	Tasks tasks;

	private String typeDoc;

	private Integer UserEmpId;

	private String navigationFlag;
	private String taskUniqueName;

	private Integer locId;

	UserDetail userDetail;

	Location location;

	private Integer feedbackId;

	private List<UserDocuments> documentList;

	private List<FeedbackDocuments> feedbackList;

	private File panCard;

	private String panCardFileName;

	private String panCardContentType;

	private int panCardContentLength;

	private File adharCard;

	private String adharCardFileName;

	private String adharCardContentType;

	private File DrivingLicence;

	private String DrivingLicenceFileName;

	private String DrivingLicenceContentType;

	private File otherDoc;

	private String otherDocFileName;

	private String otherDocContentType;

	private File otherDocIfAny;

	private String otherDocIfAnyFileName;

	private String otherDocIfAnyContentType;

	private List<Location> locationList;

	private Integer locationId;

	private DocumetRepository documetRepository;

	private String verificationId;

	private Boolean value;

	private List<UserDetail> allUsersList;

	private List<LocationArea> locationAreaList;

	List<DocumetRepository> fileList;

	InputStream userFiles;

	Integer userDocId;

	private String actionMsg;

	ArrayList<String> addFieldError;

	FileOperations fileOperations = new FileOperationsImpl();
	//private List<String> admail;
	private LocationArea locationArea;
	OutCome outCome=new OutCome();
	
	private UserFeedback userFeedback;
	private String pageTitles;
	private String pageTitle;
	/*
	 * Start By PRASHANT
	 */
	
	
	/*
	 * by lucky userForget Pass
	 * merged by amarjit
	 */
	
	public String userpassforget() {
		return SUCCESS;
	}

	public String getAllInitiatedTask() {
		System.out.println("getAllInitiatedTask");
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllInitiatedByAdminTask(criteriaTask);
		return SUCCESS;
	}
	public String getAllPublishedTask() {
		System.out.println("getAllInitiatedTask");
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllPublishedTaskByAdmin(criteriaTask);
		return SUCCESS;
	}

	public String verificationPage() {
		System.out.println(UserEmpId);
		return "success";
	}

	public String getAllAcceptedTask() {
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllInitiatedByUserTask(criteriaTask);
		return SUCCESS;
	}

	public String getAllFinishedTask() {
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllFinishedByUserTask(criteriaTask);
		return SUCCESS;
	}
	public String taskResponses() {
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		criteriaTask.setTaskUniqueName(taskUniqueName);
		tasksList = taskService.taskResponses(criteriaTask);
		return SUCCESS;
	}
	public String taskNamesForResponses() {
		pageTitles=getText("task.response");
		System.out.println("inside taskNamesForResponses");
		try{
			
		tasksList=new ArrayList<Tasks>();
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		tasksNamesList = taskService.taskNamesForResponses(criteriaTask);
		for(String taskUniqueName:tasksNamesList){
			Tasks tasks=new Tasks();
			tasks.setTaskUniqueName(taskUniqueName);
			tasksList.add(tasks);
		}
		}catch (Exception e) {
			System.out.println("I am inside catch block");
			e.printStackTrace();
		}
		/*SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		criteriaTask.setTaskUniqueName(taskUniqueName);
		tasksList = taskService.taskResponses(criteriaTask);*/
		return SUCCESS;
	}

	public String acceptTaskByUser() {
		return SUCCESS;
	}

	public String acceptTaskInputByUser() {
		tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		tasks.setStatus(Status.INITIATED_USER.toString());
		tasks.setModifiedUId((Integer) session.get("uId"));
		tasks.setModifiedDtm(new Date());
		taskService.updateTask(tasks);
		setNavigationFlag("success");
		usersService.sendEmailToAllAdminAbtTaskAccepted(tasks);
		return SUCCESS;
	}
	public String acceptPubTaskByUser() {
		tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		tasks.setStatus(Status.INITIATED_USER_MULTIUSER_ACCEPT.toString());
		tasks.setModifiedUId((Integer) session.get("uId"));
		tasks.setModifiedDtm(new Date());
		taskService.updateTask(tasks);
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllPublishedTaskByAdmin(criteriaTask);
		return SUCCESS;
	}
	public String rejectPubTaskByUser() {
		tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		tasks.setStatus(Status.INITIATED_USER_MULTIUSER_REJECT.toString());
		tasks.setModifiedUId((Integer) session.get("uId"));
		tasks.setModifiedDtm(new Date());
		taskService.updateTask(tasks);
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		UserEmpId = (Integer) session.get("UserEmpId");
		System.out.println(UserEmpId);
		criteriaTask.setUserEmpId(UserEmpId);
		tasksList = taskService.getAllPublishedTaskByAdmin(criteriaTask);
		return SUCCESS;
	}

	public String feedbackGivenByUser() {
		return SUCCESS;
	}

	// start by kiran rk

	@SuppressWarnings("unchecked")
	public String registerUser() {
		pageTitle = getText("user.Signup");
		locationList = usersService.getAllLocation();
		System.out.println("*****Inside useraction regUser After Login");
		locationAreaList = new ArrayList();
		return SUCCESS;
	}

	/*
	 * While creating new user first adding his documents After success adding
	 * userdetails Lastly mapping that info with userDocuments
	 */

	public String newUserRegistration() {
		//DataSingleton dataSingleton= DataSingleton.getDataSingleton();
		documentList = new ArrayList<UserDocuments>();
		byte[] aharcardByteFormat = null;
		byte[] panCardByteFormat = null;
		byte[] DrivingLicenceByteFormat = null;
		byte[] otherDocByteFormat = null;
		byte[] otherDocIfAnyByteFormat = null;
		try {

			if (null != adharCard) {
				aharcardByteFormat = UsersServiceImpl
						.getBytesFromFile(adharCard);

				UserDocuments userDocuments = usersService
						.loadToDB(aharcardByteFormat);
				if (userDocuments != null) {
					userDocuments.setDocumentName(adharCardFileName);
					documentList.add(userDocuments);
				}
			}

			if (null != panCard) {
				panCardByteFormat = UsersServiceImpl.getBytesFromFile(panCard);
				UserDocuments userDocuments2 = usersService
						.loadToDB(panCardByteFormat);
				if (userDocuments2 != null) {
					userDocuments2.setDocumentName(panCardFileName);
					documentList.add(userDocuments2);
				}
			}

			if (null != DrivingLicence) {
				DrivingLicenceByteFormat = UsersServiceImpl
						.getBytesFromFile(DrivingLicence);
				UserDocuments userDocuments3 = usersService
						.loadToDB(DrivingLicenceByteFormat);
				if (userDocuments3 != null) {
					userDocuments3.setDocumentName(DrivingLicenceFileName);
					documentList.add(userDocuments3);
				}
			}

			if (null != otherDoc) {
				otherDocByteFormat = UsersServiceImpl
						.getBytesFromFile(otherDoc);
				UserDocuments userDocuments4 = usersService
						.loadToDB(otherDocByteFormat);
				if (userDocuments4 != null) {
					userDocuments4.setDocumentName(otherDocFileName);
					documentList.add(userDocuments4);
				}
			}

			if (null != otherDocIfAny) {
				otherDocIfAnyByteFormat = UsersServiceImpl
						.getBytesFromFile(otherDocIfAny);
				UserDocuments userDocuments5 = usersService
						.loadToDB(otherDocIfAnyByteFormat);
				if (userDocuments5 != null) {
					userDocuments5.setDocumentName(otherDocIfAnyFileName);
					documentList.add(userDocuments5);
				}
			}
			OutCome outCome=new OutCome();

			System.out.println(getLocId());
			// after adding documents successfully adding userdetails
			if (userDetail != null && documentList != null) {
				Location location = new Location();
				location.setLocId(getLocId());
				userDetail.setLocation(location);
				userDetail.setlocationArea(locationArea);
				outCome = usersService.addNewUser(userDetail,documentList,outCome);
				//******code for send mail to the all admin ***
				
				if(outCome.isSuccess()){
					System.out.println("code for send mail to the all admin");
				try{
					usersService.sendEmailToAllAdmin(userDetail);
				}catch (Exception e) {
						System.out.println("+++++++Email Not Sent++++++++");
				}
				System.out.println("------");}
			
			}
			addActionMessage(outCome);
			
				//outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				//outCome.addMessage(new OutCome.Message("Thanks for registration.<br>After approval you will get Username and Password"));
				//addActionMessage(outCome);
			//addActionMessage("Thanks for registration.<br>After approval you will get Username and Password");

			if (outCome.isSuccess()){
				session.put("regSuccess", "Registration Success");
				return "success";
			}
			else {
				registerUser();
				return "addFieldErrorOccured";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		registerUser();
		return "error";
	}

	/*
	 * send verification id
	 */
	public String sendVerificationId() {
		pageTitle=getText("user.all");
		OutCome outCome = new OutCome();
		if (null != UserEmpId) {
			userDetail.setUserEmpId(UserEmpId);
			userDetail.setModifiedUId((Integer) session.get("uId"));
			usersService.sendVerificationID(userDetail,outCome);
			if(outCome.isSuccess()){
				outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message("email.message"));
			}
			allUsersList = usersService.getAllUser();
			addActionMessage(outCome);
			return SUCCESS;
		}
		return ERROR;
	}

	/*
	 * matching verification Id
	 */
	public String verificationPageInput() {
		OutCome outCome=new OutCome();
		System.out.println(UserEmpId);
		value = usersService.userVerification(verificationId, UserEmpId,outCome);
		if (value == true) {
			updateVarificationStatus(UserEmpId);
			addActionMessage(outCome);
			return "success";
		}
		addActionMessage(outCome);
		return "unSuccess";
	}

	private void updateVarificationStatus(Integer userEmpId2) {
		usersService.updateVarificationStatus(userEmpId2);
	}

	public String allUser() {
		pageTitle=getText("user.all");
		if (actionMsg != null) {
			System.out.println(getActionMsg());
			if (getActionMsg().equals("passChanged")) {
				outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message("userPass.change.success.byAdmin"));
			}
			if (getActionMsg().equals("userNotActive")) {
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message("userNoActive.change.fail.byAdmin"));
			}
			if (getActionMsg().equals("userPending")) {
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message("user.status"));
			}
			
			addActionMessage(outCome);
		}
		allUsersList = usersService.getAllUser();
		for(UserDetail userDetail:allUsersList){
			userDetail.setCreatedUIdName(getEmployeeNameLabel(userDetail.getCreatedUId()));
			userDetail.setModifiedUIdName(getEmployeeNameLabel(userDetail.getModifiedUId()));
		}
		return SUCCESS;
	}

	public String userDocumentReport() {
		documentList = UsersServiceImpl
				.generateUserDocumentReport(getUserEmpId());
		return "success";
	}

	public String getfile() {
		userFiles = UsersServiceImpl.getUserDocumentFile(getUserDocId());
		setPanCardContentLength(UsersServiceImpl
				.getUserDocumentFileBytes(getUserDocId()).length);
		System.out.println(typeDoc);
		if ("USERDOC".equals(typeDoc)) {
			panCardFileName = UsersServiceImpl.loadDocName(getUserDocId(),
					"UserDocuments");
		} else if ("FEEDBACKDOC".equals(typeDoc)) {
			panCardFileName = UsersServiceImpl.loadDocName(getUserDocId(),
					"FeedbackDocuments");
		}
		return "success";
	}

	public static byte[] readFully(InputStream input) throws IOException {
		byte[] buffer = new byte[8192];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		return output.toByteArray();
	}

	public String activateUser() {
		userDetail.setUserEmpId(UserEmpId);
		UserEmpId = (Integer) session.get("UserEmpId");
		userDetail.setModifiedUId(UserEmpId);
		UserDetail userDetailDb=usersService.getUserDetail(userDetail.getUserEmpId());
		if(Status.INACTIVE.toString().equals(userDetailDb.getStatus())){
			usersService.activateSuspendedUser(userDetailDb);
			return SUCCESS;
		}
		if(Status.ACTIVE.toString().equals(userDetailDb.getStatus())){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Its aleardy active."));
			addActionMessage(outCome);
			return SUCCESS;
		}
		Boolean activeUser = usersService.activateUser(userDetail);
		if (activeUser == true) {
			addActionMessage("User activated");
		} else {
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Thanks for registration.<br>After approval you will get Username and Password"));
			addActionMessage(outCome);
		}
		return SUCCESS;
	}

	public String differentLocationAreas() {
		// System.out.println("I am in diff loc areas");
		// System.out.println(location.getLocId());
		locationAreaList = usersService.getAreasOnLocation(location.getLocId());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().write("<select>");
			response.getWriter().write(
					"<option value=\"0\">" + "---Select---" + "</option>");
			for (LocationArea locationArea : locationAreaList) {
				response.getWriter().write(
						"<option  value=" + locationArea.getAreaId() + ">"
								+ locationArea.getAreaName() + "</option>");
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String feedbackDocumentReport() {
		feedbackList = UsersServiceImpl
				.generateFeedbackDocumentReport(getFeedbackId());
		return "success";
	}

	
	
	
	
	// Getter And Setter

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public File getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(File adharCard) {
		this.adharCard = adharCard;
	}

	public List<UserDocuments> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<UserDocuments> documentList) {
		this.documentList = documentList;
	}

	public List<Tasks> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Tasks> tasksList) {
		this.tasksList = tasksList;
	}

	public Integer getUserEmpId() {
		return UserEmpId;
	}

	public void setUserEmpId(Integer userEmpId) {
		UserEmpId = userEmpId;
	}

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public File getDrivingLicence() {
		return DrivingLicence;
	}

	public void setDrivingLicence(File drivingLicence) {
		DrivingLicence = drivingLicence;
	}

	public File getOtherDoc() {
		return otherDoc;
	}

	public void setOtherDoc(File otherDoc) {
		this.otherDoc = otherDoc;
	}

	public File getOtherDocIfAny() {
		return otherDocIfAny;
	}

	public void setOtherDocIfAny(File otherDocIfAny) {
		this.otherDocIfAny = otherDocIfAny;
	}

	public File getPanCard() {
		return panCard;
	}

	public void setPanCard(File panCard) {
		this.panCard = panCard;
	}

	public String getAdharCardFileName() {
		return adharCardFileName;
	}

	public void setAdharCardFileName(String adharCardFileName) {
		this.adharCardFileName = adharCardFileName;
	}

	public String getAdharCardContentType() {
		return adharCardContentType;
	}

	public void setAdharCardContentType(String adharCardContentType) {
		this.adharCardContentType = adharCardContentType;
	}

	public String getDrivingLicenceContentType() {
		return DrivingLicenceContentType;
	}

	public void setDrivingLicenceContentType(String drivingLicenceContentType) {
		DrivingLicenceContentType = drivingLicenceContentType;
	}

	public String getDrivingLicenceFileName() {
		return DrivingLicenceFileName;
	}

	public void setDrivingLicenceFileName(String drivingLicenceFileName) {
		DrivingLicenceFileName = drivingLicenceFileName;
	}

	public String getOtherDocContentType() {
		return otherDocContentType;
	}

	public void setOtherDocContentType(String otherDocContentType) {
		this.otherDocContentType = otherDocContentType;
	}

	public String getOtherDocFileName() {
		return otherDocFileName;
	}

	public void setOtherDocFileName(String otherDocFileName) {
		this.otherDocFileName = otherDocFileName;
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

	public String getPanCardContentType() {
		return panCardContentType;
	}

	public void setPanCardContentType(String panCardContentType) {
		this.panCardContentType = panCardContentType;
	}

	public String getPanCardFileName() {
		return panCardFileName;
	}

	public void setPanCardFileName(String panCardFileName) {
		this.panCardFileName = panCardFileName;
	}

	public DocumetRepository getDocumetRepository() {
		return documetRepository;
	}

	public void setDocumetRepository(DocumetRepository documetRepository) {
		this.documetRepository = documetRepository;
	}

	public String getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

	public List<UserDetail> getAllUsersList() {
		return allUsersList;
	}

	public void setAllUsersList(List<UserDetail> allUsersList) {
		this.allUsersList = allUsersList;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public List<DocumetRepository> getFileList() {
		return fileList;
	}

	public void setFileList(List<DocumetRepository> fileList) {
		this.fileList = fileList;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public Integer getUserDocId() {
		return userDocId;
	}

	public void setUserDocId(Integer userDocId) {
		this.userDocId = userDocId;
	}

	public InputStream getUserFiles() {
		return userFiles;
	}

	public void setUserFiles(InputStream userFiles) {
		this.userFiles = userFiles;
	}

	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	/**
	 * @return the panCardContentLength
	 */
	public int getPanCardContentLength() {
		return this.panCardContentLength;
	}

	/**
	 * @param panCardContentLength
	 *            the panCardContentLength to set
	 */
	public void setPanCardContentLength(int panCardContentLength) {
		this.panCardContentLength = panCardContentLength;
	}

	public ArrayList<String> getAddFieldError() {
		return addFieldError;
	}

	public void setAddFieldError(ArrayList<String> addFieldError) {
		this.addFieldError = addFieldError;
	}

	/**
	 * @return the feedbackList
	 */
	public List<FeedbackDocuments> getFeedbackList() {
		return feedbackList;
	}

	/**
	 * @param feedbackList
	 *            the feedbackList to set
	 */
	public void setFeedbackList(List<FeedbackDocuments> feedbackList) {
		this.feedbackList = feedbackList;
	}

	/**
	 * @return the feedbackId
	 */
	public Integer getFeedbackId() {
		return feedbackId;
	}

	/**
	 * @param feedbackId
	 *            the feedbackId to set
	 */
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public List<LocationArea> getLocationAreaList() {
		return locationAreaList;
	}

	public void setLocationAreaList(List<LocationArea> locationAreaList) {
		this.locationAreaList = locationAreaList;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public LocationArea getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(LocationArea locationArea) {
		this.locationArea = locationArea;
	}

	/**
	 * @return the typeDoc
	 */
	public String getTypeDoc() {
		return typeDoc;
	}

	/**
	 * @param typeDoc
	 *            the typeDoc to set
	 */
	public void setTypeDoc(String typeDoc) {
		this.typeDoc = typeDoc;
	}
	/**
	 * @return the tasksNamesList
	 */
	public List<String> getTasksNamesList() {
		return tasksNamesList;
	}
	/**
	 * @param tasksNamesList the tasksNamesList to set
	 */
	public void setTasksNamesList(List<String> tasksNamesList) {
		this.tasksNamesList = tasksNamesList;
	}
	/**
	 * @return the taskUniqueName
	 */
	public String getTaskUniqueName() {
		return taskUniqueName;
	}
	/**
	 * @param taskUniqueName the taskUniqueName to set
	 */
	public void setTaskUniqueName(String taskUniqueName) {
		this.taskUniqueName = taskUniqueName;
	}
	public UserFeedback getUserFeedback() {
		return userFeedback;
	}
	public void setUserFeedback(UserFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public String getPageTitles() {
		return pageTitles;
	}
	public void setPageTitles(String pageTitles) {
		this.pageTitles = pageTitles;
	}
	
	
	
}
