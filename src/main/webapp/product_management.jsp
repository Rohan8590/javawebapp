<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*" %>
<%@page import="com.nagarro.ecommerce.model.ProductsInformation" %>
<%@page import="com.nagarro.ecommerce.constants.EcommerceConstants" %>
<%@page import="com.nagarro.ecommerce.service.ProductsInformationServiceImpl" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management Tool</title>
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
	
	<div id="input-form">
	<h4>Please enter product details to add to stock</h4>
	<form method = "post" action = "insertInDB" onsubmit="return validate(this)" enctype="multipart/form-data">
	<table>
	<tr>
		<td><label for="pTitle">Title : </label></td>
		<td><input name="ptitle" id ="pTitle" ></td>
		<td><p style="visibility: hidden;" class="errorMessage" id="titleError">Invalid Title</p></td>
	</tr>
	<tr>
		<td><label for="pQuantity">Quantity : </label></td>
		<td><input name="pquantity" id ="pQuantity"required></td>
		<td><p style="visibility: hidden;" class="errorMessage" id="quantityError">Invalid Quantity</p></td>
	</tr>
	<tr>
		<td><label for="pSize">Size : </label></td>
		<td><input name="psize" id ="pSize"required></td>
		<td><p style="visibility: hidden;" class="errorMessage" id="sizeError">Invalid Size</p></td>
	</tr>
	<tr>
		<td><label for="pImage">Image : </label></td>
		<td><input type="file" name="pimage" id ="pImage"required/></td>
		<td><p style="visibility: hidden;" class="errorMessage" id="imageLinkError">Invalid Image</p></td>
	</tr>
	</table>
	<%
		if(session.getAttribute("exceedsimagesize") != null){
			if(session.getAttribute("exceedsimagesize").equals("true")) {
	%>
	<span class="errorMessage">Image size can't be more than 1mb</span>
	<%
		session.setAttribute("exceedsimagesize", null);
			}}
	%>
	<%
		if(session.getAttribute("exceedstotalimagesize") != null){
			if(session.getAttribute("exceedstotalimagesize").equals("true")) {
	%>
	<span class="errorMessage">Size of all uploaded images can't exceed 10mb</span>
	<%
		session.setAttribute("exceedstotalimagesize", null);
			}}
	%>
		<input type="submit" value= "Add" id ="addData">
	</form>
	</div>
	
	<div id="information-table">
	<table>
		<tr>
			<td id="tsno">S. No</td>
			<td id="ttitle">Title</td>
			<td id="tquantity">Quantity</td>
			<td id="tsize">Size</td>
			<td id="timage">Image</td>
			<td id="tactions">Actions</td>
		</tr>
		<%
			int sno=1;
			ProductsInformationServiceImpl productsInformationController = new ProductsInformationServiceImpl();
			if(session.getAttribute("username") != null){
				List<ProductsInformation> productsList = productsInformationController.getList(session.getAttribute("username").toString());
				for(ProductsInformation d : productsList) {
		%>
		<tr>
			<td><%=sno %></td>
			<% sno++; %>
			<td><%=d.getTitle()%></td>
			<td><%=d.getQuantity()%></td>
			<td><%=d.getSize()%></td>
			<td id="rimage"><img src="images/userimages/<%=d.getImage()%>"></td>
			<td><a href="edit.jsp?prodId=<%=d.getId()%>"><img src="images/edit.jpg"></a> <a href="deletedata?prodId=<%=d.getId()%>"><img src="images/cross.jpg"></a></td>
		</tr>
		<% }} %>
	</table>
	</div>
</body>
</html>