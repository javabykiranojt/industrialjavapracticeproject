<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'adminProfileEditable.jsp' starting page</title>

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
		window.parent.location.href='showAdminProfile';
	}
	
	//JQuery Ajax code
	function differentLocationAreas(locId)
	{
		var url="<%=request.getContextPath()%>/differentLocationAreas.action?location.locId="+locId;
		$("#locationAreaDropdown").load(url);
	}
	</script>
  </head>
  
  <body>
    <s:form method="post">
			<s:textfield label="FirstName" name="userDetail.firstName"></s:textfield>
			<s:textfield label="LastName" name="userDetail.lastName"></s:textfield>
			
			<s:select list="locationList" id="locationDropDown" label="City"
				listKey="locId" listValue="city" name="location.locId" headerKey="0"
				value="userDetail.location.locId" headerValue="-Select-" onchange="differentLocationAreas(this.value)"></s:select>
			
			<s:select list="locationAreaList" id="locationAreaDropdown" label="Area" name="locationArea.areaId" 
   				listKey="areaId" listValue="areaName" 
   				value="userDetail.locationArea.areaId" headerKey="0" headerValue="-Select-"></s:select>
   				
			<s:textfield label="ContactNo" name="userDetail.contactNo"></s:textfield>
			
			<s:submit value="Update" action="editAdminProfileInput"></s:submit>
	</s:form>
  </body>
</html>
