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
	function seeResponces(taskUniqueName)
  		{
  	 		popUpWithServerData("<%=request.getContextPath()%>/taskResponses.action?taskUniqueName="+escape(taskUniqueName),"Task Responses",500,1100);  
  		}
  	</script>
		<script type="text/javascript">
            function onInvokeAction(id) {
                $.jmesa.setExportToLimit(id, '');
                $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
            }
             function onInvokeExportAction(id) {
                var parameterString = $.jmesa.createParameterStringForLimit(id);
                //alert(parameterString);
                location.href = '<%=request.getContextPath()%>/taskResponses.action?' + parameterString;
            }
            function setId(id){
            	$("#taskId").val(id.value);
            }
        </script>
	</head>
	<body>	<h3><s:text name="pageTitles"></s:text>(you will not see task which already accepted by users and waiting for feedback submission. )</h3>
		<s:form name="taskForm" theme="simple">
			<jmesa:tableModel id="tag" items="${tasksList}" maxRows="8"
				maxRowsIncrements="4,8,12" stateAttr="restore" var="bean"
				exportTypes="csv,excel,pdf">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
						<jmesa:htmlColumn property="taskUniqueName" title="Click on below task to see responces."
							filterable="true" >
							
							<a href="javascript:void(0);" onclick="seeResponces('${bean.taskUniqueName}');">${bean.taskUniqueName}</a>
							
							</jmesa:htmlColumn>
					</jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel>
			<%--<s:iterator value="tasksNamesList">
				<a href="javascript:void(0);"
					onclick="seeResponces('<s:property/>');"><s:property />
				</a>
			</s:iterator>
		--%></s:form>