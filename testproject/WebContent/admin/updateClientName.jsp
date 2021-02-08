<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllClient.action';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#update_client").validationEngine()
		});
function differentLocationAreas2(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas1.action?location.locId="+locId;
		$("#locationAreaDropdown2").load(url);
	}
</script>
	</head>
	<body>
	<h2><s:text name="pageTitle"></s:text></h2>
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
	
		<s:form id="update_client" name="update_client"
			cssClass="formular" method="post">
			
		<s:textfield label="Client Name" name="userDetail.firstName"
				cssClass="required,validate[length[1,20]]" />
		
		<s:textfield label="Client LastName" name="userDetail.lastName"
				cssClass="required,validate[length[1,20]]" />
		<s:textfield label="Client Contact Number" name="userDetail.contactNo"
				cssClass="required,validate[length[1,20]]" />
		<s:textfield label="Client Email" name="userDetail.emailId"
				cssClass="validate[required,custom[email]]" />
					<s:hidden name="userDetail.gender" value="M/S"></s:hidden>
						<s:select list="locationList" id="locationDropdown2" listKey="locId"
					listValue="city" label="Location" name="userDetail.location.locId" headerKey=""
					headerValue="-Select-"
					onchange="differentLocationAreas2(this.value)" cssClass="validate[required]"/>

				<s:select list="locationAreaList" id="locationAreaDropdown2"
					label="Area" name="userDetail.locationArea.areaId" listKey="areaId" listValue="areaName" cssClass="validate[required]"></s:select>
	
		<s:hidden name="userDetail.userEmpId" id="clientId"></s:hidden>
		
						
			<s:submit value="update" action="updateClientInput" />
		</s:form>
	</body>
	</html>