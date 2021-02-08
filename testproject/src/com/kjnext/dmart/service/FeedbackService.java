package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.UserFeedback;

public interface FeedbackService {

	public Integer addFeedback(UserFeedback userFeedback);

	public void rejectFeedback(UserFeedback userFeedback);

	public void approveFeedback(UserFeedback userFeedback);
	
	public Integer addFeedbackDocument(FeedbackDocuments feedbackDocuments);

	public UserFeedback loadUserFeedback(Integer feedbackId);

	public List<UserFeedback> getAllFinishedTaskByUser(UserFeedback userFeedback);
	
	public List<UserFeedback> getAllApprovedFeedbackByAdmin(UserFeedback userFeedback) ;
	
	public List<UserFeedback> getAllRejectedFeedbackByAdmin(UserFeedback userFeedback);
	
	public List<UserFeedback> getAllFeedback(Integer clientId);
	
	public boolean updateUserFeedback(UserFeedback userFeedback, Integer feedbackId); 
	
	public boolean updateUserFeedbackbyAdmin(UserFeedback userFeedback); 
	
	public UserFeedback userFeedbackMail(Integer feedbackId);
	
	public Integer updateFeedbackDocument(FeedbackDocuments feedbackDocuments);
	
	public List<UserFeedback> getAllReassignedFeedbackByClient(UserFeedback userFeedback);
}
