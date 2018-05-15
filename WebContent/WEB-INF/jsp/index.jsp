<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>HUB</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">
    <!-- custom styles -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">

<style>
.fixedBox {
	position: fixed;
	bottom: 0;
	right: 0;
	width: 280px;
	margin-left: auto;
	margin-right: auto;
	border: solid 3px black;
	height: 380px;
}

.fixedBoxHeader {
	height: 10%;
	border-bottom: solid 1px black;
}

.fixedBoxBody {
	overflow: auto;
	overflow-y: scroll;
	height: 90%;
}

.chatBoxBody {
	overflow: auto;
	overflow-y: scroll;
	height: 80%;
}

.chatBoxFooter {
	height: 10%;
}

.sent {
	background-color: #0099CC;
	color: white;
}
</style>

</head>

<body>

	<!-- navigation bar -->
	<jsp:include page="NavBar.jsp" /> 

	<!-- header -->
	<div style="height: 10vh">
	<div class="flex-center flex-column"></div>

	<!-- container with page links -->
	<div class="container">
		<button type="button" onclick="window.location.href='techniques'" class="btn btn-blue-grey waves-effect btn-lg btn-block">Techniques</button>
		<button type="button" onclick="window.location.href='community'" class="btn btn-danger waves-effect btn-lg btn-block">Community</button>
		<button type="button" onclick="window.location.href='readingroom'" class="btn btn-blue-grey waves-effect btn-lg btn-block">Reading Room</button>
		<button type="button" onclick="window.location.href='collaboration'" class="btn btn-danger waves-effect btn-lg btn-block">Collaboration Tools</button>
		<button type="button" onclick="window.location.href='podcast'" class="btn btn-blue-grey waves-effect btn-lg btn-block">Podcasts</button>
		<!-- <button type="button" onclick="window.location.href='creativeplay'" class="btn btn-danger waves-effect btn-lg btn-block">Creative Play</button> -->
	</div>
	<!-- /container with page links -->
</br></br>

	<div id="activeUsers" class="fixedBox">
		<div id="activeUsersHeader" class="fixedBoxHeader">Online Users</div>
		<div id="activeUsersList" class="fixedBoxBody"> 
		</div>
  	</div>	
	
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>
    <script src="resources/js/sockjs.min.js"></script>
    <script src="resources/js/stomp.min.js"></script>
	<script src="resources/js/chat.js"></script>
    <script>
    	init('${user}', '${activeUsers}')
    </script>
</body>

</html>