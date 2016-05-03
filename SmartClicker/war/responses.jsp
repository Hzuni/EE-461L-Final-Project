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
<%@ page import="smartclicker.SmartQuiz" %>
<%@ page import="smartclicker.SmartQuestion" %>
<%@ page import="smartclicker.SmartClickerObjectify" %>
<%@ page import="java.util.ArrayList;" %>

<html>
  <head>
  	<link rel="stylesheet" href="resources/css/index.css">
  	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  	<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<script>
	function check(answers) {
	    document.getElementById("answer").value=answers;
	}
	</script>  
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
   <ul class="border" style="width: 100%;">
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
			<div class="w3-card-4" style="width:100%; padding-bottom: 0px;">
				<header class="w3-container w3-orange">
					<h6>Quiz:</h6>
				</header>
				<%
				System.out.println("Response Check 1");
				String identification = (String)request.getAttribute("quizId");
				SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
				System.out.println("Response Check 2: " + identification);
				SmartQuiz quiz = objectify.retrieveQuiz(identification);
				
				System.out.println("Response Checking Quiz: " + quiz);
				
				String title = quiz.getTitle();
				ArrayList<String> IDs = quiz.getQuestionIds();
				
				System.out.println("Response Checking Take Quiz Title: " + title);
				
				for(String ID : IDs) {
					System.out.println("Here????");
					SmartQuestion question = objectify.retrieveQuestion(ID);
					System.out.println("Here?!!!!" + question);
					
					pageContext.setAttribute("quiz_content", question.getQuestion());
					ArrayList<String> answers = question.getAnswers();
					pageContext.setAttribute("quiz_answer1", answers.get(0));
					pageContext.setAttribute("quiz_answer2", answers.get(1));
					pageContext.setAttribute("quiz_answer3", answers.get(2));
					pageContext.setAttribute("quiz_answer4", answers.get(3));
					pageContext.setAttribute("quizID", quiz.getQuizID());
					%>
			    	<form action="/results" method="post" style="padding: 20px;">
			      		<div>${fn:escapeXml(quiz_content)}</div>
			      		<h4>Answer choices:</h4>
			      		<div><input type="radio" name="answers" onclick="check(this.value)" value="answer1"/> : ${fn:escapeXml(quiz_answer1)}<br /></div>
			      		<div><input type="radio" name="answers" onclick="check(this.value)" value="answer2"/> : ${fn:escapeXml(quiz_answer2)}<br /></div>
			      		<div><input type="radio" name="answers" onclick="check(this.value)" value="answer3"/> : ${fn:escapeXml(quiz_answer3)}<br /></div>
			      		<div><input type="radio" name="answers" onclick="check(this.value)" value="answer4"/> : ${fn:escapeXml(quiz_answer4)}<br /></div>
			      		<div><input style="margin-top: 10px;" type="submit" class="original" value="Submit" /></div>
			      		<input type="hidden" name="quizID" value="${fn:escapeXml(quizID)}"/>
			      		<input type="hidden" name="studentAnswer" value="${fn:escapeXml(answer)}"/>
			   	 	</form>
			   	 	<%
				}
		   	 	%>
		 	</div>
		</div>   
  	</body>
<html>