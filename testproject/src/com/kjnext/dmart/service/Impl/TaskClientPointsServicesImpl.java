package com.kjnext.dmart.service.Impl;

import java.util.List;

import com.kjnext.dmart.dao.TaskClientPointsDao;
import com.kjnext.dmart.dao.Impl.TaskClientPointsDaoImpl;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.service.TaskClientPointsServices;
import com.kjnext.dmart.vo.SearchCriteriaClientPoints;

public class TaskClientPointsServicesImpl implements TaskClientPointsServices{
	
	TaskClientPointsDao taskClientPointsDao = new TaskClientPointsDaoImpl();
	
	public List<TaskClientPoints> getAllTaskClientPoints(SearchCriteriaClientPoints criteriaClientPoints) {
		return taskClientPointsDao.getAllTaskClientPoints(criteriaClientPoints);
	}

	public Integer addClientPoints(TaskClientPoints taskClientPoints) {		 
		return taskClientPointsDao.addClientPoints(taskClientPoints);
	}

	public Integer getPoints(Integer clientId) {		
		return taskClientPointsDao.getPoints(clientId);
	}

	public TaskClientPoints getClientPoints(Integer taskPointId) {
		// TODO Auto-generated method stub
		return taskClientPointsDao.getClientPoints(taskPointId);
	}

	public Integer getClientPointsId(Integer clientId) {
		// TODO Auto-generated method stub
		return taskClientPointsDao.getClientPointsId(clientId);
	}

	public boolean updateTaskClientPoints(TaskClientPoints taskClientPoints, Integer taskPointId) {
		// TODO Auto-generated method stub
		return taskClientPointsDao.updateTaskClientPoints(taskClientPoints, taskPointId);
	}
}
