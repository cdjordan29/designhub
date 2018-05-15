<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>

<body>

	<!-- navigation bar -->
	<jsp:include page="NavBar.jsp" /> 

	<!-- header -->
	<div style="height: 10vh"></div>
	<div class="flex-center flex-column">
	<h2 class="animated fadeInDown mb-4">hello, <c:out value="${pageContext.request.remoteUser}"/> </h2>
	</div>

	<div class="col-sm-3 text-center">
		<sec:authorize access="hasRole('admin') || hasRole('mod')">
			<!-- Button trigger modal -->
			<button type="button" class="btn" data-toggle="modal"
				data-target="#escalatePermissions">Escalate User Permissions</button>

			<!-- Modal -->
			<div class="modal fade" id="escalatePermissions" tabindex="-1"
				role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="modalLabel">Escalate User Permissions</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="account/changePermissions" method="POST">
								<div class="md-form">
									<input type="text" name="email" class="form-control">
									<label for="email">User Email</label>
									<input type="radio" name="permission level" value="admin" checked> Admin<br>
  									<input type="radio" name="permission level" value="moderator"> Moderator<br>
  									<input type="radio" name="permission level" value="student"> Student
								</div>
								<div class="text-center mt-4">
									<button class="btn" type="submit" value="Submit">Update Permissions</button>
								</div>
								<!-- <input type="submit" value="Submit" /> -->
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>
	<!-- /modal -->

	<!-- SCRIPTS -->
    <!-- JQuery -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>
</body>

</html>
