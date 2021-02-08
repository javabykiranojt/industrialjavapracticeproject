package com.kjnext.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;

public class LocationAction extends BaseAction{
//	****** START BY SNEHA
	private static final long serialVersionUID = 1L;
	ClientService clientService = new ClientServiceImpl();
	UsersService usersService=new UsersServiceImpl();
	
	private Location tempClientCity;
	private Location location;
	private LocationArea locationArea;
	List<Location> locationList;
	List<LocationArea> locationAreaList;
	private Integer id ;
	private String uidName;
	String navigationFlag;
	private Integer locId;
	private String city;
	Clients clients;
	List<UserDetail> userDetail;
	OutCome outCome=new OutCome();
	
	public String addLocationInputPage(){
		System.out.println("******add location input page****");

		
		
		return SUCCESS;
	}
	
	public String showLocationDetails(){
		System.out.println("*********Show Location Details*******");		
		
		locationList=usersService.getAllLocation();
		List<String> city=new ArrayList<String>();
		 for(Location loc:locationList){
			 city.add(loc.getCity());
		 }
		 if(city.contains(location.getCity())){
			 outCome.setStatus(OutCome.Status.FAILURE);
		    	outCome.addMessage(new OutCome.Message("This city already exist"));
				addActionMessage(outCome);
			 
			// addActionError("This city already exist");
		
		 }
		 
		 
		 else{ 
		Location location1 = new Location();

		uidName=(String)session.get("UserName");
		
		id=(Integer)session.get("uId");
		
		location1.setCountry("India");

		location1.setState(location.getState());
		
		location1.setCity(location.getCity());
		
		location1.setCreatedUId(id);
		
		location1.setCreatedUIdName(uidName);
		
		location1.setCreatedDtm(new Date());
		location1.setStatus("ACTIVE");
		
		clientService.addLocationDetails(location1);
		
		setNavigationFlag("success");
		
		
		
	}
		 return SUCCESS;
	}

	
	public String addLocationAreaInputPage(){
		System.out.println("******add location area input page****");
		locationList=usersService.getAllLocation();
		return SUCCESS;
	}
	
	
	public String showLocationAreaDetails(){
		System.out.println("*********Show Location Area Details*******");		
		
		locationList=usersService.getAllLocation();
		LocationArea locationArea1 = new LocationArea();
		/*uidName=(String)session.get("UserName");
		
		id=(Integer)session.get("uId");*/
		
		locationArea1.setLocation(locationArea.getLocation());
		
		locationArea1.setAreaName(locationArea.getAreaName());
	
		locationArea1.setAreaStatus("ACTIVE");
		/*location1.setCreatedUId(id);
		
		location1.setCreatedUIdName(uidName);
		
		location1.setCreatedDtm(new Date());*/
		System.out.println("*****************STATUS : "+locationArea1.getAreaStatus());
		clientService.addLocationAreaDetails(locationArea1);
		
		setNavigationFlag("success");
		
		
		return SUCCESS;
	}
	
	public String deleteLocationInputPage(){
		System.out.println("*****************************");
		return SUCCESS;
	}
	
	public String deleteLocationAreaInputPage(){
		System.out.println("*****************************");
		return SUCCESS;
	}
	
	public String confirmDeleteLocation(){

		location = usersService.getLocationById(location.getLocId());
		location.setLocId(location.getLocId());
		location.setStatus("INACTIVE");
		usersService.updateLocation(location);
		locationAreaList = usersService.getLocationAreaByLocId(location.getLocId());
		for (LocationArea locationArea : locationAreaList) {
			locationArea.setAreaStatus("INACTIVE");
			usersService.updateLocationArea(locationArea);
		}
		
		
		
		
		setNavigationFlag("success");
		return SUCCESS;
	}
	
	public String ConfirmDeleteLocationArea(){
				
				locationArea = usersService.getLocationAreaById(locationArea.getAreaId());
				locationArea.setAreaId(locationArea.getAreaId());
				locationArea.setAreaStatus("INACTIVE");
				usersService.updateLocationArea(locationArea);
				
				setNavigationFlag("success");
				return SUCCESS;
			}
			

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}
	public LocationArea getLocationArea() {
		return locationArea;
	}

	public void setLocationArea(LocationArea locationArea) {
		this.locationArea = locationArea;
	}

	public List<LocationArea> getLocationAreaList() {
		return locationAreaList;
	}

	public void setLocationAreaList(List<LocationArea> locationAreaList) {
		this.locationAreaList = locationAreaList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
}
//****** END BY SNEHA

