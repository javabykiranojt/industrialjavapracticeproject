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
   
   function approveUser()
    {
	   	var id=$("#userEmpId1").val();
	 	 if(!isExist(id)){
	 	 	return false;
	 	 } 
     if(confirm("Do you really want to Approve user")){
  		 document.taskForm.action="<%=request.getContextPath()%>/sendVerificationId.action?userEmpId="+$("#userEmpId1").val();
		 document.taskForm.submit();
	 }
   	}
   	function userDocuments(uid)
   	{
   	 popUpWithServerData("<%=request.getContextPath()%>/generateReport.action?userEmpId="+uid,"User Documents",150,400);  
   	}
    
    function activateUser()
    {
    	var id=$("#userEmpId1").val();
    	 if(!isExist(id)){
	 	 	return false;
	 	 } 
    document.taskForm.action="<%=request.getContextPath()%>/activateUser.action?userEmpId="+$("#userEmpId").val();
	document.taskForm.submit();
    } 
       
    function suspendUser()
    { 
		var id=$("#userEmpId1").val();
    		 if(!isExist(id)){
	 	 	return false;
	 	 } 
    	popUpWithServerData("<%=request.getContextPath()%>/suspendUserInput.action?uEId="+$("#uEId").val(),"Suspend User",150,400);
    }
    function changePassword()
    {
    		var id=$("#userEmpId1").val();
    		 if(!isExist(id)){
	 	 	return false;
	 	 } 
     	popUpWithServerData("<%=request.getContextPath()%>/changeUserPassByAdmin.action?uEId="+$("#uEId").val(),"Change Password",350,450);
    }
    function manageCredentials()
    {
    		var id=$("#userEmpId1").val();
    		 if(!isExist(id)){
	 	 	return false;
	 	 } 
    	popUpWithServerData("<%=request.getContextPath()%>/manageUserCredentialsByAdmin.action?uEId="+$("#uEId").val(),"Manage Credentials",350,450);
    }
    
    
  </script>
  
<!-- For jmessa sorting and filtering -->
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
              //  alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadTask.action?' + parameterString;
            }
            function setId(id){
            	$("#taskId").val(id.value);
            	$("#userEmpId1").val(id.value);
    			$("#uEId").val(id.value);
            }
        </script>
	</head>
	<body>
		<h3 style="color: #fff"><s:text name="pageTitle"></s:text></h3>
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
			<jmesa:tableModel id="tag" items="${allUsersList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="userEmpId" title="Select" filterable="false">
						<input type="radio" name="userEmpId" id="userEmpId" value="${bean.userEmpId}" onclick="setId(this)"/>
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="nameLabel"  title="Name"/>
						<jmesa:htmlColumn property="locationLabel" title="City Area" />
						<jmesa:htmlColumn property="contactNo" title="Contact No" />
						<%--
						<jmesa:htmlColumn property="emailId" title="Email Id" sortable="true" filterable="true"/>
						--%><jmesa:htmlColumn property="statusLabel" title="Status"/><%--
						<jmesa:htmlColumn property="gender" title="Gender" />
						--%><jmesa:htmlColumn property="users.uid" title="Documents" ><a href="javascript:void(0);" onclick="userDocuments(${bean.userEmpId});">Documents</a></jmesa:htmlColumn>
						<jmesa:htmlColumn property="createdDtm" title="Created on " pattern="dd/MM/yyyy"  cellEditor="org.jmesa.view.editor.DateCellEditor"/>
						<jmesa:htmlColumn property="createdUIdName" title="Created By" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" />
						<jmesa:htmlColumn property="modifiedDtm" title="Modified On" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" />
						<jmesa:htmlColumn property="modifiedUIdName" title="Modified By"/>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			<input type="button" onclick="return approveUser()" value="Approve" />
			<input type="button" onclick="return suspendUser()" value="Suspend" />
			<input type="button" onclick="return changePassword()" value="Change Passowrd" />
			<input type="button" onclick="return manageCredentials()" value="Manage Credentials" />
			<input type="button"  onclick="return activateUser()" value="Activate Directly"  />
			<s:hidden name="userDetail.userEmpId" id="userEmpId1" theme="simple" />
			<s:hidden name="uEId" id="uEId" theme="simple" />

		</form>
	</body>
</html>


