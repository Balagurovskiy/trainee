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
			
			<a class="btn btn-outline-black btn-lg" href="/shop-jsp-servlet/">Log out</a>
			<h1 class="lead">${message}</h1>
 			
	 		<table class="table table-hover">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">id</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Currency</th>
			      <th scope="col">Eatable</th>
			    </tr>
			  </thead>
	  		  <tbody>
	  			
		 	<c:forEach var="c" items="${bucket}" >
	   		  	<tr onclick="from_th_to_hidden(this, 'bucket_cache');">
			      <th>${c.id}</th>
			      <td>${c.product.name}</td>
			      <td>${c.product.price.amount}</td>
			      <td>${c.product.price.name}</td>
			      <td>			
					<c:choose>
					  <c:when test="${c.product.eatable}">
					    <div class="p-3 bg-success text-white"></div>
					  </c:when>
					  <c:otherwise>
					    <div class="p-3 bg-danger text-white"></div>
					  </c:otherwise>
					</c:choose>
				</td>
			    </tr>
	   		</c:forEach>
	   		
			  </tbody>
			</table>

			<div class="alert alert-info" role="alert">
			  TOTAL ORDER : ${total} (in currency of customer wallet)
			</div>
			
 			
 			<div class="container">
			  <div class="row">
  			    <div class="col">
	     	  		<form class="d-inline" action="/shop-jsp-servlet/bucket?action=buy" method="POST">
					  	<input class="btn btn-primary btn-block btn-lg" type="submit" value="Submit order">
					</form>
			    </div>
			    <div class="col">
	     	  		<form class="d-inline" action="/shop-jsp-servlet/bucket?action=delete" method="POST">
			  			<input type="hidden" id="bucket_cache" name="bucket_cache"/>
					  	<input class="btn btn-outline-dark btn-block btn-lg" type="submit" value="Delete Selected">
					</form>
			    </div>
   			    <div class="col">
	     	  		<form class="d-inline" action="/shop-jsp-servlet/bucket?action=clean" method="POST">
					  	<input class="btn btn-dark btn-block btn-lg" type="submit" value="Clear bucket">
					</form>
			    </div>
			    <div class="col">
			     	<form class="d-inline" action="/shop-jsp-servlet/account">
				  		<input class="btn btn-outline-dark btn-block btn-lg" type="submit" value="Back to Account">
					</form>
			    </div>
			  </div>
			</div>
	  		
 		</div>
	    
	</body>
</html>
 