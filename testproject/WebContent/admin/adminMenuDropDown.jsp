<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id=container>

	<UL class=nav id=nav>
				
		<LI>
			<A href="showAllClient" class="ImgtxtUp">Clients </A>
			
		</LI>
		
		<LI>
			<A href="allusers" class="ImgtxtUp">User </A>
			<%--<UL>
				<li>
					<s:a href="#">Initiated Users</s:a>
					<s:a href="#">Approved Users</s:a>
					<s:a href="#">Rejected Users</s:a>
					<s:a href="#">All Users</s:a>
				</li>
			</UL>
		--%></LI>
		
		<LI>
			<%--<A href="#" class="ImgtxtUp">Employee </A>
			<UL>
				<li>
					<s:a href="#">subMenu1</s:a>
					<s:a href="#">subMenu2</s:a>
					<s:a href="#">subMenu3</s:a>
				</li>
			</UL>
		--%></LI>
		
		<LI>
			<A href="#" class="ImgtxtUp">Manage </A>	
			<UL>
				<li>
					<s:a href="loadAllCities">City</s:a>
					<s:a href="loadAllCitiesAreas">City Area</s:a>
				</li>
			</UL>	
		</LI>
		<LI>
			<A href="showAllTask" class="ImgtxtUp">Task </A>	
				<UL>
				<li>
					<s:a href="taskNamesForResponses">Task Responses</s:a>
					<s:a href="showAllTask">Manage Task</s:a>
				</li>
			</UL>	
		</LI>
		
		<LI>
			<A href="getAllFinishedFeedbackByUser" class="ImgtxtUp">Feedback </A>		
			<UL>
				<li>
					<s:a href="getAllFinishedFeedbackByUser">Feedback By User</s:a>
					<s:a href="getAllApprovedFeedbackByAdmin">Approved By Admin</s:a>
					<s:a href="getAllRejectedFeedbackByAdmin">Rejected By Admin</s:a>
					<s:a href="getAllReassignedFeedbackByClient">Reassigned By Client</s:a>
				</li>
			</UL>	
		</LI>
		
		<LI>
			<A href="showAdminProfile" class="ImgtxtUp">Profile </A>
			
		</LI>
		
		<LI>
			<A href="showAllRewards" class="ImgtxtUp">Reward Points </A>
			
			<UL>
				<li>
					<s:a href="showClientRewardPoints">Client Points link</s:a>
					<s:a href="showAllRewards">User RewardPoints link</s:a>					
				</li>
			</UL>
		</LI>
		
		<LI>
			<A href="#" class="ImgtxtUp">Reports</A>
			<UL>
				<li>
					<s:a href="feedbackReport">Feedbacks</s:a>
					<%--<s:a href="taskReport">Tasks</s:a>
					--%><%--<s:a href="usersReport">Users</s:a>
					--%><s:a href="activityReport">Activities</s:a>
					<%--<s:a href="rewardsReport">Rewards</s:a>
				--%></li>
			</UL>
		</LI>
		
	</UL>
	
	<UL class=nav id=nav style="float: right;">
		<li>
			<s:a href="logout">LogOut</s:a>
		</li>
	</UL>
</div>
