<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Verification Id</title>
</head>
  <center>
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
   <s:if test="!hasActionMessages()">
  <s:form theme="simple">
  <div class="content">
   <table>
   <tr>	
   		<td class="title">Enter Verification Id</td>
   		<td class="detail"><s:textfield label="Enter Verification Id" name="verificationId"/></td>
   		<s:hidden name="UserEmpId"></s:hidden>
   </tr>
   <tr>
   		<td></td>
   		<td><s:submit value="Enter" action="verificationPageInput"/></td>
   </tr>
  </table>
  </div>
  </s:form>
  </s:if>
 </center>
