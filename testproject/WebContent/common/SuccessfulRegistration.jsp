<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Successfull</title>
 <style type="text/css">
  .ps
     {
     position:absolute;
     width: 290px;
     height: 110px;
     top:50%;
     left:50%;
     padding: 10px;
     margin:-150px 0 0 -150px;
     border: 5px solid black;
     }    
 </style>
 
 
</head>

<body>

   <s:form theme="simple" name="register">
    <div class="ps">
     <table>
      <tr><td>You are Successfully Register</td></tr>
      <tr><td>Thanks for your registration</td></tr>
      <tr><td><s:submit/></td></tr>
     </table>
    </div>  
   </s:form>
 
</body>
</html>