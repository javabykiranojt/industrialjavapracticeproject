<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
if('<s:property value='navigationFlag'/>'=='success'){
 		window.parent.location.href='loadAllCitiesAreas.action'; 
	}

	$(document).ready(function() {
			$("#frm_deleteLocationArea").validationEngine()
		});
	function doNothing(){
		window.parent.location.href='loadAllCitiesAreas.action';
		}
</script>
<s:form id="frm_deleteLocationArea" cssClass="formular">
	<s:actionerror />
    <s:hidden name="locationArea.areaId" id="locationArea.areaId"></s:hidden>
	 <br/> Do You Want to Delete This City Area?
	 <br/><br/><br/>
	 
	 <s:submit value="Delete" action="ConfirmDeleteLocationArea" theme="simple"></s:submit>
	 <input type="button" onclick="doNothing()" value="Cancel"/>
	 </s:form>
