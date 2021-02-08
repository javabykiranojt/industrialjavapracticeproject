<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<head>
	  <%--Library for jQuery ajax--%>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js"/></script>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		//alert('clientDetailId = ' +  + '<s:property value='userDetail.userEmpId'/>');
		window.parent.location.href='showAllClient.action?navigationFlag=storeAdded';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_store").validationEngine()
		});


			//ajax
	function differentLocationAreas(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas.action?location.locId="+locId;
		$("#locationAreaDropdown").load(url);
	}
	
	$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>
  
	</head>
	<body>
  			<h2><s:text name="pageTitle"></s:text></h2>
  <s:form id="add_store" name="add_store"
			cssClass="formular" method="post">
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
       <%--DATA GETTING ADDED IN CLIENT  TABLE --%>
	            
			<s:textfield label=" Store Name" name="clients.clientName"
						cssClass="required,validate[length[1,20]]"/>	
		<s:textfield label="Store Owners First Name" name="clientDetails.cltFirstName"
						cssClass="required,validate[length[1,20]]"/>	
		<s:textfield label="Store Owners Last Name" name="clientDetails.cltLastName"
						cssClass="required,validate[length[1,20]]"/>	
						
		<s:select label="Store Status (Incative Stores will not display)" 
				list="#{'ACTIVE':'Active', 'INACTIVE':'Inactive'}" name="clientDetails.cltStatus" />	
		
				<%--DATA GETTING ADDED IN CLIENT DETAIL  TABLE --%>
				
				
		<s:textfield label="Store Contact Number " name="clientDetails.cltContactNo"
				cssClass="required,validate[length[1,20]]" />
		<s:textfield label="Store Email-Id" name="clientDetails.cltEmail"
				cssClass="validate[required,custom[email]]" />
				
		<s:select list="locationList" id="locationDropdown" listKey="locId" listValue="city" label="Location" name="locId"
   				headerKey="0" headerValue="-Select-"
   				onchange="differentLocationAreas(this.value)"/>
   				
   		<s:select list="locationAreaList" id="locationAreaDropdown" label="Area" name="area1" 
   				listKey="" listValue="" 
   				headerKey="0" headerValue="-Select-"></s:select>
    
    
    	<s:hidden name="userDetail.userEmpId" id="clientId"></s:hidden>
    	
    	<s:submit value="Add Store" action="addStoreInput"   ></s:submit>
    
    </s:form>
    
    
</body>