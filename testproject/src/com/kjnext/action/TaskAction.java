package com.kjnext.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.kjnext.dmart.dao.TaskDao;
import com.kjnext.dmart.dao.Impl.TaskDaoImpl;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.FeedbackServiceImpl;
import com.kjnext.dmart.service.Impl.TaskServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.LockFunction;
import com.kjnext.dmart.vo.SearchCriteriaTask;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;

public class TaskAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	TaskService taskService = new TaskServiceImpl();

	UsersService usersService = new UsersServiceImpl();

	ClientService clientService = new ClientServiceImpl();

	List<Tasks> tasksList = null;

	List<UserDetail> userList = null;

	List<Clients> clientsList = null;

	List<Location> locationList = null;

	public String taskUniqueName;

	private String navigationFlag;
	
	private boolean reasignFlag;

	Tasks tasks;
	
	Tasks tasks1;

	TaskDao taskDao = new TaskDaoImpl();

	Location location;

	Clients clients;

	UserDetail userDetail;

	private Integer frmClientsId;

	private Integer[] userEmployeeId;

	private Integer locationId;

	private String cityAndArea;

	private String pageTitle;

	private Integer feedbackId;

	private List<FeedbackDocuments> feedbackList;

	private UserFeedback userFeedback;

	private List<Tasks> taskUniqueNameList;
	private String statusTask;
	
	List<UserDetail> reAssignUserList = null;
	
	public List<UserDetail> getReAssignUserList() {
		return reAssignUserList;
	}

	public void setReAssignUserList(List<UserDetail> reAssignUserList) {
		this.reAssignUserList = reAssignUserList;
	}

	public String getStatusTask() {
		return statusTask;
	}

	public void setStatusTask(String statusTask) {
		this.statusTask = statusTask;
	}

	public List<Tasks> getTaskUniqueNameList() {
		return taskUniqueNameList;
	}

	public void setTaskUniqueNameList(List<Tasks> taskUniqueNameList) {
		this.taskUniqueNameList = taskUniqueNameList;
	}

	// Selecting All list of Task table.
	public String loadAllTask() {

		SearchCriteriaTask criteriaTask = null;
		tasksList = taskService.getAllTask(criteriaTask);
		pageTitle = getText("showall.task");
		return SUCCESS;

	}

	public String reasignInput(){

		OutCome outCome=new OutCome();
		if (tasks != null) {
			System.out.println(tasks.getTaskId());
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
			tasks1 = taskService.loadTaskOnTaskId(tasks.getTaskId());
			reAssignUserList = usersService.getUsersOnLocationExcludeRejected(tasks);
			
			setReasignFlag(true);
			loadStaticData();
			outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
			outCome.addMessage(new OutCome.Message(
					"This task needs to be Reasigned.<hr>"
							+ " <span style=\" color:#ff66;\">Task unique name was :</span> "
							+ tasks1.getTaskUniqueName() + "<br>"
							+ "Client Store Location "
							+ tasks1.getLocation().getArea()+","
							+tasks1.getLocation().getCity()+","
							+tasks1.getLocation().getState()+","
							+tasks1.getLocation().getCountry()+","+"<br>"
							+ "Task Client Name "
							+ tasks1.getClients().getUserDetail().getFirstName()+" "+tasks1.getClients().getUserDetail().getLastName()+"<br>"
							+ "Store Name"
							+ tasks1.getClients().getClientName()+"<br>"
							+"User whose Task Feedback Was Declined : "
							+ tasks1.getUserDetail().getFirstName()+" "+ tasks1.getUserDetail().getLastName()));
			addActionMessage(outCome);
		}
		tasks.setTaskUniqueName(tasks1.getTaskUniqueName()+" (Reasigned)");

		return SUCCESS;
	}
	
	
	// For input Adding a Task to User.
	public String addTaskInputPage() {
		OutCome outCome = new OutCome();
		Integer userEmpId = (Integer) session.get("UserEmpId");
		unLockFunction(LockFunction.TASK);
		// setting userId for locking - task opening function
		if (servletContext.getAttribute(LockFunction.TASK.toString()) == null) {
			servletContext
					.setAttribute(LockFunction.TASK.toString(), userEmpId);
		} else {
			setLockedByFlg(true);
			loadStaticData();
			outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
			List<Object> param = new ArrayList<Object>();
			param.add(getEmployeeNameLabel((Integer) servletContext
					.getAttribute(LockFunction.TASK.toString())));
			outCome.addMessage(new OutCome.Message("error.lockedby", param));
			addActionMessage(outCome);
			return "noUserClientExit";
		}

		userList = new ArrayList<UserDetail>();
		clientsList = new ArrayList<Clients>();
		locationList = usersService.getAllLocation();

		for (Location location : locationList) {
			location.setCityAndArea(location.getCity() + "-"
					+ location.getArea());

		}

		List<Object> param = new ArrayList<Object>();
		param.add("Users");
		param.add("Clients");
		List<Clients> clientsList1 = clientService.showAllClients();
		for (Clients clt : clientsList1) {
			System.out.println(clt.getUserDetail().getStatus());
		}
		List<UserDetail> userList1 = usersService.getAllActiveUser();
		if (userList1 != null && userList1.isEmpty() && clientsList1 != null
				&& clientsList1.isEmpty()) {
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addFailureMessage(new OutCome.Message(
					"error.db.maintanance", param));
			addActionMessage(outCome);
			return "noUserClientExit";
		}
		if (userList1 != null && userList1.isEmpty()) {
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("error.user.notpresent",
					param));
			addActionMessage(outCome);
			return "noUserClientExit";
		}
		if (clientsList1 != null && clientsList1.isEmpty()) {
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("error.client.notpresent",
					param));
			addActionMessage(outCome);
			return "noUserClientExit";
		}
		return SUCCESS;
	}

	// Data coming fron popUp has been inserted into tasks table
	public String addTask() {
		unLockFunction(LockFunction.TASK);
		System.out.println(tasks.getTaskId());
		boolean isDuplicate = false;
		OutCome outCome = new OutCome();

		try {
			if (getFrmClientsId() == 0) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message(
						"Please select Client."));

			}
			if (getUserEmployeeId() == null || getUserEmployeeId().length == 0) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message(
						"Please select User."));

			}
			if (getLocationId() == 0) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message(
						"Please select Location."));
			}
			if (tasks.getTaskUniqueName()==null ||"".equals(tasks.getTaskUniqueName())) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message(
				"Please select task unique name."));
			}
			if (!outCome.isSuccess()) {
				addActionMessage(outCome);
				userList = new ArrayList<UserDetail>();
				clientsList = new ArrayList<Clients>();
				locationList = usersService.getAllLocation();
				if(location==null){
					location=new Location();
				}
				location.setLocId(getLocationId());
				differentClientsEdit();
				differentUsersEdit();
				return SUCCESS;
			}

			taskUniqueNameList = taskService.taskUniqueName();
			List<String> uniqueList1 = new ArrayList<String>();
			for (Tasks uniqueList : taskUniqueNameList) {
				System.out.println(uniqueList.getTaskUniqueName());
				uniqueList1.add(uniqueList.getTaskUniqueName());
			}
			// Amit...
			getTaskUniqueNameLabel(tasks.getTaskUniqueName(),locationId,frmClientsId);
			if (uniqueList1.contains(getTaskUniqueNameLabel(tasks.getTaskUniqueName(),locationId,frmClientsId)+"-"+todaysDate())) {
				System.out.println(tasks.getTaskUniqueName());
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message(
						"Task Name Already Exist!. Please give unique name."));
				addActionMessage(outCome);
				addTaskInputPage();

			} else {
				for (Integer userEmpIdForTask : getUserEmployeeId()) {
					outCome = new OutCome();
					Clients clients = new Clients();
					Tasks tasksNew = new Tasks();
					clients.setClientId(getFrmClientsId());
					BeanUtils.copyProperties(tasksNew, tasks);
					Location location = new Location();
					location.setLocId(getLocationId());
					tasksNew.setLocation(location);
					UserDetail userDetail = new UserDetail();
					userDetail.setUserEmpId(userEmpIdForTask);
					tasksNew.setUserDetail(userDetail);
					tasksNew.setClients(clients);
					tasksNew.setStatus(Status.INITIATED_ADMIN_MULTIUSER
							.toString());
					tasksNew.setCreatedUId((Integer) session.get("uId"));
					tasksNew.setCreatedDtm(new Date());
					if (outCome.isSuccess()) {
						isDuplicate = taskDao.checkTaskDuplication(
								userEmpIdForTask, getLocationId(),
								getFrmClientsId());
					}
					if (isDuplicate) {
						List<Object> param = new ArrayList<Object>();
						getEmployeeNameLabel(userEmpIdForTask);
						param.add(getEmployeeNameLabel(userEmpIdForTask));
						outCome.setStatus(OutCome.Status.FAILURE);
						outCome.addMessage(new OutCome.Message(
								"task.duplicate.error", param));
					}
					if (outCome.isSuccess()) {
						System.out.println("Task is getting assgned for >> "
								+ getEmployeeNameLabel(userEmpIdForTask));
						tasksNew.setTaskUniqueName(getTaskUniqueNameLabel(tasks.getTaskUniqueName(),locationId,frmClientsId)+"-"+todaysDate());
						taskService.addTask(tasksNew);
					}
					if (outCome.isSuccess()) {
						taskService.sendTaskAddedEmail(userEmpIdForTask,
								outCome);
					}
					if (outCome.isSuccess()) {
						outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
						outCome
								.addMessage(new OutCome.Message(
										"Task assgned to "
												+ getEmployeeNameLabel(userEmpIdForTask)));
					}
					addActionMessage(outCome);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			tasks=new Tasks();
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("internalProblem"));
			addActionMessage(outCome);
		}
		userList = new ArrayList<UserDetail>();
		clientsList = new ArrayList<Clients>();
		locationList = usersService.getAllLocation();
		return SUCCESS;
	}
	private String getTaskUniqueNameLabel(String taskUniqueName, Integer locationId, Integer frmClientsId2) {
		String taskName="";
		clientsList = clientService.getClientsOnLocation(locationId);
		
		for(Clients clients:clientsList){
			
			if(clients.getClientId().intValue()==frmClientsId2.intValue()){
				System.out.println("client name " + clients.getClientName());
				System.out.println("client name " + clients.getClientDetails().getlocationArea()
						.getAreaName());
				System.out.println("client name " + clients.getClientDetails().getLocation().getCity());
				taskName=clients.getClientName().substring(0, 3);
				taskName=taskName+"-"+clients.getClientDetails().getLocation().getCity()+"-"+clients.getClientDetails().getlocationArea()
				.getAreaName().substring(0, 3);
			}
			
		}
		/*//String locName=
		TaskDaoImpl daoImpl=new TaskDaoImpl();
		daoImpl.getLocName(locationId2);
		daoImpl.getClientName(frmClientsId2);*/
		return taskUniqueName+"-"+taskName;
	}

	/*
	 * by amarjit for reassign task
	 */
	public String addTaskToOther() {
		unLockFunction(LockFunction.TASK);
		System.out.println(tasks.getTaskId());
		Tasks tasks1 = taskService.loadTaskOnTaskId(tasks.getTaskId());
		boolean isDuplicate = false;
		OutCome outCome = new OutCome();
		//tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		try {
			
			if (getUserEmployeeId() == null || getUserEmployeeId().length == 0) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.PARTIAL_SUCCESS);
				outCome.addFailureMessage(new OutCome.Message(
						"Please select User."));
				reAssignUserList = usersService.getUsersOnLocationExcludeRejected(tasks);
				outCome.addMessage(new OutCome.Message(
						"This task needs to be Reasigned.<hr>"
								+ " <span style=\" color:#ff66;\">Task unique name was :</span> "
								+ tasks1.getTaskUniqueName() + "<br>"
								+ "Client Store Location "
								+ tasks1.getLocation().getArea()+","
								+tasks1.getLocation().getCity()+","
								+tasks1.getLocation().getState()+","
								+tasks1.getLocation().getCountry()+","+"<br>"
								+ "Task Client Name "
								+ tasks1.getClients().getUserDetail().getFirstName()+" "+tasks1.getClients().getUserDetail().getLastName()+"<br>"
								+ "Store Name"
								+ tasks1.getClients().getClientName()+"<br>"
								+"User whose Task Feedback Was Declined : "
								+ tasks1.getUserDetail().getFirstName()+" "+ tasks1.getUserDetail().getLastName()));
				addActionMessage(outCome);
				return SUCCESS;
			}
			
			/*if (tasks.getTaskUniqueName()==null ||"".equals(tasks.getTaskUniqueName())) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addFailureMessage(new OutCome.Message(
				"Please select task unique name."));
			}*/
			/*if (!outCome.isSuccess()) {
				addActionMessage(outCome);
				userList = new ArrayList<UserDetail>();	
				tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
				reAssignUserList = usersService.getUsersOnLocationExcludeRejected(tasks);
				tasks.setTaskUniqueName("");
				return SUCCESS;
			}*/

			/*taskUniqueNameList = taskService.taskUniqueName();
			List<String> uniqueList1 = new ArrayList<String>();
			for (Tasks uniqueList : taskUniqueNameList) {
				System.out.println(uniqueList.getTaskUniqueName());
				uniqueList1.add(uniqueList.getTaskUniqueName());
			}*/
			// Amit...
			/*if (uniqueList1.contains(getTaskUniqueName())) {
				System.out.println(tasks.getTaskUniqueName());
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message(
						"Task Name Already Exist!. Please give unique name."));
				addActionMessage(outCome);
				addTaskInputPage();
			} else {*/
				for (Integer userEmpIdForTask : getUserEmployeeId()) {
					outCome = new OutCome();
					Clients clients = new Clients();
					Tasks tasksNew = new Tasks();
					clients.setClientId(tasks1.getClients().getClientId());
					BeanUtils.copyProperties(tasksNew, tasks);
					Location location = new Location();
					location.setLocId(tasks1.getLocation().getLocId());
					tasksNew.setLocation(location);
					UserDetail userDetail = new UserDetail();
					userDetail.setUserEmpId(userEmpIdForTask);
					tasksNew.setUserDetail(userDetail);
					tasksNew.setClients(clients);
					tasksNew.setStatus(Status.INITIATED_ADMIN_MULTIUSER
							.toString());
					tasksNew.setCreatedUId((Integer) session.get("uId"));
					tasksNew.setCreatedDtm(new Date());
					if (outCome.isSuccess()) {
						isDuplicate = taskDao.checkTaskDuplication(
								userEmpIdForTask, getLocationId(),
								getFrmClientsId());
					}
					if (isDuplicate) {
						List<Object> param = new ArrayList<Object>();
						getEmployeeNameLabel(userEmpIdForTask);
						param.add(getEmployeeNameLabel(userEmpIdForTask));
						outCome.setStatus(OutCome.Status.FAILURE);
						outCome.addMessage(new OutCome.Message(
								"task.duplicate.error", param));
					}
					if (outCome.isSuccess()) {
						System.out.println("Task is getting assgned for >> "
								+ getEmployeeNameLabel(userEmpIdForTask));
						taskService.addTask(tasksNew);
					}
					if (outCome.isSuccess()) {
						taskService.sendTaskAddedEmail(userEmpIdForTask,
								outCome);
					}
					if (outCome.isSuccess()) {
						System.out.println("update feedback reassignd by client");
						System.out.println(tasks1.getTaskId());
						List<UserFeedback> feedList = new FeedbackServiceImpl().getAllReassignedFeedbackByClient(new UserFeedback());
						for (UserFeedback userFeedback : feedList) {
							if (userFeedback.getTasks().getTaskId()== tasks1.getTaskId()) {
								userFeedback.setStatus(Status.REJECTED_ADMIN.toString());
							}
							new FeedbackServiceImpl().updateUserFeedbackbyAdmin(userFeedback);
						}
						outCome.setStatus(OutCome.Status.FAILURE);
						outCome
								.addMessage(new OutCome.Message(
										"Task assgned to "
												+ getEmployeeNameLabel(userEmpIdForTask)+". New name of task "+tasksNew.getTaskUniqueName()));
					}
					addActionMessage(outCome);
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("internalProblem"));
			addActionMessage(outCome);
		}
		userList = new ArrayList<UserDetail>();
		clientsList = new ArrayList<Clients>();
		locationList = usersService.getAllLocation();
		tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		reAssignUserList = usersService.getUsersOnLocationExcludeRejected(tasks);
	
		return SUCCESS;
	}


	// Update task assign to user
	public String updateTaskInputPage() {
		OutCome outCome = new OutCome();
		Integer userEmpId = (Integer) session.get("UserEmpId");
		loadStaticData();
		unLockFunction(LockFunction.TASK);
		// setting userId for locking - task opening function
		if (servletContext.getAttribute(LockFunction.TASK.toString()) == null) {
			servletContext
					.setAttribute(LockFunction.TASK.toString(), userEmpId);
		} else {
			setLockedByFlg(true);
			outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
			List<Object> param = new ArrayList<Object>();
			param.add(getEmployeeNameLabel(userEmpId));
			outCome.addMessage(new OutCome.Message("error.lockedby", param));
			addActionMessage(outCome);
			return "noUserClientExit";
		}
		return SUCCESS;
	}

	// Updated data has to inserted into task
	public String updateTask() {

		try {

			unLockFunction(LockFunction.TASK);

			Clients clients = new Clients();
			clients.setClientId(getFrmClientsId());
			Location location = new Location();
			location.setLocId(getLocationId());
			tasks.setUserDetail(tasks.getUserDetail());
			tasks.setClients(clients);
			tasks.setLocation(location);
		/*	Tasks tasksDb=taskService.loadTaskOnTaskId(tasks.getTaskId());
			tasks.setCreatedDtm(tasksDb.getCreatedDtm());
			tasks.setCreatedUId(tasksDb.getCreatedUId());
		*/	tasks.setModifiedUId((Integer) session.get("uId"));
			tasks.setModifiedDtm(new Date());
			System.out.println(tasks.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
		userList = usersService.getAllUser();
		clientsList = clientService.showAllClients();
		locationList = usersService.getAllLocation();
		if (tasks.getStatus().equals("INITIATED_USER")
				|| tasks.getStatus().equals("FINISHED_USER")) {
			System.out.println("inside if condition");
			addActionError("Task accepted by user.You can not update it.");
			return "noUpdation";
		} else {
			taskService.updateTask(tasks);
			setNavigationFlag("success");
			return SUCCESS;
		}

	}

	public String finalAsignTaskToUser() {
		OutCome outCome = new OutCome();
		try {
			System.out.println(tasks.getTaskId());
			if (tasks.getTaskId() == null) {
				SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
				criteriaTask.setTaskUniqueName(taskUniqueName);
				tasksList = taskService.taskResponses(criteriaTask);
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message(
						"Please select user to asign task."));
				addActionMessage(outCome);
				return SUCCESS;
			}
			System.out.println(tasks.getTaskUniqueName());
			if (!validateTasks(tasks.getTaskUniqueName())) {
				SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
				criteriaTask.setTaskUniqueName(tasks.getTaskUniqueName());
				tasksList = taskService.taskResponses(criteriaTask);
				outCome
						.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
				outCome
						.addMessage(new OutCome.Message(
								"You can not change Task asignment, person to whom task is asigned is in progress."));
				addActionMessage(outCome);
				return SUCCESS;
			}//Amit 
			/*statusTask=taskService.loadStatusTaskId(tasks.getTaskId());
				System.out.println(statusTask);
			if("REJECTED_TASK".equals(statusTask)){
				
				outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
		outCome.addMessage(new OutCome.Message("You can not assign task.This is Rejected User "));				
			}else*///{
			
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
			tasks.setStatus(Status.INITIATED_ADMIN.toString());
			tasks.setModifiedUId((Integer) session.get("uId"));
			tasks.setModifiedDtm(new Date());
			taskService.updateTask(tasks);
			// update other tasks with rejected status as they are not
			// intersted.
			// get all list of for that and update with them
			taskService.updateTaskForRejectedUsers(tasks.getTaskUniqueName(),
					tasks.getTaskId());
			outCome
					.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
			outCome
					.addMessage(new OutCome.Message(
							"Selected user is confirmed for asigned task and other all Users are rejected ( initimated via Email)."));
		//}
		} catch (Exception e) {
			e.printStackTrace();
			outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("internalProblem"));
		}
		SearchCriteriaTask criteriaTask = new SearchCriteriaTask();
		System.out.println(taskUniqueName);
		criteriaTask.setTaskUniqueName(taskUniqueName);
		tasksList = taskService.taskResponses(criteriaTask);
		addActionMessage(outCome);
		return SUCCESS;
	}

	private boolean validateTasks(String taskUniqueName) {
		return taskService.validateTasks(taskUniqueName);
	}

	// Ajax call action method Select Total number of Users on respective
	// Locations only
	public String differentUsers() {

		System.out.println("differentUsers");
		System.out.println("location ID : " + location.getLocId());
		userList = usersService.getUsersOnLocation(location.getLocId());
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");

		try {
			response.getWriter().write("<select>");
			response.getWriter().write(
					"<option>" + "---Select---" + "</option>");
			for (UserDetail detail : userList) {
				// So that admin can assign task to ACTIVE users only
				if (Status.ACTIVE.toString().equals(detail.getStatus())) {
					response.getWriter().write(
							"<option  value=" + detail.getUserEmpId() + ">"
									+ detail.getFirstName() + " "
									+ detail.getLastName() + " - "
									+ detail.getlocationArea().getAreaName()
									+ "</option>");
				}
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Ajax call action method Select Total number of Clients on respective
	// Locations only
	public String differentClients() {

		System.out.println("differentClients");
		System.out.println("location ID : " + location.getLocId());

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		try {
			clientsList = clientService.getClientsOnLocation(location
					.getLocId());
			response.getWriter().write("<select>");
			response.getWriter().write(
					"<option>" + "---Select---" + "</option>");
			for (Clients clients : clientsList) {
				response.getWriter().write(
						"<option  value="
								+ clients.getClientId()
								+ ">"
								+ clients.getClientName()
								+ " - "
								+ clients.getClientDetails().getlocationArea()
										.getAreaName() + "</option>");
			}
			response.getWriter().write("</select>");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteTask() {

		return SUCCESS;
	}

	public String deleteTaskInput() {
		try {
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
			tasks.setStatus(Status.INACTIVATED_ADMIN.toString());
			tasks.setModifiedUId((Integer) session.get("uId"));
			tasks.setModifiedDtm(new Date());
			taskService.inactivateTask(tasks);
			setNavigationFlag("success");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Getter And Setter
	public List<Tasks> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Tasks> tasksList) {
		this.tasksList = tasksList;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public List<UserDetail> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetail> userList) {
		this.userList = userList;
	}

	public List<Clients> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public Tasks getTasks() {
		return tasks;
	}

	public void setTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	public Integer getFrmClientsId() {
		return frmClientsId;
	}

	public void setFrmClientsId(Integer frmClientsId) {
		this.frmClientsId = frmClientsId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the userEmployeeId
	 */
	public Integer[] getUserEmployeeId() {
		return userEmployeeId;
	}

	/**
	 * @param userEmployeeId
	 *            the userEmployeeId to set
	 */
	public void setUserEmployeeId(Integer[] userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getCityAndArea() {
		return cityAndArea;
	}

	public void setCityAndArea(String cityAndArea) {
		this.cityAndArea = cityAndArea;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public List<FeedbackDocuments> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackDocuments> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public UserFeedback getUserFeedback() {
		return userFeedback;
	}

	public void setUserFeedback(UserFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}

	public void loadStaticData() {
		if (tasks != null && tasks.getTaskId() != null)
			tasks = taskService.loadTaskOnTaskId(tasks.getTaskId());
		userList = usersService.getAllUser();
		clientsList = clientService.showAllClients();
		locationList = usersService.getAllLocation();
	}

	public String closeAndUnlockTask() {
		loadStaticData();
		setNavigationFlag("success");
		unLockFunction(LockFunction.TASK);
		return SUCCESS;
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

	/**
	 * @return the reasignFlag
	 */
	public boolean isReasignFlag() {
		return reasignFlag;
	}

	/**
	 * @param reasignFlag the reasignFlag to set
	 */
	public void setReasignFlag(boolean reasignFlag) {
		this.reasignFlag = reasignFlag;
	}
	private String todaysDate(){
//		 This is how to get today's date in Java
        Date today = new Date();
     
        //If you print Date, you will get un formatted output
        System.out.println("Today is : " + today);
     
        //formatting date in Java using SimpleDateFormat
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String todaysDate = DATE_FORMAT.format(today);
        return todaysDate;
	}

	/**
	 * @return the tasks1
	 */
	public Tasks getTasks1() {
		return tasks1;
	}

	/**
	 * @param tasks1 the tasks1 to set
	 */
	public void setTasks1(Tasks tasks1) {
		this.tasks1 = tasks1;
	}
	
	public String differentUsersEdit() {

		System.out.println("differentUsers");
		System.out.println("location ID : " + location.getLocId());
		userList = usersService.getUsersOnLocation(location.getLocId());

		try {
			for (UserDetail detail : userList) {
				// So that admin can assign task to ACTIVE users only
				if (Status.ACTIVE.toString().equals(detail.getStatus())) {
									detail.setFirstName(detail.getFirstName() + " "
									+ detail.getLastName() + " - "
									+ detail.getlocationArea().getAreaName()
									);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Ajax call action method Select Total number of Clients on respective
	// Locations only
	public String differentClientsEdit() {

		System.out.println("differentClients");
		System.out.println("location ID : " + location.getLocId());
		try {
			clientsList = clientService.getClientsOnLocation(location
					.getLocId());
			for (Clients clients : clientsList) {
								
				clients.setClientName(clients.getClientName()
								+ " - "
								+ clients.getClientDetails().getlocationArea()
										.getAreaName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
