<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>	
	if('<s:property value='navigationFlag'/>'=='success'){	
		//alert("Feedback Sent Successfully");
		window.parent.location.href='feedbackReport?actionMsg='+'<s:property value='actionMsg'/>';
	}  
</script>
<script>

	

	$(document).ready(function() {
		
			$("#review_Feedback").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='feedbackReport.action';
		}
		function updateUserFeedback(f) {
		var file = document.getElementById("offlineDoc");
		f = f.elements;
 		if(/.*\.(gif)|(jpeg)|(jpg)|(doc)$/.test(f['filename'].value.toLowerCase()))
 		 {
  			alert(file);
  			return true;
 		 }
		 alert('Please Upload Gif or Jpg Images, or Doc Files Only.');
 		f['filename'].focus();
		 return false;
		};			
		}
		
	
</script>
	
<body style="width:800px">
	<h2><s:text name="pageTitle"></s:text></h2>
	
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
		
			<b>Task Id- </b>
			<s:property value="userFeedback.tasks.taskUniqueName"/><br>
		<b>Agent Name -</b> 
		<s:property value="userFeedback.userDetail.firstName"/>&nbsp;
			<s:property value="userFeedback.userDetail.lastName"/> 
			<br>
			<b>Client- </b>
			<s:property value="userFeedback.clients.clientName"/>
			<br>
		<table style="width:600px">
		<tr>
			<td><h4>Document name</h4></td>
			<td><h4>Created On</h4></td>
			<td></td>
			</tr>
		<s:iterator value="feedbackList">
		<tr>	
		<td style="width:200px">
				
					<s:property value="documentName" />
				</td>
				<td style="width:200px">
					
					<s:property value="createdDtm" />
				</td>
				<td style="width:150px">
				<s:url action="getFile.action" id="urlTag" escapeAmp="false">
					<s:param name="userDocId">
						<s:property value="documetRepository.docId" />
					</s:param>
					<s:param name="typeDoc">
						<s:text name="FEEDBACKDOC"></s:text>
					</s:param>
					</s:url>
				<a href="<s:property value="#urlTag" />">Download</a>
				</td>
				</tr>
			</s:iterator>
			
			</table>
			
			
			<s:form method="post" id="review_Feedback" name="review_Feedback"
			cssClass="formular" enctype="multipart/form-data" >
			<table id="offline_Feedback">
			<s:file name="billUpload" label="Upload Bill"></s:file>
			<s:file name="imageUpload" label="Upload Image "></s:file>
			<s:file name="otherDocIfAny" label="Other Additional Doc"></s:file>
			<s:file name="offlineFeedback"  label="Offline Feedback Document" ></s:file>
			
			
			<s:textarea label="Task Assign Feedback"
				name="userFeedback.taskAssignProof"
				cssClass="required,validate[length[1,120]]"
				style="width: 213px; height: 81px;" />
			<s:textarea label="Additional Feedback"
				name="userFeedback.feedbackDetails"
				cssClass="required,validate[length[1,120]]"
				style="width: 213px; height: 81px;" />
			
			
			</table>
			<tr align="left">
				<td>
					<s:submit  value="Update Feedback" action="updateUserFeedbackbyAdmin" theme="simple"/>
				</td>
			</tr>
			
			<s:hidden name="userFeedback.feedbackId" id="feedbackId" theme="simple" />
			<s:hidden name="userFeedback.tasks.taskId" />
			
		</s:form>
