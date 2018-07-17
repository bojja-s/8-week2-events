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
	<title>Events View</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	<h2> Edit Event - ${editevent.name} </h2>
	<div class="container1" id="signin">
		<form id="eventform" method="POST" action="${contextPath}/events/update" class="form-signin">
			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>
				<div>
					<label for="nameInput">&nbsp;&nbsp;&nbsp;Name: </label>  
					<input name="name" type="text" class="form-control" placeholder="" autofocus="true" value="${editevent.name}"/>
				</div>
				<br/>
				<div>
					<label for="dateInput">&nbsp;&nbsp;&nbsp;Date: </label>
					<input name="date" type="date" class="form-control" placeholder="" value="2013-01-08"/>
					<span>${error}</span>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
				<br/>
				<div>
					<label for="locationInput">&nbsp;&nbsp;&nbsp;Location: </label>  
					<input name="location" type="text" class="form-control" placeholder="" autofocus="true" value="${editevent.state}"/>
					<select id="stateObj" name="state">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
					</select>	            
				</div>
				<br/>
				<input type="hidden" name="host" value="${editevent.host}" />
				<input type="hidden" name="id" value="${editevent.id}" />
				<input type="hidden" name="status" value="${editevent.status}" />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
			</div>
		</form>		
	</div>
	</div>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
        for (iLoop = 0; iLoop< document.getElementById("stateObj").length; iLoop++) {
        	if (document.getElementById("stateObj")[iLoop].value == 'AZ') {
        		document.getElementById("stateObj").selectedIndex = iLoop;
        		break;
        	}
        }
      </script>
	</body>
</html>	