<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/tld/jmesa.tld" prefix="jmesa"%>
<html>
	<head>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>

	<script type="text/javascript">
	function addLocations(){
	
	popUpWithServerData("<%=request.getContextPath()%>/addNewLocation.action", "Add Location",350,600);

	}


	function deleteLocations(){

		var locationId=$("#location").val();
		if(locationId==null || locationId=="")
		{
		alert("Please select an option");
			return;
		}
		//alert('locationId='+locationId);	
		popUpWithServerData("<%=request.getContextPath()%>/deleteLocation.action?location.locId="+$("#location").val(), "Delete Location",350,600);
	
		} 
		$.ajaxSetup ({
    // Disable caching of AJAX responses
    cache: false
});	
</script>


<!-- For jmessa sorting and filtering -->

		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
               // alert(parameterString);
                location.href = '<%=request.getContextPath()%>/loadLocation.action?' + parameterString;
            }
            function setId(id){
            	$("#location").val(id.value);
            	          }
             
        </script>

 </head>

<body>
		<h3 style="color: #fff"><s:text name="pageTitles"></s:text></h3>
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
	<form name="cityform">
	<jmesa:tableModel id="tag" items="${locationList}" maxRows="8"   maxRowsIncrements="4,8,12" stateAttr="restore" var="bean">
	<jmesa:htmlTable width="100%">
		<jmesa:htmlRow>
		
		
			<jmesa:htmlColumn property="location" title="Select" >
					<input type="radio" name="location.locId" id="location.locId" value="${bean.locId}" onclick="setId(this);" />
			</jmesa:htmlColumn>
			<jmesa:htmlColumn property="city"  title="City_Name" />
			<jmesa:htmlColumn property="createdUIdName" title="Created By"/>
			<jmesa:htmlColumn property="createdDtm" title="Created On" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
			<jmesa:htmlColumn property="modifiedUIdName" title="Modified By"/>
			<jmesa:htmlColumn property="modifiedDtm" title="Modified On" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
			<jmesa:htmlColumn property="status" title="Status" />		
		</jmesa:htmlRow>
	</jmesa:htmlTable>		
	</jmesa:tableModel><br>
	<input type="button" onclick="addLocations()" value="Add City" />
	<input type="button" onclick="deleteLocations()" value="Delete" />
	
	<s:hidden name="location.locId" id="location" theme="simple" />	
</form>
</body>
</html>