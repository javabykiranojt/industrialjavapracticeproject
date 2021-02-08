<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tld/jmesa.tld" prefix="jmesa"%>
<html>
	<head>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>

		<script type="text/javascript">
				
		function acceptPublishedTasks(){
		var taskId=$("#taskId").val();
	
	if(taskId==null || taskId=="")
	{
	alert("Please select an option");
	return;
	}
		document.taskForm.action="<%=request.getContextPath()%>/acceptPubTaskByUser.action?task.taskId="+$("#taskId").val();	 
	}
		
		function rejectPublishedTasks(){
		var taskId=$("#taskId").val();
	
	if(taskId==null || taskId=="")
	{
	alert("Please select an option");
	return;
	}
	document.taskForm.action="<%=request.getContextPath()%>/rejectPubTask.action?task.taskId="+$("#taskId").val();	 
<%--	popUpWithServerData("<%=request.getContextPath()%>/rejectPubTask.action?tasks.taskId="+$("#taskId").val(),"Update Task",450,650);
--%>
	}

function giveFeedback(){
	var taskId=$("#taskId").val();
	//alert('taskId='+taskId);	
	popUpWithServerData("<%=request.getContextPath()%>/deleteTask.action?task.taskId="+$("#taskId").val(),"Delete Task",450,650);
}
</script>
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                //alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadTask.action?' + parameterString;
            }
            function setId(id){
            	$("#taskId").val(id.value);
            }
        </script>
	</head>
	<body>
		<s:form name="taskForm" theme="simple">
			<jmesa:tableModel id="tag" items="${tasksList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="taskId" title="Select" filterable="false">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.taskId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="clients.clientName"  title="Client Name" filterable="false"/>
						<jmesa:htmlColumn property="userDetail"  title="User Name" filterable="false"/>
						<jmesa:htmlColumn property="location.city" title="City" filterable="false"/>
						<jmesa:htmlColumn property="clients.clientDetails.locationArea.areaName" title="Area" filterable="false"/>
						<jmesa:htmlColumn property="scheduleDate" pattern="dd/MM/yyyy"
							title="Schedule Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
						<jmesa:htmlColumn property="expiryDate" pattern="dd/MM/yyyy"
							title="Expiry Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
						<jmesa:htmlColumn property="time" title="Time" filterable="false"/>
						<jmesa:htmlColumn property="venue" title="Venue Details" filterable="false"/>
						<jmesa:htmlColumn property="statusLabel" title="Status" filterable="false"/>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<s:hidden name="tasks.taskId" id="taskId" theme="simple" />
			<s:if test="%{#session.usersType != 'AE'}">
				<s:submit theme="simple" onclick="acceptPublishedTasks()" value="I am ready to Perform Task"></s:submit>
				<s:submit theme="simple" onclick="rejectPublishedTasks()"  value="I am Not Interested to Perform Task"></s:submit>
			</s:if>
			<s:else>
				<s:submit theme="simple" action="finalAsignTaskToUser" value="Click To Select One User For This Task"></s:submit>
			</s:else>
		</s:form>


