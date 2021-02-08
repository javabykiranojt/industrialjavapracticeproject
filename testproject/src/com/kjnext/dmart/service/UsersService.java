package com.kjnext.dmart.service;

import java.util.ArrayList;
import java.util.List;

import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.vo.SearchCriteriaUsers;
import com.kjnext.utility.OutCome;

public interface UsersService {

	public UserDetail getUserDetail(Integer uid);
	
	public String  sendForgetPasswordAction(String Emailid);
	
	public OutCome addNewUser(UserDetail user, List<UserDocuments> documentList, OutCome outCome);

	public void updateUser(UserDetail user);
	
	public void removeUser(UserDetail user);
	
	public Boolean activateUser(UserDetail user);
	
	// Managing "Users"
	public void updateUsers(Users users);
	
	public Users getUsers(Integer uid);
	
	
	public String createUnamePassWord(int length);
	
	public void addUserDocuments(Integer userEmpId,List<UserDocuments> documentList );
	
	public List<UserDetail> getAllUser();
	
	public List<UserDetail> showAllUser();
	
	public List<Location> getAllLocation();
	
	public List<UserDetail> getAllApprovedUser();
	
	public List<UserDetail> getAllRejectedUser();
	
	public List<UserDetail> getAllPendingUser();
	
	public Boolean sendMail(Users user, UserDetail userDetail);
	
	public Boolean userVerification(String verificationId, Integer userEmpId, OutCome outCome);

	public List<UserDetail> getUsersOnLocation(Integer locId);
	
	public List<UserDetail> getUsersOnLocationExcludeRejected(Tasks tasks);
	
	public UserDocuments loadToDB(byte[] bytes);
	
	public void sendVerificationID(UserDetail userDetail,OutCome outCome);
	
	public List<UserDetail> getAllActiveUser();
	
	public List<LocationArea> getAreasOnLocation(Integer locId);
	
	public List<LocationArea> getAllAreasOnLocation();
	
	public List<UserDetail> allUsers();
	
	public Location getLocationById(Integer locId);
	
	public List<LocationArea> getLocationAreaByLocId(Integer locId);

	public void updateLocation(Location location);
	
	public LocationArea getLocationAreaById(Integer areaId);
	
	public void updateLocationArea(LocationArea locationArea);

	public void updateVarificationStatus(Integer userEmpId);
	
	public List<String> sendEmailToAllAdmin(UserDetail userDetail);
	public List<String> sendEmailToAllAdminAbtTaskAccepted(Tasks tasks);
	public List<String> sendEmailToAllAdminForFeedbackSubmission(Tasks tasks);
	 List<UserDetail> getEmployeeNameLabel(Integer empId);
	 
	 public String taskStatus(Integer userEmpId); 
	 
	 //public List<String> applicationDataForEmail();
	 
	 public List<String> sentApplicationDataToEmail(UserDetail userDetail);

	public boolean activateSuspendedUser(UserDetail userDetailDb);
		
	
	
}
