<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
	alert('File Uploaded Successfully' +  + '<s:property value='clientname'/>');
 		window.parent.location.href='showAllClient.action'; 
	}

	$(document).ready(function() {
			$("#frm_uploadDoc").validationEngine()
		});
	
</script>
<s:form id="frm_uploadDoc" cssClass="formular" enctype="multipart/form-data" method="post">
	<s:actionerror />
   	<h3>Client Name : <s:property value="clientname"/></h3>
	 <s:file name="clientDoc" label="Select Client Document"></s:file>
	  <s:hidden name="userDetail.userEmpId" id="userEmpId"></s:hidden>
	  <br/>
	 <s:submit value="Upload" action="uploadClientDoc" theme="simple"></s:submit>
	 
	 </s:form>
	 