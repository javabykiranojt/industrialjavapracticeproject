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
		function addTaskClientPoints(){

	popUpWithServerData("<%=request.getContextPath()%>/addNewTaskClientPointsInput.action", "Add Task Client Points",350,600);

}
function updateClients(){

	var clientId=$("#taskPointId").val();
	//alert('taskPointId='+taskPointId);	
	//alert('update');
	popUpWithServerData("<%=request.getContextPath()%>/updateClient.action?clients.clientId="+$("#clientId").val(),"Update Client",450,550);

}

function editTaskClientPoints(){	
		var taskId=$("#taskPointId").val();
		 if(!isExist(taskId)){
	 	 	return false;
	 	 } 
	 	 	
	popUpWithServerData("<%=request.getContextPath()%>/editTaskClientPointsInput.action?taskPointId="+$("#taskPointId").val(),"Edit Task Client Point",350,450);

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
               // alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadClient.action?' + parameterString;
            }
            function setId(id){
            	$("#taskPointId").val(id.value);
            }
        </script>
	</head>
	<body>
		<h3 style="color: #fff"><s:text name="pageTitle"></s:text></h3>
		<form name="">
			<jmesa:tableModel id="tag" items="${clientPointsList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="taskPointId" title="Select">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.taskPointId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="clients.clientName" title="Client Name" />
						<jmesa:htmlColumn property="taskPoints" title="Task Points"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="createdDtm" title="Created Date"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="modifiedDtm" title="Modified Date"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="createdUIdName" title="Created By"></jmesa:htmlColumn>
						<jmesa:htmlColumn property="modifiedUIdName" title="Modifed By"></jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<input type="button" onclick="addTaskClientPoints()" value="Add Task Client Points" />
			<input type="button" onclick="return editTaskClientPoints()" value="Edit Task Client Point" />
			<s:hidden name="taskClientPoints.taskPointId" id="taskPointId" theme="simple" />
		</form>
	</body>
</html>


