<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<%--Library for jQuery ajax--%>
		<script>
		
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllClient.action?navigationFlag=clientAdded';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_client").validationEngine()
		});

		//ajax
	function differentLocationAreas(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas1.action?location.locId="+locId;
		$("#locationAreaDropdown").load(url);
	}
	function differentLocationAreas2(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas1.action?location.locId="+locId;
		$("#locationAreaDropdown2").load(url);
	}
		
	
		$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
	</head>
	<body>
		<s:if test="hasActionMessages()">
			<div class="infoHolder">
				<s:actionmessage />
			</div>
		</s:if>
		<s:if test="hasActionErrors()">
			<div class="errorHolder, dynErrorHolder">
				<s:actionerror />
			</div>
		</s:if>
		<s:form id="add_client" name="add_client" cssClass="formular"
			method="post" enctype="multipart/form-data">



			<%--DATA GETTING ADDED IN CLIENT  TABLE --%>
			<table>

				<H1>
					<font color="grey">ADD CLIENTS AND STORE DETAILS</font>
				</H1>

				<s:textfield label=" Store Name" name="clients.clientName"
					cssClass="required,validate[length[1,20]]" />
				<s:textfield label="Store Owners First Name"
					name="clientDetails.cltFirstName"
					cssClass="required,validate[length[1,20]]" />
				<s:textfield label="Store Owners Last Name"
					name="clientDetails.cltLastName"
					cssClass="required,validate[length[1,20]]" />

				<s:select label="Store Status (Incative Stores will not display)"
					list="#{'ACTIVE':'Active', 'INACTIVE':'Inactive'}"
					name="clientDetails.cltStatus" />

				<%--DATA GETTING ADDED IN CLIENT DETAIL  TABLE --%>


				<s:textfield label="Store Contact Number "
					name="clientDetails.cltContactNo"
					cssClass="required,validate[length[1,20]]" />
				<s:textfield label="Store Email-Id" name="clientDetails.cltEmail"
					cssClass="validate[required,custom[email]]" />

				<s:select list="locationList" id="locationDropdown" listKey="locId"
					listValue="city" label="Store Location" name="locId" headerKey=""
					headerValue="-Select-"
					onchange="differentLocationAreas(this.value)" cssClass="validate[required]"/>

				<s:select list="locationAreaList" id="locationAreaDropdown"
					label="Store Area" name="area1" listKey="areaId" listValue="areaName"
					headerKey="" headerValue="" cssClass="validate[required]"></s:select>


				<s:textfield label="Client Name" name="userDetail.firstName"
					cssClass="required,validate[length[1,20]]" />

				<s:textfield label="Client  Last Name" name="userDetail.lastName"
					cssClass="required,validate[length[1,20]]" />

				<s:hidden name="userDetail.gender" value="M/S"></s:hidden>
				
				<s:select list="locationList" id="locationDropdown2" listKey="locId"
					listValue="city" label="Location" name="locId1" headerKey=""
					headerValue="-Select-"
					onchange="differentLocationAreas2(this.value)" cssClass="validate[required]"/>

				<s:select list="locationAreaList" id="locationAreaDropdown2"
					label="Area" name="area2" listKey="areaId" listValue="areaName" cssClass="validate[required]"></s:select>
				<s:file name="offlineFeedback" label="Upload Offline Feedback Doc " ></s:file>

				<s:textfield label="Client Contact Number"
					name="userDetail.contactNo"
					cssClass="required,validate[length[1,13]]" />

				<s:textfield label="Client Email" name="userDetail.emailId"
					cssClass="validate[required,custom[email]]" />
			</table>
			<tr align="left">
				<td>
					<s:submit value="ADD Client and Store" action="addClientInput"
						theme="simple" />
				</td>
			</tr>
			<s:hidden name="navigationFlag" value="navigationFlag"></s:hidden>

		</s:form>
	</body>
</html>
