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

function approveFeedback(){
	var feedbackId=$("#feedbackId").val();
	if(!isExist(feedbackId)){
		return false;
	}		
	popUpWithServerData("<%=request.getContextPath()%>/approveFeedback.action?userFeedback.feedbackId="+$("#feedbackId").val(),"Feedback",350,450);

}
function feedbackDocuments(feedbackId)
  	{
  	 popUpWithServerData("<%=request.getContextPath()%>/generateFeedbackReport.action?feedbackId="+feedbackId,"Feedback Documents",150,400);  
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
                location.href = '<%=request.getContextPath()%>/loadTask.action?' + parameterString;
            }
            function setId(id){
            	$("#feedbackId").val(id.value);
            }
        </script>
	</head>
	<body>
	
			<h3 style="color: #fff"><s:text name="pageTitle"></s:text></h3>
		<form name="taskForm">
			<jmesa:tableModel id="tag" items="${userFeedbackList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="feedbackId" title="Select" filterable="false">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.feedbackId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="tasks.taskUniqueName" title="Task Id" filterable="true"/>
						<jmesa:htmlColumn property="userDetail.firstName" title="Agent Name" filterable="false"/>
						<jmesa:htmlColumn property="tasks.venue" title="Task Venue" filterable="false"/>
						<jmesa:htmlColumn property="clients.userDetail.firstName" title="Client Name" filterable="true"/>
						<jmesa:htmlColumn property="taskAssignProof" title="Proof" filterable="false" />
						<jmesa:htmlColumn property="feedbackDetails" title="Details" filterable="false"/>
						
						<jmesa:htmlColumn title="Documents" filterable="false">
							<a href="javascript:void(0);" onclick="feedbackDocuments(${bean.feedbackId});">Documents</a>
						</jmesa:htmlColumn>
						
						<jmesa:htmlColumn property="statusLabel" title="Status of task" filterable="false"/>
						<jmesa:htmlColumn property="createdUIdName" title="Task Assigned by" filterable="true"/>
						<jmesa:htmlColumn property="createdDtm" title="Assigned on " filterable="false"/>
						<%--<jmesa:htmlColumn property="modifiedUIdName" title="Task Modified by" filterable="false"/>
						<jmesa:htmlColumn property="modifiedDtm" title="Modified on " filterable="false"/>
						--%><jmesa:htmlColumn property="sentStatus" title="Reported to Client " filterable="false"/>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<input type="button" onclick="return approveFeedback()"
				value="Approve Feedback" />
		<s:hidden name="userFeedback.feedbackId" id="feedbackId" theme="simple" />
		</form>
	</body>
</html>


