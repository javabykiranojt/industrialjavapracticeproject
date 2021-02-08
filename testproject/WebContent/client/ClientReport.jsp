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
		function differentFeedback(){
	        var monthId = document.clientForm.noMonth.value;
	        document.clientForm.action="feedbackReportMonths.action";
			document.clientForm.submit();
	        document.getElementById("noMonth").selectedIndex = 0;
        }
        function feedbackDocuments(feedbackId)
		   	{
		   	 popUpWithServerData("<%=request.getContextPath()%>/generateFeedbackReport.action?feedbackId="+feedbackId,"Feedback Documents",250,400);  
		   	}
</script>
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                location.href = '/DMALL/loadClient.action?' + parameterString;
            }
            function setId(id){
            	$("#feedbackId").val(id.value);
            }
        </script>
        
       <script type="text/javascript">
		function reassignFeedback(){

		var feedbackId=$("#feedbackId").val();
		if(feedbackId==null || feedbackId=="")
		{
		alert("Please select an option");
			return;
		}
		//alert('locationId='+locationId);	
		popUpWithServerData("<%=request.getContextPath()%>/addFeedbackByClient.action?userFeedback.feedbackId="+$("#feedbackId").val(), "Reassign Feedback",350,600);
	
		} 
</script>
        
	</head>
	<body>
		<form name="clientForm" method="post">
		<s:select label="Select Months" headerKey="-1" headerValue="Months"
				list="#{'2':'2','3':'3','6':'6','12':'12'}" name="noMonth" id="noMonth"/>
				<input type="button" value="Go"  onclick="javascript: differentFeedback()"/>
				
			<jmesa:tableModel id="tag" items="${userFeedbackList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="feedbackId" title="Select">
							<input type="radio" name="productIdRadio" id="productIdRadio"
								value="${bean.feedbackId}" onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="tasks.taskUniqueName" title="Task Name" />
						<jmesa:htmlColumn property="sentDtm" title="Date" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" />
						<jmesa:htmlColumn property="feedbackDetails" title="Feedback" />
						<jmesa:htmlColumn property="taskAssignProof" title="Additional Feedback" />
						<jmesa:htmlColumn title="Documents" ><a href="javascript:void(0);" onclick="feedbackDocuments(${bean.feedbackId});">Documents</a></jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<input type="button" onclick="reassignFeedback()" value="Reassign" />
			<s:hidden name="userFeedback.feedbackId" id="feedbackId" theme="simple" />
		</form>
	</body>
</html>


