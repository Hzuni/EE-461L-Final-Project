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
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" href="resources/css/index.css">

<link href='https://fonts.googleapis.com/css?family=Roboto'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">


  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<%
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	/*The Smart User Who is currently logged in will be stored in our variable named loggedInUser*/
	SmartUser loggedInUser = null;
	if (user != null) {
		SmartClickerObjectify objectify = SmartClickerObjectify
				.getInstance();
		loggedInUser = objectify.retrieveUser(user.getUserId());
	}
%>



<body>	
	<nav class="navbar navbar-inverse" style="background-color: #ff9800; border-color: #E7E7E7;">
		<div class="container-fluid">
			<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
				<a href="#" class="navbar-brand navbar-link" style="font-weight: bold; color: #ffffff">Smart Clicker</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">					
					
					<li >
					<%
							if (user != null) {
							pageContext.setAttribute("user", user);
					%><p style = "color:#ffffff" > Welcome ${fn:escapeXml(user)}</p>
					</li>
					
					<li><a href="#">Register Id</a></li>
					
					<li><a href="<%=userService.createLogoutURL(request.getRequestURI())%>"><span class="glyphicon glyphicon-user"></span>Sign out</a></li>
					<%
							} else {
					%>
					
					<li>
					<a href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign in</a>
					<%
						}
					%>
					</li>
				</ul>
				
			</div>
		</div>
	</nav>
	<%
	if (user != null) {
	%>

	<div id="page-wrap">
		<h1>Welcome to Smart Clicker!</h1>
		<div style="padding-bottom: 20px;">
			<div class="w3-card-4	w3-row"
				style="width: 100%; padding-bottom: 0px; margin-bottom: 10px;">
				<header class="w3-container w3-orange">
					<h3>My Created Quizes</h3>
				</header>
				<div class="w3-threequarter w3-white" style="padding: 20px;">
					<div id="quizlist">
						<div id="textbox">
							<p class="alignleft">
								<b>Your Created Quizes</b>
							</p>
							<p class="alignright">
								<b>Quiz Id</b>
							</p>
						</div>
						<div style="clear: both;"></div>
						<%
							HashMap<String, String> createdQuizes = loggedInUser
										.displayCreatedQuizes();
								System.out.println(createdQuizes);
								Set<String> idSet = createdQuizes.keySet();

								for (String id : idSet) {
									pageContext.setAttribute("quizId", id);
									pageContext
											.setAttribute("quizTitle", createdQuizes.get(id));
						%>
						<div id="textbox">
							<%--  								<p class="alignleft"><a href="responses.jsp">${quizTitle}</a></p> --%>
							<form action=/results method="get"
								style="margin-left: 0px; margin-top: 0px;">
								<input type="hidden" name="quizID"
									value="${fn:escapeXml(quizId)}" />
								<div>
									<input
										style="text-align: left; background-color: transparent; text-decoration: underline; border: none; margin-top: 0px; margin-right: 5px; width: 800px;"
										type="submit" class="alignleft"
										value="${fn:escapeXml(quizTitle)}" />
								</div>
							</form>
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
							<input style="margin-top: 10px; margin-right: 5px; width: 130px;"
								type="submit" class="original" value="Create Quiz" />
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
								placeholder="Quiz ID"> </input>
						</div>
						<div class="w3-quarter">
							<div
								style="margin-left: 34%; margin-top: 20%; padding-bottom: 13px;">
								<input
									style="margin-top: 10px; margin-right: 5px; width: 130px; margin-bottom: 6px;"
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
			<h2>To get state using Smart Clicker please login.</h2>
		</div>
	</div>
</body>
<%
	}
%>
</html>