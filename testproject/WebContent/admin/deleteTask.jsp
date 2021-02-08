<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 window.parent.location.href='showAllTask.action'; //doc.formname.action
	}

	$(document).ready(function() {
			$("#frm_deleteTask").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='showAllTask.action';
		}
</script>
<s:form id="frm_deleteTask" cssClass="formular">
	<s:actionerror />
    <s:hidden name="tasks.taskId" id="taskId"></s:hidden>
	 <br/> Do You Want to Delete
	 <br/><br/><br/>
	 
	 <s:submit value="Delete" action="deleteTaskInput" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
	 