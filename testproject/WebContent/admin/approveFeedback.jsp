<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 window.parent.location.href='getAllFinishedFeedbackByUser.action'; //doc.formname.action
	}

	$(document).ready(function() {
			$("#frm_approveFeedback").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='getAllFinishedFeedbackByUser.action';
		}
		$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
function addRewardPoints(){
		window.parent.location.href='showClientRewardPoints.action';
}
</script><body>

	 <s:if test="hasActionMessages()">
				 <div class="infoHolder">
					<s:actionmessage />
				</div>
		</s:if>
		<s:if test="hasActionErrors()">
				 <div class="errorHolder, dynErrorHolder">
					<s:actionerror/>
				</div>
		</s:if>
	<s:form id="frm_approveFeedback" cssClass="formular">
	
	
    <s:hidden name="userFeedback.feedbackId"></s:hidden>
     <s:if test="hasActionErrors()">
		<a href="#" onclick="addRewardPoints();">Please click here to add reward points for a user.
		</a>
    </s:if>
	 <br/> <p>Do you want to Approve ?</p>
	<br/><br/>
	 
	 <s:submit value="Approve" action="approveFeedbackInput" theme="simple"></s:submit>
		 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
	</body> 