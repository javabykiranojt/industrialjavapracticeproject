<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'suspendUser.jsp' starting page</title>

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
		window.parent.location.href='allusers?actionMsg='+'<s:property value='actionMsg'/>';
	}
	</script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>
	<script type="text/javascript">

	function closeDialog()
	{
		window.parent.location.href='allusers';
	}


	</script>
  </head>
  
  <body>
  <s:set name="alreadyInactive" value="alreadyInactive"/>
  <s:if test="%{#alreadyInactive=='yes'}">
  	The selected user is already suspended.
  	<input type="button" value="OK" onclick="closeDialog()"/>
  </s:if>
  <s:else>
  	Do you want to suspend the selected user?
    <s:form>
    	<s:hidden name="uEId"></s:hidden>
   		<s:submit action="suspendUser" value="Yes"></s:submit>
   	</s:form>
  </s:else>
    
  </body>
</html>
