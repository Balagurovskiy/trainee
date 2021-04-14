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
			
			<a class="btn btn-outline-black btn-lg" href="/welcome">Log out</a>
			<h1 class="lead">${message}</h1>
 			
	 		<table class="table table-hover">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">id</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Currency</th>
			      <th scope="col">Order Date</th>
			    </tr>
			  </thead>
	  		  <tbody>
	  			
		 	<c:forEach var="c" items="${history}" >
	   		  	<tr>
			      <th>${c.id}</th>
			      <td>${c.product.name}</td>
			      <td>${c.product.price}</td>
			      <td>${c.product.currency.name}</td>
			      <td>${c.date}</td>
			    </tr>
	   		</c:forEach>
	   		
			  </tbody>
			</table>

 			
 			<div class="container">
			    <div class="col">
			     	<form class="d-inline" action="/account">
				  		<input class="btn btn-outline-dark btn-block btn-lg" type="submit" value="Back to Account">
					</form>
			    </div>
			  </div>
			</div>
	</body>
</html>
 