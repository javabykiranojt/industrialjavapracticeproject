<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Document Report</title>
	</head>

	<body>
		<s:form>
			<s:iterator value="feedbackList">
				<s:url action="getFile.action" id="urlTag" escapeAmp="false">
					<s:param name="userDocId">
						<s:property value="documetRepository.docId" />
					</s:param>
					<s:param name="typeDoc">
						<s:text name="FEEDBACKDOC"></s:text>
					</s:param>
					</s:url>
				<a href="<s:property value="#urlTag" />"><s:property value="documentName" /></a> <br>
			</s:iterator>

		</s:form>

	</body>
</html>
