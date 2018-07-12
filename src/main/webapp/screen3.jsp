<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Purchase Summary </title>

<link href="css/bootstrap.min.css"	rel="stylesheet">

</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<h2>
			Welcome ${pageContext.request.userPrincipal.name} | <a
				onclick="document.forms['logoutForm'].submit()">Logout</a>
		</h2>
		
		<h5> Summary of Purchase </h5>
		<form:label path = "seatcount" >${seatcount}  - Tickets purchased </form:label >
		
		
	</c:if>
	<script>
function myFunc($val) {
        alert($val);
       }
function myFunc2($val) {
    alert($val);
   }       
</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
