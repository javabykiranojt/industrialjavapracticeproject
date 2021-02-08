package com.kjnext.dmart.service.Impl;

import java.util.List;

import com.kjnext.dmart.dao.UserRewardsDao;
import com.kjnext.dmart.dao.Impl.UserRewardsDaoImpl;
import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.service.RewardServices;
import com.kjnext.dmart.vo.SearchCriteriaUserRewards;

public class RewardServicesImpl implements RewardServices{
	
	UserRewardsDao userRewardsDao = new UserRewardsDaoImpl();
	
	

	public List<UserRewards> showAllRewards(SearchCriteriaUserRewards searchCriteriaClientPoints) {
		return userRewardsDao.showAllRewards(searchCriteriaClientPoints);
	}



	public Integer addRewardsToUser(UserRewards userRewards) {
		// TODO Auto-generated method stub
		return userRewardsDao.addRewardsToUser(userRewards);
	}



	public boolean updateUserRewards(UserRewards userRewards, Integer rewardId) {
		return userRewardsDao.updateUserRewards(userRewards, rewardId);
	}



	public boolean verifyIfUserExist(Integer userEmpId) {
		// TODO Auto-generated method stub
		return userRewardsDao.verifyIfUserExist(userEmpId);
	}



	public Integer getPoints(Integer userEmpId) {
		// TODO Auto-generated method stub
		return userRewardsDao.getPoints(userEmpId);
	}



	public Integer getUserRewardsId(Integer userEmpId) {
		// TODO Auto-generated method stub
		return userRewardsDao.getUserRewardsId(userEmpId);
	}



	public UserRewards loadUserRewards(Integer rewardId) {
		// TODO Auto-generated method stub
		return userRewardsDao.loadUserRewards(rewardId);
	}


}
