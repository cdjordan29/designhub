<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hub.models.Podcast"%>
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
    
<style>
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
				<h2>Radford Recommended Podcasts</h2>
				</br>
			</div>

			<div class="row">
				<%
					java.util.List<Podcast> radfordRecommendeds = (java.util.ArrayList<Podcast>)request.getAttribute("radfordRecommendeds");
					for (int i = 0; i < radfordRecommendeds.size(); i++) {
				%>


				<div class="col-lg-3">

					<!-- Card 1 START -->

					<div class="card card-cascade wider reverse my-4">
						<!--Card image-->
							
							<%if(radfordRecommendeds.get(i).getDownload()!=""){ %>
							<div class="embed-responsive embed-responsive-4by3">
							   <iframe  
									src="<%=radfordRecommendeds.get(i).getDownload()%>" 
									frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
								</iframe>
								<a href="#!">
 									<div class="mask"></div>
 								</a>
 							</div>
 							<%
							} else {
 							%>
 							<div class="view overlay hm-white-slight">
							<img
								src="https://lh3.googleusercontent.com/-GiMe27B6kAA/U0hZYXHd99I/AAAAAAAAAB4/kEJaPTmEZxw/w530-h530-n-rw/1555295_579492948792126_383365863_n.jpg"
								class="img-fluid mx-auto shoppic"> <a href="#!">
								<div class="mask"></div>
							</a>
							</div>
 							<%
							}
 							%>
 						
						<!--/Card image-->
						<!--Card content-->
						<div class="card-body">
							<!--Title-->
							<h4 class="card-title"><%=radfordRecommendeds.get(i).getTitle()%></h4>
							<p class="card-text">
								<%=radfordRecommendeds.get(i).getDescription()%></p>
							

							<a href="<%=radfordRecommendeds.get(i).getDownload()%>"
								class="btn btn-danger" target="_blank"> Download </a>

							<sec:authorize access="hasRole('admin') || hasRole('mod')">
							
							<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#editPodcast<%=radfordRecommendeds.get(i).getId()%>">
    								Edit
								</button>
								<div class="modal fade" id="editPodcast<%=radfordRecommendeds.get(i).getId()%>" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
        							<div class="modal-content">
            						<div class="modal-header">
                						<h5 class="modal-title" id="modalLabel">Edit</h5>
                						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    					<span aria-hidden="true">&times;</span>
                						</button>
            						</div>
            						<div class="modal-body">
            							<form action="podcast/updatePodcast/<%=radfordRecommendeds.get(i).getId()%>" method="POST">
								<div class="md-form">
									<input type="text" name="title" class="form-control" value="<%=radfordRecommendeds.get(i).getTitle()%>">
									<label for="title">Title</label>
								</div>
								<div class="md-form">
									<input type="text" name="download" class="form-control" value="<%=radfordRecommendeds.get(i).getDownload()%>">
									<label for="download">YouTube Link</label>
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

								<form action="podcast/deletePodcast"
									method="POST">
									<input type="hidden" name="id"
										value="<%=radfordRecommendeds.get(i).getId()%>" />
									<button class="btn col-lg-12" type="submit" id="Delete">Delete</button>
									<!-- <input type="submit" value="Delete"/> -->
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
							</sec:authorize>
						</div>
						<!--/.Card content-->
					</div>
					<!-- Card 1 END-->
				</div>
				<!--col-sm-4 END-->

				<%
					}
				%>
			

			<div class="col-sm-3 text-center">
			</br>
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn col-lg-12" data-toggle="modal" data-target="#radfordPodcast">
    					Add New Recommendation
					</button>
					<!-- Modal -->
					<div class="modal fade" id="radfordPodcast" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Recommendation</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="podcast/addPodcast" method="POST">
								<div class="md-form">
									<input type="text" name="title" class="form-control">
									<label for="title">Title</label>
								</div>
								<div class="md-form">
									<input type="text" name="download" class="form-control">
									<label for="download">YouTube Link</label>
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
		</div>
		</div>

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