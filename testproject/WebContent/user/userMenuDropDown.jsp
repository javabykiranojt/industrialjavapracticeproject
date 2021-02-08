<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id=container>
	<UL class=nav id=nav>

		<LI>
			<A href="#" class="ImgtxtUp">Task </A>
			<UL>
				<li>
					<s:a href="getAllPublishedTask">Tasks To Accept</s:a>
					<s:a href="getAllInitiatedTask">Task For You</s:a>
					<s:a href="getAllFinishedTask">Your Finished Task</s:a>

				</li>
			</UL>
		</LI>

		<LI>
			<A href="getAllAcceptedTask" class="ImgtxtUp">Feedback </A>

		</LI>
		<%--<LI>
			<A href="activityReportUser" class="ImgtxtUp">Activities </A>
		</LI>
			--%><LI>
			<A href="getUserRewards" class="ImgtxtUp">My Reward Points</A>
		</LI>
		<LI>
			<A href="showUserProfile" class="ImgtxtUp">Profile </A>
		</LI>
	</UL>
	<UL class=nav id=nav style="float: right;">


		<li>
			<s:a href="logout">LogOut</s:a>
		</li>
	</UL>
</div>
