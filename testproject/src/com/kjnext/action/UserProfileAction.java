package com.kjnext.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.RewardServices;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.RewardServicesImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.utility.OutCome;

public class UserProfileAction extends BaseAction{

	UsersService usersService = new UsersServiceImpl();
	UserDetail userDetail;
	Users users;
	private String navigationFlag;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	RewardServices rewardServices = new RewardServicesImpl();
	UserRewards userRewards;
	Integer points;
	String requestPoint;
	private Location location;
	private List<Location> locationList;
	private LocationArea locationArea;
	private List<LocationArea> locationAreaList;
	OutCome outCome=new OutCome();	
	 private String actionMsg;
	 private String oldPass;
	
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNavigationFlag() {
		return navigationFlag;
	}


	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}


	public UserDetail getUserDetail() {
		return userDetail;
	}


	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Users getUsers() {
		return users;
	}

	public String loadProfile()
	{
		if (getActionMsg()!=null) {
			if (getActionMsg().equals("passChangedByUser")) {
				outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message("passChanged.UserProfileAction.success"));
				
			}
			addActionMessage(outCome);
		}
		Integer key = (Integer) session.get("UserEmpId");
		userDetail = usersService.getUserDetail(key);
		users = userDetail.getUsers();
		locationList = usersService.getAllLocation();
		locationAreaList = usersService.getAreasOnLocation(userDetail.getLocation().getLocId());
		return "success";
	}
	
	

	public String editUserProfileInput()
	{
		Integer key = (Integer) session.get("UserEmpId");
		UserDetail tempUserDetail = usersService.getUserDetail(key);
		
		//setting the details came from profile-editable jsp
		tempUserDetail.setFirstName(userDetail.getFirstName());
		tempUserDetail.setLastName(userDetail.getLastName());
		tempUserDetail.setLocation(location);
		tempUserDetail.setlocationArea(locationArea);
		tempUserDetail.setContactNo(userDetail.getContactNo());
		
		//setting modified date
		tempUserDetail.setModifiedDtm(new Date());
		
		//setting modifiedID that means the user who made changes by letest..
		users = tempUserDetail.getUsers();
		tempUserDetail.setModifiedUId(users.getUid());
		
		//finally updating user_detail..
		usersService.updateUser(tempUserDetail);
		
		//dummy data
		locationList = new ArrayList<Location>();
		locationAreaList = new ArrayList<LocationArea>();
		
		setNavigationFlag("success");
		return "success";
	}
	
	public String changeUserPassword()
	{
		System.out.println("I am in change user password..");
		return "success";
	}
	
	public String changeUserPasswordInput()
	{
		oldPassword=getOldPass();
		loadProfile();
		String tempOldPassword = users.getUpass();
		if(tempOldPassword.equals(oldPassword))
		{
			if(newPassword.equals(confirmNewPassword) && !(newPassword.equals("")))
			{
				users.setUpass(newPassword);
				users.setModifiedDtm(new Date());
				usersService.updateUsers(users);
				setActionMsg("passChangedByUser");
				setNavigationFlag("success");
			}
			else
			{		outCome.setStatus(OutCome.Status.FAILURE);
					outCome.addMessage(new OutCome.Message("Incorrect New Password combination"));
					addActionMessage(outCome);
					//addActionError("Incorrect New Password combination");
			}
		}
		else
		{	
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Incorrect Old Password"));
			addActionMessage(outCome);
			//addActionError("Technologyorrect Old Password");
		}
		return "success";
	}
	/*
	 * 
	 * @author Amarjit Sharma 
	 * for user rewards point management
	 */
	public String loadUserRewards(){
		OutCome  outCome=new OutCome();
			System.out.println((Integer) session.get("UserEmpId"));
			System.out.println(rewardServices.verifyIfUserExist((Integer) session.get("UserEmpId")));
		if (rewardServices.verifyIfUserExist((Integer) session.get("UserEmpId"))) {
			Integer uEiD = (Integer) session.get("UserEmpId");
			System.out.println(uEiD);
			Integer rewardId = rewardServices.getUserRewardsId(uEiD);
			System.out.println(rewardId);
			userRewards = rewardServices.loadUserRewards(rewardId);
			this.points =Integer.parseInt(userRewards.getPoint()) ;			
		}else{
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("No Reward Points Exists"));
			addActionMessage(outCome);
		}
		return "success";
	}
	
	public String redeemUserPointsInput(){
		loadProfile();
		loadUserRewards();
		return "success";
	}
	
	public String redeemUserPoints() {
		Integer uId = (Integer) session.get("UserEmpId");
		userDetail = usersService.getUserDetail(uId);
		Integer rID = rewardServices.getUserRewardsId(uId);	
		userRewards = rewardServices.loadUserRewards(rID);
		userRewards.setRequestRedeemPoint(getRequestPoint());
		rewardServices.updateUserRewards(userRewards, rID);
		setNavigationFlag("success");
		return "success";
	}


	public UserRewards getUserRewards() {
		return userRewards;
	}


	public void setUserRewards(UserRewards userRewards) {
		this.userRewards = userRewards;
	}


	public Integer getPoints() {
		return points;
	}


	public void setPoints(Integer points) {
		this.points = points;
	}


	public String getRequestPoint() {
		return requestPoint;
	}


	public void setRequestPoint(String requestPoint) {
		this.requestPoint = requestPoint;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
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


	public String getActionMsg() {
		return actionMsg;
	}


	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}


	public String getOldPass() {
		return oldPass;
	}


	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}


	

	
	
}
