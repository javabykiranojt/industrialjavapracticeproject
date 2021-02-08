<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>

		<script>
		 function checkdate()
	 {
	    //alert("Checking data 19");
	    var expiryDate=dojo.widget.byId("expiryDate").getValue();
	    var scheduleDate=dojo.widget.byId("scheduleDate").getValue();
	       if(expiryDate==''||scheduleDate==''){
    	         alert("Dates can not be blank.");
	             return false;
	       }
	       if(expiryDate<scheduleDate )
	       {
	             alert("Expiry date cannot be smaller than Scheduled date.");
	             return false;
	       }
	 }
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllTask.action';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#update_task").validationEngine();
		});
	
	//JQuery Ajax code
	function differentUsersAndClients(locId1){
		var url="<%=request.getContextPath()%>/differentClients.action?location.locId="+locId1;
		$("#clientDropdown").load(url);
		url="<%=request.getContextPath()%>/differentUsers.action?location.locId="+locId1;
		$("#userDropdown").load(url);
	}

</script>
		<sx:head />
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

		<s:form id="update_task" name="update_task" cssClass="formular"
			method="post">
			Task name : <s:property value="tasks.taskUniqueName"/>

			<s:select list="locationList" id="locId1" label="Select_City"
				listKey="locId" listValue="city" name="locationId" headerKey="0"
				value="tasks.location.locId" headerValue="-Select-"
				onchange="differentUsersAndClients(this.value)"></s:select>

			<s:select list="clientsList" id="clientDropdown"
				label="Select_Client_Store" listKey="clientId"
				listValue="clientName+' - '+clientDetails.locationArea.areaName"
				name="frmClientsId" headerKey="0" value="tasks.clients.clientId"
				headerValue="-Select-"></s:select>
<s:hidden name="tasks.userDetail.userEmpId"></s:hidden>

<s:hidden name="tasks.taskUniqueName" readonly="true" label="Task Id"></s:hidden>
			<s:select list="userList" id="userDropdown" label="Select_User"
				listKey="userEmpId" disabled="true"
				listValue="firstName+' '+lastName+' - '+locationArea.areaName"
				name="userEmployeeId" headerKey="0"
				value="tasks.userDetail.userEmpId" headerValue="-Select-"></s:select>

			<sx:datetimepicker label="Select_ScheduleDate"
				name="tasks.scheduleDate" displayFormat="dd-MM-yyyy" required="true"
				id="scheduleDate" />

			<sx:datetimepicker label="Select_ExpiryDateDate"
				name="tasks.expiryDate" displayFormat="dd-MM-yyyy" required="true"
				id="expiryDate" />

			<s:textfield label="Select_Time" name="tasks.time"
				cssClass="required,validate[length[1,20]]" />

			<s:textarea label="Select_Venue" name="tasks.venue"
				cssClass="required,validate[length[1,1200]]" />

			<s:hidden name="tasks.status"></s:hidden>
			
			<s:if test="!LockedByFlg">
				<s:submit value="Update task" onclick="return checkdate();"
					action="updateTask"/>
			</s:if>
			<s:submit value="Close" onclick="self.parent.location.reload()"
				action="closeAndUnlockTask" />
			<s:hidden name="tasks.taskId" id="taskId"></s:hidden>

		</s:form>
	</body>
</html>
