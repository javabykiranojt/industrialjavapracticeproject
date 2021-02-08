package com.kjnext.dmart.dao;

import java.util.List;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.vo.SearchCriteriaUserRewards;

public interface UserRewardsDao {

	public List<UserRewards> showAllRewards(SearchCriteriaUserRewards criteriaUserRewards);
	public Integer addRewardsToUser(UserRewards userRewards);
	public Boolean updateUserRewards(UserRewards userRewards,Integer rewardId); 
	public boolean verifyIfUserExist(Integer userEmpId);
	public Integer getPoints(Integer userEmpId);
	public Integer getUserRewardsId(Integer userEmpId);
	public UserRewards loadUserRewards(Integer rewardId);
}
