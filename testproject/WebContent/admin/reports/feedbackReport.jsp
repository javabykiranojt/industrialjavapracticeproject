<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tld/jmesa.tld" prefix="jmesa"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
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
			var theGlobalResult;
			function feedbackDocuments(feedbackId)
		   	{
		   	 popUpWithServerData("<%=request.getContextPath()%>/generateFeedbackReport.action?feedbackId="+feedbackId,"Feedback Documents",150,400);  
		   	}
		   	function reviewDocuments(feedbackId)
		   	{
		   		//alert(feedbackId);
		   	 popUpWithServerData("<%=request.getContextPath()%>/reviewFeedbackReport.action?feedbackId="+feedbackId,"Feedback Documents",700,700);  
		   	}
			
			function emailFeedback(){
				var feedbackId=theGlobalResult;
				var id = getValues();
				//alert(id);
				if(!isExist(id)){
	 	 	return false;
	 	 } 
				popUpWithServerData("<%=request.getContextPath()%>/emailFeedback.action?feedbackString="+theGlobalResult,"Email Feedback",350,500);
			
			}
			
			function getValues(){
			var cbs = document.getElementsByName('feedbackId');
			var result ='';
			for(var i=0;i<cbs.length;i++){
				if(cbs[i].checked)
				result+=(result.length>0? "," : "")+cbs[i].value;
			}
				//alert(result);
				theGlobalResult = result;
				return theGlobalResult;
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
            	$("#feedbackId").val(id.value);
            	var result=getValues();
            	//alert(result);
            	theGlobalResult = result;
            }

        </script>
        
        <SCRIPT type="text/javascript">
        	//JQuery Ajax code
	function differentUsersAndClients(locId1){
		
		url="<%=request.getContextPath()%>/differentFeedbackReport.action?location.locId="+locId1;
		$("#userFeedbackDropDown").load(url);
	}
        function differentFeedback(){
        clientId = document.feedback_report.clientDropdown.value;
        document.feedback_report.action="feedbackReport.action";
		document.feedback_report.submit();
        document.getElementById("clientDropdown").selectedIndex = 0;
        }
       </SCRIPT>
        <sx:head />
	</head><body><h3 style="color: #fff"><s:text name="pagetTitle"></s:text></h3>
	
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
			<form name="feedback_report" method="post">
			<font color="white">Select Client Name </font><s:select list="clientsList" id="clientDropdown"  listKey="clientId"
				listValue="clientFullName" name="frmClientsId" headerKey="0"
				headerValue="-Select-"></s:select>	
				<input type="button" value="Go"  onclick="javascript: differentFeedback()"/>
				
			<jmesa:tableModel id="userFeedbackDropDown" items="${userFeedbackList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean" >
				<jmesa:htmlTable width="100% "  >
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="feedbackId" title="Select " filterable="false">
							<input type="checkbox" name="feedbackId" id="feedbackId"
								value="${bean.feedbackId}" onclick="getValues();" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn title="Review" filterable="false"><a href="javascript:void(0);" onclick="reviewDocuments(${bean.feedbackId});">Edit</a></jmesa:htmlColumn>
						<jmesa:htmlColumn property="tasks.taskUniqueName" title="Task Id" filterable="true"/>
						<jmesa:htmlColumn property="userDetail.firstName" title="Agent Name" filterable="true"/>
						<jmesa:htmlColumn property="tasks.venue" title="Task Venue" filterable="false"/>
						<jmesa:htmlColumn property="clients.userDetail.firstName" title="Client Name" filterable="false"/>
						<jmesa:htmlColumn property="taskAssignProof" title="Proof" filterable="false" />
						<jmesa:htmlColumn property="feedbackDetails" title="Details" filterable="false"/>
						<jmesa:htmlColumn property="taskUserStatus" title="Status of task" filterable="false"/>
						<jmesa:htmlColumn property="createdUIdName" title="Task Assigned by" filterable="false"/>
						<jmesa:htmlColumn property="createdDtm" title="Assigned on " filterable="false"/><%--
						<jmesa:htmlColumn property="modifiedUIdName" title="Task Modified by" filterable="false"/>
						<jmesa:htmlColumn property="modifiedDtm" title="Modified on " filterable="false"/>
						--%><jmesa:htmlColumn property="sentStatus" title="Reported to Client " filterable="false"/>
						<jmesa:htmlColumn property="sentDtm" title="Feedback Sent on " filterable="false"/>
						
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<s:hidden name="userFeedback.feedbackId" id="feedbackId" theme="simple" />
			<input type="button" onclick="return emailFeedback(); " value="Email Feedback" />
		</form>
		</body>
		</html>


