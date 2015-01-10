<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="${context}/css/SignIntheme.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignIn - Todo</title>
</head>
<body class="bodyCss">
	<div align="center">
		<h1>Sign In</h1>
	</div>
	<hr class="line">
	
	
	<!-- Sign In Form -->
	<form:form action="${context}/todo/signin" method="post" commandName="signInForm">
		<form:errors cssClass="globlaErrors gerror" element="div"/>
		<table align="center" cellpadding="10" >
			<tr>
            <td align="right">User Name</td>
            <td><form:input path="userName" /></td>
            <td><form:errors path="userName" cssClass="error" ></form:errors></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="error"></form:errors></td>
            <form:hidden path="fromPage" value="LoginPage"/>
        </tr>
        <tr>
        <td>
        </td>
        <td><form:checkbox path="keepMeSignIn"/> Keep me signed in</td>
        </tr>
        <tr>
        	<td></td>
            <td align="center">
                <input type="submit" value="SignIn" />
            </td>
        </tr>
		</table>
	</form:form>
</body>
</html>