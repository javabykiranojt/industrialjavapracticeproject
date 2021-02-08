package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.vo.SearchCriteriaTask;

public interface TaskDao {
	public Integer addTask(Tasks tasks);

	public void updateTask(Tasks tasks);

	public void inactivateTask(Tasks tasks);

	public List<Tasks> getAllTask(SearchCriteriaTask criteriaTask);

	public Tasks loadTaskOnTaskId(Integer taskId);

	public List<Tasks> userFeedbackClientNameDate(Integer taskId);
	
    public boolean checkTaskDuplication(Integer userEmployeeId,Integer locationId,Integer frmClientsId);

    public List<String> getUserEmailIdName(Integer userEmployeeId);

	public List<String> taskResponsesUnique();

	public void updateTaskForRejectedUsers(String taskUniqueName, Integer taskId);

	public boolean validateTasks(String taskUniqueName); 
	public List<Tasks> taskUniqueName();
	public String loadStatusTaskId(Integer taskId);
}






