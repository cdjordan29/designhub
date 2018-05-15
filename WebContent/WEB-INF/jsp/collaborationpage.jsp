<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hub.models.RadfordRecommendedCollaboration"%>
<%@ page import="com.hub.models.StudentRecommendedCollaboration"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>HUB</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Material Design Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/mdb.min.css"
	rel="stylesheet">
<!-- custom styles -->
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">


<style>
.collabBlock {
	float: left;
	border: 2px black solid;
	margin: 5px;
	padding: 5px;
}

    #myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: red;
  color: white;
  cursor: pointer;
  padding: 12px;
  border-radius: 4px;
}

#myBtn:hover {
  background-color: #555;
}

</style>
</head>

<body>

	<!-- navigation bar -->
	<jsp:include page="NavBar.jsp" /> 

	<!-- header -->
	<div style="height: 10vh">
	<div class="flex-center flex-column"></div>

		<div class="container-fluid">
			<div class="flex-center flex-column" style="height: 15vh">
				<h2>Radford Recommended Collaboration Tools</h2>
				</br>
			</div>
			<div class="row">

				<%
					java.util.List<RadfordRecommendedCollaboration> radfordRecommendeds = (java.util.ArrayList<RadfordRecommendedCollaboration>) request.getAttribute("radfordRecommendeds");
					for (int i = 0; i < radfordRecommendeds.size(); i++) {
				%>
				<!-- Use this class for style -->
				<div class="col-lg-3">
					<div class="card">
					<!--Card image-->
						<div class="view overlay hm-white-slight">
							<img
								src="<%=radfordRecommendeds.get(i).getImage()%>"
								class="img-fluid mx-auto shoppic"> <a href="#!">
								<div class="mask"></div>
							</a>
						</div>
						<!--/Card image-->
						<div class="card-body">
							<h4 class="card-title"><%=radfordRecommendeds.get(i).getTitle()%></h4>
							<p class="card-text"><%=radfordRecommendeds.get(i).getDescription()%></p>

							<a href="<%=radfordRecommendeds.get(i).getDownload()%>"
								class="btn btn-danger" target="_blank"> Download </a>
								
							<sec:authorize access="hasRole('admin') || hasRole('mod')">

								<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#editRR<%=radfordRecommendeds.get(i).getId()%>">
    								Edit
								</button>
								<div class="modal fade" id="editRR<%=radfordRecommendeds.get(i).getId()%>" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
        							<div class="modal-content">
            						<div class="modal-header">
                						<h5 class="modal-title" id="modalLabel">Edit</h5>
                						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    					<span aria-hidden="true">&times;</span>
                						</button>
            						</div>
            						<div class="modal-body">
            							<form action="collaboration/updateRadfordRecommendation/<%=radfordRecommendeds.get(i).getId()%>" 
            								method="POST">
            								<div class="md-form">
												<input type="text" name="title" class="form-control" value="<%=radfordRecommendeds.get(i).getTitle()%>">
												<label for="title">Title</label>
											</div>
											<div class="md-form">
												<input type="text" name="download" class="form-control" value="<%=radfordRecommendeds.get(i).getDownload()%>">
												<label for="download">Download Link</label>
											</div>
											<div class="md-form">
												<input type="text" name="image" class="form-control" value="<%=radfordRecommendeds.get(i).getImage()%>">
												<label for="image">Image Link</label>
											</div>
											<div class="md-form">
												<input type="text" name="description" class="form-control" value="<%=radfordRecommendeds.get(i).getDescription()%>">
												<label for="description">Description</label>
											</div>
											<div class="text-center mt-4">
												<button class="btn" type="submit" value="Submit">Update</button>
											</div>
										<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
										</form>
            						</div>
            						</div>
            						</div>
								</div>	
															
								<form action="collaboration/deleteRadfordRecommendation"
									method="POST">
									<input type="hidden" name="id"
										value="<%=radfordRecommendeds.get(i).getId()%>" />
									<button class="btn col-lg-12" type="submit" id="Delete">Delete</button>
									<!--  <input type="submit" value="Delete"/>-->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
							
							</sec:authorize>
						</div>
					</div>
					</br>
				</div>

				<%
					}
				%>
				<div class="col-lg-3 text-center">
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#radfordRecommended">
    					Add New Recommendation
					</button>
					<!-- Modal -->
					<div class="modal fade" id="radfordRecommended" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Recommendation</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="collaboration/addRadfordRecommendation" method="POST">
								<div class="md-form">
									<input type="text" name="title" class="form-control">
									<label for="title">Title</label>
								</div>
								<div class="md-form">
									<input type="text" name="download" class="form-control">
									<label for="download">Download Link</label>
								</div>
								<div class="md-form">
									<input type="text" name="image" class="form-control">
									<label for="image">Image Link</label>
								</div>
								<div class="md-form">
									<input type="text" name="description" class="form-control">
									<label for="description">Description</label>
								</div>
								<div class="text-center mt-4">
									<button class="btn" type="submit" value="Submit">Add</button>
								</div>
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
			</div>
			<!-- /row -->

		</div>
		<!-- /container -->

		</br>
		</br>
		<hr />
		</br>
		</br>


		<div class="container-fluid">
			<div class="flex-center flex-column" style="height: 15vh">
				<h2>Student Recommended Collaboration Tools</h2>
			</div>
			<div class="row">
				<%
					java.util.List<StudentRecommendedCollaboration> studentRecommendeds = (java.util.ArrayList<StudentRecommendedCollaboration>) request.getAttribute("studentRecommendeds");
					for (int i = 0; i < studentRecommendeds.size(); i++) {
				%>
				<!-- Use this class for style -->
				<div class="col-lg-3">
					<div class="card">
					<!--Card image-->
						<div class="view overlay hm-white-slight">
							<img
								src="<%=studentRecommendeds.get(i).getImage()%>"
								class="img-fluid mx-auto shoppic"> <a href="#!">
								<div class="mask"></div>
							</a>
						</div>
						<!--/Card image-->
						<div class="card-body">
							<h4 class="card-title"><%=studentRecommendeds.get(i).getTitle()%></h4>
							
							<p class="card-text"><%=studentRecommendeds.get(i).getDescription()%></p>

							<a href="<%=studentRecommendeds.get(i).getDownload()%>"
								class="btn btn-danger" target="_blank"> Download </a>

								<form
									action="collaboration/thumbsUp/<%=studentRecommendeds.get(i).getId()%>"
									method="POST">
									<button type="submit" class="btn btn-default">
										<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
									</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <span><%=studentRecommendeds.get(i).getThumbsUp()%></span>
								</form>

								<form
									action="collaboration/thumbsDown/<%=studentRecommendeds.get(i).getId()%>"
									method="POST">
									<button type="submit" class="btn btn-default">
										<i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
									</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <span><%=studentRecommendeds.get(i).getThumbsDown()%></span>
								</form>

							<sec:authorize access="hasRole('admin') || hasRole('mod')">
							
							<!-- edit modal -->
								<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#editSR<%=studentRecommendeds.get(i).getId()%>">
    								Edit
								</button>
								<div class="modal fade" id="editSR<%=studentRecommendeds.get(i).getId()%>" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
        							<div class="modal-content">
            						<div class="modal-header">
                						<h5 class="modal-title" id="modalLabel">Edit</h5>
                						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    					<span aria-hidden="true">&times;</span>
                						</button>
            						</div>
            						<div class="modal-body">
            							<form action="collaboration/updateStudentRecommendation/<%=studentRecommendeds.get(i).getId()%>" 
            								method="POST">
            								<div class="md-form">
												<input type="text" name="title" class="form-control" value="<%=studentRecommendeds.get(i).getTitle()%>">
												<label for="title">Title</label>
											</div>
											<div class="md-form">
												<input type="text" name="download" class="form-control" value="<%=studentRecommendeds.get(i).getDownload()%>">
												<label for="download">Download Link</label>
											</div>
											<div class="md-form">
												<input type="text" name="image" class="form-control" value="<%=studentRecommendeds.get(i).getImage()%>">
												<label for="image">Image Link</label>
											</div>
											<div class="md-form">
												<input type="text" name="description" class="form-control" value="<%=studentRecommendeds.get(i).getDescription()%>">
												<label for="description">Description</label>
											</div>
											<div class="text-center mt-4">
												<button class="btn" type="submit" value="Submit">Update</button>
											</div>
										<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
										</form>
            						</div>
            						</div>
            						</div>
								</div>	
							<!-- /edit modal -->
							
								<form action="collaboration/deleteStudentRecommendation"
									method="POST">
									<input type="hidden" name="id"
										value="<%=studentRecommendeds.get(i).getId()%>" />
									<button class="btn col-lg-12" type="submit" id="Delete">Delete</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
							
							</sec:authorize>

						</div>
						<!--/card-body-->
					</div>
					<!-- /card -->
				</div>
				<!-- /col-sm-3 -->

				<%
					}
				%>
				<div class="col-lg-3 text-center">
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#studentRecommended">
    					Add New Recommendation
					</button>

					<!-- Modal -->
					<div class="modal fade" id="studentRecommended" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Recommendation</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="collaboration/addStudentRecommendation" method="POST">
								<div class="md-form">
									<input type="text" name="title" class="form-control">
									<label for="title">Title</label>
								</div>
								<div class="md-form">
									<input type="text" name="download" class="form-control">
									<label for="download">Download Link</label>
								</div>
								<div class="md-form">
									<input type="text" name="image" class="form-control">
									<label for="image">Image Link</label>
								</div>
								<div class="md-form">
									<input type="text" name="description" class="form-control">
									<label for="description">Description</label>
								</div>
								<div class="text-center mt-4">
									<button class="btn" type="submit" value="Submit">Add</button>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
            			</div>
        				</div>
    					</div>
					</div>
					<!-- /modal -->
					</sec:authorize>
				</div>
				<!-- /col-sm-3 -->
			</div>
			<!-- /row -->
		</div>
<<<<<<< HEAD
		<!-- /container -->
=======
		
		<button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa fa-arrow-up"></i></button>
		<script>
		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {scrollFunction()};

		function scrollFunction() {
    	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        	document.getElementById("myBtn").style.display = "block";
    	} else {
        	document.getElementById("myBtn").style.display = "none";
   		}
		}

		// When the user clicks on the button, scroll to the top of the document
		function topFunction() {
    		document.body.scrollTop = 0;
    		document.documentElement.scrollTop = 0;
		}
		</script>
		</br></br>
>>>>>>> 485b42cd7b3ce84c97fecd84424393977aec7e8b

		<!-- SCRIPTS -->
		<!-- JQuery -->
		<script
			src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
		<!-- Bootstrap tooltips -->
		<script
			src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
		<!-- Bootstrap core JavaScript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<!-- MDB core JavaScript -->
		<script
			src="${pageContext.request.contextPath}/resources/js/mdb.min.js"></script>
</body>

</html>