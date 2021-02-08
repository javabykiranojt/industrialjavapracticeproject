<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		//alert('taskId = ' +  + '<s:property value='tasks.taskId'/>');
		window.parent.location.href='showAllTask.action';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_task").validationEngine()
		});
	$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
		<sx:head />
	</head>
	<body>
		<s:form id="add_task" name="add_task" cssClass="formular"
			method="post">
			<s:actionerror />
			<s:select name="frmClientsId" listKey="clientId" headerKey="0"
				headerValue="-select-" list="clientsList" listValue="clientName"
				label="Select_Client"></s:select>

			<s:select list="userList" label="Select_User" listKey="userEmpId"
				listValue="firstName" name="userEmployeeId" headerKey="0"
				headerValue="-Select-"></s:select>
			<s:select list="locationList" label="Select_Location" listKey="locId"
				listValue="city" name="locationId" headerKey="0"
				headerValue="-Select-"></s:select>

			<sx:datetimepicker label="Select_Date" name="tasks.date"
				displayFormat="dd-MM-yyyy" required="true" />
			<%--	<s:textfield label="Select_Time" name="tasks.time"
				cssClass="required,validate[length[1,20]]" />
		--%>
			<s:textfield label="Select_Venue" name="tasks.venue"
				cssClass="required,validate[length[1,20]]" />

			<s:submit value="ADD" action="addFeedback" />
			
//by kiran --follow these instructions for feedback addition <br>

-- Page will be coming from link given for each task. NO Add button<br> 
			
Tasks1 -- label<br>

location -- label<br>

person name - label<br>

Feedback -- Text Area<br>

date -- date picker<br>
 
time  -- terxtbox<br>

photos if any -- browse button for many uploads<br>

		</s:form>
	</body>
</html>
