package com.kjnext.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;

public class AdminAction extends BaseAction {

	UsersService usersService = new UsersServiceImpl();

	Users users;

	private String navigationFlag;

	private String oldPassword;

	private String newPassword;

	private String confirmNewPassword;

	private UserDetail userDetail;

	private Integer uEId;

	private String alreadyInactive;

	private String actionMsg;

	OutCome outCome = new OutCome();

	private String oldPass;

	private String pageTitles;

	private String paseTitle;

	private String taskStatus;

	// ****** START BY SNEHA

	ClientService clientService = new ClientServiceImpl();

	private Location location;

	private List<Location> locationList;

	private LocationArea locationArea;

	private List<LocationArea> locationAreaList;

	String sendemailstatus = null;

	public String getSendemailstatus() {
		return sendemailstatus;
	}

	public void setSendemailstatus(String sendemailstatus) {
		this.sendemailstatus = sendemailstatus;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
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

	// ****** END BY SNEHA

	public Integer getUEId() {
		return uEId;
	}

	public void setUEId(Integer id) {
		uEId = id;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	/*
	 * by lucky merged by amarjit
	 * 
	 */
	public String sendForgetPasswordAction() {
		System.out.println(getUserDetail().getEmailId());
		String status = usersService.sendForgetPasswordAction(getUserDetail()
				.getEmailId());

		if (status.equals("success")) {
			addActionMessage("user name and password send successfully in your Email");
			sendemailstatus = "success";
			return sendemailstatus;
		} else if (status.equals("nosend")) {
			addActionError("Email could not be send");
			sendemailstatus = "nosend";
			return sendemailstatus;
		} else {
			addActionMessage("Email id is invalid");
			sendemailstatus = "invalid";
			return sendemailstatus;
		}
		

	}

	public String loadProfile() {
		if (getActionMsg() != null) {
			if (getActionMsg().equals("passChanged")) {
				outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
				outCome.addMessage(new OutCome.Message(
						"passChanged.AdminAction.success"));

			}
			addActionMessage(outCome);
		}
		Integer key = (Integer) session.get("UserEmpId");
		userDetail = usersService.getUserDetail(key);
		users = userDetail.getUsers();
		locationList = usersService.getAllLocation();
		locationAreaList = usersService.getAreasOnLocation(userDetail
				.getLocation().getLocId());
		String uType = (String) session.get("usersType");
		if("CA".equals(uType)){
			return "successClient";
		}
		return "success";
	}

	public String editAdminProfileInput() {
		Integer key = (Integer) session.get("UserEmpId");
		UserDetail tempUserDetail = usersService.getUserDetail(key);

		// setting the details came from profile-editable jsp
		tempUserDetail.setFirstName(userDetail.getFirstName());
		tempUserDetail.setLastName(userDetail.getLastName());
		tempUserDetail.setLocation(location);
		tempUserDetail.setlocationArea(locationArea);
		tempUserDetail.setContactNo(userDetail.getContactNo());

		// setting modified date
		tempUserDetail.setModifiedDtm(new Date());

		// setting modifiedID that means the user who made changes by letest..
		users = tempUserDetail.getUsers();
		tempUserDetail.setModifiedUId(users.getUid());

		// finally updating user_detail..
		usersService.updateUser(tempUserDetail);

		// dummy data
		locationList = new ArrayList<Location>();
		locationAreaList = new ArrayList<LocationArea>();

		setNavigationFlag("success");
		return "success";
	}

	public String changeAdminPassword() {
		return "success";
	}

	public String changeAdminPasswordInput() {
		oldPassword = getOldPass();
		loadProfile();
		String tempOldPassword = users.getUpass();
		if (tempOldPassword.equals(oldPassword)) {
			if (newPassword.equals(confirmNewPassword)
					&& !(newPassword.equals(""))) {
				users.setUpass(newPassword);
				users.setModifiedDtm(new Date());
				usersService.updateUsers(users);
				setActionMsg("passChanged");
				setNavigationFlag("success");
			} else {
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message(
						"Incorrect New Password combination"));
				addActionMessage(outCome);
				// addActionError("Incorrect New Password combination");
			}
		} else {
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Incorrect Old Password"));
			addActionMessage(outCome);
			// addActionError("Incorrect Old Password");
		}
		return "success";
	}

	public String suspendUserCheck() {
		alreadyInactive = "no";
		UserDetail tempUserDetail = usersService.getUserDetail(uEId);
		System.out.println(uEId);
		if (Status.INACTIVE.toString().equals(tempUserDetail.getStatus())) {
			alreadyInactive = "yes";
		}

		taskStatus = usersService.taskStatus(uEId);

		System.out.println(taskStatus.toString());

		if (taskStatus.toString().equals("INITIATED_USER")
				|| taskStatus.toString().equals("INITIATED_ADMIN_MULTIUSER")) {

			setActionMsg("userPending");
			setNavigationFlag("success");
			/*
			 * outCome.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
			 * outCome.addMessage(new OutCome.Message("This User Have Some
			 * Pending Task.So You Can't Do Inactive"));
			 * addActionMessage(outCome);
			 */
		}

		return "success";
	}

	public String suspendUser() {
		loadProfile();
		UserDetail tempUserDetail = usersService.getUserDetail(uEId);
		tempUserDetail.setStatus(Status.INACTIVE.toString());
		tempUserDetail.setModifiedDtm(new Date());
		tempUserDetail.setModifiedUId(users.getUid());
		usersService.updateUser(tempUserDetail);
		setNavigationFlag("success");
		return "success";
	}

	public String changeUserPassByAdminInput() {
		pageTitles = getText("change.userPass");

		UserDetail tempUserDetail = usersService.getUserDetail(uEId);
		System.out.println(uEId);
		if (!Status.ACTIVE.toString().equals(tempUserDetail.getStatus())) {
			setActionMsg("userNotActive");
			// actionMsg="You are not allowed to change the password.";
			navigationFlag = "success";
		}
		// System.out.println("I am in change user password
		// action................");

		return "success";

	}

	/*
	 * public String errorMsgToAdmin(){
	 * 
	 * return "success"; }
	 */
	public String changeUserPassByAdmin() {
		loadProfile();
		if (newPassword.equals(confirmNewPassword) && !(newPassword.equals(""))) {
			UserDetail tempUserDetail = usersService.getUserDetail(uEId);
			Users tempUsers = tempUserDetail.getUsers();
			tempUsers.setUpass(newPassword);
			tempUsers.setModifiedDtm(new Date());
			tempUserDetail.setModifiedUId(users.getUid());
			tempUserDetail.setModifiedDtm(new Date());
			usersService.updateUsers(tempUsers);
			usersService.updateUser(tempUserDetail);
			setActionMsg("passChanged");
			setNavigationFlag("success");
		} else

		{
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message(
					"Incorrect New Password combination"));
			addActionMessage(outCome);
			// addActionError("Technologyorrect New Password combination");
		}
		return "success";
	}

	public String manageUserCredentialsByAdmin() {
		pageTitles = getText("manage.credential");
		userDetail = usersService.getUserDetail(uEId);
		users = userDetail.getUsers();
		return "success";
	}

	// kiran

	public String loadCities() {

		// ****** START BY SNEHA
		pageTitles = getText("all.cities");
		locationList = clientService.getLocationList();

		for (Location location : locationList) {
			Integer uid = location.getModifiedUId();
			if (uid != null) {
				Users tempUsers = usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					location.setModifiedUIdName(uD.getFirstName() + " "
							+ uD.getLastName());
				}
			} else {
				location.setModifiedUIdName("-");
			}

			uid = location.getCreatedUId();
			if (uid != null) {
				Users tempUsers = usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					location.setCreatedUIdName(uD.getFirstName());
				}
			} else {
				location.setCreatedUIdName("-");
			}

		}

		// ****** END BY SNEHA

		return SUCCESS;
	}

	// @ Amit

	public String applicationForUser() {
		pageTitles = getText("application.form");
		return "success";
	}

	public String applicationDataForEmail() {
		usersService.sentApplicationDataToEmail(userDetail);
		outCome.setStatus(OutCome.Status.SUCCESS_WITH_INFO);
		outCome.addMessage(new OutCome.Message("inquiry.mail.send"));
		addActionMessage(outCome);
		pageTitles = getText("application.form");
		return "success";
	}

	public String loadCityAreas() {
		paseTitle = getText("city.areas");

		// ****** START BY SNEHA

		locationAreaList = clientService.getLocationAreaList();

		// ****** END BY SNEHA
		return SUCCESS;
	}

	public String getAlreadyInactive() {
		return alreadyInactive;
	}

	public void setAlreadyInactive(String alreadyInactive) {
		this.alreadyInactive = alreadyInactive;
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

	public String getPageTitles() {
		return pageTitles;
	}

	public void setPageTitles(String pageTitles) {
		this.pageTitles = pageTitles;
	}

	public String getPaseTitle() {
		return paseTitle;
	}

	public void setPaseTitle(String paseTitle) {
		this.paseTitle = paseTitle;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

}
