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
	    		
	 		<table class="table table-hover">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">id</th>
			      <th scope="col">Name</th>
			      <th scope="col">Cash</th>
			      <th scope="col">Currency</th>
			    </tr>
			  </thead>
	  		  <tbody>
	  			
		 	<c:forEach var="c" items="${customers}" >
	   		  	<tr onclick="from_th_to_hidden(this, 'customer_cache');">
			      <th>${c.id}</th>
			      <td>${c.name}</td>
			      <td>${c.cash}</td>
			      <td>${c.currency.name}</td>
			    </tr>
	   		</c:forEach>
	   		
			  </tbody>
			</table>
			
	  		<form class="d-inline" action="/register">
			  	<input class="btn btn-outline-primary btn-lg" type="submit" value="Register">
			</form>
			
	  		<form class="d-inline" action="/account">
	  			<input type="hidden" id="customer_cache" name="customer_cache"/>
			  	<input class="btn btn-outline-dark btn-lg" type="submit" value="Sign in">
			</form>
 		</div>
	    
	</body>
</html>
 