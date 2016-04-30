<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="smartclicker.SmartUser"%>
<%@ page import="smartclicker.SmartClickerServlet"%>
<%@ page import="smartclicker.SmartClickerObjectify"%>


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
	if (user != null) {
		/*SmartUser loggedInUser = SmartClickerServlet.userManagment(user.getUserId());*/
		SmartClickerObjectify objectify = SmartClickerObjectify
				.getInstance();
		SmartUser loggedInUser = objectify.retrieveUser(user
				.getUserId());
	}

	String userName = request.getParameter("userName");
	if (userName == null) {
		userName = "default";
	}
	pageContext.setAttribute("userName", userName);
%>

<ul class="border">
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
					<nav>
						<ul>
							<li>${fn:escapeXml("My First Quiz")}</li>
							<li>Quiz 1</li>
							<li>Quiz 2</li>
							<li>Quiz 3</li>
							<li>Quiz 4</li>
							<li>Quiz 5</li>
							<li>Quiz 6</li>
							<li>Quiz 7</li>
							<li>Quiz 8</li>
							<li>Quiz 9</li>
						</ul>
					</nav>
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
							<textarea
								style="margin-top: 0px; margin-left: 10px; width: 130px; float: left"
								class="title" name="inputID" rows="1" cols="10"
								placeholder="Quiz ID">
								</textarea>
						</div>
						<div class="w3-quarter">
							<div style="margin-left: 34%; margin-top: 70%;padding-bottom: 10px;">
							<input
								style="margin-top: 10px; width: 130px; "
								type="submit" class="original" value="Take Quiz" />
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