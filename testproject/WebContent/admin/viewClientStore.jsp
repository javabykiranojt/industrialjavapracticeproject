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
		
		function doNothing(){
		window.parent.location.href='showAllClient.action';
		}
	</script>
</head>
<body><h2><s:text name="pageTitle"></s:text></h2>
	<form name="viewStore">
			<jmesa:tableModel id="tag" items="${storeList}" maxRows="8"
				  maxRowsIncrements="4,8,12"
				stateAttr="restore" var="bean">
				<jmesa:htmlTable width="100%">
					<jmesa:htmlRow>
					<%--<s:hidden><jmesa:htmlColumn property="clients" title="Select">
							<input type="radio" name="clients.clientId" id="clients.clientId"
								value="${bean.clientId}" onclick="setId(this);" />
						</jmesa:htmlColumn></s:hidden>
						--%><jmesa:htmlColumn property="clientDetails.cltFirstName"  title="Owner Firstname" filterable="false"/>
						<jmesa:htmlColumn property="clientDetails.cltLastName" title="Owner LastName" filterable="false"/>
						<jmesa:htmlColumn property="clientDetails.cltContactNo" title="ContactNo" filterable="false"/>
						<jmesa:htmlColumn property="clientDetails.cltEmail" title="EmailId" filterable="false"/>
						<jmesa:htmlColumn property="clientName" title="Store Name" filterable="false"/>
						<%--<jmesa:htmlColumn property="clientDetails.locationArea" title="Store Location" />
					--%></jmesa:htmlRow>
				</jmesa:htmlTable>
			</jmesa:tableModel><br>
			 <input type="button" onclick="doNothing()" value="OK"/>
			</form>
			</body>
</html>