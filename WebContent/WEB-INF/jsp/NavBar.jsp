<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- navigation bar -->
	<nav class="navbar navbar-expand-lg navbar-dark danger-color">
		<a class="navbar-brand" href="index">Design Thinking HUB</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="index">Home</a> 
				<a class="nav-item nav-link" href="techniques">Techniques</a> 
				<a class="nav-item nav-link" href="community">Community</a> 
				<a class="nav-item nav-link" href="readingroom">Reading Room</a> 
				<a class="nav-item nav-link" href="collaboration">Collaboration Tools</a> 
				<a class="nav-item nav-link" href="podcast">Podcasts</a> 
				<!-- <a class="nav-item nav-link" href="creativeplay">Creative Play</a> -->
				<a class="nav-item nav-link" href="account">My Account</a> 
				<a class="nav-item nav-link" href="<c:url value="/logout" />">Logout</a>
			</div>
		</div>
	</nav>
	<!-- /navigation bar -->