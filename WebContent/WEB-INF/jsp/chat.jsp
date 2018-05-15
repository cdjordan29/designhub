<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Design Hub</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mdb.min.css" />


</head>
<body>
	
	<!-- navigation bar -->
	<jsp:include page="NavBar.jsp" /> 
	
	<div id="activeUsers" class="fixedBox">
		<div id="activeUsersHeader" class="fixedBoxHeader">Online Users</div>
		<div id="activeUsersList" class="fixedBoxBody"> 
		</div>
  	</div>
    	
	<script src="resources/js/sockjs.min.js"></script>
    <script src="resources/js/stomp.min.js"></script>
    <script src="resources/js/chat.js"></script>
    <script>
    	init('${user}', '${activeUsers}')
    </script>
</body>
</html>