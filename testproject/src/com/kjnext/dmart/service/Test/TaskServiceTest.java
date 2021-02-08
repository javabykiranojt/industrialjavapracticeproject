package com.kjnext.dmart.service.Test;

import java.util.List;

import junit.framework.TestCase;

import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.service.TaskService;
import com.kjnext.dmart.service.Impl.TaskServiceImpl;
import com.kjnext.dmart.vo.SearchCriteriaTask;

public class TaskServiceTest extends TestCase {

	TaskService taskService = null;

	@Override
	protected void setUp() throws Exception {
		taskService = new TaskServiceImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		taskService = null;
	}

	

	
	/*public void testDeleteTask() {
		Tasks tasks = new Tasks();
		Clients clients = new Clients();
		clients.setClientId(1001);
		tasks.setClients(clients);
		tasks.setDate(new Date());
		Location location = new Location();
		location.setLocId(1);
		tasks.setLocation(location);
		tasks.setStatus(Status.INACTIVE.toString());
		tasks.setTime(new Date());
		tasks.setVenue("deccan123");
		UserDetail detail = new UserDetail();
		detail.setUserEmpId(2);
		tasks.setUserDetail(detail);
		tasks.setTaskId(6);
		taskService.updateTask(tasks);
	}*/
	public void testGetAllTask() {
		SearchCriteriaTask criteriaTask=null;
		List<Tasks> list=taskService.getAllTask(criteriaTask);
		assertEquals(list.size(), 16);
	}
	public void testGetAllTaskForUser() {
		SearchCriteriaTask criteriaTask=new SearchCriteriaTask();
		criteriaTask.setUserEmpId(1);
		List<Tasks> list=taskService.getAllTaskForUser(criteriaTask);
		assertEquals(list.size(), 15);
		SearchCriteriaTask criteriaTask1=new SearchCriteriaTask();
		criteriaTask1.setUserEmpId(2);
		List<Tasks> list1=taskService.getAllTaskForUser(criteriaTask1);
		assertEquals(list1.size(), 1);
		
	}
	public void testGetAllTaskForClient() {
		SearchCriteriaTask criteriaTask=new SearchCriteriaTask();
		criteriaTask.setClientId(1001);
		List<Tasks> list=taskService.getAllTaskForClient(criteriaTask);
		assertEquals(list.size(), 16);
		SearchCriteriaTask criteriaTask1=new SearchCriteriaTask();
		criteriaTask1.setClientId(1001);
		List<Tasks> list1=taskService.getAllTaskForClient(criteriaTask1);
		assertEquals(list1.size(), 16);
		
	}
	public void testGetTask() {
		SearchCriteriaTask criteriaTask=new SearchCriteriaTask();
		criteriaTask.setTaskId(10);
		Tasks task=taskService.getTask(criteriaTask);
		assertEquals(task.getVenue(),"deccan");
		assertNotSame(task.getVenue(),"deccan1");
	}
	public void testGetAllTaskCustom() {
		SearchCriteriaTask criteriaTask=new SearchCriteriaTask();
		criteriaTask.setUserEmpId(1);
		criteriaTask.setLocId(2);
		List<Tasks> list=taskService.getAllTask(criteriaTask);
		assertEquals(list.size(), 2);
		SearchCriteriaTask criteriaTask1=new SearchCriteriaTask();
		criteriaTask1.setUserEmpId(2);
		criteriaTask1.setLocId(1);
		List<Tasks> list1=taskService.getAllTask(criteriaTask1);
		assertEquals(list1.size(), 1);
	}


	/*public void testAddTask() {
		Tasks tasks = new Tasks();
		Clients clients = new Clients();
		clients.setClientId(1001);
		tasks.setClients(clients);
		tasks.setDate(new Date());
		Location location = new Location();
		location.setLocId(1);
		tasks.setLocation(location);
		tasks.setStatus(Status.INITIATED_ADMIN.toString());
		tasks.setTime(new Date());
		tasks.setVenue("deccan");
		UserDetail detail = new UserDetail(); 
		detail.setUserEmpId(1);
		tasks.setUserDetail(detail);
		Integer taskId = taskService.addTask(tasks);
		System.out.println(taskId);
		assertNotNull(taskId);
	}*/
	/*public void testUpdateTask() {
		Tasks tasks = new Tasks();
		Clients clients = new Clients();
		clients.setClientId(1001);
		tasks.setClients(clients);
		tasks.setDate(new Date());
		Location location = new Location();
		location.setLocId(1);
		tasks.setLocation(location);
		tasks.setStatus(Status.INITIATED_ADMIN.toString());
		tasks.setTime(new Date());
		tasks.setVenue("deccan123");
		UserDetail detail = new UserDetail();
		detail.setUserEmpId(2);
		tasks.setUserDetail(detail);
		tasks.setTaskId(6);
		taskService.updateTask(tasks);
	}*/

}
