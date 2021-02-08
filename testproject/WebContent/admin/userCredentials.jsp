<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    function popUpWithServerData(page, pagetitle,height,width) {
				var $dialog = $("<div></div>").html("<iframe style=\"border: 0px; \" src=\"" + page + "\" width=\"100%\" height=\"100%\"></iframe>")
				.dialog({autoOpen: false,
			            height: height,
			            width: width,
			            title:pagetitle,
			            modal: true,
			            });
				$dialog.dialog("open");
		}
	function changePassword()
    {
     	parent.popUpWithServerData("<%=request.getContextPath()%>/changeUserPassByAdmin.action?uEId="+$("#uEId").val(),"Change Password",350,450);
    }
    
   
    
  
    </script>
    
 </head>
  
  <body><h2><s:text name="pageTitles"></s:text></h2>
  	<table>
  		<tr>
  			<s:form method="post">
			<s:label label="Username" name="users.uname"></s:label>
			<s:label label="Password" name="users.upass"></s:label>
			<s:hidden name="uEId" id="uEId" theme="simple" />
			</s:form>
  		</tr>
  		<tr>
  			<input type="button" onclick="changePassword()" value="Change Passowrd" />
  		</tr>
  	
  	</table>
  </body>
</html>
