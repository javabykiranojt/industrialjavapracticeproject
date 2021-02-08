package com.kjnext.dmart.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kjnext.dmart.dao.FeedbackDao;
import com.kjnext.dmart.dao.Impl.FeedbackDaoImpl;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.service.FeedbackService;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.email.SendEmail;

public class FeedbackServiceImpl implements FeedbackService {

	FeedbackDao feedbackDao = new FeedbackDaoImpl();

	UserFeedback userFeedback = null;

	public Integer addFeedback(UserFeedback userFeedback) {
		return feedbackDao.addFeedback(userFeedback);
	}

	public Integer addFeedbackDocument(FeedbackDocuments feedbackDocuments) {
		feedbackDao = new FeedbackDaoImpl();
		return feedbackDao.addFeedbackDocument(feedbackDocuments);
	} 

	public void approveFeedback(UserFeedback userFeedback) {
		userFeedback.setModifiedDtm(new Date());
		userFeedback.setStatus(Status.FINISHED_ADMIN.toString());
		feedbackDao.approveFeedback(userFeedback);

	}

	public void rejectFeedback(UserFeedback userFeedback) {
		userFeedback.setModifiedDtm(new Date());
		userFeedback.setStatus(Status.REJECTED_ADMIN.toString());
		feedbackDao.rejectFeedback(userFeedback);
	}


	public UserFeedback loadUserFeedback(Integer feedbackId) {
		return feedbackDao.loadUserFeedback(feedbackId);
	}

	public List<UserFeedback> getAllFinishedTaskByUser(UserFeedback userFeedback) {
		userFeedback.setStatus(Status.FINISHED_USER.toString());
		return feedbackDao.getAllFinishedTaskByUser(userFeedback);
	}
	
	public List<UserFeedback> getAllApprovedFeedbackByAdmin(UserFeedback userFeedback) {
		userFeedback.setStatus(Status.FINISHED_ADMIN.toString());
		return feedbackDao.getAllFinishedTaskByUser(userFeedback);
	}
	
	public List<UserFeedback> getAllRejectedFeedbackByAdmin(UserFeedback userFeedback) {
		userFeedback.setStatus(Status.REJECTED_ADMIN.toString());
		return feedbackDao.getAllFinishedTaskByUser(userFeedback);
	}

// **** START BY SNEHA FOR REASSIGN FEEDBACK BY CLIENT
	
	public List<UserFeedback> getAllReassignedFeedbackByClient(UserFeedback userFeedback) {
		userFeedback.setStatus(Status.REASSIGNED_BY_CLIENT.toString());
		return feedbackDao.getAllFinishedTaskByUser(userFeedback);
	}
	
//	 **** END BY SNEHA FOR REASSIGN FEEDBACK BY CLIENT
	
	public List<UserFeedback> getAllFeedback(Integer clientId) {
		List<UserFeedback> feedList = feedbackDao.getAllFeedback(clientId);
		List<UserFeedback> feedList1 = new ArrayList<UserFeedback>();
		for (UserFeedback userFeedback : feedList) {
			if (userFeedback.getClients().getClientDetails().getlocationArea().getAreaName().equals(
					userFeedback.getTasks().getClients().getClientDetails().getlocationArea().getAreaName())) {
				feedList1.add(userFeedback);
			}
		}
		return feedList1;
	}

	
	public boolean updateUserFeedback(UserFeedback userFeedback,
			Integer feedbackId) {		
		
		return feedbackDao.updateUserFeedback(userFeedback, feedbackId);
	}

	public UserFeedback userFeedbackMail(Integer feedbackId) {
		
		
		UserFeedback userFeed=feedbackDao.userFeedbackMail(feedbackId);
		String userEmail=userFeed.getUserDetail().getEmailId();
		System.out.println(userEmail);
		
		try {
				SendEmail email = new SendEmail();
				email.setFrom(email.email_from,"Mystery Shoppers" );
				email.setTo(userEmail);
				email.setPriority("1");
				email.setSubject("Your Feedback Has been Accepted");
				email.setBody("Your Feedback Has been accepted by Indian Mystery Shoppers.<br>" +
						"You might have got Reward points please login to our portal<br> to see your creadited reward points<br>"+
								"Please contact us if you have any queries.<br>" +
												"<br><br>Thanks & Regards,<br>Indian Mystery Shoppers.");
				email.setSMTPHost(email.email_smtp, email.email_from,email.email_password);
				email.sendMsg();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return userFeed;
	}

	public boolean updateUserFeedbackbyAdmin(UserFeedback userFeedback) {
		
		return feedbackDao.updateUserFeedbackbyAdmin(userFeedback);
	}


	public Integer updateFeedbackDocument(FeedbackDocuments feedbackDocuments) {
		List<FeedbackDocuments> docList = feedbackDao.loadFeedbackDocumentList(feedbackDocuments);
		Integer count = 0;
		String docStatus = "";
		Integer createdid = 0;
		if (docList!=null) {
			for (FeedbackDocuments feedbackDoc : docList) {
				System.out.println(feedbackDoc.getFeedbackDocId());
				if (feedbackDoc.getDocumentStatus()!=null 
						&& feedbackDoc.getDocumentStatus().toString().equals(feedbackDocuments.getDocumentStatus().toString())
						 ) {
					feedbackDocuments.setFeedbackDocId(feedbackDoc.getFeedbackDocId());
					feedbackDocuments.setCreatedUId(feedbackDoc.getCreatedUId());
					count =feedbackDao.updateFeedbackDocument(feedbackDocuments);
				}			
			}
		}
		for (FeedbackDocuments feedDoc : docList) {
			docStatus = docStatus.concat(feedDoc.getDocumentStatus().toString());
			createdid = feedDoc.getCreatedUId();
		}
		System.out.println(docStatus.toString());
		System.out.println(feedbackDocuments.getDocumentStatus().toString());
		if (!docStatus.toString().contains(feedbackDocuments.getDocumentStatus().toString())) {
			feedbackDocuments.setCreatedUId(createdid);
			count =feedbackDao.addFeedbackDocumentByAdmin(feedbackDocuments);	
		}
		
		
		return count;
	}
	
}
