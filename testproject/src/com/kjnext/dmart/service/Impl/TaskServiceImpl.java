package com.kjnext.dmart.service.Impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kjnext.dmart.dao.TaskDao;
import com.kjnext.dmart.dao.Impl.TaskDaoImpl;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.vo.SearchCriteriaTask;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;
import com.kjnext.utility.email.SendEmail;

public class TaskServiceImpl implements TaskService {
	private static final Integer Integer = null;
	
	TaskDao taskDao = new TaskDaoImpl();

	public Integer addTask(Tasks tasks) {
		Integer taskId = taskDao.addTask(tasks);
		return taskId;
	}

	public void updateTask(Tasks tasks) {
		taskDao.updateTask(tasks);

	}

	public void inactivateTask(Tasks tasks) {
		taskDao.inactivateTask(tasks);

	}

	public List<Tasks> getAllTask() {
		SearchCriteriaTask criteriaTask = null;
		List<Tasks> list=taskDao.getAllTask(criteriaTask);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllTaskForClient(SearchCriteriaTask criteriaTask) {
		criteriaTask.setClientId(1001);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllTaskForUser(SearchCriteriaTask criteriaTask) {
		return taskDao.getAllTask(criteriaTask);
	}

	public Tasks getTask(SearchCriteriaTask criteriaTask) {
		return taskDao.getAllTask(criteriaTask).get(0);
	}

	public List<Tasks> getAllActiveTask(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.ACTIVE);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllFinishedByAdminTask(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.FINISHED_ADMIN);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllFinishedByClinetTask(
			SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.FINISHED_CLIENT);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllFinishedByUserTask(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.FINISHED_USER);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllInActiveTask(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INACTIVE);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllInitiatedByAdminTask(
			SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INITIATED_ADMIN);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllInitiatedByClientTask(
			SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INITIATED_CLIENT);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllInitiatedByUserTask(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INITIATED_USER);
		return taskDao.getAllTask(criteriaTask);
	}

	public List<Tasks> getAllTask(SearchCriteriaTask criteriaTask) {
		List<Tasks> list=taskDao.getAllTask(criteriaTask);
		List<Tasks> list1=new ArrayList<Tasks>();
		for(Tasks tasks:list){
			if(!tasks.getStatus().equals(Status.REJECTED_TASK)){
				list1.add(tasks);
			}
		}
		
		return list1;
	}

	public Tasks loadTaskOnTaskId(Integer taskId) {

		return taskDao.loadTaskOnTaskId(taskId);
	}

	/*
	 * public static void main(String[] args) { TaskServiceImpl task=new
	 * TaskServiceImpl(); task.sendTaskAddedEmail(1);
	 *  }
	 */
	public void sendTaskAddedEmail(Integer userEmployeeId ,OutCome outCome) {
	       System.out.println("sending mail after task addition in TaskServiceImpl");          
	       List<String> getFnameEmailID=taskDao.getUserEmailIdName(userEmployeeId); 	
		    try { 
		    	SendEmail email = new SendEmail();
		    	email.setFrom(email.email_from,"Mystery Shoppers" );
				email.setTo(getFnameEmailID.get(1));
				email.setPriority("1");
				email.setSubject("New Task For You");
				email.setBody("Dear "+getFnameEmailID.get(0)+",<br><br>Task is added for you," +
						"<br>Please login to our portal to view it.<br>" +
						"click below to login <br><br>" +
						"<b>"+email.App_URL +"</b><br><br> " +
						"Please accept task if you are intersted.<br>" +
						"Mystery Shoppers will inform you if your task is finalise<br><br>"+
						"<i>Please contact us if you have any queries.<i><br>" +
						"<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");
				email.setSMTPHost(email.email_smtp, email.email_from,email.email_password);
				email.sendMsg();
		    }
		catch (Exception e) {
			e.printStackTrace();
		  }
		
}

	
	public static void main(String[] args) {
		TaskServiceImpl impl=new TaskServiceImpl();
		impl.sendTaskAddedEmail(3, new OutCome());
	}
	private Reader FileInputStream(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEmployeeNameLabel(Integer empId) {
		UsersService usersService = new UsersServiceImpl();
		List<UserDetail> list = usersService.getEmployeeNameLabel(empId);
		System.out.println(list.get(0));
		;
		Object o = list.get(0);
		Object[] objects = (Object[]) o;
		System.out.println(objects[0] + " " + objects[1]);
		return objects[0] + " " + objects[1];
	}

	public List<Tasks> getAllPublishedTaskByAdmin(
			SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INITIATED_ADMIN_MULTIUSER);
		return taskDao.getAllTask(criteriaTask);
	}
	public List<Tasks> taskResponses(SearchCriteriaTask criteriaTask) {
		criteriaTask.setStatus(Status.INITIATED_USER_MULTIUSER_ACCEPT);
		List<Tasks> list1=taskDao.getAllTask(criteriaTask);
		criteriaTask.setStatus(Status.INITIATED_USER_MULTIUSER_REJECT);
		List<Tasks> list2=taskDao.getAllTask(criteriaTask);
		criteriaTask.setStatus(Status.INITIATED_ADMIN_MULTIUSER);
		List<Tasks> list3=taskDao.getAllTask(criteriaTask);
		criteriaTask.setStatus(Status.REJECTED_TASK);
		List<Tasks> list4=taskDao.getAllTask(criteriaTask);
		criteriaTask.setStatus(Status.INITIATED_ADMIN);
		List<Tasks> list5=taskDao.getAllTask(criteriaTask);
		list1.addAll(list2);
		list1.addAll(list3);
		list1.addAll(list4);
		list1.addAll(list5);
		return list1;
	}


	public List<String> taskNamesForResponses(SearchCriteriaTask criteriaTask) {
		List<String> list1=taskDao.taskResponsesUnique();
		return list1;
		}

	public void updateTaskForRejectedUsers(String taskUniqueName, java.lang.Integer taskId) {
		taskDao.updateTaskForRejectedUsers(taskUniqueName,taskId);
	}

	public boolean validateTasks(String taskUniqueName) {
		return taskDao.validateTasks(taskUniqueName);
	}
	public List<Tasks> taskUniqueName(){
		return taskDao.taskUniqueName();
	}

	public String loadStatusTaskId(Integer taskId) {
		// TODO Auto-generated method stub
		return taskDao.loadStatusTaskId(taskId);
	}

}
