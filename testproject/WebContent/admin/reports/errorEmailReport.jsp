<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
	<head>

<script>
	if('<s:property value='navigationFlag'/>'=='success'){	
		window.parent.location.href='showAllClient';
	}
</script>
		
		
	</head>
	<body>
		<s:form method="post">
			<p>There is no <span style="coloer:#ff66ff;"><b>Credentials</b></span>
			 set for the Client <B><s:property value="userFeedback.clients.userDetail.firstName" /> <s:property value="userFeedback.clients.userDetail.lastName" /></B></p>
			 <p>Please Set Client Credentials now...</p>
			 <p>To Create Client Credentials Click Create Credentials</p>
				<s:submit value="Create Credentials" action="clientError"/>
		</s:form>
		
	</body>
</html>
