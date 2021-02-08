<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'userProfileEditable.jsp' starting page</title>

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
		window.parent.location.href='getUserRewards';
	}
	</script>
	
  </head>
  
  <body>
  	<s:form>
			<s:if test="hasActionMessages()">
				 <div class="infoHolder">
					<s:actionmessage />
				</div>
		</s:if>
		<s:if test="hasActionErrors()">
				 <div class="errorHolder">
					<s:actionerror />
				</div>
		</s:if>
	
		<h3 style="width: 300px;">My Points Table</h3>
		<s:label>Net Points Available :</s:label><s:property value="userRewards.point" /><br>
		<s:label>Points redeemed so far : </s:label><s:property value="userRewards.redeemPoint" /><br>			
		<s:textfield name="requestPoint" label="Enter Request Points" cssStyle="width:100px"/><br>						
		<s:if test="!hasActionErrors()">
		<s:submit action="redeemPoints" value="Request Redeem" cssStyle="float:left; margin-right: 100px;"></s:submit>
	</s:if>
							<s:submit value="Close"  onclick="self.parent.location.reload()"  />
	</s:form>
  </body>
</html>
