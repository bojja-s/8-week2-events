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
	<title>Events View</title>
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
		<h4>Here are some of the events in your state</h4>
	</c:if>
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
		<c:forEach var="event" items="${hoststateevents}">
				<tr>
					<td> ${event.name} </td>
					<td>${event.date}</td>
					<td>${event.user.location} </td>
					<td>${event.user.firstName}</td>
					<td>
					<spring:url value="/events/${event.id}/${event.actions[0]}" var="actionOneURL" />
					<spring:url value="/events/${event.id}/${event.actions[1]}" var="actionTwoURL" />
					<button class="btn btn-primary" onclick="location.href='${actionOneURL}'">${event.actions[0]}</button>
					<button class="btn btn-primary" onclick="location.href='${actionTwoURL}'">${event.actions[1]}</button>
				</tr>
		</c:forEach>
	</table>
	<h4>Here are some of the events in other states</h4>
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
		<c:forEach var="otherstatesevent" items="${otherstatesevents}">
				<tr>
					<td> ${otherstatesevent.name} </td>
					<td>${otherstatesevent.date}</td>
					<td>${otherstatesevent.location}, ${otherstatesevent.state} </td>
					<td>${otherstatesevent.user.firstName}</td>
					<td>
					<spring:url value="/events/${otherstatesevent.id}/${otherstatesevent.actions[0]}" var="actionOneURL" />
					<spring:url value="/events/${otherstatesevent.id}/${otherstatesevent.actions[1]}" var="actionTwoURL" />
					<button class="btn btn-primary" onclick="location.href='${actionOneURL}'">${otherstatesevent.actions[0]}</button>
				</tr>
		</c:forEach>
	</table>	
	Create an Event
	<div class="container1" id="signin">
	<form method="POST" action="${contextPath}/events" class="form-signin">
		<div class="form-group ${error != null ? 'has-error' : ''}">
			<span>${message}</span>
			<div>
				<label for="nameInput">&nbsp;&nbsp;&nbsp;Name: </label>  
				<input name="name" type="text" class="form-control" placeholder="" autofocus="true"/>
			</div>
			<br/>
			<div>
				<label for="dateInput">&nbsp;&nbsp;&nbsp;Date: </label>
				<input name="date" type="date" class="form-control" placeholder=""/>
				<span>${error}</span>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
			<br/>
			<div>
				<label for="locationInput">&nbsp;&nbsp;&nbsp;Location: </label>  
				<input name="location" type="text" class="form-control" placeholder="" autofocus="true"/>
				<select name="state">
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
				</select>	            
			</div>
			<br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</div>
	</form>		
	<c:if test="${not empty msg}">
	<div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
		</div>
		</c:if>    
		</div>	
	</div>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	</body>
</html>