<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/mdb.min.css" rel="stylesheet">
<!-- custom styles -->
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<title>Login page</title>
<style>
input[type=text]:focus:not([readonly]) + label {
            color: #212529; 
        }

        input[type=text]:focus:not([readonly]) {
            border-bottom: 1px solid #ff3547;
            box-shadow: 0 1px 0 0 #ff3547; 
        }
input[type=password]:focus:not([readonly]) + label {
            color: #212529; 
        }

        input[type=password]:focus:not([readonly]) {
            border-bottom: 1px solid #ff3547;
            box-shadow: 0 1px 0 0 #ff3547; 
        }
</style>
</head>
<body>

<!-- header -->
<div style="height: 25vh">
<div class="flex-center flex-column">
	<h1 class="animated fadeInDown mb-4">Design Thinking HUB</h1>
</div>

<!-- login -->
<div class="container">
<div class="row">
<div class="col-md-3"></div>
<div class="col-md-6">

	<!-- Card -->
	<div class="card">
	<!-- Card body -->
    <div class="card-body">
    <!-- form login -->
	<form name="f" action="<spring:url value='/login_perform'/>" method="POST">
    	<p class="h4 text-center mb-4">Sign in</p>
    	
    	<!-- error messages -->
    	<c:if test="${not empty error}">
			<div class="error text-center">${error}</div>
		</c:if>
		<c:if test="${not empty sessionError}">
			<div class="error text-center">${sessionError}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg text-center">${msg}</div>
		</c:if>
		</br>
		
    	<!-- input username -->
    	<div class="md-form">
        	<i class="fa fa-user prefix grey-text"></i>
        	<input type="text" id="username" name="username" class="form-control" autocomplete="off">
        	<label for="username">Username</label>
    	</div>

    	<!-- input password -->
    	<div class="md-form">
        	<i class="fa fa-lock prefix grey-text"></i>
        	<input type="password" id="password" name="password" class="form-control" autocomplete="off">
        	<label for="password">Password</label>
    	</div>
    	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
    	<div class="text-center mt-4">
        	<button class="btn danger-color" type="submit" id="loginButton">Login</button>
    	</div>
    	</br>
    	<div class="modal-footer">
    		<p> Not a member?  <a href="createAccount">Sign Up</a></p>
    	</div>
	</form>
	<!-- /form login -->
    </div>
    <!-- /Card body -->
	</div>
	<!-- /Card -->
	
<div class="col-md-3"></div>
</div>
</div>
<!-- /row -->
</div>
<!-- /login -->

<!-- JQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>    
</body>
</html>