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
function acceptTasks(){
	var taskId=$("#taskId").val();
	popUpWithServerData("<%=request.getContextPath()%>/acceptTaskByUser.action?tasks.taskId="+$("#taskId").val(),"Accept Task",300,450);
}
function rejectTasks(){
	var taskId=$("#taskId").val();
	popUpWithServerData("<%=request.getContextPath()%>/rejectTask.action?tasks.taskId="+$("#taskId").val(),"Update Task",450,650);
}

function giveFeedback(){
	var taskId=$("#taskId").val();
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
               // alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadTask.action?' + parameterString;
            }
            function setId(id){
            	$("#taskId").val(id.value);
            }
        </script>
	</head>
	<body>
		<form name="taskForm">
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
						<jmesa:htmlColumn property="userDetail.firstName"  title="User Name" filterable="false"/>
						<jmesa:htmlColumn property="location.city" title="City" filterable="false"/>
						<jmesa:htmlColumn property="clients.clientDetails.locationArea.areaName" title="Area" filterable="false"/>
						<jmesa:htmlColumn property="scheduleDate" pattern="dd/MM/yyyy"
							title="Schedule Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
						<jmesa:htmlColumn property="time" title="Time" filterable="false"/>
						<jmesa:htmlColumn property="venue" title="Venue Details" filterable="false"/>
						<jmesa:htmlColumn property="statusLabel" title="Status" filterable="false"/>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<%--<input type="button" onclick="acceptTasks()" value="Accept Task"  />
			<input type="button" onclick="rejectTasks()" value="Reject Task" />
			<input type="button" onclick="giveFeedback()" value="Give Feedback" />
			
			--%><s:hidden name="tasks.taskId" id="taskId" theme="simple" />
		</form>
	</body>
</html>


