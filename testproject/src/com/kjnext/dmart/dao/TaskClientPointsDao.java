package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.vo.SearchCriteriaClientPoints;

public interface TaskClientPointsDao {
	
	public List<TaskClientPoints> getAllTaskClientPoints(SearchCriteriaClientPoints searchCriteriaClientPoints);
	public Integer addClientPoints(TaskClientPoints taskClientPoints);
	public Integer getPoints(Integer clientId);
	public TaskClientPoints getClientPoints(Integer taskPointId);
	public boolean updateTaskClientPoints (TaskClientPoints taskClientPoints, Integer taskPointId);
	public Integer getClientPointsId(Integer clientId);
     	
}
