<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
	//alert('clientDetailId = ' +  + '<s:property value='userDetail.userEmpId'/>');
 		window.parent.location.href='showAllClient.action'; 
	}

	$(document).ready(function() {
			$("#frm_deleteClient").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='showAllClient.action';
		}
</script>
<s:form id="frm_deleteClient" cssClass="formular">
	<s:actionerror />
    <s:hidden name="userDetail.userEmpId" id="clientId"></s:hidden>
	 <br/> Do You Want to Delete
	 <br/><br/><br/>
	 
	 <s:submit value="Delete" action="deleteClientInput" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
	 