package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.UserFeedback;

public interface FeedbackDao {
	
	public Integer addFeedbackDocument(FeedbackDocuments feedbackDocuments);

	public Integer addFeedback(UserFeedback userFeedback);

	public void approveFeedback(UserFeedback userFeedback);

	public void rejectFeedback(UserFeedback userFeedback);

	public UserFeedback loadUserFeedback(Integer feedbackId);

	public List<UserFeedback> getAllFinishedTaskByUser(UserFeedback userFeedback);
	
	public List<UserFeedback> getAllApprovedFeedbackByAdmin(UserFeedback userFeedback) ;
	
	public List<UserFeedback> getAllRejectedFeedbackByAdmin(UserFeedback userFeedback);
	
	public List<UserFeedback> getAllFeedback(Integer clientId);
	
	public boolean updateUserFeedback(UserFeedback userFeedback, Integer feedbackId);
	
	public UserFeedback userFeedbackMail(Integer feedbackId);
	
	public boolean updateUserFeedbackbyAdmin(UserFeedback userFeedback); 
	
	public Integer updateFeedbackDocument(FeedbackDocuments feedbackDocuments);
	
	public List<FeedbackDocuments> loadFeedbackDocumentList(FeedbackDocuments feedbackDocuments);
	
	public Integer addFeedbackDocumentByAdmin(FeedbackDocuments feedbackDocuments);
 
}
