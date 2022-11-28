<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*" %>
<%@page import="com.nagarro.ecommerce.model.ProductsInformation" %>
<%@page import="com.nagarro.ecommerce.controller.ProductsInformationControllerImpl" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Product Information</title>
<script src="script.js" type="text/javascript"></script>
<link href="css/product_management.css" type= "text/css" rel ="stylesheet">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP1.1
			response.setHeader("Pragma", "no-cache"); //HTTP 1.0
			response.setHeader("Expires", "0"); //Proxy
			
			if(session.getAttribute("username") == null){
				response.sendRedirect("index.jsp");
			}
	%>
	
	<div id="heading" align="center">
	<h2>Product Management Tool</h2>
	</div>
	<div id="head-right" align="right">
	<span id="welcome">Hi ${username}</span>
	<form action = "logout" method = "post">
		<input type="submit" value="Logout">
	</form>
	</div>
	
	<%
			String tempId = request.getQueryString();
			int productId = Integer.parseInt(tempId.substring(tempId.lastIndexOf("=") + 1));
			session.setAttribute("id", productId);
			
			ProductsInformationControllerImpl productsInformationController = new ProductsInformationControllerImpl();
			ProductsInformation p = productsInformationController.get(productId);
		%>
	
	<div id="input-form">
	<h4>Please enter product details to add to stock</h4>
	<form method = "post" action = "editData" onsubmit="return validate(this)" enctype="multipart/form-data">
	<table>
	<tr>
		<td><label for="pTitle">Title : </label></td>
		<td><input name="ptitle" id ="pTitle" value='<%=p.getTitle()%>' required></td>
		<td><span style="visibility: hidden;" class="errorMessage" id="titleError">Invalid Title</span></td>
	</tr>
	<tr>
		<td><label for="pQuantity">Quantity : </label></td>
		<td><input name="pquantity" id ="pQuantity" value='<%=p.getQuantity()%>' required></td>
		<td><span style="visibility: hidden;" class="errorMessage" id="quantityError">Invalid Quantity</span></td>
	</tr>
	<tr>
		<td><label for="pSize">Size : </label></td>
		<td><input name="psize" id ="pSize" value='<%=p.getSize()%>' required></td>
		<td><span style="visibility: hidden;" class="errorMessage" id="sizeError">Invalid Size</span></td>
	</tr>
	<tr>
		<td><label for="pImage">Image : </label></td>
		<td><input type="file" name="pimage" value='<%=p.getImage()%>' id ="pImage"required/></td>
		<td><span style="visibility: hidden;" class="errorMessage" id="imageLinkError">Invalid Image Link</span></td>
	</tr>
	</table>
	<%
	if(session.getAttribute("exceedsimagesizeedit") != null){
		if(session.getAttribute("exceedsimagesizeedit").equals("true")) { 
	%>
	<span class="errorMessage">Image size can't be more than 1mb</span>
	<%
	session.setAttribute("exceedsimagesizeedit", null);
		}} 
	%>
	<%
	if(session.getAttribute("exceedstotalimagesizeedit") != null){
		if(session.getAttribute("exceedstotalimagesizeedit").equals("true")) { 
	%>
	<span class="errorMessage">Size of all uploaded images can't exceed 10mb</span>
	<%
	session.setAttribute("exceedstotalimagesizeedit", null);
		}} 
	%>
		<input type="submit" value= "Save" id ="addData">
	</form>
	</div>
</body>
</html>