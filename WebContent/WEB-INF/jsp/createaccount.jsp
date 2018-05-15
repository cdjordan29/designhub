<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				
		<title>Create Account</title>
		<style>
		input[type=email]:focus:not([readonly]) + label {
            color: #212529; 
        }

        input[type=email]:focus:not([readonly]) {
            border-bottom: 1px solid #ff3547;
            box-shadow: 0 1px 0 0 #ff3547; 
        }
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
		
		<script
  		src="https://code.jquery.com/jquery-3.3.1.min.js"
  		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  		crossorigin="anonymous"></script>
  		
		<script>
		function validatePassword(){		
			return $('#password').val().length >= 7;
		}
		
		function validatePasswordConfirm(){
			if($('#passwordConfirm').val() !== $('#password').val()){
				return false;
			}
			return true;
		}
		
		function validateFirstName(){
			return true;
		}
		
		function validateLastName(){
			return true;
		}
		
		function validate(){ 
			ok = (validatePassword() && validatePasswordConfirm() && validateFirstName() && validateLastName());
			return ok;
		}
		</script>
</head>

<body>
	
<!-- header -->
<div style="height: 25vh">
<div class="flex-center flex-column">
	<h1 class="animated fadeInDown mb-4">Design Thinking HUB</h1>
</div>

<!-- create account -->
<div class="container">
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<!-- card -->
		<div class="card">
		<div class="card-body">
			<!-- form createaccount -->
			<form action="" method="POST" onsubmit="return validate()">
			<p class="h4 text-center mb-4">Create Account</p>
				<!-- email -->
				<div class="md-form">
					<i class="fa fa-user prefix grey-text"></i>
					<input type="email" id="email" name="email" autocomplete="off"/>
					<label for="email">Email</label>
				</div>
				<!-- password -->
				<div class="md-form">
					<i class="fa fa-lock prefix grey-text"></i>
					<input type="password" id="password" name="password" minlength="7" maxlength="45" autocomplete="off"/>
					<label for="password">Password <span style="font-size: 12px; font-style: italic;">(min length 7)</span></label>
				</div>
				<!-- password confirm -->
				<div class="md-form">
					<i class="fa fa-exclamation ml-1 prefix grey-text"></i>
					<input type="password" id="passwordConfirm" name="passwordConfirm"/>
					<label for="passwordConfirm">Confirm Password</label>
				</div>
				<!-- first name -->
				<div class="md-form">
					<i class="fa fa-tag prefix grey-text"></i>
					<input type="text" id="firstName" name="firstName"/>
					<label for="firstName">First Name</label>
				</div>
				<!-- last name -->
				<div class="md-form">
					<i class="fa fa-tag prefix grey-text"></i>
					<input type="text" id="lastName" name="lastName"/>
					<label for="lastName">Last Name</label>
				</div>
				
				<input type="hidden"name="${_csrf.parameterName}"value="${_csrf.token}"/>
				
				<div class="text-center mt-4">
					<button class="btn danger-color" type="submit" id="createAccountButton" value="Create">Create</button>
				</div>
			</form>
			<!-- /form createaccount -->	
		</div>
		<!-- /card body -->
		</div>
		<!-- /card -->
	<div class="col-md-3"></div>
</div>
</div>
<!-- /row -->
</div>	
<!-- /create account -->

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