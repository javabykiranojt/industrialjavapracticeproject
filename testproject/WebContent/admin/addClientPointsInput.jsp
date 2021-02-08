<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>

		<script>
	
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showClientRewardPoints';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_taskPoints").validationEngine()
		});
	
	$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
		
		
	</head>
	<body>
		<s:form id="add_taskPoints" name="add_taskPoints" cssClass="formular"
			method="post">
			<s:actionerror />
			<s:select name="frmClientsId" listKey="clientId" headerKey="0"
				headerValue="-select-" list="clientsList" listValue="clientName"
				label="Select_Client"></s:select>
			
			<s:textfield label="Enter Points" name="taskClientPoints.taskPoints"
				cssClass="required,validate[length[1,5]]" />

			<s:submit value="ADD" action="addNewTaskClientPoints"/>
			<s:textfield name="location.locId" id="locId12"></s:textfield>
			<s:textfield name="userDetail.userEmpId" id="userEmployeeId"></s:textfield>

			
		</s:form>
	</body>
</html>
