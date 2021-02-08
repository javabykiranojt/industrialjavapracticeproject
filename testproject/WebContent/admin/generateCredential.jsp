<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
		//alert('clientId = ' +  + '<s:property value='userDetail.userEmpId'/>');
		alert('Mail Sent Successfully...');
		window.parent.location.href='showAllClient.action';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#generateCredentialInput").validationEngine()
		});

</script>
	</head>
	<body><h2><s:text name="pageTitle"></s:text></h2> 
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
		<s:form id="generateCredentialInput" name="generateCredentialInput"
			cssClass="formular" method="post">
		
			<s:textfield label="First Name" name="userDetail.firstName"
				cssClass="required,validate[length[1,20]]" readonly="true"/>
		<%--<s:textfield label="Last Name" name="userDetail.lastName"
				cssClass="required,validate[length[1,20]]" />
				--%><s:textfield label="Email Id" name="userDetail.emailId"
				cssClass="required,validate[length[1,50]]" />
				
				<s:hidden name="userDetail.userEmpId" id="clientId"></s:hidden>
		
		<s:submit value="Submit" action="generateCredentialInput" />
		</s:form>
	</body>
	</html>