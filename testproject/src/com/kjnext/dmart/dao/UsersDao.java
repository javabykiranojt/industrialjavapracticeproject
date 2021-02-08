package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.vo.SearchCriteriaUsers;

public interface UsersDao {

	public UserDetail getUserDetail(Integer uid);
	
	public Integer addNewUser(UserDetail userDetail);

	public Boolean updateUser(UserDetail user);
	
	public Boolean removeUser(UserDetail user);
	
	public Users activateUser(Users user);
	
	public boolean verifyEmailExist(String email);
	//
	public Boolean updateUsers(Users users);
	
	public Users getUsers(Integer uid);
	
	public Boolean updateUserDetails(Users activeUser,UserDetail userDetails);
	
	public void addUserDocuments(Integer userEmpId, List<UserDocuments> userDocuments);

	public List<UserDetail> getAllUser();
	
	public List<UserDetail> showAllUser();
	
    public List<UserDetail> getAllApprovedUser();
	
	public List<UserDetail> getAllRejectedUser();
	
	public List<UserDetail> getAllPendingUser();
	
	public UserDetail userVerification(String verificationId, Integer userEmpId);
	
	List<Location> getAllLocation();

	List<UserDetail> getUsersOnLocation(Integer locId);
	
	public UserDocuments addDocumentsToDB(byte[] bytes);
  
	public List getUserDocumentList(Integer uid);
	
	public List<UserDetail> getAllActiveUser();
	
	public List<LocationArea> getAreasOnLocation(Integer locId);
	
	public List<LocationArea> getAllAreasOnLocation();
	
	public List<UserDetail> allUsers();

	public Location getLocationById(Integer locId);
	
	public List<LocationArea> getLocationAreaByLocId(Integer locId);
	
	public Boolean updateLocation(Location location);
	
	public LocationArea getLocationAreaById(Integer areaId);
	
	public Boolean updateLocationArea(LocationArea locationArea);

	public void updateVarificationStatus(Integer userEmpId);
	
	public List<String> getAllAdmin();
public List<String> getAllAdminEmail();
	public List<String> getAllAdminEmailForTask();
	public List<UserDetail> getEmployeeNameLabel(Integer empId);
	
	public String taskStatus(Integer userEmpId);
	
	 public List<String> applicationDataForEmail();
	 	
	 public List<UserDetail> sendForgetPasswordDao(String Emailid);

	public void activateSuspendedUser(UserDetail userDetailDb);

	public boolean checkIfEmailIdSuspended(String email);
}
