<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html">
<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.8.2.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-ui.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.jmesa.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.validate.min.js" /></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery-1.3.min.js" /></script>

		<!-- Below code need to addd to jsp for validations -->
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/validationEngine.jquery.css"
			type="text/css" media="screen" title="no title" charset="utf-8" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/template.css" type="text/css"
			media="screen" title="no title" charset="utf-8" />
		<script src="<%=request.getContextPath()%>/js/jquery-1.4.min.js"
			type="text/javascript"></script>
		<script
			src="<%=request.getContextPath()%>/js/jquery.validationEngine-en.js"
			type="text/javascript"></script>
		<script
			src="<%=request.getContextPath()%>/js/jquery.validationEngine.js"
			type="text/javascript"></script>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/kjnextstyle.css"
			type="text/css" />
			<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jmesa.css" type="text/css">
	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery-ui.css" type="text/css">
			
		<style>

body
{
font-family:Arial, Helvetica, sans-serif;
font-size:13px;

}
input[type="button"], input[type="submit"] { 
    background: none repeat scroll 0 0 #AF8A66;
    border: medium none;
   color: #FFFFFF;
    cursor: pointer;
    font-size: 14px;
    font-weight: 700;
    padding: 4px 8px;
    text-decoration: none;
    text-transform: uppercase;
} 
label.error 
{

font-size:11px;
background-color:#cc0000;
color:#FFFFFF;
padding:3px;
margin-left:5px;
-moz-border-radius: 4px;
-webkit-border-radius: 4px; 
}

input[type="button"], input[type="submit"] { 
    background: none repeat scroll 0 0 #AF8A66;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-size: 14px;
    font-weight: 700;
    padding: 4px 8px;
    text-decoration: none;
    text-transform: uppercase;
} 
.content
{
  	background: none repeat scroll 0 0 #8D5824;
    margin: 50px 0 0;
    padding: 20px 0 30px;
    width: 960px;
}
.content td.title{
	color: #FFFFCC;
    font-size: 13px;
    font-weight: 700;
    margin: 0;
    padding: 6px 0 6px 20px;
    }
.content td.detail{
    color: #FFFFFF;
    font-size: 13px;
    font-weight: 400;
    margin: 0;
    padding: 6px 0 6px 20px;
    }
    .content .col01{float:left; width:619px; padding:20px 20px 0 20px; margin:0 0; }
    h3 {
    border-bottom: 1px solid BLACK;
    color:  BLACK;
    font-size: 18px;
    font-weight: 400;
    margin: 0 20px 10px;
    padding: 0 0 10px;
}

body {
    /*background: url("common/../images/pg-bg.jpg") no-repeat scroll center top #000000;*/
    font-family: 'PT Sans',sans-serif,Verdana,Arial;
    font-size: 14px;
    line-height: 1.5em;
    margin: 0;
    padding: 0;
    text-align: left;
    color:black;
    /*color: #FFFFCC;*/
    font-size: 13px;
    font-weight: 700;
    margin: 0;
    padding: 6px 0 6px 20px;
}
</style>


		<title><tiles:insertAttribute name="title" ignore="true" />
		</title>
	</head>


	<body>
		<tiles:insertAttribute name="body" />
</html>
