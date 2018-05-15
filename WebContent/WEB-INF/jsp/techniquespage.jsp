<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.hub.models.TechniqueCategory"%>
<%@ page import="com.hub.models.TechniqueSubCategory"%>
<%@ page import="com.hub.models.Technique"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
    .card-header{background-color: #3F729B;}
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

		<!-- content begins-->
	<div class="container" id="#top-section">

		<!-- table of contents -->

		<div class="row">
		<!-- container with all buttons -->
		<div class="container-fluid">
		<%
		java.util.List<TechniqueCategory> techniqueCategory = (java.util.ArrayList<TechniqueCategory>) request.getAttribute("techniqueCategorys");
		for (int i = 0; i < techniqueCategory.size(); i++) {
		%>
		
		<button type="button" onclick="window.location.href='#<%=techniqueCategory.get(i).getName()%>'" class="btn unique-color waves-effect btn-lg btn-block"><%=techniqueCategory.get(i).getName()%></button>
		<sec:authorize access="hasRole('admin') || hasRole('mod')">
			<form action="techniques/deleteTechniqueCategory" method="POST">
				<input type="hidden" name="id"
					value="<%=techniqueCategory.get(i).getId()%>" />
				<button class="btn col-lg-4" type="submit" id="Delete">Delete <%=techniqueCategory.get(i).getName()%></button>
				<!--  <input type="submit" value="Delete"/>-->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>								
		</sec:authorize>
		<%
		}
		%>	

		
					<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					</br>
					<button type="button" class="btn col-lg-4" data-toggle="modal" data-target="#techniqueSection">
    					Add New Technique
					</button>
					<!-- Modal -->
					<div class="modal fade" id="techniqueSection" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Technique</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="techniques/addTechniqueCategory" method="POST">
								<div class="md-form">
									<input type="text" name="category" class="form-control">
									<label for="category">New Technique</label>
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
				
				<!-- /modal -->
		</div>
		</div>

	
		<!-- END table of contents -->
		</br></br></br></br></br></br></br></br></br></br><!-- lol --></br></br></br></br></br></br></br></br></br></br></br>
		<!-- BEGIN interviewing section -->
		<%
		java.util.List<TechniqueCategory> techniqueSection = (java.util.ArrayList<TechniqueCategory>) request.getAttribute("techniqueCategorys");
		for (int i = 0; i < techniqueSection.size(); i++) {
		%>
		<div style="height: 15vh"></div>
		<div class="container-fluid" id="<%=techniqueCategory.get(i).getName()%>">
			<!-- header -->
			
			<div class="flex-center flex-column">
				<h3><%=techniqueCategory.get(i).getName()%></h3>
			</div>
		<div id="accordian">
		
			<%
		java.util.List<TechniqueSubCategory> techniqueSubSection = (java.util.ArrayList<TechniqueSubCategory>) request.getAttribute("techniqueSubCategories");
		for (int x = 0; x < techniqueSubSection.size(); x++) {
			if(techniqueSubSection.get(x).getCategoryId()==techniqueCategory.get(i).getId()) {
		%>
		
		<div class="card">	
		<div class="card-header" id="header<%=techniqueSubSection.get(x).getId() %>">
		<h5 class="mb-0">
			<button class="btn btn-link" data-toggle="collapse" data-target="#collapse<%=techniqueSubSection.get(x).getId() %>"
				aria-expanded="true" aria-controls="collapse<%=techniqueSubSection.get(x).getId() %>"><%=techniqueSubSection.get(x).getName() %></button>
		</h5>
		</div>
		<div id="collapse<%=techniqueSubSection.get(x).getId() %>" class="collapse" aria-labelledby="heading<%=techniqueSubSection.get(x).getName() %>"
			data-parent="#accordion">
			<div class="card-body">
				<h4 class="card-title"><%=techniqueSubSection.get(x).getName() %></h4>
				<p><%=techniqueSubSection.get(x).getDescription()%></p>
				<sec:authorize access="hasRole('admin') || hasRole('mod')">
				
				<button type="button" class="btn pull-left col-lg-7" data-toggle="modal" data-target="#editSection<%=techniqueSubSection.get(x).getId()%>">
    								Edit <%=techniqueSubSection.get(x).getName()%>
								</button>
								<div class="modal fade" id="editSection<%=techniqueSubSection.get(x).getId()%>" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
									<div class="modal-dialog" role="document">
        							<div class="modal-content">
            						<div class="modal-header">
                						<h5 class="modal-title" id="modalLabel">Edit</h5>
                						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    					<span aria-hidden="true">&times;</span>
                						</button>
            						</div>
            						<div class="modal-body">
            							<form action="techniques/updateTechniqueSubCategory/<%=techniqueSubSection.get(x).getId()%>" 
            								method="POST">
            								<div class="md-form">
												<input type="text" name="name" class="form-control" value="<%=techniqueSubSection.get(x).getName()%>">
												<label for="name">Title</label>
											</div>
											<div class="md-form">
											<textarea class="form-control rounded-0" name="description" rows="15"><%=techniqueSubSection.get(x).getDescription()%></textarea>
												
												<label for="description">Content</label>
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
				
				<form action="techniques/deleteTechniqueSubCategory" method="POST">
				<input type="hidden" name="id"
					value="<%=techniqueSubSection.get(x).getId()%>" />
				<button class="btn pull-left col-lg-4" type="submit" id="Delete">Delete <%=techniqueSubSection.get(x).getName()%></button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>					
					
		</sec:authorize>
			</div>
			
		</div>
	
		</div>
		
		<%
		}}
		%>
		</div>
		</br>
			<div class="col-sm-10 text-left">
				<sec:authorize access="hasRole('admin') || hasRole('mod')">
					<!-- Button trigger modal -->
					<button type="button" class="btn col-lg-4" data-toggle="modal" data-target="#section<%=techniqueCategory.get(i).getId()%>">
    					Add to <%=techniqueCategory.get(i).getName()%>
					</button>
					<!-- Modal -->
					<div class="modal fade" id="section<%=techniqueCategory.get(i).getId()%>" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    					<div class="modal-dialog" role="document">
        				<div class="modal-content">
            			<div class="modal-header">
                			<h5 class="modal-title" id="modalLabel">New Technique</h5>
                			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    		<span aria-hidden="true">&times;</span>
                			</button>
            			</div>
            			<div class="modal-body">
                			<form action="techniques/addTechniqueSubCategory" method="POST">
							    <div class="md-form">
									<input type="text" name="name" class="form-control">
									<label for="name">Title</label>
								</div>
								<div class="md-form">
									<textarea class="form-control rounded-0" name="description" rows="15"></textarea>
									<label for="description"> Content</label>
								</div>
								<input type="hidden" name="categoryId" value="<%=techniqueCategory.get(i).getId()%>">
								<div class="text-center mt-4" >
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
		<%
		}
		%>	
		
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

	</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
	
	
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