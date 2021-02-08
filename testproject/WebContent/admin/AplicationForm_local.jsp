<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head><script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery-1.10.1.min.js" /></script>

	<script type="text/javascript">
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='aplicationForm.action?navigationFlag=mailSend';
	}
	function goBack()
	  {
	  window.history.back()
	  }
	 function reSet(){
	  alert("Do u realy want to reset");
	  window.parent.location.href='aplicationForm.action';
	}
	//This is for validate the form textfield
		$(document).ready(function() {
			$("#application_form").validationEngine()
		});
	</script>

</head>
	<body> <s:if test="hasActionMessages()">
				 <div class="infoHolder">
					<s:actionmessage />
				</div>
		</s:if>
		<s:if test="hasActionErrors()">
				 <div class="errorHolder, dynErrorHolder">
					<s:actionerror />
				</div>
		</s:if>
<div class="content">
	<h3>
		<s:text name="pageTitles"></s:text>
	</h3>
		<s:form name="application_form" method="post" id="application_form" cssClass="formular">
		
		<table width="680" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="113" class="title">
					First Name
				</td>
				<td width="567" class="detail">
					<s:textfield name="userDetail.firstName"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Last name
				</td>
				<td class="detail">
					<s:textfield name="userDetail.lastName"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Contact No
				</td>
				<td class="detail">
					<s:textfield name="userDetail.contactNo"
						cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Email Id
				</td>
				<td class="detail">
					<s:textfield name="userDetail.emailId"
						cssClass="required,validate[required,custom[email]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Gender
				</td>
				<td class="detail">
					<s:select headerKey="-1" headerValue="Select Gender"
						list="#{'Male':'Male', 'Female':'Female'}" name="userDetail.gender"
						theme="simple" />
				</td>
			</tr>
			<tr>
				<td class="title">
					City
				</td>
				<td class="detail">
				<s:textfield name="userDetail.city" cssClass="required,validate[length[1,20]]" theme="simple"></s:textfield>
					</td>
			</tr>
			<tr>
				<td class="title">
					Address
				</td>
				<td class="detail">
				<s:textfield name="userDetail.address" cssClass="required,validate[length[1,100]]" theme="simple"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="title">
					Message:
			</td></tr>
		<tr>
				<td>(Meximum 200 charactors)</td>
				<td class="detail">
				<s:textarea name="userDetail.message" cssClass="required,validate[length[1,200]]" theme="simple" cols="20" rows="5"></s:textarea>
			</td>
			</tr>
			<%--<tr>
				<td class="title">
					If Additional document
				</td>
				<td class="detail">
					<s:file name="otherDocIfAny" theme="simple"></s:file>
				</td>
			</tr>
			--%><tr>
				<td class="title">
					&nbsp;
				</td>
				<td class="detail">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="title">
					<s:submit action="applicationEmail" align="center"></s:submit>
				</td>
				<td class="detail">
					<input type="button" value="Back" onclick="goBack()" />
				</td>
				<td class="title">
				<input type="button" value="Reset" onclick="reSet()" />
				
				</td>
			</tr>
			</table>

	</s:form>
</div>
</html>