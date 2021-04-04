<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>INTERNET SHOP</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<script><%@include file="row_event.js" %></script>
	</head>
	<body> 
		<div class="jumbotron">
			<h1 class="display-4">${page_header}</h1>
			<h1 class="lead">${message}</h1>
 
			<c:if test="${hasError}">
 				<div class="alert alert-danger" role="alert">
				 	${error} !
				</div>
			</c:if>
	  		<form class="d-inline" action="/shop-jsp-servlet/register" method="post">
	  			<input type="text" id="register_name" name="register_name"/>
			  	<input class="btn btn-outline-primary btn-lg" type="submit" value="Create">
			  	<a class="btn btn-outline-black btn-lg" href="/shop-jsp-servlet/">Back</a>
			</form>
 		</div>
	    
	</body>
</html>
 