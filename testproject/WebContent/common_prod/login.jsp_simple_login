<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Project Name</title>
	</head>

	<body>
		<div align="center">

			<h3>
				SignIn
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
			<s:form action="login.action" method="post">
				<tr>
					<td>
						<b><font color="Red">UserName:</font> </b>
						<s:textfield name="users.uname" key="label.username"
							theme="simple" />
					</td>
				</tr>
				<tr>
					<td>
						<b><font color="Red"> PassWord : </font> </b>
						<s:password name="users.upass" key="label.password" theme="simple" />
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<div align="right">
							<s:submit key="label.login" theme="simple" />
							<s:submit value="SignUp" action="signUpPage" theme="simple" />
						</div>
						<div>
							<a href="userpassforget.action">Forgetpassword</a>
						</div>

					</td>
				</tr>
			</s:form>
		</div>

	</body>
</html>
