package com.kjnext.action;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import com.kjnext.dmart.dao.TaskClientPointsDao;
import com.kjnext.dmart.dao.Impl.TaskClientPointsDaoImpl;

import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.TaskClientPointsServices;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.TaskClientPointsServicesImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.SearchCriteriaClientPoints;




@SuppressWarnings("serial")
public class ClientRewardAction extends BaseAction{
	
	List<TaskClientPoints> clientPointsList = null;
	TaskClientPointsServices tackClientPointServices = new TaskClientPointsServicesImpl();
	UsersService usersService = new UsersServiceImpl();
	Clients clients;
	TaskClientPoints taskClientPoints;
	List<Clients> clientsList = null;
	List<UserDetail> userList = null;
	List<Location> locationList = null;
	ClientService clientService = new ClientServiceImpl();
	private String navigationFlag;
	Location location;
	UserDetail userDetail;
	TaskClientPointsDao taskClientPointsDao=new TaskClientPointsDaoImpl();
	private Integer frmClientsId;
	private Integer userEmployeeId;
	private Integer locationId;	
	private String cityAndArea;
	private Integer taskPointId;
	private String pageTitle;

	
	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}
	
	//for load all client reward points
	public String showClientRewardPoints(){
		
		pageTitle=getText("client.points");
		SearchCriteriaClientPoints searchCriteriaClientPoints = new SearchCriteriaClientPoints();
		clientPointsList = tackClientPointServices.getAllTaskClientPoints(searchCriteriaClientPoints);
		for (TaskClientPoints taskClientPoints : clientPointsList) {
			Integer uid = taskClientPoints.getModifiedUId();
			if(uid!=null)
			{
				Users tempUsers =usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					taskClientPoints.setModifiedUIdName(uD.getFirstName()+" "+uD.getLastName());
				}
			}
			else
			{
				taskClientPoints.setModifiedUIdName("-");
			}
			
			uid = taskClientPoints.getCreatedUId();
			if(uid!=null)
			{
				Users tempUsers =usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					taskClientPoints.setCreatedUIdName(uD.getFirstName());
				}
			}
			else
			{
				taskClientPoints.setCreatedUIdName("-");
			}
			
		}
		
		return SUCCESS;
	}
	
	//for clientpoints input page
	public String addClientPointsInput(){
		
		userList = usersService.getAllUser();
		clientsList = clientService.showAllClients();
		locationList = usersService.getAllLocation();
		
		List<Clients> clientExistList=clientService.showAllClientsAddPoints();
		   
	    for (Clients clients : clientExistList) {
			clientsList.remove(clients);
		}	
	    System.out.println(clientsList);
		for (Location location : locationList) {
			location.setCityAndArea(location.getCity()+"-"+location.getArea());
			
		}
		return SUCCESS;
	}
	
	//for add client points
	public String addClientPoints(){
		try {
			Clients clients = new Clients();
			clients.setClientId(getFrmClientsId());		
			userDetail.setUserEmpId(getUserEmployeeId());
			taskClientPoints.setTaskPoints(taskClientPoints.getTaskPoints());
			taskClientPoints.setClients(clients);			
			taskClientPoints.setCreatedDtm(new Date());
			System.out.println(session.get("uId"));
			taskClientPoints.setCreatedUId((Integer)session.get("uId"));
			tackClientPointServices.addClientPoints(taskClientPoints);
			clientsList = clientService.showAllClients();
			userList = usersService.getAllUser();
			clientsList = clientService.showAllClients();
			locationList = usersService.getAllLocation();
			for (Location location : locationList) {
				location.setCityAndArea(location.getCity()+"-"+location.getArea());
				
			}
			
			setNavigationFlag("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
// @Edit Client Task Point
	
	public String getClientPointsEditInput(){
		
		this.taskClientPoints = tackClientPointServices.getClientPoints(getTaskPointId());
		
		System.out.println(taskClientPoints.getClients().getClientName());
		System.out.println(taskClientPoints.getTaskPoints());		
		return SUCCESS;
	}
	
	//for update 
	
	public String updateTaskClientPoints(){
		System.out.println(getTaskClientPoints().getTaskPointId());
		Integer cId = getTaskClientPoints().getTaskPointId();
		TaskClientPoints taskClientPoint = tackClientPointServices.getClientPoints(cId);
		
		taskClientPoint.setTaskPoints(getTaskClientPoints().getTaskPoints());
		taskClientPoint.setModifiedDtm(new Date());
		taskClientPoint.setModifiedUId((Integer)session.get("uId"));
		tackClientPointServices.updateTaskClientPoints(taskClientPoint, cId);
		setNavigationFlag("success");
		return SUCCESS;
	}

	public List<TaskClientPoints> getClientPointsList() {
		return clientPointsList;
	}

	public void setClientPointsList(List<TaskClientPoints> clientPointsList) {
		this.clientPointsList = clientPointsList;
	}



	public TaskClientPoints getTaskClientPoints() {
		return taskClientPoints;
	}



	public void setTaskClientPoints(TaskClientPoints taskClientPoints) {
		this.taskClientPoints = taskClientPoints;
	}

	public List<Clients> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<Clients> clientsList) {
		this.clientsList = clientsList;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public Integer getUserEmployeeId() {
		return userEmployeeId;
	}

	public void setUserEmployeeId(Integer userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	public String getCityAndArea() {
		return cityAndArea;
	}

	public void setCityAndArea(String cityAndArea) {
		this.cityAndArea = cityAndArea;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<UserDetail> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetail> userList) {
		this.userList = userList;
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public Integer getTaskPointId() {
		return taskPointId;
	}

	public void setTaskPointId(Integer taskPointId) {
		this.taskPointId = taskPointId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
}
