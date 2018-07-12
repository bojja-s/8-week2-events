<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Book Tickets View</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">

</head>

<body>
<div class="container">
	<!--  Welcome user and Logout -->
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
		<h2>
			Welcome, ${pageContext.request.userPrincipal.name} | 
			<a onclick="document.forms['logoutForm'].submit()">Logout</a>
		</h2>
		<h4>Here are some of the events in your store</h4>
	</c:if>
<h1>All Users</h1>
	
<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Date</th>
					<th>Location</th>
					<th>Host</th>
					<th>Action/Status</th>
				</tr>
			</thead>

			<c:forEach var="event" items="${events}">
				<tr>
					<td> ${event.name} </td>
					<td>${event.date}</td>
					<td>${event.location}</td>
					<td>${event.host}</td>
					<td>
						<spring:url value="/events/${event.id}" var="joinUrl" />
						<spring:url value="/events/${event.id}/edit" var="editUrl" />
						<spring:url value="/events/${event.id}/delete" var="deleteUrl" /> 
						

						<button class="btn btn-info" onclick="location.href='${joinUrl}'">Join</button>
						<button class="btn btn-primary" onclick="location.href='${editUrl}'">Edit</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>
		Create an Event
		<div class="container1" id="signin">
    		<form method="POST" action="${contextPath}/events" modelAttribute="eventForm" class="form-signin">
				<spring:bind path="name">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:hidden path=""/>
						<label for="name">&nbsp;&nbsp;&nbsp;Name:&nbsp;&nbsp;&nbsp; </label>  
						<form:input type="text" path="name" class="form-control" placeholder="" autofocus="true"/>
						<form:errors path="name"></form:errors>
					</div>
					<br/>
				</spring:bind>   
				<spring:bind path="date">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:hidden path=""/>
						<label for="name">&nbsp;&nbsp;&nbsp;Date:&nbsp;&nbsp;&nbsp; </label>  
						<form:input type="date" path="date" class="form-control" placeholder="" autofocus="true"/>
						<form:errors path="date"></form:errors>
					</div>
					<br/>
				</spring:bind>  
				<spring:bind path="location">
					<div class="form-group ${status.error ? 'has-error' : ''}">
						<form:hidden path=""/>
						<label for="name">&nbsp;&nbsp;&nbsp;Location:&nbsp;&nbsp;&nbsp; </label>  
						<form:input type="text" path="location" class="form-control" placeholder="" autofocus="true"/>
						<form:errors path="location"></form:errors>
					</div>
					<br/>
				</spring:bind>  								 		
    		</form>
    	</div>	
	</div>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
