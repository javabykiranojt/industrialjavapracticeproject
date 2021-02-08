package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.vo.SearchCriteriaTask;
import com.kjnext.utility.OutCome;

public interface TaskService {
	
	public Integer addTask(Tasks tasks);

	public void updateTask(Tasks tasks);

	public void inactivateTask(Tasks tasks);
	
	public Tasks loadTaskOnTaskId(Integer taskId);

	public Tasks getTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllTaskForUser(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllTaskForClient(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllActiveTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllInActiveTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllInitiatedByAdminTask(SearchCriteriaTask criteriaTask);
	
	public List<Tasks> getAllPublishedTaskByAdmin(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllInitiatedByUserTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllFinishedByUserTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllFinishedByAdminTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllInitiatedByClientTask(SearchCriteriaTask criteriaTask);

	public List<Tasks> getAllFinishedByClinetTask(SearchCriteriaTask criteriaTask);
	
	public void sendTaskAddedEmail(Integer userEmployeeId, OutCome outCome);

	public List<Tasks> taskResponses(SearchCriteriaTask criteriaTask);

	public List<String> taskNamesForResponses(SearchCriteriaTask criteriaTask);

	public void updateTaskForRejectedUsers(String taskUniqueName, Integer taskId);

	public boolean validateTasks(String taskUniqueName);
	
	public List<Tasks> taskUniqueName();
 	
	public String loadStatusTaskId(Integer taskId);
}





