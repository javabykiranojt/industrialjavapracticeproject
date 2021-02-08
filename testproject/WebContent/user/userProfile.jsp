<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>

<script type="text/javascript">
	function editMyProfile()
	{
		popUpWithServerData("<%=request.getContextPath()%>/editUserProfile.action","Update Profile",450,650);	
	}
	
	function changeMyPassword()
	{
		popUpWithServerData("<%=request.getContextPath()%>/changeUserPassword.action","Change Password",350,450);
	}
	</script>
<div class="content">
	<h3>
		My Profile
	</h3>

	<table>
		<tr>
			<s:form method="post">
				<table width="680" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="113" class="title">
							Username
						</td>
						<td width="567" class="detail">
							<s:property value="users.uname" />
						</td>
					</tr>
					<tr>
						<td class="title">
							Password
						</td>
						<td class="detail">
							<s:property value="users.upass" />
						</td>
					</tr>
					<tr>
						<td class="title">
							First Name
						</td>
						<td class="detail">
							<s:property value="userDetail.firstName" />
						</td>
					</tr>
					<tr>
						<td class="title">
							Last Name
						</td>
						<td class="detail">
							<s:property value="userDetail.lastName" />
						</td>
					</tr>
					<tr>
						<td class="title">
							City
						</td>
						<td class="detail">
							<s:property value="userDetail.location.city" />
						</td>
					</tr>
					<tr>
						<td class="title">
							Address
						</td>
						<td class="detail">
							<s:property value="userDetail.locationArea.areaName" />
						</td>
					</tr>
					<tr>
						<td class="title">
							Contact No
						</td>
						<td class="detail">
							<s:property value="userDetail.contactNo" />
						</td>
					</tr>
					<tr>
						<td class="title">
							Email ID
						</td>
						<td class="detail">
							<s:property value="userDetail.emailId" />
						</td>
					</tr>
					<tr>
						<td class="title">
							&nbsp;
						</td>
						<td class="detail">
							&nbsp;
						</td>
					</tr>
				</table>
			</s:form>
		</tr>
		<tr>
			<input type="button" value="Edit" onclick="editMyProfile()" />
			<input type="button" value="Change Password"
				onclick="changeMyPassword()" />
		</tr>
	</table>
</div>
