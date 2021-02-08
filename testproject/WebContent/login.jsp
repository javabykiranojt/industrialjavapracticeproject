<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

	<head>
		<title>Project Name</title>
	</head>

	
		<div align="center">
			
			<h3>
				Project Name
			</h3>
			
			<s:actionerror/>
			<s:actionmessage/>
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
						<s:password name="users.upass" key="label.password"
							theme="simple" />
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<div align="right">
							<s:submit key="label.login" theme="simple" />
							<s:a href="signUpPage">Register</s:a>
						</div>
					</td>
				</tr>
			</s:form>
		</div>
		
	

