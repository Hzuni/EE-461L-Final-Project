<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
<%@ page import="smartclicker.SmartClickerServlet" %>


<html>
  <head>
  	<link rel="stylesheet" href="resources/css/index.css">
  	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
  </head>
  	<%
  	UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
  	if(user != null){
    	SmartClickerServlet.updatingObjectify(user.getUserId());
  	}
    String userName = request.getParameter("userName");
    if (userName == null) {
    	userName = "default";
    }
    pageContext.setAttribute("userName", userName);
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Key userKey = KeyFactory.createKey("User", userName);
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
  
  <%
  if (user != null) {
	/*
		Need to ensure that our keys are correct
		Issue is managing the information in Datastore, need appropriate keys to find existing users
		possible solution: create an arraylist of userKeys and store that in the datastore
	*/
	/*String id = "";
	SmartUser smartUser= new SmartUser(id);
	try {
		datastore.get(smartUser.getGoogleId());
		//Existing User
	}
	catch(com.google.appengine.api.datastore.EntityNotFoundException e) {
		//New User
	}*/
	
	pageContext.setAttribute("user", user);
	%>
	<body>
		<div id="page-wrap">
			<h1>Welcome to Smart Clicker!</h1>
	        <div style="padding-bottom: 20px;">
	        	<div class="w3-card-4" style="width:100%; padding-bottom: 0px; margin-bottom: 10px;">
	          		<header class="w3-container w3-orange">
						<h3>My Created Classes</h3>
					</header>
		            <div class="w3-container">
		            	<!-- This is where the created classes will be populated -->
		            
		            	<!-- This is where the created classes will be populated -->
		            	<form action = /teacher method = "post" style="padding: 20px;">
				      		<div><input style="margin-top: 10px; margin-left: 900px; width: 130px;" type="submit" class="original" value="Create Class" /></div>
				   	 	</form>
				   	 	
				   	 	<%

				   	    Query query = new Query("Question", userKey).addSort("date", Query.SortDirection.DESCENDING);
				   	    List<Entity> questions = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(200));
				   	    
				        for (Entity question : questions) {
				        	int check = 1;			//REPLACE WITH AN ACTUAL CHECK TO MAKE SURE THE TEACHER CREATED THIS QUIZ
				        	if(check == 1){
				                pageContext.setAttribute("question_title", question.getProperty("title"));
					            pageContext.setAttribute("question_content", question.getProperty("content"));
				                %>
				                <div style="padding-bottom: 20px;">
				                <div class="w3-card-4" style="width:100%; padding-bottom: 0px;">
					                <header class="w3-container w3-orange">
									  <h3>${fn:escapeXml(post_title)}</h3>
									</header>
					                <div class="w3-container">
						                <p><b>${fn:escapeXml(post_user.nickname)}</b> wrote:</p>
							            <blockquote>${fn:escapeXml(post_content)}</blockquote>
							            <p style="color:grey;">On: ${fn:escapeXml(post_date)}</p>
						            </div>
					            </div>
					            </div>
					            <%
					            
				        	}
				        }
				   	 	%>
				   	 	
				   	 	<form action = /teacher method = "get" style="padding: 20px;">
				      		<div><input style="margin-top: 10px; margin-left: 900px; width: 130px;" type="submit" class="original" value="Create Quiz" /></div>
				   	 	</form>
				   	 	
		       		</div>
	      		</div>
	      		<div class="w3-card-4" style="width:100%; padding-bottom: 0px; margin-bottom: 10px;">
	          		<header class="w3-container w3-orange">
						<h3>My Joined Classes</h3>
					</header>
		            <div class="w3-container">
		            	<!-- This is where the joined classes will be populated -->
		            
		            	<!-- This is where the joined classes will be populated -->
		            	<form action = /student method = "get" style="padding: 20px;">
				      		<div><input style="margin-top: 10px; margin-left: 900px; width: 130px;" type="submit" class="original" value="Join Class" /></div>
				   	 	</form>
				   	 	
		       		</div>
	      		</div>
	     	</div>
		 </div>   
  	</body>
	<%
  } else {
	%>
	<body>
		<div id="page-wrap">
			<div>
			  	<h1>Welcome to Smart Clicker!</h1>
			  	<h2>To get begin using Smart Clicker please login.</h2>
			</div>
		</div>   
  	</body>
	<%
  }
  %>
</html>