<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>

		<script>
	
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showClientRewardPoints';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#edit_taskPoints").validationEngine()
		});
	
$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
		
		
	</head>
	<body>
		<s:form id="edit_taskPoints">
				<h2>Edit Task Points for <s:property value="taskClientPoints.clients.clientName" /></h2>
				<s:hidden name="taskClientPoints.taskPointId"></s:hidden>
				Client name : <s:property value="taskClientPoints.clients.clientName" /> <br>
				<s:textfield label="Enter Points" name="taskClientPoints.taskPoints" 
				cssClass="required,validate[length[1,5]]" />
				<s:submit value="UPDATE" action="updateTaskClientPoints"/>
				
				
		</s:form>
	</body>
</html>
