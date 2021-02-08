<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 window.parent.location.href='getAllFinishedFeedbackByUser.action'; //doc.formname.action
	}

	$(document).ready(function() {
			$("#frm_rejectFeedback").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='getAllFinishedFeedbackByUser.action';
		}
</script>
<s:form id="frm_rejectFeedback" cssClass="formular">
	<s:actionerror />
    <s:hidden name="userFeedback.feedbackId"></s:hidden>
	 <br/> Do You Want to Reject
	 <br/><br/><br/>
	 
	 <s:submit value="Reject" action="rejectFeedbackInput" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
	 