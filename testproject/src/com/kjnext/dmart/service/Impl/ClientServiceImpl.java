package com.kjnext.dmart.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.utility.email.SendEmail;


import com.kjnext.dmart.dao.ClientDao;
import com.kjnext.dmart.dao.UsersDao;
import com.kjnext.dmart.dao.Impl.ClientDaoImpl;
import com.kjnext.dmart.dao.Impl.UsersDaoImpl;
import com.kjnext.dmart.hibernate.ClientDetails;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.vo.Status;
import com.kjnext.dmart.vo.Type;
import com.kjnext.utility.email.SendEmail;
import com.kjnext.dmart.hibernate.Location;


public class ClientServiceImpl implements ClientService {

	ClientDao clientDao=new ClientDaoImpl();
	Clients clients = null;
	UsersService userService=new UsersServiceImpl();
	UsersDao usersDao=new UsersDaoImpl();
	
	public void addClient(Clients clients) {
		clientDao.addClient(clients);
	}

	public void removeClient(Clients clients) {
		clients = clientDao.loadClientDetails(clients.getClientId());
		clientDao.removeClient(clients);
	}

	public void updateClient(Clients clients) {
		clientDao.updateClient(clients);
		
	}

	public Clients loadClientDetails(Integer clientId) {		
		return clientDao.loadClientDetails(clientId);
	}

	public List<Clients> showAllClients() {
		List<Clients> clList = clientDao.showAllClients();
		List<Clients> clList1 = new ArrayList<Clients>();
		for (Clients clients : clList) {
			clients.setClientFullName(clients.getUserDetail().getFirstName()+" "+
					clients.getUserDetail().getLastName()+" - "+
					clients.getClientName()+","+clients.getClientDetails().getlocationArea().getAreaName());
			clList1.add(clients);
		}
		return clList1;
	
	}
/******STARTED BY SHIVTEJ********/
	public void addClientDetails(ClientDetails clientDetails) {
		clientDao.addClientDetails(clientDetails);
	}
	
	public void addUserDetails(UserDetail userDetail) {
		clientDao.addUserDetails(userDetail);
		
	}
/*****END BY SHIVTEJ************/
	
	
	
	public void generateCredential(UserDetail userDetail) {
		
		userDetail=userService.getUserDetail(userDetail.getUserEmpId());
		String username=userService.createUnamePassWord(6);
		String password=userService.createUnamePassWord(4);
		Users users=new Users();
		users.setUname(username);
		users.setUpass(password);
		users.setUtype(Type.CA.toString());
		users.setCreatedDtm(new Date());
		userDetail.setCreatedDtm(userDetail.getCreatedDtm());
		userDetail.setModifiedDtm(new Date());
		userDetail.setUsers(users);
		usersDao.addNewUser(userDetail);
		try{
 		SendEmail email=new SendEmail();
		  try{
			  if(userDetail.getEmailId()!=null)
			  {
				email.setTo(userDetail.getEmailId());	  
				email.setFrom(email.email_from,"Mystery Shoppers" );
				//email.setReplyTo("amits27@gmail.com", "Reply To Me");
				email.setPriority("1");
				email.setSubject("Register successfully for login");
				//send un, pass, vid, link sm part remaining
				email.setBody(" Username for login="+users.getUname()+"\n"+" Password for login="+users.getUpass());
				email.setSMTPHost(email.email_smtp, email.email_from,email.email_password);
				System.out.println(users.getUname()+"....User Name....");
				System.out.println(users.getUpass()+"....password....");
				email.sendMsg();
				System.out.println("mail sent...");
				  
			   }
		  	 }catch (Exception e) 
		  	{
			  e.printStackTrace();
		    }
		}catch(Exception exceptions){
			exceptions.printStackTrace();
		}



}

	public List<Clients> getClientsOnLocation(Integer locId) {
		return clientDao.getClientsOnLocation(locId);
	}

//	****** START BY SNEHA
	
	public List<Location> getLocationList(){
		return clientDao.getLocationList();
	}
	
	
	public void addLocationDetails(Location location) {
		clientDao.addLocationDetails(location);
	}
	
	public List<LocationArea> getLocationAreaList() {
		
		return clientDao.getLocationAreaList();
	}

	public void addLocationAreaDetails(LocationArea locationArea){
		
		clientDao.addLocationAreaDetails(locationArea);
		
	}
	
	public void reassignFeedback(UserFeedback userFeedback){
		clientDao.reassignFeedback(userFeedback);
	}
//	****** END BY SNEHA

	/*public Location getCityAlreadyExists(String city){
		return clientDao.getCityAlreadyExists(city);
	}*/
	
	public List<Clients> showAllClientsAddPoints() {
	    return clientDao.getExistedClientsAddPoints();
	}
	public List<Clients> viewStores(Integer userEmpId){
		
		return clientDao.viewStores(userEmpId);
	}

}

