<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="smartclicker.SmartUser" %>

<html>
  <head>
  	<link rel="stylesheet" href="resources/css/index.css">
  	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
  </head>
  <%
  String userName = request.getParameter("userName");
    if (userName == null) {
    	userName = "default";
    }
    pageContext.setAttribute("userName", userName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    %>
   <ul class="border">
		<li class="topbar" style="font-weight:bold"><a href="home.jsp">Smart Clicker</a></li>
		
		<ul style="float:right; list-style-type:none;">
			
			<li class="topbar">
				<%
				if (user != null) {
					pageContext.setAttribute("user", user);
					%>
					Welcome ${fn:escapeXml(user)} 
					<li class="topbar">
						<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign out</a>
					</li>
					<%
	    		} else {
					%>
					<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
					<%
	    		}
				%>
			</li>
  		</ul>
  </ul>
  <% /*STORE QUIZZES LIKE POSTS WERE STORED IN THE BLOG*/ %>
  
  <body>
		<div id="page-wrap">
			<div>
			  	<h1>Quiz Creation!</h1>
			  	<h2>To create a quiz, fill out the Blanks.</h2>
			</div>
			
			<div class="w3-card-4" style="width:100%; padding-bottom: 0px;">
				<header class="w3-container w3-orange">
					<h6>Your Quiz:</h6>
				</header>
		    	<form action="/teacher" method="get" style="padding: 20px;">
		    		<div><textarea class="title" name="title" rows="1" cols="60" placeholder="Insert title here"></textarea></div>
		      		<div><textarea name="question" rows="3" cols="60" placeholder="Type your question here"></textarea></div>
		      		<h4>Set answer choices:</h4>
		      		<div><textarea class="title" name="answer1" rows="1" cols="60" placeholder="Input Choice 1"></textarea></div>
		      		<div><textarea class="title" name="answer2" rows="1" cols="60" placeholder="Input Choice 2"></textarea></div>
		      		<div><textarea class="title" name="answer3" rows="1" cols="60" placeholder="Input Choice 3"></textarea></div>
		      		<div><textarea class="title" name="answer4" rows="1" cols="60" placeholder="Input Choice 4"></textarea></div>
		      		<h4>Enter the Correct Answer Choice Number:</h4><h6>ex) 1</h6>
		      		<div><textarea class="title" name="correct" rows="1" cols="20" placeholder=" "></textarea></div>
		      		<div><input style="margin-top: 10px;" type="submit" class="original" value="Create" /></div>
		      		<input type="hidden" name="userName" value="${fn:escapeXml(userName)}"/>
		   	 	</form>
		 	</div>
		</div>   
  	</body>
<html>