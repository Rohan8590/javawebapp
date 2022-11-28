<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link href="css/index.css" type= "text/css" rel ="stylesheet">
</head>
<body>

<div id="outer-div">
<div id="login">
	<h2>Login</h2>
</div>
<form method = "post" action = "login" id="loginForm">
<div id="main-content">
	<br>
	<table>
	<tr>
		<td><label for="loginUname">UserName : </label></td>
		<td><span class="star">*</span><input name="loginuname" id ="loginUname" required></td>
	</tr>
	<tr>
		<td><label for="loginPass">Password : </label></td>
		<td><span class="star">*</span><input name="loginpass" type="password" id ="loginPass"required></td>
	</tr>
	</table>
	<%
	if(session.getAttribute("isvalidunamepass") != null){
		if(session.getAttribute("isvalidunamepass").equals("false")) { 
	%>
	<span class="errorMessage">Invalid user name/password.</span>
	<%
	session.setAttribute("isvalidunamepass", null);
		}} 
	%>
	<br>
	<span id ="fpassSpan"><a href=fpass.jsp>Forgotten Your Password?</a></span>
</div>
<div id="button-area">
	<input type="submit" value= "Login" id ="loginSubmitBtn">
</div>
</form>
</div>
</body>
</html>