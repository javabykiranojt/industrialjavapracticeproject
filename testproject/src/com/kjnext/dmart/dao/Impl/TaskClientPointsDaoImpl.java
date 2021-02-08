package com.kjnext.dmart.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.TaskClientPointsDao;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.vo.SearchCriteriaClientPoints;

public class TaskClientPointsDaoImpl implements TaskClientPointsDao {

	@SuppressWarnings({ "unchecked", "unchecked", "unchecked", "unchecked" })
	public List<TaskClientPoints> getAllTaskClientPoints(
			SearchCriteriaClientPoints searchCriteriaClientPoints) {
		
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			Criteria criteria = hibernateSession.createCriteria(
					TaskClientPoints.class);

			if (searchCriteriaClientPoints == null) {
				return criteria.list();
			}
			if (searchCriteriaClientPoints.getClientId() != null) {
				criteria.createCriteria("clients").add(
						Expression.eq("clientId", searchCriteriaClientPoints.getClientId()));
			}
			

			
			
			List<TaskClientPoints> listTaskClientPoints = criteria.list();

			return listTaskClientPoints;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		
	}

	public Integer addClientPoints(TaskClientPoints taskClientPoints) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(taskClientPoints);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return taskClientPoints.getTaskPointId();
	}

	@SuppressWarnings("unchecked")
	public Integer getPoints(Integer clientId) {
Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Integer points = 0;
		List<TaskClientPoints> checkPointsList =hibernateSession.createCriteria(TaskClientPoints.class).createCriteria("clients").add(Expression.eq("clientId",clientId)).list();
         if(checkPointsList.isEmpty()) 
        	 return 0;
         else{
        	 for(TaskClientPoints tcp: checkPointsList){
        		 points = tcp.getTaskPoints();
        	 }
			}
         return points;
	}

	public boolean updateTaskClientPoints(TaskClientPoints taskClientPoints, Integer taskPointId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		boolean updated=false;
		try 
		{
			
			TaskClientPoints taskClientPoint =(TaskClientPoints) hibernateSession.load(TaskClientPoints.class, taskPointId);
			taskClientPoint.setTaskPoints(taskClientPoints.getTaskPoints());
			taskClientPoint.setModifiedDtm(taskClientPoints.getModifiedDtm());
			taskClientPoint.setModifiedUId(taskClientPoints.getModifiedUId());
			
			hibernateSession.update(taskClientPoint);
			updated=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return updated;
	}

	public TaskClientPoints getClientPoints(Integer taskPointId) {
		TaskClientPoints taskClientPoints = null;
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			taskClientPoints = (TaskClientPoints) hibernateSession.load(TaskClientPoints.class, taskPointId);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return taskClientPoints;
	}

	public Integer getClientPointsId(Integer clientId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Integer clientPointId = 0;
		List<TaskClientPoints> checkPointsList =hibernateSession.createCriteria(TaskClientPoints.class).add(Expression.eq("clientId", clientId)).list();
         if(checkPointsList.isEmpty()) 
        	 clientPointId = 0;
         else{
        	 for(TaskClientPoints clientPoints: checkPointsList){
        		 clientPointId = clientPoints.getTaskPointId();
        	 }
			}
         return clientPointId;
	}

}
