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
			
			<c:if test="${ordered}">
				<div class="alert alert-success" role="alert">
				 	Bucket updated! Current number of products in the bucket is : ${bucket_size} .
				</div>
			</c:if>
			
			
			<a class="btn btn-outline-black btn-lg" href="/welcome">Log out</a>
			<h1 class="lead">${message}</h1>
 			
	 		<table class="table table-hover">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">id</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Currency</th>
			    </tr>
			  </thead>
	  		  <tbody>
	  			<tr>
			      <th scope="col" class="alert alert-secondary" colspan="4">Food</th>
			    </tr>
		 	<c:forEach var="c" items="${food}" >
	   		  	<tr onclick="from_th_to_hidden(this, 'ordered_cache');">
			      <th>${c.id}</th>
			      <td>${c.name}</td>
			      <td>${c.price}</td>
			      <td>${c.currency.name}</td>
			    </tr>
	   		</c:forEach>
	   			<tr>
			      <th scope="col"  class="alert alert-secondary" colspan="4">Stuff</th>
			    </tr>
			<c:forEach var="c" items="${stuff}" >
	   		  	<tr onclick="from_th_to_hidden(this, 'ordered_cache');">
			      <th>${c.id}</th>
			      <td>${c.name}</td>
			      <td>${c.price}</td>
			      <td>${c.currency.name}</td>
			    </tr>
	   		</c:forEach>
	   		
	   		
			  </tbody>
			</table>

 			
 			<div class="container">
			  <div class="row">
			    <div class="col">
	     	  		<form class="d-inline" action="/product_list" method="POST">
			  			<input type="hidden" id="ordered_cache" name="ordered_cache"/>
					  	<input class="btn btn-dark btn-block btn-lg" type="submit" value="Order">
					</form>
			    </div>
			    <div class="col">
			     	<form class="d-inline" action="/account">
				  		<input class="btn btn-outline-dark btn-block btn-lg" type="submit" value="Back to Account">
					</form>
			    </div>
			  </div>
			</div>
	  		
 		</div>
	    
	</body>
</html>
 