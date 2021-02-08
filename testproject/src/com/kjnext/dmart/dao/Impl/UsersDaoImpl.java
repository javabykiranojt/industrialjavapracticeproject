//@Author("kiran");
package com.kjnext.dmart.dao.Impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.UsersDao;
import com.kjnext.dmart.hibernate.ClientDetails;
import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.hibernate.HibernateUtil;

public class UsersDaoImpl implements UsersDao {

	// Session session = null;
	Integer userEmpId;

	Transaction transaction = null;

	public UserDetail getUserDetail(Integer uid) {
		UserDetail userDetail = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			userDetail = (UserDetail) hibernateSession.load(UserDetail.class,
					uid);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return userDetail;
	}

	public Integer addNewUser(UserDetail userDetail) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));

		try {

			// hibernateSession.getTransaction().begin();
			userEmpId = userDetail.getUserEmpId();

			userEmpId = (Integer) hibernateSession.save(userDetail);

		} catch (Exception e) {
			// hibernateSession.getTransaction().rollback();
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return userEmpId;
	}

	public Boolean updateUser(UserDetail user) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		boolean updated = false;
		try {
			System.out.println("*****Data added successfully IN USERDAO IMPL");
			hibernateSession.update(user);
			updated = true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			// hibernateSession.getTransaction().commit();
			hibernateSession = null;
		}
		return updated;
	}

	// This just adds "Users" obj in "users" table
	public Users activateUser(Users user) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/*
	 * Managing Users table
	 */
	public Boolean updateUsers(Users users) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Users getUsers(Integer uid) {
		Users users = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			users = (Users) hibernateSession.load(Users.class, uid);
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/*
	 * update active user Uid in UserDetails
	 */
	public Boolean updateUserDetails(Users activeUser, UserDetail userDetails) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			UserDetail userDetail = (UserDetail) hibernateSession.load(
					UserDetail.class, userDetails.getUserEmpId());
			userDetail.setUsers(activeUser);
			hibernateSession.update(userDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Boolean removeUser(UserDetail user) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {

			hibernateSession.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	// add userDoc
	public void addUserDocuments(Integer userEmpId,
			List<UserDocuments> userDocuments) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			UserDetail userDetail = new UserDetail();
			userDetail.setUserEmpId(userEmpId);
			for (UserDocuments documents : userDocuments) {
				documents.setUserDetail(userDetail);
				documents.setCreatedDtm(new Date());
				hibernateSession.save(documents);
			}
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getAllUser() {
		System.out.println("-----------i m in getAll user-------------");
		// Session hibernateSession =
		// (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Session hibernateSession = HibernateUtil.getSession();
		try {
			List<String> listUType = new ArrayList<String>();
			listUType.add("UA");
			listUType.add("UU");

			List<UserDetail> userDetailsList = hibernateSession.createCriteria(
					UserDetail.class).createCriteria("users").add(
					Expression.in("utype", listUType)).list();

			List<UserDetail> pendingUsersList = hibernateSession
					.createCriteria(UserDetail.class).add(
							Expression.eq("status", Status.PENDING_USER
									.toString())).list();

			List<UserDetail> approvedUsersList = hibernateSession
					.createCriteria(UserDetail.class).add(
							Expression.eq("status", Status.APPROVED_USER
									.toString())).list();

			pendingUsersList.addAll(approvedUsersList);
			pendingUsersList.addAll(userDetailsList);

			return pendingUsersList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	// Started By Shivtej to display Userdetail Table
	public List<UserDetail> showAllUser() {
		List<UserDetail> userDetailsList = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			Query query = hibernateSession
					.createSQLQuery("select distinct userEmpId from clients");
			List<Integer> clientUserEmpIdList = query.list();
			if (!clientUserEmpIdList.isEmpty()) {
				Criteria criteria = hibernateSession.createCriteria(
						UserDetail.class).add(
						Expression.in("userEmpId", clientUserEmpIdList));
				userDetailsList = criteria.list();
			} else {
				userDetailsList = new ArrayList<UserDetail>();
			}
			return userDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Integer getMaxUserEmpId() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List lmax = hibernateSession.createCriteria(UserDetail.class)
				.setProjection(Projections.max("userEmpId")).list();
		Integer maxEmpId = ((Integer) lmax.get(0)).intValue();
		return maxEmpId;
	}

	// Shivtej
	public Integer getMaxClientEmpId() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List lmax = hibernateSession.createCriteria(ClientDetails.class)
				.setProjection(Projections.max("cltDetailId")).list();
		Integer maxEmpId = ((Integer) lmax.get(0)).intValue();
		return maxEmpId;
	}

	public static void main(String[] args) {
		UsersDaoImpl daoImpl = new UsersDaoImpl();
		System.out.println(daoImpl.getAllUser().size());
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getAllApprovedUser() {
		List<UserDetail> userlist = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));

		try {
			userlist = hibernateSession.createCriteria(UserDetail.class).add(
					Expression.eq("status", Status.APPROVED_USER.toString()))
					.list();
			return userlist;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getAllPendingUser() {
		List<UserDetail> userlist = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			userlist = hibernateSession.createCriteria(UserDetail.class).add(
					Expression.eq("status", Status.PENDING_USER.toString()))
					.list();
			return userlist;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getAllRejectedUser() {
		List<UserDetail> userlist = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {

			userlist = hibernateSession.createCriteria(UserDetail.class).add(
					Expression.eq("status", Status.REJECTED_USER.toString()))
					.list();
			return userlist;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	// Approved user after matching verification id
	@SuppressWarnings("unchecked")
	public UserDetail userVerification(String verificationId, Integer userEmpId) {
		UserDetail userDetail = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			userDetail = (UserDetail) hibernateSession.load(UserDetail.class,
					userEmpId);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return userDetail;
	}

	@SuppressWarnings("unchecked")
	public List<Location> getAllLocation() {

		// Session hibernateSession =
		// (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Session hibernateSession = HibernateUtil.getSession();
		try {

			List<Location> locationList = (List<Location>) hibernateSession
					.createCriteria(Location.class).list();
			Collections.sort(locationList,new LocationComparator());
			return locationList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<UserDetail> getUsersOnLocation(Integer locId) {
		Location location = new Location();
		location.setLocId(locId);
		// Session hibernateSession =
		// (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Session hibernateSession = HibernateUtil.getSession();
		try {
			List<UserDetail> userDetailList = (List<UserDetail>) hibernateSession
					.createCriteria(UserDetail.class).add(
							Expression.eq("location", location)).list();
			List<UserDetail> list = new ArrayList<UserDetail>();
			for (UserDetail detail : userDetailList) {
				if (detail.getUsers() != null) {
					Users users = detail.getUsers();
					if ("UA".equals(users.getUtype())
							|| "UU".equals(users.getUtype())) {
						list.add(detail);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/*
	 * load file in to db
	 */
	public UserDocuments addDocumentsToDB(byte[] bytes) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		if (bytes != null) {
			InputStream inputStream = new ByteArrayInputStream(bytes);
			DocumetRepository documetRepository = new DocumetRepository();
			Blob blob = null;
			try {
				blob = Hibernate.createBlob(inputStream);
				documetRepository.setDocument(blob);
				hibernateSession.save(documetRepository);
				documetRepository.getDocId();
				UserDocuments userDocuments = new UserDocuments();
				userDocuments.setDocumetRepository(documetRepository);
				return userDocuments;
			} catch (Exception e) {
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
			} finally {
				hibernateSession = null;
			}
		}

		return null;
	}

	/*
	 * 
	 * get user document list from db
	 */
	@SuppressWarnings("unchecked")
	public List getUserDocumentList(Integer uid) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<UserDocuments> UserDocIdList = hibernateSession
					.createCriteria(UserDocuments.class).setProjection(
							Projections.property("uidDocId")).add(
							Expression.eq("userDetail.userEmpId", uid)).list();

			List<DocumetRepository> fileList = hibernateSession.createCriteria(
					DocumetRepository.class).add(
					Expression.in("docId", UserDocIdList)).list();

			return fileList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public boolean verifyEmailExist(String email) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));

		List<UserDetail> checkEmailExistList = hibernateSession.createCriteria(
				UserDetail.class).add(Expression.eq("emailId", email)).list();
		if (checkEmailExistList.isEmpty())
			return false;
		else
			return true;
	}

	/*
	 * This method will return all users having type "UA" or "UU" which are
	 * ACTIVE
	 */
	public List<UserDetail> getAllActiveUser() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<String> listUType = new ArrayList<String>();
			listUType.add("UA");
			listUType.add("UU");

			List<UserDetail> userDetailsList = hibernateSession.createCriteria(
					UserDetail.class).createCriteria("users").add(
					Expression.in("utype", listUType)).list();
			return userDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<LocationArea> getAreasOnLocation(Integer locId) {
		// TODO Auto-generated method stub
		// Session hibernateSession =
		// (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Session hibernateSession = HibernateUtil.getSession();
		try {

			List<LocationArea> locationAreaList = hibernateSession
					.createCriteria(LocationArea.class).createCriteria(
							"location").add(Expression.eq("locId", locId))
					.list();
			
			Collections.sort(locationAreaList, new AreaComparator());
				
				 
				
			
			return locationAreaList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<LocationArea> getAllAreasOnLocation() {
		// Session hibernateSession =
		// (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Session hibernateSession = HibernateUtil.getSession();
		try {
			List<LocationArea> allLocationAreaList = hibernateSession
					.createCriteria(LocationArea.class).createCriteria(
							"location").list();

			return allLocationAreaList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());

		} finally {
			hibernateSession = null;
		}

	}

	// ************************ START BY SNEHA

	public List<UserDetail> allUsers() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<UserDetail> historyUidList = hibernateSession.createCriteria(
					UserDetail.class).list();
			return historyUidList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Location getLocationById(Integer locId) {
		Location location = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			location = (Location) hibernateSession.load(Location.class, locId);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return location;
	}

	public List<LocationArea> getLocationAreaByLocId(Integer locId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<LocationArea> locationAreaList = hibernateSession
					.createCriteria(LocationArea.class).createCriteria(
							"location").add(Expression.eq("locId", locId))
					.list();
			return locationAreaList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public LocationArea getLocationAreaById(Integer areaId) {
		LocationArea locationArea = null;
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			locationArea = (LocationArea) hibernateSession.load(
					LocationArea.class, areaId);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return locationArea;
	}

	public Boolean updateLocation(Location location) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(location);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Boolean updateLocationArea(LocationArea locationArea) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(locationArea);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public void updateVarificationStatus(Integer userEmpId) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			UserDetail userDetail = (UserDetail) hibernateSession.get(
					UserDetail.class, userEmpId);
			userDetail.setVerificationId("DONE");
			hibernateSession.update(userDetail);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	// ************************* END BY SNEHA
	// *****start by Amit***
	public List<String> getAllAdmin() {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {

			List<UserDetail> getAllAdminList = (List<UserDetail>) hibernateSession
					.createCriteria(UserDetail.class).list();
			List<String> list = new ArrayList<String>();

			for (UserDetail detail : getAllAdminList) {
				System.out.println(detail.getEmailId());

				if (detail.getUsers() != null) {
					Users users = detail.getUsers();

					System.out.println(users.getUtype());
					if ("AE".equals(users.getUtype())) {
						list.add(detail.getEmailId());
					}
				}
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public List<String> getAllAdminEmail() {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {

			List<UserDetail> getAllAdminList = (List<UserDetail>) hibernateSession
					.createCriteria(UserDetail.class).list();
			List<String> list1 = new ArrayList<String>();

			for (UserDetail detail : getAllAdminList) {
				System.out.println(detail.getEmailId());

				if (detail.getUsers() != null) {
					Users users = detail.getUsers();

					System.out.println(users.getUtype());
					if ("AE".equals(users.getUtype())) {
						list1.add(detail.getEmailId());
					}
				}
			}
			return list1;

		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public List<String> getAllAdminEmailForTask() {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<UserDetail> getAllAdminList = (List<UserDetail>) hibernateSession
					.createCriteria(UserDetail.class).list();
			List<String> list2 = new ArrayList<String>();

			for (UserDetail detail : getAllAdminList) {
				System.out.println(detail.getEmailId());

				if (detail.getUsers() != null) {
					Users users = detail.getUsers();

					System.out.println(users.getUtype());
					if ("AE".equals(users.getUtype())) {
						list2.add(detail.getEmailId());
					}
				}
			}
			return list2;

		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}

	}

	public List<UserDetail> getEmployeeNameLabel(Integer empId) {
		List<UserDetail> listUserDetail = null;

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			Query query = hibernateSession
					.createSQLQuery("select firstName,lastName from user_detail where userEmpId='"
							+ empId + "' ");
			listUserDetail = query.list();
			return listUserDetail;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}
	public String taskStatus(Integer userEmpId){
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
			System.out.println(userEmpId);
			String taskStatus="";
			try{
	List<Tasks> tasklist = (List<Tasks>) hibernateSession.createCriteria(Tasks.class).createCriteria("userDetail").add(Expression.eq("userEmpId", userEmpId)).list();
				// List<String> listStatus=new ArrayList<String>();
				for(Tasks tsk:tasklist){
				System.out.println(tsk.getStatus());
					taskStatus=tsk.getStatus();
					
				}
				return taskStatus;
			}catch(Exception e){
				e.printStackTrace();
				hibernateSession.getTransaction().rollback();
				throw new RuntimeException(e.getMessage());
				}finally{
					hibernateSession=null;
				}
	}

	public List<String> applicationDataForEmail() {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<UserDetail> getAllAdminList = (List<UserDetail>) hibernateSession
					.createCriteria(UserDetail.class).list();
			List<String> list3 = new ArrayList<String>();

			for (UserDetail detail : getAllAdminList) {
				System.out.println(detail.getEmailId());

				if (detail.getUsers() != null) {
					Users users = detail.getUsers();

					System.out.println(users.getUtype());
					if ("AE".equals(users.getUtype())) {
						list3.add(detail.getEmailId());
					}
				}
			}
			return list3;

		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		
	}	

	}
	
	public List<UserDetail> sendForgetPasswordDao(String Emailid) {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			
			List<UserDetail> sendforgetpasswordaction= hibernateSession.createCriteria(
					UserDetail.class).add(Expression.eq("emailId", Emailid)).list();
			for(UserDetail li:sendforgetpasswordaction)
			{
				System.out.println(li.getEmailId());
			}
			
			if (sendforgetpasswordaction.isEmpty())
			{
				return null;
			}
			else
			{
				return sendforgetpasswordaction;
			}
		}catch(Exception e){
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
			}finally{
				hibernateSession=null;
			}
	}

	public void activateSuspendedUser(UserDetail userDetailDb) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			System.out.println("*****Data added successfully IN USERDAO IMPL");
			hibernateSession.update(userDetailDb);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			// hibernateSession.getTransaction().commit();
			hibernateSession = null;
		}
	}

	public boolean checkIfEmailIdSuspended(String email) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			
			Criteria criteria=	hibernateSession.createCriteria(UserDetail.class).add(Expression.eq("status", "INACTIVE"));
			List<UserDetail> list=criteria.list();
			
			if(list.size()>0){
			return true;	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			// hibernateSession.getTransaction().commit();
			hibernateSession = null;
		}
		return false;	
	}

}
