<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 window.parent.location.href='getAllInitiatedTask.action'; //doc.formname.action
	}

	$(document).ready(function() {
			$("#frm_acceptTask").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='getAllInitiatedTask.action';
		}
</script>
<s:form id="frm_acceptTask" cssClass="formular">
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
	
    <s:hidden name="tasks.taskId"></s:hidden>
	 <br/> Do You Want to Accept
	 <br/><br/><br/>
	 
	 <s:submit value="Accept" action="acceptTaskInputByUser" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
	 