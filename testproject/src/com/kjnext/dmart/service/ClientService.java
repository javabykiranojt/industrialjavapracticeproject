package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.ClientDetails;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;

public interface ClientService {
	
	public void addClient(Clients clients);
	
	
	//public void addClientDetails(ClientDetails clientDetails);//Remains to implements. 
	
	public void updateClient(Clients clients);

	public void removeClient(Clients clients);
	
	public Clients loadClientDetails(Integer clientId);
	
	public List<Clients> showAllClients();
	
	//Shivtej
	public void addClientDetails(ClientDetails  clientDetails);

	public void generateCredential(UserDetail userDetail);

	public void addUserDetails(UserDetail userDetail);
	
	public List<Clients> getClientsOnLocation(Integer locId);
	
	//****** START BY SNEHA
	
	public List<Location> getLocationList();
	
	public void addLocationDetails(Location location);
	
	public List<LocationArea> getLocationAreaList();
	
	public void addLocationAreaDetails(LocationArea locationArea);
	
	public void reassignFeedback(UserFeedback userFeedback);
	
	//****** END BY SNEHA
	
	public List<Clients> showAllClientsAddPoints();
	
	//public Location getCityAlreadyExists(String city);
	
	public List<Clients> viewStores(Integer userEmpId);
}






