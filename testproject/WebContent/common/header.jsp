<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<script type="text/javascript">
		function popUpWithServerData(page, pagetitle,height,width) {
				var $dialog = $("<div></div>").html("<iframe style=\"border: 0px; \" src=\"" + page + "\" width=\"100%\" height=\"100%\"></iframe>")
				.dialog({autoOpen: false,
			            height: height,
			            width: width,
			            title:pagetitle,
			            modal: true,
			            });
				$dialog.dialog("open");
		}
	
	</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.2.2.pack.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.3.min.js" /></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js" /></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jmesa.css" type="text/css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/kjnextstyle.css"
	type="text/css" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/kjnextDropDown.css"
	type="text/css" />
	
</head>

	<div style="float:right; width:40%; ">
		
		
		<%--<s:if test="%{#session.gender == 'Male'}">
	Welcome Mr.  <s:property value="%{#session.UserName}" />
</s:if>

<s:else>
    Welcome Ms.  <s:property value="%{#session.UserName}" />
</s:else>
		
		
		Welcome Mr.  <s:property value="%{#session.UserName}" />
		<br />
		Last accessed on :<s:property value="%{#session.lastAccesedTime}" />
	--%></div>
