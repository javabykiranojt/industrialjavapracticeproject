<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='loadAllCitiesAreas';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_locationArea").validationEngine()
		});
	$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
	
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
		
		
		<s:form id="add_locationArea" name="add_locationArea" cssClass="formular" method="post">
		
		<%--
		<s:textfield label="City" name="locationArea.location.city"
				cssClass="required,validate[length[1,20]]" />
		
		--%>
		<s:select list="locationList" id="locationDropdown" listKey="locId" listValue="city" label="Location" name="locationArea.location.locId"
   				headerKey="0" headerValue="-Select-"/>
		
		
		<s:textfield label="Area" name="locationArea.areaName"
				cssClass="required,validate[length[1,20]]" />
				
				
		<s:submit value="ADD" action="showAllLocationAreas" />
		</s:form>
	</body>
</html>