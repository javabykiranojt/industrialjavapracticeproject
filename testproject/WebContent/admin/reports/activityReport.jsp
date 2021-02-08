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


		<!-- For jmessa sorting and filtering -->
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '<%=request.getContextPath()%>/searchHistory.action?' + parameterString;
            }
            function setId(id){
            	$("#taskId").val(id.value);
            	$("#userEmpId1").val(id.value);
    			$("#uEId").val(id.value);
            }
        </script>
	</head>
	<body>
		<h2 style="color: #ffff">
			<s:text name="pagesTitle"></s:text>
		</h2>
		<s:if test="hasActionMessages()">
			<div class="infoHolder">
				<s:actionmessage />
			</div>
		</s:if>
		<s:if test="hasActionErrors()">
			<div class="errorHolder,dynErrorHolder">
				<s:actionerror />
			</div>
		</s:if>

		<form name="taskForm" action="activityReport">
			<s:if test="#session.usersType != 'UA'">
				<s:select list="historyUidList" label="Activity"
					headerValue="-Select activity for-" headerKey="0" name="Activity"
					theme="simple" listKey="userEmpId"
					listValue="%{firstName+ '  ' +lastName}" />
				<s:submit value="go" action="searchHistory" theme="simple"></s:submit>
			</s:if>
			<jmesa:tableModel id="tag" items="${allActivityList}" maxRows="8"
				maxRowsIncrements="4,8,12" stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="date" title="Date - Time" />
						<jmesa:htmlColumn property="activityDetails" title="Activity"></jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel>
			<br>

			<jmesa:tableModel id="tag" items="${allActivityListuserdetail}"
				maxRows="8" maxRowsIncrements="4,8,12" stateAttr="restore"
				var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="date" title="Date - Time" />
						<jmesa:htmlColumn property="activityDetails"
							title="Activity for UserDetail"></jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel>

			<br>


		</form>
	</body>
</html>


