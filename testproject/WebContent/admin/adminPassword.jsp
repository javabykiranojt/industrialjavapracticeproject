<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'adminPassword.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAdminProfile.action?actionMsg=<s:property value='actionMsg'/>';
	}
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
   	<s:form>
   		<s:password label="Old Password" name="oldPass"></s:password>
   		<s:password label="New Password" name="newPassword"></s:password>
   		<s:password label="Confirm Password" name="confirmNewPassword"></s:password>
   		<s:submit action="changeAdminPasswordInput"></s:submit>
   	</s:form>
  </body>
</html>
