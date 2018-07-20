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
</head>
<body>
	<div class="container">
	<h2> Event Details</h2>
	<div class="container1" id="signin">
		<form id="eventform" method="POST" action="${contextPath}/events/update" class="form-signin">
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>
				<div>
					<h4>
						<label for="nameInput">&nbsp;&nbsp;&nbsp;Name: ${selectedevent.name}</label>  
					</h4>
					<label for="nameInput">&nbsp;&nbsp;&nbsp;Host${selectedevent.host}</label>  
					<label for="nameInput">&nbsp;&nbsp;&nbsp;Date: ${selectedevent.date}</label>  
					<label for="nameInput">&nbsp;&nbsp;&nbsp;Location: ${selectedevent.name}</label>  
					
					<label for="nameInput">&nbsp;&nbsp;&nbsp;People who are attending this event: ${selectedevent.name}</label>  
				</div>
				<div style="width: 100%; height: 50%; background-color: red; clear:both">-</div>
    			<div style="width: 50%; height: 50%; background-color: green; float:left;">
					<table height="100%" width="100%" border="1">
					      <tr height="100">
					        <td>This is item text.</td>
					      </tr>
					
					      <tr>
					        <td>This is item text 2.</td>
					      </tr>
					    </table>
    			</div>
   				 <div style="width: 50%; height: 50%; background-color: blue; float:right;">
   				 	<h3> Message Wall </h3>	
   				 	<textarea id="taMessageWall" name="advanced" rows="3" cols="33" maxlength="200" wrap="hard">
	        		</textarea>	
   				 </div>
 
			</div>
		</form>		
	</div>
	</div>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
    </script>
	</body>
</html>	