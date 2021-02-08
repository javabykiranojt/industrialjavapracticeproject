package com.kjnext.dmart.service.Test;

import java.io.IOException;

import junit.framework.TestCase;

import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.service.DocumentService;
import com.kjnext.dmart.service.FeedbackService;
import com.kjnext.dmart.service.Impl.DocumentServiceImpl;
import com.kjnext.dmart.service.Impl.FeedbackServiceImpl;
import com.kjnext.dmart.vo.Status;

public class FeedbackServiceTest extends TestCase {

	FeedbackService feedbackService;

	DocumentService documentService;

	@Override
	protected void setUp() throws Exception {
		feedbackService = new FeedbackServiceImpl();
		documentService = new DocumentServiceImpl();
	}

	@Override
	protected void tearDown() throws Exception {
		feedbackService = null;
		documentService = null;
	}

	/*public void testAddUserFeedBack() throws IOException {
	 File file1 = new File("abc.txt");
	 File file2 = new File("pqr.txt");
	 byte[] bytes1 = documentService.getBytesFromFile(file1);
	 int docId1 = documentService.storeDocument(bytes1);
	 byte[] bytes2 = documentService.getBytesFromFile(file2);
	 int docId2 = documentService.storeDocument(bytes2);
	 System.out.println("docId 1 " + docId1);
	 System.out.println("docId 2 " + docId2);

	 UserFeedback userFeedback = new UserFeedback();
	 userFeedback.setFeedbackDetails("It was good");
	 userFeedback.setStatus(Status.INITIATED_USER.toString());
	 userFeedback.setTaskAssignProof("at 6.00 I went thr");
	 UserDetail userDetail = new UserDetail();
	 userDetail.setUserEmpId(2);
	 userFeedback.setUserDetail(userDetail);
	 Clients clients = new Clients();
	 clients.setClientId(1001);
	 userFeedback.setClients(clients);
	 feedbackService.addFeedback(userFeedback);
	 System.out.println("Feedback ID >>>" + userFeedback.getFeedbackId());

	 FeedbackDocuments feedbackDocuments = new FeedbackDocuments();
	 feedbackDocuments.setDocumentName(file1.getName());
	 feedbackDocuments.setFeedbackDocId(docId1);
	 feedbackDocuments.setUserFeedback(userFeedback);
	 feedbackService.addFeedbackDocument(feedbackDocuments);

	 FeedbackDocuments feedbackDocuments1 = new FeedbackDocuments();
	 feedbackDocuments1.setDocumentName(file2.getName());
	 feedbackDocuments1.setFeedbackDocId(docId2);
	 feedbackDocuments1.setUserFeedback(userFeedback);
	 feedbackService.addFeedbackDocument(feedbackDocuments1);

	 System.out.println("feedback do id "
	 + feedbackDocuments.getFeedbackDocId());
	 System.out.println("feedback1 do id "
	 + feedbackDocuments1.getFeedbackDocId());

	 }*/

	/*public void testApproved() throws IOException {

		feedbackService.approveFeedback();

	}*/

	/*public void testReject() throws IOException {
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setFeedbackId(1);
		feedbackService.rejectFeedback(userFeedback);
		UserFeedback userFeedback1=feedbackService.loadUserFeedback(1);
		assertEquals(userFeedback1.getStatus().toString(), Status.REJECTED_ADMIN.toString());
		
	}*/
	
	public void testapproveFeedback(){
		UserFeedback userFeedback = new UserFeedback();
		userFeedback.setFeedbackId(1);
		feedbackService.approveFeedback(userFeedback);
		UserFeedback userFeedback1=feedbackService.loadUserFeedback(1);
		
		assertEquals(userFeedback1.getStatus().toString(), Status.FINISHED_ADMIN.toString());
		assertEquals(userFeedback1.getStatus().toString(), Status.FINISHED_ADMIN.toString());
	}
	
	
	
	
}


