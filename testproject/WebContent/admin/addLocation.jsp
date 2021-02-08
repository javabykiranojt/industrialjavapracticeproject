<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
	
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='loadAllCities';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_location").validationEngine()
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
		<s:form id="add_location" name="add_location" cssClass="formular" method="post">
		
		
		<%--<s:textfield label="Country" name="location.country" value="India"
				cssClass="required,validate[length[1,20]]" />
				
		--%><s:textfield label="State" name="location.state"
				cssClass="required,validate[length[1,20]]" />
				
		<s:textfield label="City" name="location.city"
				cssClass="required,validate[length[1,20]]" />
				
		<s:submit value="ADD" action="showAllLocation" />
		</s:form>
	</body>
