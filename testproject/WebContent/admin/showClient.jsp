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
		function viewStore(){
		var userDetailId=$("#userDetail").val();
	if(userDetailId==null || userDetailId=="")
	{
	alert("Please select an option");
	return;
	}
	popUpWithServerData("<%=request.getContextPath()%>/viewStoreClient.action?userDetail.userEmpId="+$("#userDetail").val(),"View Store",550,600);
	}
		function addClients(){

	popUpWithServerData("<%=request.getContextPath()%>/addNewClient.action", "Add Client",600,700);

}
function updateClients(){

	   $(".ui-dialog-titlebar").hide()     

	var userDetailId=$("#userDetail").val();
	
	
	if(userDetailId == null || userDetailId == "")
	{
	 alert("Please select an option");
	return;
	}
		//alert('userDetailId='+userDetailId);
	//alert('update');
	popUpWithServerData("<%=request.getContextPath()%>/updateClient.action?userDetail.userEmpId="+$("#userDetail").val(),"Update Client",450,550);


}

function deleteClients(){

	var userDetailId=$("#userDetail").val();
	if(userDetailId==null || userDetailId=="")
	{
	alert("Please select an option");
	return;
	}
	//alert('userDetailId='+userDetailId);	
	popUpWithServerData("<%=request.getContextPath()%>/deleteClient.action?userDetail.userEmpId="+$("#userDetail").val(),"Delete Client",500,500);


}
function generateClient()
{
	var userDetailId=$("#userDetail").val();
	if(userDetailId==null || userDetailId=="")
	{
	alert("Please select an option");
	return;
	}
	//alert('userDetailId='+userDetailId);	
	popUpWithServerData("<%=request.getContextPath()%>/generateCredential.action?userDetail.userEmpId="+$("#userDetail").val(),"Client credentials",450,550);

}

	function addStore(){

	   

	var userDetailId=$("#userDetail").val();
	var userDetailName=$("#fname").val();
	
	
	if(userDetailId == null || userDetailId == "")
	{
	 alert("Please select an option");
	return;
	}
		//alert('userDetailId='+userDetailId);
		//alert('userDetailName='+userDetailName);
	//alert('addStore');
	popUpWithServerData("<%=request.getContextPath()%>/addStore.action?userDetail.userEmpId="+$("#userDetail").val(),"Add Store",450,550);


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
            	$("#userDetail").val(id.value);
            	          }
             
        </script>
	</head>
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
	<body>
		<h3 style="color:#fff">
			<s:text name="pageTitle"></s:text>
		</h3>
		<form name="clientForm">
			<jmesa:tableModel id="tag" items="${userDetailList}" maxRows="8"
				maxRowsIncrements="4,8,12" stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="userDetail" title="Select">
							<input type="radio" name="userDetail.userEmpId"
								id="userDetail.userEmpId" value="${bean.userEmpId}"
								onclick="setId(this);" />
						</jmesa:htmlColumn>
						<jmesa:htmlColumn property="firstName" title="Firstname" />
						<jmesa:htmlColumn property="lastName" title="LastName" />
						<jmesa:htmlColumn property="gender" title="Gender" />
						<jmesa:htmlColumn property="location.city" title="Location" />
						<jmesa:htmlColumn property="locationArea.areaName" title="Address" />
						<jmesa:htmlColumn property="contactNo" title="Contact No" />
						<jmesa:htmlColumn property="emailId" title="Email Id"
							sortable="true" filterable="true" />
						<jmesa:htmlColumn property="status" title="Status" />
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel>
			<br>
			<input type="button" onclick="addClients()" value="Add" />
			<input type="button" onclick="addStore()" value="Add Store" />
			<input type="button" onclick="viewStore()" value="View Store">
			<input type="button" onclick="updateClients()" value="Edit" />
			<input type="button" onclick="deleteClients()" value="Delete" />
			<input type="button" onclick="generateClient()" value="Credential" />
			<s:hidden name="userDetail.userEmpId" id="userDetail" theme="simple" />

		</form>
	</body>
</html>


