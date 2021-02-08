<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Send User Email</title>
	</head>

	<body>
		<div align="center">
			<h3>
				Enter your registered Email address with Us. 
			</h3>
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
			<s:form method="post">
				<tr>
					<td>
						<b><font color="Red"> Email: </font> </b>
						<s:textfield name="userDetail.emailId" theme="simple" />
					</td>
				</tr>

				<tr>
					<td>
						<div align="right">
							<s:submit action="sendforgetpassword" value="Send" theme="simple"/>
						</div>
					</td>
				</tr>
			</s:form>
		</div>

	</body>
</html>
