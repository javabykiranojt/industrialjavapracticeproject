
package com.kjnext.action;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.kjnext.dmart.hibernate.UserDetail;
import com.kjnext.dmart.hibernate.UserRewards;
import com.kjnext.dmart.hibernate.Users;
import com.kjnext.dmart.service.RewardServices;
import com.kjnext.dmart.service.UsersService;
import com.kjnext.dmart.service.Impl.RewardServicesImpl;
import com.kjnext.dmart.service.Impl.UsersServiceImpl;
import com.kjnext.dmart.vo.SearchCriteriaUserRewards;

/*
 * 
 * @author : Amarjit Sharma
 */

public class RewardAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	 List<UserRewards> userRewardList = null;
	RewardServices rewardServices = new RewardServicesImpl();
	UsersService usersService = new UsersServiceImpl();
	private UserRewards  userRewards;
	private UserDetail userDetail;
	private String navigationFlag;
	private Integer rId;
	private Integer gTaskPoints;
	private String pageTitle;
	
	//for loading All reward Points
	public String showAllRewards() {
		pageTitle=getText("reward.point");
		SearchCriteriaUserRewards  criteriaUserRewards = null;
		userRewardList = rewardServices.showAllRewards(criteriaUserRewards);
		for (UserRewards userRewards : userRewardList) {
			Integer uid = userRewards.getModifiedUId();
			if(uid!=null)
			{
				Users tempUsers =usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					userRewards.setModifiedUIdName(uD.getFirstName()+" "+uD.getLastName());
				}
			}
			else
			{
				userRewards.setModifiedUIdName("-");
			}
			
			uid = userRewards.getCreatedUId();
			if(uid!=null)
			{
				Users tempUsers =usersService.getUsers(uid);
				Set<UserDetail> set = tempUsers.getUserDetails();
				for (UserDetail uD : set) {
					userRewards.setCreatedUIdName(uD.getFirstName());
				}
			}
			else
			{
				userRewards.setCreatedUIdName("-");
			}
			String rPoint = userRewards.getRedeemPoint();
			if (rPoint == null || rPoint.length() == 0) {
				userRewards.setRedeemPoint("-");
			}
			String reqPoint = userRewards.getRequestRedeemPoint();
			if (reqPoint == null || reqPoint.length() == 0) {
				userRewards.setRequestRedeemPoint("-");
			}
			String mop = userRewards.getModeOfPayment();
			if (mop== null || mop.length() == 0) {
				userRewards.setModeOfPayment("-");
			}
			String remark = userRewards.getRemarks();
			if (remark== null || remark.length() == 0) {
				userRewards.setRemarks("-");
			}
		}
		return SUCCESS;
	}

	// for redeemRewardsInput
	
	public String redeemRewardsInput(){
		userRewards = rewardServices.loadUserRewards(userRewards.getRewardId());
		
		return SUCCESS;
	}
	
	public String redeemRewards(){
		UserRewards uReward = rewardServices.loadUserRewards(userRewards.getRewardId());
		Integer point = 0;
		Integer rPoint = 0;
		String retn ="";
	
		if (Integer.parseInt(uReward.getPoint())>getGTaskPoints()) {
			point = Integer.parseInt(uReward.getPoint())- getGTaskPoints();
			
			if (uReward.getRedeemPoint()!= null && !uReward.getPoint().equals("-")) {
				rPoint = Integer.parseInt(uReward.getRedeemPoint())+ getGTaskPoints();
			} else	{
				rPoint = getGTaskPoints();
			}
			
			uReward.setPoint(point.toString());
			uReward.setRedeemPoint(rPoint.toString());
			uReward.setModifiedDtm(new Date());
			uReward.setModeOfPayment(userRewards.getModeOfPayment());
			uReward.setRemarks(userRewards.getRemarks());
			uReward.setRequestRedeemPoint("0");
			uReward.setModifiedUId((Integer)session.get("uId"));
			rewardServices.updateUserRewards(uReward, userRewards.getRewardId());
			setNavigationFlag("success");
			retn=  SUCCESS;
			
		}	else{
			rewardServices.loadUserRewards(userRewards.getRewardId());
			addActionMessage("Please check the points...");
			retn= "pointError";
		}
		
		return retn;
	}

	public RewardServices getRewardServices() {
		return rewardServices;
	}

	public void setRewardServices(RewardServices rewardServices) {
		this.rewardServices = rewardServices;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public UserRewards getUserRewards() {
		return userRewards;
	}

	public void setUserRewards(UserRewards userRewards) {
		this.userRewards = userRewards;
	}



	public List<UserRewards> getUserRewardList() {
		return userRewardList;
	}



	public void setUserRewardList(List<UserRewards> userRewardList) {
		this.userRewardList = userRewardList;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public Integer getRId() {
		return rId;
	}

	public void setRId(Integer id) {
		rId = id;
	}

	public Integer getGTaskPoints() {
		return gTaskPoints;
	}

	public void setGTaskPoints(Integer taskPoints) {
		gTaskPoints = taskPoints;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	
	

	

}
