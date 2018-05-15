<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hub.models.CommunityBulletin"%>
<%@ page import="com.hub.models.CommunityEvent"%>
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


</head>

<body>

	<!-- navigation bar -->
	<jsp:include page="NavBar.jsp" /> 

	<!-- header -->
	<div style="height: 10vh">
	<div class="flex-center flex-column"></div>

		<div class="container-fluid">
			<div class="flex-center flex-column" style="height: 15vh">
				<h2>Community Bulletins</h2>
				</br>
			</div>

			<div class="row">
				<%
					java.util.List<CommunityBulletin> communityBulletins = (java.util.ArrayList<CommunityBulletin>) request.getAttribute("communityBulletins");
					for (int i = 0; i < communityBulletins.size(); i++) {
				%>
				<!-- Use this class for style -->
				<div class="col-sm-3">
					<div class="card">
					<!--Card image-->
						<!--<div class="view overlay hm-white-slight">
							<img
								src="https://lh3.googleusercontent.com/-GiMe27B6kAA/U0hZYXHd99I/AAAAAAAAAB4/kEJaPTmEZxw/w530-h530-n-rw/1555295_579492948792126_383365863_n.jpg"
								class="img-fluid"> <a href="#!">
								<div class="mask"></div>
							</a>
						</div>-->
						<!--/Card image-->
						<div class="card-body">
							<h4 class="card-title"><%=communityBulletins.get(i).getDescription()%></h4>
							<div>
								<form
									action="community/thumbsUp/<%=communityBulletins.get(i).getId()%>"
									method="POST">
									<button type="submit" class="btn btn-default px-6">
										<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
									</button>
									<!-- <input type="submit" value="+" /> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <span><%=communityBulletins.get(i).getThumbsUp()%></span>
								</form>

								<form
									action="community/thumbsDown/<%=communityBulletins.get(i).getId()%>"
									method="POST">
									<button type="submit" class="btn btn-default px-6">
										<i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
									</button>
									<!-- <input type="submit" value="-" /> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /> <span><%=communityBulletins.get(i).getThumbsDown()%></span>
								</form>

							</div>
							<sec:authorize access="hasRole('admin') || hasRole('mod')">

								<form action="community/deleteBulletin"
									method="POST">
									<input type="hidden" name="id"
										value="<%=communityBulletins.get(i).getId()%>" />
									<button class="btn" type="submit" id="Delete">Delete</button>
									<!-- <input type="submit" value="Delete"/> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
							</sec:authorize>
						</div>
					</div><!-- Card 1 END-->
				</div>

				<%
					}
				%>
				<div class="col-sm-3 text-center">
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn" data-toggle="modal" data-target="#communityBulletin">
    					Add New Bulletin
					</button>

					<!-- Modal -->
					<div class="modal fade" id="communityBulletin" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Bulletin</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="community/addBulletin" method="POST">
									<div class="md-form">
										<input type="text" name="description" class="form-control">
										<label for="description">Description</label>
									</div>
									<div class="text-center mt-4">
										<button class="btn" type="submit" value="Submit">Add</button>
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
				</div><!-- /modal -->
			</div><!-- /row -->	
		</div><!-- /container -->
		
		</br>
		</br>
		<hr />
		</br>
		</br>
		
		<div class="container-fluid">
			<div class="flex-center flex-column" style="height: 15vh">
				<h2>Upcoming Events</h2>
			</div>

			<div class="row">
				<%
					java.util.List<CommunityEvent> communityEvents = (java.util.ArrayList<CommunityEvent>)request.getAttribute("communityEvents");
					for (int i = 0; i < communityEvents.size(); i++) {
				%>
				<!-- Use this class for style -->
				<div class="col-sm-3">
					<div class="card">
						<!--Card image-->
						<!-- <div class="view overlay hm-white-slight">
							<img
								src="https://lh3.googleusercontent.com/-GiMe27B6kAA/U0hZYXHd99I/AAAAAAAAAB4/kEJaPTmEZxw/w530-h530-n-rw/1555295_579492948792126_383365863_n.jpg"
								class="img-fluid"> <a href="#!">
								<div class="mask"></div>
							</a>
						</div>-->
						<!--/Card image-->
						<div class="card-body">
							<h4 class="card-title"><%=communityEvents.get(i).getDate()%></h4>
							<p><%=communityEvents.get(i).getDescription()%></p>
							
							<sec:authorize access="hasRole('admin') || hasRole('mod')">

								<form action="community/deleteEvent"
									method="POST">
									<input type="hidden" name="id"
										value="<%=communityEvents.get(i).getId()%>" />
									<button class="btn" type="submit" id="Delete">Delete</button>
									<!-- <input type="submit" value="Delete"/> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
							</sec:authorize>

						</div><!--/card-body-->
					</div><!--/card-->
				</div><!--/col-sm-3-->

				<%
					}
				%>
			
				<div class="col-sm-3 text-center">
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn" data-toggle="modal" data-target="#communityEvent">
    					Add New Event
					</button>
					
					<!-- Modal -->
					<div class="modal fade" id="communityEvent" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Event</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="community/addEvent" method="POST">
									<div class="md-form">
										<input type="date" name="date" class="form-control">
										<label for="date"></label>
									</div>
									<div class="md-form">
										<input type="text" name="description" class="form-control">
										<label for="description">Description</label>
									</div>
									<div class="text-center mt-4">
										<button class="btn" type="submit" value="Submit">Add</button>
									</div>
									<!-- <input type="submit" value="Submit" /> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
							</form>
            			</div>
        				</div>
    					</div>
					</div><!-- /modal -->
					</sec:authorize>
				</div><!-- /col-sm-3 -->
			</div><!-- /row -->
		</div><!-- /container-->

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
