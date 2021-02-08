package com.kjnext.dmart.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.TaskDao;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.vo.SearchCriteriaTask;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.hibernate.HibernateUtil;

public class TaskDaoImpl implements TaskDao {

	public Integer addTask(Tasks tasks) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(tasks);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return tasks.getTaskId();
	}

	public void inactivateTask(Tasks tasks) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(tasks);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public void updateTask(Tasks tasks) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(tasks);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tasks> getAllTask(SearchCriteriaTask criteriaTask) {
		System.out.println("**INSIDE TASK DAO IMPL get alltask");
	
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			Criteria criteria = hibernateSession.createCriteria(
					Tasks.class);

			if (criteriaTask == null) {
				return criteria.list();
			}
			if (criteriaTask.getClientId() != null) {
				criteria.createCriteria("clients").add(
						Expression.eq("clientId", criteriaTask.getClientId()));
			}

			if (criteriaTask.getTaskId() != null) {
				criteria.add(Expression.eq("taskId", criteriaTask.getTaskId()));
			}

			/*if (criteriaTask.getLocId() != null) {
				criteria.createCriteria("location").add(
						Expression.eq("locname", criteriaTask.getlocname()));
			}*/

			if (criteriaTask.getUserEmpId() != null) {
				criteria.createCriteria("userDetail")
						.add(Expression.eq("userEmpId", criteriaTask.getUserEmpId()));
			}

			if (criteriaTask.getStatus() != null) {
				criteria.add(Expression.eq("status", criteriaTask.getStatus()
						.toString()));
				
				/*
				 * if
				 * (!criteriaTask.getStatus().toString().equals(Status.INACTIVE)) {
				 * criteria.add(Expression.eq("status",
				 * Status.ACTIVE.toString())); }
				 */
			}
			if (criteriaTask.getTaskUniqueName() != null) {
				criteria.add(Expression.eq("taskUniqueName", criteriaTask.getTaskUniqueName()
						.toString()));
				}
			List<Tasks> listTask = criteria.list();

			return listTask;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	
	public List<String > taskResponsesUnique(){
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List<String > taskUniqueNames=null;
		try {
			Query query=hibernateSession.createSQLQuery("select distinct taskUniqueName from tasks where status in ('INITIATED_USER_MULTIUSER_ACCEPT','INITIATED_USER_MULTIUSER_REJECT','INITIATED_ADMIN_MULTIUSER','REJECTED_TASK','INITIATED_ADMIN')");
				taskUniqueNames=query.list();
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return taskUniqueNames;
	}
	
/*
 * public static void main(String[] args) { TaskDaoImpl daoImpl = new
 * TaskDaoImpl(); System.out.println(daoImpl.getAllTask(null)); }
 */

	public Tasks loadTaskOnTaskId(Integer taskId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {

			Tasks tasks = (Tasks)hibernateSession.get(Tasks.class,
					taskId);
			return tasks;
		}catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public List<Tasks> userFeedbackClientNameDate(Integer taskId) {
		List<Tasks> returnClientnameAndDate=null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		
		try {
			Criteria criteria = hibernateSession.createCriteria(Tasks.class);  	
			returnClientnameAndDate=criteria.add(Restrictions.eq("taskId", taskId)).list();   
	             	
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnClientnameAndDate;
		}

	public boolean checkTaskDuplication(Integer userEmployeeId, Integer locationId, Integer frmClientsId) {
		List<Tasks> checkTaskDuplication=null;
		// Tasks tasks=new Tasks();
		boolean isDuplicate=false;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		
		try {
			System.out.println("Checking duplicate task for >> "+userEmployeeId+" location >>"+ locationId + " Client Id >>" + frmClientsId);
			String sqlForDuplocateTask="select * from Tasks where userEmpId='"+userEmployeeId+"' and locId='"+locationId+"' and client_id ='"+frmClientsId+"' and status ='INITIATED_ADMIN'";
			Query query=hibernateSession.createSQLQuery(sqlForDuplocateTask);
			/*
			 * Criteria criteria = hibernateSession.createCriteria(Tasks.class);
			 * checkTaskDuplication=criteria.add(Restrictions.eq("userDetail.userEmpId",userEmployeeId)).add(Restrictions.eq("location.locId",locationId)).add(Restrictions.eq("clients.clientId",frmClientsId)).list();
			 */
			
			if(query.list() !=null && query.list().isEmpty())
					isDuplicate=false;
	               else
	            	isDuplicate=true;;   
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// for any problem it will not allow to add task
		return isDuplicate;
			}

	public List<String> getUserEmailIdName(Integer userEmployeeId) {
	       
		   System.out.println("inside send mail TaskDao Impl userEmailId Name");
		   
		  
	        List<String> emailIdandName =new ArrayList<String>();
		
	        List<UserDetail> userEmailIdNameList=null;
	          Session hibernateSession = (Session) (CallContextService.getInstance()
					.getContext().getAttribute("hibernateSession"));
			System.out.println();
			try {
				Criteria criteria = hibernateSession.createCriteria(UserDetail.class);  	
				userEmailIdNameList=criteria.add(Restrictions.eq("userEmpId",userEmployeeId)).list();
				for (UserDetail userDetail : userEmailIdNameList) 
				{
				      emailIdandName.add(userDetail.getFirstName());
				      emailIdandName.add(userDetail.getEmailId());
				}
			} 	    
			catch (Exception e) {
				e.printStackTrace();
			    }
			
			return emailIdandName;
			}

	@SuppressWarnings("unchecked")
	public void updateTaskForRejectedUsers(String taskUniqueName, Integer taskId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		
		try {
			Query query=hibernateSession.createSQLQuery("select taskId from tasks where taskUniqueName='"+taskUniqueName+"' and taskId <> '"+taskId+"'");
			List<Integer> list=query.list();
				for(Integer taskId1:list){
					Query query2=hibernateSession.createQuery("update Tasks set status='REJECTED_TASK' where taskId=:tskId");
					query2.setParameter("tskId", taskId1);
					int updated=query2.executeUpdate();
					System.out.println("Task updated ..>>"+updated);
				}
			/*List<Tasks> listTask = hibernateSession.createCriteria(Tasks.class).add(Restrictions.eq("taskUniqueName", taskUniqueName)).list();
			for (Tasks tasks : listTask) {
				System.out.println(tasks.getTaskId());
				System.out.println(tasks.getStatus());
				if (tasks.getTaskId()!= taskId) {
					tasks.setStatus(Status.REJECTED_TASK.toString());
					hibernateSession.update(tasks);
				}
			}*/
			
		}catch (Exception e) {
			hibernateSession = null;
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} 
		}

		public boolean validateTasks(String taskUniqueName) {
			boolean canChangeTaskAsignment=false;
			Session hibernateSession = (Session) (CallContextService.getInstance()
					.getContext().getAttribute("hibernateSession"));
			
			try {
				Criteria criteria=	hibernateSession.createCriteria(Tasks.class).add(Expression.eq("taskUniqueName", taskUniqueName)).add(Expression.eq("status", "REJECTED_TASK"));
				if(0<criteria.list().size()){
					Criteria criteria1=	hibernateSession.createCriteria(Tasks.class).add(Expression.eq("taskUniqueName", taskUniqueName)).add(Expression.eq("status", "INITIATED_ADMIN"));
					if(0<criteria1.list().size()){
						canChangeTaskAsignment=true;
					}
				}else{
					canChangeTaskAsignment=true;
				}
			}catch (Exception e) {
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
			return canChangeTaskAsignment;
			}
		
		public List<Tasks> taskUniqueName(){
			Session hibernateSession = (Session) (CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			
			try{
				List<Tasks> listOfUniqueName=(List<Tasks>)hibernateSession.createCriteria(Tasks.class,"taskUniqueName").list();
				
				return listOfUniqueName;
			}
			
			catch(Exception e){
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			}finally{
				hibernateSession=null;
			}
			
		}
		
		public String loadStatusTaskId(Integer taskId) {
			//String taskStatuss="";
			Session hibernateSession = (Session) (CallContextService.getInstance()
					.getContext().getAttribute("hibernateSession"));
			try {
				Tasks taskStatuss = (Tasks)hibernateSession.get(Tasks.class,taskId);
				 //Tasks taskStatuss=(Tasks)hibernateSession.createCriteria(Tasks.class).add(Expression.eq("status",taskId));
				
				 System.out.println(taskStatuss.getStatus());
				 String st=taskStatuss.getStatus();
				
				return st;
			}catch (Exception e) {
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
		}
	}
    
