package com.kjnext.dmart.service.Impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.UsersDao;
import com.kjnext.dmart.dao.Impl.UsersDaoImpl;
import com.kjnext.dmart.hibernate.DocumetRepository;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.Location;
import com.kjnext.dmart.hibernate.LocationArea;
import com.kjnext.dmart.hibernate.Tasks;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserDocuments;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.OutCome;
import com.kjnext.utility.email.SendEmail;

public class UsersServiceImpl implements UsersService {
	UsersDao usersDao = new UsersDaoImpl();
	String sendmailstatus=null;
	UserDetail userDetail = new UserDetail();

	private static final String ALLOWED_PASSWORD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789!&$%*";

	public UserDetail getUserDetail(Integer uid) {
		if (uid != null) {
			return usersDao.getUserDetail(uid);
		} else {
			System.out.println("Provide the userID to be searched");
			return null;
		}
	}

	/*
	 * Adding user details
	 */
	public OutCome addNewUser(UserDetail userDetail,
			List<UserDocuments> documentList,OutCome outCome) {
		System.out.println("Inside addnew User UserServiceImpl");
		outCome = validate(userDetail, documentList,outCome);
		if (outCome.isSuccess()) {
			System.out.println(userDetail.getFirstName());
			System.out.println(userDetail.getlocationArea());

			if (userDetail != null && documentList.isEmpty() == false) {
				userDetail.setStatus(Status.PENDING_USER.toString());
				userDetail.setCreatedDtm(new Date());
				Integer userEmpId = usersDao.addNewUser(userDetail);

				if (userEmpId != null) {
					addUserDocuments(userEmpId, documentList);
				} else {
					System.out.println("Error in adding");
				}
			}
		}
		return outCome;
	}

	private OutCome validate(UserDetail userDetail,
			List<UserDocuments> documentList,OutCome outCome) {
		System.out.println("Inside validation of UserServiceImpl");

		if (userDetail.getFirstName().equals("")){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("First name is mandatory"));
		}
		if (userDetail.getGender().equals("-1")){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Gender is mandatory"));
		}
			System.out.println(userDetail.getGender());
			System.out.println(userDetail.getlocationArea().getAreaId());
			System.out.println(userDetail.getGender());
	/*	if (userDetail.getLastName().equals("")){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Last name is mandatory"));
		}*/
		if (0==userDetail.getlocationArea().getAreaId()){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Location Area is mandatory"));
		}
		if (0==(userDetail.getLocation().getLocId())){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Location is mandatory"));
		}
		if (userDetail.getContactNo().equals("")){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Contact no. is mandatory"));
		}
		String email = userDetail.getEmailId();

		if (email.equals("")){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Email Field can not be blank"));
		}

		boolean checkEmailExist = usersDao.verifyEmailExist(email);
		if (checkEmailExist == true){
			if(checkIfEmailIdSuspended(email)){
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message("Please send request to admin for Activating your account"));
			}else{
				outCome.setStatus(OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message("Email Id already registerd"));
			}
				
		}
		if (documentList.isEmpty()){
			outCome.setStatus(OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message("Attach atleast one document"));
		}

		return outCome;
	}


	private boolean checkIfEmailIdSuspended(String email) {
		return usersDao.checkIfEmailIdSuspended(email);
	}

	/*
	 * adding user documents
	 */
	public void addUserDocuments(Integer userEmpId,
			List<UserDocuments> documentList) {
		if (userEmpId != null && documentList.isEmpty() == false) {
			usersDao.addUserDocuments(userEmpId, documentList);
		}
	}

	/*
	 * Approve added userDetails
	 */
	public Boolean activateUser(UserDetail userDetail) {
		Integer modifiedid=userDetail.getModifiedUId();
		if (userDetail != null) {
			String password = createUnamePassWord(8);
			if (password != null) {
				Users users = new Users();
				users.setUpass(password);
				// for directly activating user
				if (userDetail.getEmailId() == null) {
					userDetail = usersDao.getUserDetail(userDetail
							.getUserEmpId());
				}

				users.setUname(userDetail.getEmailId());
				users.setUtype("UA");
				users.setCreatedDtm(new Date());
				Users activeUser = usersDao.activateUser(users);

				if (activeUser != null) {
					userDetail.setStatus(Status.ACTIVE.toString());
					userDetail.setUsers(activeUser);
					userDetail.setModifiedUId(modifiedid);
					Boolean updated = usersDao.updateUser(userDetail);
					if (updated == true) {
						if (sendMail(users, userDetail)) {
							return true;
						}
						return false;
					}
				} else {
					System.out.println("Error in approved user");
				}
			}
		} else {
			System.out.println("Provide the user to be approve");
		}
		return false;
	}

	public void removeUser(UserDetail user) {
		if (user != null) {
			Boolean user_removed = usersDao.removeUser(user);
			if (user_removed == true) {
				System.out.println("User removed");
			} else {
				System.out.println("User not removed");
			}
		}
	}

	public void updateUser(UserDetail user) {
		if (user != null) {
			Boolean user_updated = usersDao.updateUser(user);
			if (user_updated == true) {
				System.out.println("User Updated");
			} else {
				System.out.println("Not updated");
			}
		} else {
			System.out.println("Enter the user details to be updated");
		}
	}

	/*
	 * Managing Users table
	 */

	public void updateUsers(Users users) {
		if (users != null) {
			Boolean user_updated = usersDao.updateUsers(users);
			if (user_updated == true) {
				System.out.println("User Updated");
			} else {
				System.out.println("Not updated");
			}
		} else {
			System.out.println("Enter the user details to be updated");
		}
	}

	public Users getUsers(Integer uid) {
		if (uid != null) {
			return usersDao.getUsers(uid);
		} else {
			System.out.println("Provide the userID to be searched");
			return null;
		}
	}

	/*
	 * Create username and password for approved user
	 */
	public String createUnamePassWord(int length) {
		SecureRandom secureRandom = new SecureRandom();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			result.append(ALLOWED_PASSWORD_CHARS.charAt(secureRandom
					.nextInt(ALLOWED_PASSWORD_CHARS.length())));
		}
		return result.toString();
	}

	public List<UserDetail> getAllUser() {
		System.out.println(usersDao.getAllUser());

		return usersDao.getAllUser();

	}

	public List<UserDetail> showAllUser() {
		return usersDao.showAllUser();
	}

	// ************ START BY SNEHA

	public List<UserDetail> allUsers() {
		return usersDao.allUsers();
	}

	public Location getLocationById(Integer locId) {
		if (locId != null) {
			return usersDao.getLocationById(locId);
		} else {
			System.out.println("Provide the LocId");
			return null;
		}
	}

	public List<LocationArea> getLocationAreaByLocId(Integer locId) {
		if (locId != null) {
			return usersDao.getLocationAreaByLocId(locId);
		} else {
			System.out.println("Provide the locId");
			return null;
		}
	}

	public LocationArea getLocationAreaById(Integer areaId) {
		if (areaId != null) {
			return usersDao.getLocationAreaById(areaId);
		} else {
			System.out.println("Provide the areaId");
			return null;
		}
	}

	public void updateLocation(Location location) {
		if (location != null) {
			Boolean location_updated = usersDao.updateLocation(location);
			if (location_updated == true) {
				System.out.println("Location Updated");
			} else {
				System.out.println("Location Not Updated");
			}
		} else {
			System.out.println("Enter the locoation to be updated");
		}
	}

	public void updateLocationArea(LocationArea locationArea) {
		if (locationArea != null) {
			Boolean location_area_updated = usersDao
					.updateLocationArea(locationArea);
			if (location_area_updated == true) {
				System.out.println("Location Area Updated");
			} else {
				System.out.println("Location Area Not Updated");
			}
		} else {
			System.out.println("Enter the locoation area to be updated");
		}
	}

	// ************ END BY SNEHA

	public List<Location> getAllLocation() {
		return usersDao.getAllLocation();
	}

	public List<UserDetail> getAllApprovedUser() {
		return usersDao.getAllApprovedUser();
	}

	public List<UserDetail> getAllPendingUser() {
		return usersDao.getAllPendingUser();
	}

	public List<UserDetail> getAllRejectedUser() {
		return usersDao.getAllRejectedUser();
	}

	// send two mails one mail of verification
	// after verification send mail of usernm and password
	public Boolean sendMail(Users users, UserDetail userDetail) {
		System.out.println("started sending email....");
		SendEmail email = new SendEmail();
		try {
			if (users.getUname() != null) {
				email.setFrom(email.email_from, "Mystery Shoppers");
				email.setTo(userDetail.getEmailId());
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setPriority("1");
				email.setSubject("Mystery Shoppers Registration Successful");
				email
						.setBody("Congratulations !!! <br><br>Your account has been created.<br>You can login by using following credentials:\n"
								+ "<br>------------------------<br>"
								+ "Username:"
								+ users.getUname()
								+ "<br>"
								+ "Password:"
								+ users.getUpass()
								+ "<br>"
								+ "------------------------<br>"
								+ "Click below link for login:<br><b>"
								+ " "
								+ email.App_URL
								+ "</b><br><i>Please contact us if you have any queries.<i><br>"
								+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");
				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				email.sendMsg();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Abt userVerification
	public Boolean userVerification(String verificationId, Integer userEmpId,
			OutCome outCome) {
		if (verificationId != null && !"".equals(verificationId)) {
			UserDetail verifiedUser = usersDao.userVerification(verificationId,
					userEmpId);
			if (verifiedUser != null
					&& verificationId.equals(verifiedUser.getVerificationId())) {
				activateUser(verifiedUser);
				outCome
						.setStatus(com.kjnext.utility.OutCome.Status.SUCCESS_WITH_INFO);
				outCome
						.addMessage(new OutCome.Message(
								"Successfully Verified,<br>You wil get your login details on your email id."));
				return true;
			} else if (verifiedUser != null
					&& "DONE".equals(verifiedUser.getVerificationId())) {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome
						.addMessage(new OutCome.Message(
								"Varification is alreay done OR with this url agent is already varified , Please contact Administartor."));
				return false;
			} else {
				outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
				outCome.addMessage(new OutCome.Message(
						"Unsuccessful Please enter the exact verification ID"));
				return false;
			}
		} else {
			outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
			outCome.addMessage(new OutCome.Message(
					"Please enter varification ID."));
			return false;
		}
	}

	public List<UserDetail> getUsersOnLocation(Integer locId) {
		return usersDao.getUsersOnLocation(locId);
	}

	/*
	 * load file in to db
	 */
	public UserDocuments loadToDB(byte[] bytes) {
		UserDocuments userDocuments = usersDao.addDocumentsToDB(bytes);
		return userDocuments;
	}

	/*
	 * send verification id to selected user
	 */
	public void sendVerificationID(UserDetail userDetail, OutCome outCome) {
		String verificationId = createUnamePassWord(5);
		Integer tempModifiedUId = userDetail.getModifiedUId();
		UserDetail details = usersDao.getUserDetail(userDetail.getUserEmpId());
		if (null != details && details.getVerificationId() == null) {
			details.setVerificationId(verificationId);
			Boolean updated = usersDao.updateUser(details);
			details.setStatus(Status.APPROVED_USER.toString());
			details.setModifiedDtm(new Date());
			details.setModifiedUId(tempModifiedUId);
			usersDao.updateUser(details);
			if (updated == true) {
				try {
					SendEmail email = new SendEmail();
					email.setFrom(email.email_from, "Mystery Shoppers");
					// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To
					// Me");
					email.setTo(details.getEmailId());
					email.setPriority("1");
					email.setSubject("Mystery Shoppers Account Verification");
					email
							.setBody("Thank you for Registering with us.<br>Your verification Id is : "
									+ details.getVerificationId()
									+ "<br>Copy and paste the above verification id in the page specified below:"
									+ "<br><br><b> "
									+ email.App_URL
									+ "/verificationPage?UserEmpId="
									+ details.getUserEmpId()
									+ "</b><br><br>Please contact us if you have any queries.<br>"
									+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");

					email.setSMTPHost(email.email_smtp, email.email_from,
							email.email_password);
					email.sendMsg();
				} catch (Throwable e) {
					e.printStackTrace();
					outCome
							.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
					outCome
							.addFailureMessage(new OutCome.Message(
									"email.error"));
				}
			}

		} else {
			outCome.setStatus(com.kjnext.utility.OutCome.Status.FAILURE);
			// outCome.addFailureMessage(new
			// OutCome.Message("duplicate.approve"));
			outCome.addMessage(new OutCome.Message("duplicate.approve"));
		}

	}

	/*
	 * get byte code of given file to save file in db
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) file.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}

	// Note:
	// methods are made temp static change to nonstatic after testing and write
	// the HIbernate code in dao with seperate method

	/*
	 * get all documents of user
	 */
	@SuppressWarnings("unchecked")
	public static List<UserDocuments> generateUserDocumentReport(Integer uid) {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List<UserDocuments> UserDocIdList = null;
		try {

			UserDocIdList = hibernateSession
					.createCriteria(UserDocuments.class).add(
							Expression.eq("userDetail.userEmpId", uid)).list();
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return UserDocIdList;
	}

	public static String loadDocName(Integer docId, String tableName) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			List<String> docName = hibernateSession.createQuery(
					"select documentName from " + tableName + " where docId ="
							+ docId).list();
			return docName.get(0).toString();
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	/*
	 * get file for preview
	 */
	@SuppressWarnings("unchecked")
	public static byte[] getUserDocumentFileBytes(Integer docid) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			DocumetRepository documetRepository = (DocumetRepository) hibernateSession
					.get(DocumetRepository.class, docid);
			byte[] bytes = toByteArray(documetRepository.getDocument());
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	@SuppressWarnings("unchecked")
	public static InputStream getUserDocumentFile(Integer docid) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			DocumetRepository documetRepository = (DocumetRepository) hibernateSession
					.get(DocumetRepository.class, docid);
			byte[] bytes = toByteArray(documetRepository.getDocument());
			InputStream fileInputStream = new ByteArrayInputStream(bytes);
			return fileInputStream;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	// file to byte array
	public static byte[] toByteArray(Blob fromImageBlob) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(fromImageBlob, baos);
		} catch (Exception e) {
		}
		return null;
	}

	private static byte[] toByteArrayImpl(Blob fromImageBlob,
			ByteArrayOutputStream baos) throws SQLException, IOException {
		int blobLength = (int) fromImageBlob.length();
		byte[] blobAsBytes = fromImageBlob.getBytes(1, blobLength);
		return blobAsBytes;
	}

	public List<UserDetail> getAllActiveUser() {
		return usersDao.getAllActiveUser();
	}

	public List<LocationArea> getAreasOnLocation(Integer locId) {
		return usersDao.getAreasOnLocation(locId);
	}

	@SuppressWarnings("unchecked")
	public static List<FeedbackDocuments> generateFeedbackDocumentReport(
			Integer feedbackId) {

		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		List<FeedbackDocuments> feedbackDocumentslist = null;
		List<FeedbackDocuments> feedbackDoclist = new ArrayList<FeedbackDocuments>();

		try {

			feedbackDocumentslist = hibernateSession.createCriteria(
					FeedbackDocuments.class).add(
					Expression.eq("userFeedback.feedbackId", feedbackId))
					.list();
			for (FeedbackDocuments feedbackDocuments : feedbackDocumentslist) {
				// if
				// (feedbackDocuments.getDocumentStatus().equals(Status.ACTIVE.toString()))
				// {
				feedbackDoclist.add(feedbackDocuments);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return feedbackDoclist;
	}

	public List<LocationArea> getAllAreasOnLocation() {

		return usersDao.getAllAreasOnLocation();
	}

	public void updateVarificationStatus(Integer userEmpId) {
		usersDao.updateVarificationStatus(userEmpId);
	}

	public List<String> sendEmailToAllAdmin(UserDetail userDetail) {
		List<String> adminEmail = usersDao.getAllAdmin();
		try {
			for (String admineMail : adminEmail) {
				SendEmail email = new SendEmail();
				email.setFrom(email.email_from, "Mystery Shoppers");
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setTo(admineMail);
				email.setPriority("1");
				email
						.setBody("New User is added.Below is detail--<br>"
								+ "Name : "
								+ userDetail.getFirstName()
								+ " "
								+ userDetail.getLastName()
								+ "<br>"
								+ " Contact No "
								+ userDetail.getContactNo()
								+ "<br>"
								+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");
				email.setSubject("New User registered mobile no:  --"
						+ userDetail.getContactNo());
				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				try{
					email.sendMsg();
				}catch (Exception e) {
					System.out.println("Email not sent to admin");
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return adminEmail;
	}

	public List<String> sendEmailToAllAdminAbtTaskAccepted(Tasks tasks) {
		List<String> taskAdmin = usersDao.getAllAdminEmail();
		try {
			for (String task : taskAdmin) {

				SendEmail email = new SendEmail();
				email.setFrom(email.email_from, "Mystery Shoppers");
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setTo(task);
				email.setPriority("1");
				email.setSubject("New Task Accepted By User");
				email.setBody("Tasks Name : " + tasks.getTaskUniqueName()
						+ "<br>" + " Venue " + tasks.getVenue() + "<br>"
						+ "Name of Person "
						+ tasks.getUserDetail().getFirstName() + " "
						+ tasks.getUserDetail().getLastName()
						+ " <br>Contact No "
						+ tasks.getUserDetail().getContactNo());
				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				email.sendMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return taskAdmin;
	}

	public List<String> sendEmailToAllAdminForFeedbackSubmission(Tasks tasks) {

		List<String> feedBackTask = usersDao.getAllAdminEmailForTask();

		try {
			for (String feedBack : feedBackTask) {

				SendEmail email = new SendEmail();
				email.setFrom(email.email_from, "Mystery Shoppers");
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setTo(feedBack);
				email.setPriority("1");
				email.setSubject("Feedback Submitted for task : "
						+ tasks.getTaskUniqueName());
				email
						.setBody("We have received Feedback from : <br>"
								+ tasks.getUserDetail().getFirstName()
								+ " "
								+ tasks.getUserDetail().getLastName()
								+ "Contact No : "
								+ tasks.getUserDetail().getContactNo()
								+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");

				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				email.sendMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return feedBackTask;

	}

	public List<UserDetail> getEmployeeNameLabel(Integer empId) {
		return usersDao.getEmployeeNameLabel(empId);

	}

	public String taskStatus(Integer userEmpId) {
		// TODO Auto-generated method stub
		return usersDao.taskStatus(userEmpId);
	}

	public List<String> sentApplicationDataToEmail(UserDetail userDetail) {

		List<String> formToMail =usersDao.applicationDataForEmail();
		
		try {
		//	for (String toMail : formToMail) {

				SendEmail email = new SendEmail();
				email.setFrom(email.email_from, "Mystery Shoppers");
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setTo(email.email_to);
				email.setPriority("1");
				email.setSubject("Inquiry Form : "
						+ userDetail.getFirstName());
				email
						.setBody("Below is detail from Inquiry from : <br><br>" +
								"First Name : "
								+ userDetail.getFirstName()
								+ " <br> Last Name"
								+ userDetail.getLastName()
								+ " <br> Contact No : "
								+ userDetail.getContactNo()
								+ " <br> Email Id : "
								+ userDetail.getEmailId()
								+ " <br> Ciy :"
								+ userDetail.getCity()
								+ " <br> Address :"
								+ userDetail.getAddress()
								+ " <br> Message :"
								+ userDetail.getMessage()
								+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");

				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				email.sendMsg();
		//	}
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	return formToMail;	
	}

	
	public List<UserDetail> getUsersOnLocationExcludeRejected(Tasks tasks) {
		List<UserDetail> list1 = usersDao.getUsersOnLocation(tasks.getLocation().getLocId());
		List<UserDetail> list2 = new ArrayList<UserDetail>();
		UserDetail userDetail = usersDao.getUserDetail(tasks.getUserDetail().getUserEmpId());
		list1.remove(userDetail);
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			UserDetail userDetail1 = (UserDetail) iterator.next();
			System.out.println(userDetail1.getUserEmpId());
			list2.add(userDetail1);
		}
		return list2;
	}

	public String sendForgetPasswordAction(String Emailid) {
		List<UserDetail> sendmail=usersDao.sendForgetPasswordDao(Emailid);
		if(sendmail!=null){
		for(UserDetail sendmailref:sendmail)
		{
		System.out.println("started sending email....");
		SendEmail email = new SendEmail();
		try {
			if (sendmailref!=null) {
				email.setFrom(email.email_from, "Mystery Shoppers");
				email.setTo(Emailid);
				// email.setReplyTo("shoppingkjnext@gmail.com", "Reply To Me");
				email.setPriority("1");
				
				email.setSubject("Your Password");
				email
						.setBody("Please use below credentials <br>"
								+ "<br>"
								+ "Username:"
								+ sendmailref.getUsers().getUname()
								+ "<br>Password:"
								+ sendmailref.getUsers().getUpass()
								+ "<br><br>"
								+ "Click below link for login:<br>"
								+ " "
								+ email.App_URL
								+ "<br><br><i>Please contact us if you have any queries.<i><br>"
								+ "<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");
				email.setSMTPHost(email.email_smtp, email.email_from,
						email.email_password);
				email.sendMsg();
				sendmailstatus="success";
				return sendmailstatus;
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendmailstatus="nosend";
			return sendmailstatus;			
		}
		
		}
		}
		sendmailstatus="invalid";
		return sendmailstatus;		
	}

	public boolean activateSuspendedUser(UserDetail userDetailDb) {
		userDetailDb.setStatus(Status.ACTIVE.toString());
		usersDao.activateSuspendedUser(userDetailDb);
		return false;
	}

	
}
