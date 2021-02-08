<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="/tld/jmesa.tld" prefix="jmesa"%>
<html>
	<head>

		<script>	
	if('<s:property value='navigationFlag'/>'=='success'){	
		alert("Feedback Sent Successfully");
		window.parent.location.href='feedbackReport';
	}  
</script>

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>
		
	</head>
	<body>
			<h2>Email Report</h2>
		<s:form>				
			<p>Client Name : <b><s:property value="userFeedback.clients.userDetail.firstName" /> <s:property value="userFeedback.clients.userDetail.lastName" /></b></p>
			<p>Total Feedback You Selected: <b><s:property value="userFeedback.allCount" /></b></p>
			<p>Unsent Feedback : <b><s:property value="userFeedback.unsentCount" /></b></p>
			<p>Sent Feedback : <b><s:property value="userFeedback.sentCount" /></b></p>
			
			<s:submit value="Send Feedbacks" action="emailFeedbackToClient"/>
			<s:hidden name="feedbackString"></s:hidden>
			
		</s:form>
	
	
		
	</body>
</html>
