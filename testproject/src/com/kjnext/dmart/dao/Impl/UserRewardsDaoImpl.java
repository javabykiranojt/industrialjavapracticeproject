package com.kjnext.dmart.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import com.kjnext.context.CallContextService;
import com.kjnext.dmart.dao.UserRewardsDao;
import com.kjnext.dmart.hibernate.TaskClientPoints;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.vo.SearchCriteriaUserRewards;

public class UserRewardsDaoImpl implements UserRewardsDao{

	@SuppressWarnings("unchecked")
	public List<UserRewards> showAllRewards(SearchCriteriaUserRewards criteriaUserRewards) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		try {
			Criteria criteria = hibernateSession.createCriteria(
					UserRewards.class);

			if (criteriaUserRewards == null) {
				return criteria.list();
			}

			if (criteriaUserRewards.getRewardId() != null) {
				criteria.add(Expression.eq("rewardId", criteriaUserRewards.getRewardId()));
			}

			if (criteriaUserRewards.getUserEmpId() != null) {
				criteria.createCriteria("userDetail")
						.add(
								Expression.eq("userEmpId", criteriaUserRewards
										.getUserEmpId()));
			}
			List<UserRewards> listUserRewards = criteria.list();

			return listUserRewards;
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
	}

	public Integer addRewardsToUser(UserRewards userRewards) {
		Session hibernateSession = (Session) (CallContextService.getInstance()
				.getContext().getAttribute("hibernateSession"));
		try {
			hibernateSession.save(userRewards);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateSession.getTransaction().rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			hibernateSession = null;
		}
		return userRewards.getRewardId();
		
	}

	public Boolean updateUserRewards(UserRewards userRewards, Integer rewardId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		boolean updated=false;
		try 
		{
			UserRewards userReward =(UserRewards) hibernateSession.load(UserRewards.class, rewardId);
			userReward.setPoint(userRewards.getPoint());
			userReward.setModifiedDtm(userRewards.getModifiedDtm());
			userReward.setModifiedUId(userRewards.getModifiedUId());
			hibernateSession.update(userReward);
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
	public boolean verifyIfUserExist(Integer userEmpId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		
		List<UserRewards> checkUserExistList =hibernateSession.createCriteria(UserRewards.class).createCriteria("userDetail").add(Expression.eq("userEmpId", userEmpId)).list();
         if(checkUserExistList.isEmpty()) 
        	 return false;
         else
	         return true;
			}

	@SuppressWarnings("unchecked")
	public Integer getPoints(Integer userEmpId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Integer points = 0;
		List<UserRewards> checkPointsList =hibernateSession.createCriteria(UserRewards.class).createCriteria("userDetail").add(Expression.eq("userEmpId", userEmpId)).list();
         if(checkPointsList.isEmpty()) 
        	 return 0;
         else{
        	 for(UserRewards rewards: checkPointsList){
        		 points = Integer.parseInt(rewards.getPoint());
        	 }
			}
         return points;
	}

	public Integer getUserRewardsId(Integer userEmpId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		Integer rewardId = 0;
		List<UserRewards> checkPointsList =hibernateSession.createCriteria(UserRewards.class).createCriteria("userDetail").add(Expression.eq("userEmpId", userEmpId)).list();
         if(checkPointsList.isEmpty()) 
        	 return 0;
         else{
        	 for(UserRewards rewards: checkPointsList){
        		 rewardId = rewards.getRewardId();
        	 }
			}
         return rewardId;
	}

	public UserRewards loadUserRewards(Integer rewardId) {
		Session hibernateSession = (Session)(CallContextService.getInstance().getContext().getAttribute("hibernateSession"));
		UserRewards userRewards =(UserRewards) hibernateSession.load(UserRewards.class, rewardId);		
         return userRewards;
	}

	

	
	
}
	


