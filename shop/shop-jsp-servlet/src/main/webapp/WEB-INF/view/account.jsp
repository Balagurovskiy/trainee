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
			
			<div class="alert alert-success" role="alert">
			 	Account Balance : ${balance}
			</div>
			
			<a class="btn btn-outline-black btn-lg" href="/welcome">Log out</a>
			<h1 class="lead">${message}</h1>
 			
 			
 			<div class="container">
			  <div class="row">
			    <div class="col">
	     	  		<form class="d-inline" action="/product_list">
					  	<input class="btn btn-outline-dark btn-block btn-lg" style="height:100px" type="submit" value="Shopping">
					</form>
			    </div>
			    <div class="col">
	      	  		<form class="d-inline" action="/bucket">
					  	<input class="btn btn-outline-primary btn-block btn-lg" style="height:100px" type="submit" value="Bucket">
					</form>
			    </div>
			    <div class="col">
			     	<form class="d-inline" action="/history">
				  		<input class="btn btn-outline-dark btn-block btn-lg" style="height:100px" type="submit" value="Order history">
					</form>
			    </div>
			  </div>
			</div>
	  		
 		</div>
	    
	</body>
</html>
 