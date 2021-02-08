<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 		window.parent.location.href='clientFeedBackReport.action'; 
	}

	$(document).ready(function() {
			$("#reassign_feedback").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='clientFeedBackReport.action';
		}
</script>

<body>

<s:form id="reassign_feedback" name="reassign_feedback" cssClass="formular" method="post">
	<br/> Do You Want to Reaasign Feedback?
	<br/><br/><br/>
	FeedBack: <s:textarea label="FeedBack" name="userFeedback.remarkByClient" cssClass="required" theme="simple"/>
	 
	<br/>
	 
   <tr><td><s:submit value="Reassign" action="ReassignFeedback" theme="simple"/></td><td>
	<input type="button" onclick="doNothing()" value="Cancel"/></td></tr>
	<s:hidden name="userFeedback.feedbackId" id="feedbackId" theme="simple" />
	<s:hidden name="productIdRadio" id="productIdRadio"></s:hidden>
    
	 </s:form>
</body>