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
			
	function addLocationAreas(){
	
	popUpWithServerData("<%=request.getContextPath()%>/addNewLocationArea.action", "Add Location Area",350,600);

	}

	function deleteLocationAreas(){

		var locationAreaId=$("#locationArea").val();
		if(locationAreaId==null || locationAreaId=="")
		{
		alert("Please select an option");
			return;
		}
		//alert('locationId='+locationId);	
		popUpWithServerData("<%=request.getContextPath()%>/deleteLocationArea.action?locationArea.areaId="+$("#locationArea").val(), "Delete Location Area",350,600);
	
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
                location.href = '<%=request.getContextPath()%>/loadLocationArea.action?' + parameterString;
            }
            function setId(id){
            	$("#locationArea").val(id.value);
            	          }
             
        </script>
</head>

<body>
		<h3 style="color: #fff"><s:text name="paseTitle"></s:text></h3>
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

	<form name="cityAreaform">
	<jmesa:tableModel id="tag" items="${locationAreaList}" maxRows="8"   maxRowsIncrements="4,8,12" stateAttr="restore" var="bean">
	<jmesa:htmlTable width="100%">
		<jmesa:htmlRow>
			<jmesa:htmlColumn property="locationArea" title="Select" >
				<input type="radio" name="locationArea.areaId" id="locationArea.areaId" value="${bean.areaId}" onclick="setId(this);"/>
			</jmesa:htmlColumn>
			<jmesa:htmlColumn property="location.city"  title="City_Name" />
			<jmesa:htmlColumn property="areaName"  title="Area_Name" />
			<jmesa:htmlColumn property="areaStatus" title="Status" />				
			
			
			<%--<jmesa:htmlColumn property="createdUIdName" title="Created By"/>
			<jmesa:htmlColumn property="createdDtm" title="Created On" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
			<jmesa:htmlColumn property="modifiedUIdName" title="Modified By"/>
			<jmesa:htmlColumn property="modifiedDtm" title="Modified On" pattern="dd/MM/yyyy" cellEditor="org.jmesa.view.editor.DateCellEditor" filterable="false"/>
						
		--%>
	</jmesa:htmlRow>
	</jmesa:htmlTable>		
	</jmesa:tableModel><br>
	<input type="button" onclick="addLocationAreas()" value="Add Area" />
	<input type="button" onclick="deleteLocationAreas()" value="Delete" />
	
			<s:hidden name="locationArea.areaId" id="locationArea" theme="simple" />	


</form>
</body>
</html>