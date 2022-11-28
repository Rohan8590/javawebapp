<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link href="css/fpass.css" type= "text/css" rel ="stylesheet">
</head>
<body>
<div id="outer-div">
	<form method = "post" action = "fpass" id="fPass">
	<div id="main-content">
	<br>
	<table>
	<tr>
		<td><label for="loginUname">UserName : </label></td>
		<td><span class="star">*</span><input name="loginuname" id ="loginUname" required></td>
	</tr>
	<tr>
		<td><label for="userEmail">Email : </label></td>
		<td><span class="star">*</span><input type="email" name="useremail" id ="userEmail" required></td>
		</tr>
	</table>
	<%
	if(session.getAttribute("isvalidunamemail") != null){
		if(session.getAttribute("isvalidunamemail").equals("false")) { 
	%>
	<span class="errorMessage">Invalid user name/email.</span>
	<%
	session.setAttribute("isvalidunamemail", null);
		}} 
	%>
	
	<%
	if(session.getAttribute("userpass") != null){
	%>
	<span class="successMessage">Your password is: <%= session.getAttribute("userpass") %>.</span>
	<%
	session.setAttribute("userpass", null);
		}
	%>
	</div>
	<div id="button-area">
		<input type="submit" value= "Show Password" id ="showPassButton">
		<a href="index.jsp"><span>Login</span></a>
	</div>
	</form>
</div>
</body>
</html>