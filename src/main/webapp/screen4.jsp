<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Event Details View</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
<style>
#wrapper {
  margin-left: 300px;
}
#signin {
  float: right;
  width: 70%;
   margin-left: -200px
  
}
#signup {
  float: left;
  width: 80%;
  margin-left: -200px;
  border: 2px solid black
  
}
#cleared {
  clear: both;
}
</style>    
</head>

<body>
	<h2>Name:${selectedevent.name}</h2>
	<div>
		<table>
			<tr><td> Host: ${selectedevent.host}</td></tr>
			<tr><td> Date: ${selectedevent.date}</td></tr>	
			<tr><td> Location: ${selectedevent.location}</td></tr>	
			<tr><td> People who are attending this event: ${allusersforlisting.size()}</td></tr>	
		</table>
	</div>	
<div id="wrapper">
				
<div class="container1" id="signin">
	<form id="eventform" method="POST" action="${contextPath}/events/${selectedevent.id}/updatedetails">
		<table >				
			<tr ><td><h3>Message Wall</h3></td></tr>
			<tr border="-1"><td><textarea id="taMessageWall" name="advanced" wrap="hard" disabled="disabled"></textarea></td></tr>
		</table>
		<table border="-1">				
			<tr><td><h6>Add comment:</h6></td></tr>
			<tr><td><input type="text" /><tr><td>
			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</table>	
	</form>
</div>

<div class="container2" id="signup" >
		<c:forEach var="userlist" items="${allusersforlisting}">
			<tr>
				<td>${userlist[0]}</td>
				<td>${userlist[1]}</td>
				<td>${userlist[2]}</td>
			</tr>
		</c:forEach>
	</div>
<div id="cleared"></div>	
</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
    </script>
</body>
</html>
