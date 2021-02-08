<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<%=session.getAttribute("loginErr") %>
      <%                 
    Object condition = (Object)session.getAttribute("loginErr");
    if(condition!=null){
        response.sendRedirect("http://localhost:8080/testproject/error.html");
}else{
response.sendRedirect("http://localhost:8080/testproject/about.html");

}
   %>

                   
		 

  </body>
</html>
