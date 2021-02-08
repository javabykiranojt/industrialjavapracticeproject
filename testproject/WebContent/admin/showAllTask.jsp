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
			<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js" /></script>
		<script type="text/javascript">
function addTasks(){
	popUpWithServerData('<%=request.getContextPath()%>/addTaskInputPage.action', "Add Task",550,700);
}
function updateTasks(){
		var taskId=$("#taskId").val();
		 if(!isExist(taskId)){
	 	 	return false;
	 	 } 
		popUpWithServerData("<%=request.getContextPath()%>/updateTaskInputPage.action?tasks.taskId="+$("#taskId").val(),"Update Task",450,650);
}

function deleteTasks(){

	var taskId=$("#taskId").val();
	if(!isExist(taskId)){
	 	 	return false;
	 	 } 
	popUpWithServerData("<%=request.getContextPath()%>/deleteTask.action?tasks.taskId="+$("#taskId").val(),"Delete Task",300,400);

}
$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
		<script type="text/javascript">
            function onInvokeAction(id) {
           
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                //alert("parameterString="+parameterString);
                location.href = '<%=request.getContextPath()%>/showAllTask.action?'+ parameterString;

            }
            function setId(id){
            	$("#taskId").val(id.value);
            }
        </script>
	</head>
	<body>

		<h3 style="color:#fff"><s:text name="pageTitle"></s:text></h3>
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

		<form name="taskForm">
			<jmesa:tableModel id="tag" items="${tasksList}" maxRows="4"
				 maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>

						<jmesa:htmlColumn property="taskId" title="Select"
							filterable="false">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.taskId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="clients.clientName"
							title="Client Name" filterable="false" />
						<jmesa:htmlColumn property="taskUniqueName"
							title="Task Id" filterable="false" filterable="true" />
						<jmesa:htmlColumn property="userDetail" title="User Name"
							filterable="false" />
						<jmesa:htmlColumn property="location.city" title="City"
							filterable="false" />
						<jmesa:htmlColumn
							property="clients.clientDetails.locationArea.areaName"
							title="Area" filterable="false" />
						<jmesa:htmlColumn property="scheduleDate" pattern="dd/MM/yyyy"
							title="Schedule Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor"
							filterable="false" />
						<jmesa:htmlColumn property="expiryDate" pattern="dd/MM/yyyy"
							title="Expiry Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor"
							filterable="false" />
						<jmesa:htmlColumn property="time" title="Time" filterable="false" />
						<jmesa:htmlColumn property="venue" title="Venue Details"
							filterable="false" />
						<jmesa:htmlColumn property="createdDtm" pattern="dd/MM/yyyy"
							title="Task Created Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor"
							filterable="false" />
						<jmesa:htmlColumn property="statusLabel" title="Status" />
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<input type="button" onclick="addTasks()" value="Add Task" />
			<input type="button" onclick="return updateTasks()" value="Edit Task" />
			<input type="button" onclick="return deleteTasks()" value="Delete" />
			<s:hidden name="tasks.taskId" id="taskId" theme="simple" />
			<s:actionmessage />
		</form>
	</body>
</html>


