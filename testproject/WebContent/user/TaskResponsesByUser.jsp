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
            function setUniqueName(taskUniqueName){
            	$("#taskUniqueName").val(taskUniqueName);
            }
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
	<h3>All Task responses from users</h3>
		<s:form name="taskForm" theme="simple" action="finalAsignTaskToUser">
			<jmesa:tableModel id="tag" items="${tasksList}" maxRows="8"
				maxRowsIncrements="4,8,12" stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="taskId" title="Select">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.taskId}"
								onclick="setId(this);setUniqueName('${bean.taskUniqueName}');" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="userDetail" title="User Name" />
						<jmesa:htmlColumn property="userDetail.contactNo"
							title="Contact No" />
						<jmesa:htmlColumn property="userDetail.locationArea.areaName"
							title="Users Area" />
						<jmesa:htmlColumn property="userDetail.emailId"
							title="Users Email Id" />
						<jmesa:htmlColumn property="location.city" title="Task City" />
						<jmesa:htmlColumn
							property="clients.clientDetails.locationArea.areaName"
							title=" Task Area" />
						<jmesa:htmlColumn property="clients.clientName"
							title="Client Name" />
						<jmesa:htmlColumn property="scheduleDate" pattern="dd/MM/yyyy"
							title="Schedule Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor"
							filterable="false" />
						<jmesa:htmlColumn property="expiryDate" pattern="dd/MM/yyyy"
							title="Expiry Date"
							cellEditor="org.jmesa.view.editor.DateCellEditor"
							filterable="false" />
						<jmesa:htmlColumn property="time" title="Time" />
						<jmesa:htmlColumn property="venue" title="Venue Details" />
						<jmesa:htmlColumn property="statusLabel" title="Status" />
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel>
			<br>
			<s:hidden name="tasks.taskId" id="taskId" theme="simple" />
			<s:hidden name="tasks.taskUniqueName" id="taskUniqueName"
				theme="simple" />
							<s:hidden name="taskUniqueName" id="taskUniqueName_id"
				theme="simple" />
			<s:if test="!hasActionMessages()">
				<s:submit value="Click To Select One User For This Task" ></s:submit>
			</s:if>
			<s:else>
				<input type="button" value="Close" onclick="self.parent.location.reload()"/>
			</s:else>
		</s:form>