package com.kjnext.dmart.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.FeedbackDao;
import com.kjnext.dmart.hibernate.FeedbackDocuments;
import com.kjnext.dmart.hibernate.UserFeedback;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.vo.Status;
import com.kjnext.utility.hibernate.HibernateUtil;

public class FeedbackDaoImpl implements FeedbackDao {

	public Integer addFeedbackDocument(FeedbackDocuments feedbackDocuments) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(feedbackDocuments);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return feedbackDocuments.getFeedbackDocId();
	}
	public Integer addFeedbackDocumentByAdmin(FeedbackDocuments feedbackDocuments) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			feedbackDocuments.setModifiedDtm(new Date());
			
			hibernateSession.save(feedbackDocuments);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return feedbackDocuments.getFeedbackDocId();
	}
	
	
	public Integer updateFeedbackDocument(FeedbackDocuments feedbackDocuments) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			FeedbackDocuments feedDoc = (FeedbackDocuments) hibernateSession.load(FeedbackDocuments.class, feedbackDocuments.getFeedbackDocId());
			//if (feedDoc!=null) {
					//feedDoc.setDocumentStatus(Status.INACTIVE.toString());
				//	hibernateSession.update(feedDoc);
				//}
			
			feedDoc.setCreatedDtm(feedbackDocuments.getCreatedDtm());
			feedDoc.setCreatedUId(feedbackDocuments.getCreatedUId());
			feedDoc.setDocumetRepository(feedbackDocuments.getDocumetRepository());
			feedDoc.setUserFeedback(feedbackDocuments.getUserFeedback());
			feedDoc.setDocumentName(feedbackDocuments.getDocumentName());
			feedDoc.setModifiedDtm(new Date());
			feedDoc.setModifiedUId(feedbackDocuments.getModifiedUId());
			feedbackDocuments.setFeedbackDocId(feedDoc.getFeedbackDocId());
			hibernateSession.saveOrUpdate(feedDoc);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return feedbackDocuments.getFeedbackDocId();
	}
	public Integer addFeedback(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(userFeedback);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return userFeedback.getFeedbackId();
	}

	public void approveFeedback(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.saveOrUpdate(userFeedback);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
	}

	public void rejectFeedback(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.update(userFeedback);
		}catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}

	}

	public UserFeedback loadUserFeedback(Integer feedbackId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			UserFeedback userFeedback = (UserFeedback) hibernateSession.load(UserFeedback.class, feedbackId);
			return userFeedback;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<UserFeedback> getAllFinishedTaskByUser(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
		List<UserFeedback> userFeedbackList = (List<UserFeedback>) hibernateSession.createCriteria(UserFeedback.class).add(
						Expression.eq("status", userFeedback.getStatus()
								.toString())).list();
			return userFeedbackList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		

	}
	
	@SuppressWarnings("unchecked")
	public List<UserFeedback> getAllApprovedFeedbackByAdmin(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
		List<UserFeedback> userFeedbackList = (List<UserFeedback>)hibernateSession.createCriteria(UserFeedback.class).add(
						Expression.eq("status", userFeedback.getStatus()
								.toString())).list();

		return userFeedbackList;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserFeedback> getAllRejectedFeedbackByAdmin(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
		List<UserFeedback> userFeedbackList = (List<UserFeedback>)hibernateSession.createCriteria(UserFeedback.class).add(
						Expression.eq("status", userFeedback.getStatus()
								.toString())).list();

		return userFeedbackList;
	} catch (Exception e) {
		e.printStackTrace();
		hibernateSession.getTransaction().rollback();
		throw new RuntimeException(e.getMessage());
	}finally{
		hibernateSession=null;
	}
	}

	
	public List<UserFeedback> getAllFeedback(Integer clientId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		List<UserFeedback> userFeedbackList =hibernateSession.createCriteria(UserFeedback.class).createCriteria("clients").add(Expression.eq("clientId", clientId)).list();
		return userFeedbackList;
	}

	
	public boolean updateUserFeedback(UserFeedback userFeedback,
			Integer feedbackId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		boolean updated=false;
		try 
		{
			UserFeedback usrFeedback =(UserFeedback) hibernateSession.load(UserFeedback.class, feedbackId);
			usrFeedback.setSentStatus(userFeedback.getSentStatus());
			usrFeedback.setSentDtm(userFeedback.getSentDtm());
			usrFeedback.setModifiedUId(userFeedback.getModifiedUId());
			hibernateSession.update(usrFeedback);
			updated=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return updated;
	}

	public UserFeedback userFeedbackMail(Integer feedbackId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
			System.out.println(feedbackId);
		try {
			UserFeedback userFeedbackEmail = (UserFeedback) hibernateSession.get(UserFeedback.class, feedbackId);
				
			return userFeedbackEmail;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
	}

	
	public boolean updateUserFeedbackbyAdmin(UserFeedback userFeedback) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		boolean updated=false;
		try 
		{
			UserFeedback usrFeedback =(UserFeedback) hibernateSession.load(UserFeedback.class, userFeedback.getFeedbackId());
			
			
			usrFeedback.setModifiedUId(userFeedback.getModifiedUId());
			usrFeedback.setFeedbackDetails(userFeedback.getFeedbackDetails());
			usrFeedback.setTaskAssignProof(userFeedback.getTaskAssignProof());
			usrFeedback.setModifiedDtm(new Date());
			usrFeedback.setFeedbackDocumentses(userFeedback.getFeedbackDocumentses());
			hibernateSession.update(usrFeedback);
			updated=true;
			
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return updated;
	}

	
	@SuppressWarnings("unchecked")
	public List<FeedbackDocuments> loadFeedbackDocumentList(
			FeedbackDocuments feedbackDocuments) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		List<FeedbackDocuments> feedDocList=null;
		try {
			feedDocList =(List<FeedbackDocuments>)hibernateSession.createCriteria(FeedbackDocuments.class).add(Expression.eq("userFeedback", feedbackDocuments.getUserFeedback())).list();
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		}finally{
			hibernateSession=null;
		}
		return feedDocList;
	}
	
/*	public static void main(String[] args) {
		FeedbackDaoImpl impl = new FeedbackDaoImpl();
		UserFeedback feedback = new UserFeedback();
		feedback.setStatus(Status.FINISHED_USER.toString());
		impl.getAllFinishedTaskByUser(feedback);
		
	}
*/
}
