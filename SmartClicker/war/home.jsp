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
<%@ page import="java.util.logging.Logger"%>

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
	Logger logger = Logger.getLogger("MyLogger"); 
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
					
					<li>
						<form action=/register method="post">
								<input style=" width: 120px;" type="submit" class="original" value="RegisterId" />							
						</form>						
					</li>
					
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
		<h1>Welcome to Smart Clicker!</h1>
			<div class="card container" style = "  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);margin-bottom:10px;">
				
				
				<div class="card-header" style="background-color: #ff9800 !important; ">
					
				<h3>My Created Quizes<h3>
				
				</div>				
				
				<div class = "row">
					<%
						HashMap<String, String> createdQuizes = loggedInUser.displayCreatedQuizes();
							logger.info(createdQuizes.toString());

							Set<String> idSet = createdQuizes.keySet();
					%>
				
						<div class = col-xs-8>	
							<div class ="row">
								<div class = "col-xs-6">
									<p ><b>Quiz Titles</b></p>
										<%
											for (String id : idSet) {
												pageContext.setAttribute("quizTitle", createdQuizes.get(id));								
                                                pageContext.setAttribute("quizId", id);
                                               
										%>							
									 
										<form action=/results method="get">
											<input type="hidden" name="quizID" value="${fn:escapeXml(quizId)}" />
											<input
												style="text-align left; background-color: transparent; text-decoration: underline; border: none;"
												type="submit" value="${fn:escapeXml(quizTitle)}" />
										
										</form>
																															
										
										<%
										}
										%>								
									
								</div>	
								<div class = "col-xs-6">
									<p > <b>Quiz Ids</b></p>										
										<form action=/results method="get">
										<%
											for (String id : idSet) {
												pageContext.setAttribute("quizId", id);
					
										%>	
									<input type="hidden"  value="${fn:escapeXml(quizId)}" />
									<p >${quizId}</p>
										<%
											}
										%>
					
										</form>
								</div>		
							</div>			
						</div>
					<div class="col-sm-4 ">
						<div class = "container">
						<form action=/teacher method="post">
								<input style=" width: 120px;" type="submit" class="original" value="Create Quiz" />							
						</form>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="card container" style = "box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12); padding-top:30px;">
				
				<div class="card-header" style="background-color: #ff9800 !important; ">
					
					<h3>Take a Quiz<h3>
				
				</div>
				<div class="container">
					<form action=/student method="get" >
						<div class = "row">	
							<div class="col-xs-8">
							<h3>Enter A Quiz ID</h3>
							<input
								style="margin-top: 0px; margin-left: 10px; width: 120px; float: left"
								class="title" name="inputID" "
								placeholder="Quiz ID">
								
							</div>
							<div class="col-sm-4">								
								<input style=" width: 120px; margin-top: 20px;" type="submit" class="original" value="Take Quiz" />
							</div>
						</div>
					</form>
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