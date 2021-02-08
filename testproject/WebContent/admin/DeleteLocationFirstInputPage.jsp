<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 		window.parent.location.href='loadAllCities.action'; 
	}

	$(document).ready(function() {
			$("#frm_deleteLocation").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='loadAllCities.action';
		}
</script>
<s:form id="frm_deleteLocation" cssClass="formular">
	<s:actionerror />
    <s:hidden name="location.locId" id="location.locId"></s:hidden>
	 <br/> Do You Want to Delete This City?
	 <br/> All The Areas In This City Will Be Deleted.
	 <br/> Do You Want To Continue?
	 <br/><br/><br/>
	 
	 <s:submit value="Delete" action="ConfirmDeleteLocation" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>

