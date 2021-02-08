package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.vo.SearchCriteriaClientPoints;




public interface TaskClientPointsServices {
	
	public List<TaskClientPoints> getAllTaskClientPoints(SearchCriteriaClientPoints criteriaClientPoints);
	public Integer addClientPoints(TaskClientPoints taskClientPoints);
	public Integer getPoints(Integer clientId);
	public TaskClientPoints getClientPoints(Integer taskPointId);
	public boolean updateTaskClientPoints (TaskClientPoints taskClientPoints, Integer taskPointId);
	public Integer getClientPointsId(Integer clientId);
}
