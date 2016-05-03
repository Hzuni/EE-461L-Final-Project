<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="smartclicker.SmartUser"%>
<%@ page import="smartclicker.SmartClickerServlet"%>
<%@ page import="smartclicker.SmartClickerObjectify"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>


<html>
<head>
<link rel="stylesheet" href="resources/css/index.css">
<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>
<%
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	/*The Smart User Who is currently logged in will be stored in our variable named loggedInUser*/
	SmartUser loggedInUser = null;
	if (user != null) {
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		loggedInUser = objectify.retrieveUser(user.getUserId());
	}
	
%>

<ul class="border" style="width: 100%;">
	<li class="topbar" style="font-weight: bold"><a href="home.jsp">Smart
			Clicker</a></li>

	<ul style="float: right; list-style-type: none;">

		<li class="topbar">
			<%
				if (user != null) {
						pageContext.setAttribute("user", user);
			%> Welcome ${fn:escapeXml(user)}
		<li class="topbar"><a
			href="<%=userService.createLogoutURL(request.getRequestURI())%>">Sign
				out</a></li>
		<%
			} else {
		%>
		<a href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
			in</a>
		<%
			}
		%>
		</li>
	</ul>
</ul>

<%
	if (user != null) {
%>
<body>
	<div id="page-wrap">
		<h1>Welcome to Smart Clicker!</h1>
		<div style="padding-bottom: 20px;">
			<div class="w3-card-4	w3-row"
				style="width: 100%; padding-bottom: 0px; margin-bottom: 10px;">
				<header class="w3-container w3-orange">
					<h3>My Created Quizes</h3>
				</header>
				<div class="w3-threequarter w3-white" style="padding: 20px;">
					<div id = "quizlist">		
							<div id="textbox"> 								
  							<p class="alignleft"><b>Your Created Quizes</b></></p>
							<p class="alignright"><b>Quiz Id</b></p>
							</div>
							<div style="clear: both;"></div>						
							<% 
							HashMap<String,String> createdQuizes = loggedInUser.displayCreatedQuizes();
							System.out.println(createdQuizes);
							Set<String> idSet = createdQuizes.keySet();
							
							for(String id : idSet)
							{
								pageContext.setAttribute("quizId", id);
								pageContext.setAttribute("quizTitle",createdQuizes.get(id));
							%>																				
							<div id="textbox"> 								
 								<p class="alignleft"><a href="#">${quizTitle}</a></p>
							<p class="alignright">${quizId}</p>
							</div>
							<div style="clear: both;"></div>						
						
							<%
							}
							%>
					</div>
				</div>

				<div class="w3-quarter">
					<form action=/teacher method="post"
						style="margin-left: 25%; margin-top: 75%;">
						<div>
							<input style="margin-top: 10px; width: 130px;" type="submit"
								class="original" value="Create Quiz" />
						</div>
					</form>

				</div>
			</div>
			<div class="w3-card-4"
				style="width: 100%; padding-bottom: 0px; margin-bottom: 10px;">
				<header class="w3-container w3-orange">
					<h3>Take a Quiz</h3>
				</header>
				<div class="w3-container">
					<form action=/student method="get" style="padding: 20px;">
						<div class="w3-threequarter">
							<h3>Enter A Quiz ID</h3>
							<input
								style="margin-top: 0px; margin-left: 10px; width: 130px; float: left"
								class="title" name="inputID" rows="1" cols="10"
								placeholder="Quiz ID">
								</input>
						</div>
						<div class="w3-quarter">
							<div
								style="margin-left: 34%; margin-top: 20%; padding-bottom: 10px;">
								<input style="margin-top: 10px; width: 130px; margin-bottom: 6px;" type="submit"
									class="original" value="Take Quiz" />
							</div>
						</div>
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