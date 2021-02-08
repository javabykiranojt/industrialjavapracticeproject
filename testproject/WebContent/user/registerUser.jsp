<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags"%>
<head>

	<%--Library for jQuery ajax--%>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js" /></script>

	<script type="text/javascript">
	function goBack()
	  {
	  window.history.back()
	  }
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_user").validationEngine()
		});
		
	//JQuery Ajax code
	function differentLocationAreas(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas.action?location.locId="+locId;
		$("#locationAreaDropdown").load(url);
	}
	
		$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
	</script>

</head>

<div class="content">
	<h3>
		<s:text name="user.Signup"></s:text>
	</h3>

	<s:form method="post" enctype="multipart/form-data" id="add_user"
		name="add_user" action="registerNewUserInput">
		<font color="red"> <s:iterator value="addFieldError">*
			<s:property />
				<br>
			</s:iterator> </font>
		<table width="680" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="113" class="title">
					First Name
				</td>
				<td width="567" class="detail">
					<s:textfield name="userDetail.firstName"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Last name
				</td>
				<td class="detail">
					<s:textfield name="userDetail.lastName"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Contact No
				</td>
				<td class="detail">
					<s:textfield name="userDetail.contactNo"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Email Id
				</td>
				<td class="detail">
					<s:textfield name="userDetail.emailId"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Gender
				</td>
				<td class="detail">
					<s:select headerKey="-1" headerValue="Select Gender"
						list="#{'M':'Male', 'F':'Female'}" name="userDetail.gender"
						theme="simple" />
				</td>
			</tr>
			<tr>
				<td class="title">
					Location
				</td>
				<td class="detail">
					<s:select list="locationList" id="locationDropdown" name="locId"
						listKey="locId" listValue="city" headerKey="0"
						headerValue="-Select-"
						onchange="differentLocationAreas(this.value)" theme="simple" />


				</td>
			</tr>
			<tr>
				<td class="title">
					Area
				</td>
				<td class="detail">
					<s:select list="locationAreaList" id="locationAreaDropdown"
						name="locationArea.areaId" listKey="" listValue="" headerKey="0"
						headerValue="-Select-" theme="simple"></s:select>
				</td>
			</tr>
			<tr>
				<td class="title">
					Aadhar Card
				</td>
				<td class="detail">
					<s:file name="adharCard" theme="simple"></s:file>

				</td>
			</tr>
			<tr>
				<td class="title">
					Pan Card
				</td>
				<td class="detail">
					<s:file name="panCard" theme="simple"></s:file>

				</td>
			</tr>
			<tr>
				<td class="title">
					Driving License
				</td>
				<td class="detail">
					<s:file name="DrivingLicence" theme="simple"></s:file>

				</td>
			</tr>
			<tr>
				<td class="title">
					Any other Additional document
				</td>
				<td class="detail">
					<s:file name="otherDoc" theme="simple"></s:file>
				</td>
			</tr>
			<tr>
				<td class="title">
					Any other Additional document
				</td>
				<td class="detail">
					<s:file name="otherDocIfAny" theme="simple"></s:file>
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
			<tr>
				<td class="title">
					<input type="submit" value="Submit" />
				</td>
				<td class="detail">
					<input type="button" value="Back" onclick="goBack()" />
				</td>
			</tr>


			<%--<s:submit value="Submit" align="center" action="registerNewUserInput"  theme="simple"/>
			<s:submit value="Back" align="center" action="#"  theme="simple"/>
		--%>
		</table>

	</s:form>
</div>
