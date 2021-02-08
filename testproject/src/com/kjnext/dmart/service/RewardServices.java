package com.kjnext.dmart.service;

import java.util.List;

import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.vo.SearchCriteriaUserRewards;


public interface RewardServices {
	
	public List<UserRewards> showAllRewards(SearchCriteriaUserRewards  searchCriteriaClientPoints);
	public Integer addRewardsToUser(UserRewards userRewards);
	public boolean updateUserRewards(UserRewards userRewards, Integer rewardId); 
	public boolean verifyIfUserExist(Integer userEmpId);
	public Integer getPoints(Integer userEmpId);
	public Integer getUserRewardsId(Integer userEmpId);
	public UserRewards loadUserRewards(Integer rewardId);
}
