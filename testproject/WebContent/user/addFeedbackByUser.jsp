<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<script>
	
	if('<s:property value='navigationFlag'/>'=='success'){	
	//	alert('taskId = ' +  + '<s:property value='tasks.taskId'/>');
		window.parent.location.href='getAllAcceptedTask.action';
	}
	//This is for validate the form textfield
	$(document).ready(function() {
			$("#add_Feedback").validationEngine();
			$("#offline_Feedback").hide();
		});
		</script>
		
	
	</head>

	<body>
	
		
			
		<s:if test="hasActionMessages()">
				 <div class="infoHolder">
					<s:actionmessage />
				</div>
		</s:if>
		<s:if test="hasActionErrors()">
				 <div class="errorHolder, dynErrorHolder ">
					<s:actionerror />
				</div>
		</s:if>
	
		<s:form method="post" id="add_Feedback" name="add_Feedback"
			cssClass="formular" enctype="multipart/form-data">
			
			
			<div id="online_Feedback" style="width:700px">
			<s:file name="billUpload" label="Upload Bill"></s:file>
			<s:file name="imageUpload" label="Upload Image "></s:file>
			<s:file name="otherDocIfAny" label="Other Additional Doc"></s:file>
			<s:url action="getFile.action" id="urlTag" escapeAmp="false">
					<s:param name="userDocId">
						<s:property value="clientDocuments.docId" />
					</s:param>
					<s:param name="typeDoc">
						<s:text name="USERDOC"></s:text>
					</s:param>
				</s:url>
				<!--<a href="<s:property value="#urlTag" />"><s:property value="tasks.clients.docname" /></a> --><br>
				<s:hidden name="typeDoc" value="USERDOC"></s:hidden>
			<table id="online_Feedback" style="width:400px">
				<tr>
					<td colspan="2"><h2>Download Feedback form
						<a href="<s:property value="#urlTag"/>">here</a>&nbsp;</h2>(
						<i>Please download form, Fill it with proper detail before uploading here.</i>)
					</td>
				</tr>
				<tr>
					
					<td><s:file name="offlineFeedback" label="Upload Offline Feedback Doc " cssErrorClass="debug">
					</s:file>
					
					</td>
				</tr>
				
			</table>
			<s:textarea label="Feedback"
				name="userFeedback.taskAssignProof"
				cssClass="required,validate[length[1,120]]"
				style="width: 213px; height: 81px;" />
			<s:textarea label="Additional Feedback"
				name="userFeedback.feedbackDetails"
				cssClass="required,validate[length[1,120]]"
				style="width: 213px; height: 81px;" />
			
			<s:hidden name="tasks.taskId" id="taskId" theme="simple" />
			<s:submit value="Submit" action="addFeedback" />
			
			</div>
		</s:form>
			
		

	</body>
</html>
