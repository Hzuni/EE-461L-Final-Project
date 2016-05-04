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
					<h6>Quiz Results:</h6>
				</header>
				<%
				SmartQuiz quiz = (SmartQuiz)request.getAttribute("smartQuiz");
				
				System.out.println("/*********************************************************************************************/");
				System.out.println("Entered results");
				System.out.println("");
				System.out.println("");
				System.out.println("Checking Quiz in results: " + quiz);
				
				String title = quiz.getTitle();
				ArrayList<String> IDs = quiz.getQuestionIds();
				
				System.out.println("Checking results Quiz Title: " + title);
				
				int index = 1;
				for(String ID : IDs) {
					String requestIndex = "answers" + index;
					
					System.out.println("Results Index Check: " + requestIndex);
					String Answer = (String)request.getAttribute(requestIndex);
					
					SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
					
					SmartQuestion question = objectify.retrieveQuestion(ID);
					System.out.println("Results Answer Check: " + Answer);
					
					
					System.out.println("");
					System.out.println("");
					System.out.println("Exited results");
					System.out.println("/*********************************************************************************************/");
					System.out.println("");
					System.out.println("");
					
					pageContext.setAttribute("quiz_content", question.getQuestion());
					ArrayList<String> answers = question.getAnswers();
					pageContext.setAttribute("quiz_answer1", answers.get(0));
					pageContext.setAttribute("quiz_answer2", answers.get(1));
					pageContext.setAttribute("quiz_answer3", answers.get(2));
					pageContext.setAttribute("quiz_answer4", answers.get(3));
					%>
			    	<form action="home.jsp" style="padding: 20px;">
			      		<div>${fn:escapeXml(quiz_content)}</div>
			      		<h4>Answer choices:</h4>
			      		<% if(Answer.equals("answer1")){ %>
			      		<div>Your Answer: ${fn:escapeXml(quiz_answer1)}</div>
			      		<% } else if(Answer.equals("answer2")) {%>
			      		<div>Your Answer: ${fn:escapeXml(quiz_answer2)}</div>
			      		<% } else if(Answer.equals("answer3")) {%>
			      		<div>Your Answer: ${fn:escapeXml(quiz_answer3)}</div>
			      		<% } else if(Answer.equals("answer4")) {%>
			      		<div>Your Answer: ${fn:escapeXml(quiz_answer4)}</div>
			      		<% } %>
			      		<% if(question.getCorrect() == 1){ %>
			      		<div><b>Correct : ${fn:escapeXml(quiz_answer1)}</b><br /></div>
			      		<% } else { %>
			      		<div> : ${fn:escapeXml(quiz_answer1)}<br /></div>
			      		<% } %>
			      		<% if(question.getCorrect() == 2){ %>
			      		<div><b>Correct : ${fn:escapeXml(quiz_answer2)}</b><br /></div>
			      		<% } else { %>
			      		<div> : ${fn:escapeXml(quiz_answer2)}<br /></div>
			      		<% } %>
			      		<% if(question.getCorrect() == 3){ %>
			      		<div><b>correct : ${fn:escapeXml(quiz_answer3)}</b><br /></div>
			      		<% } else { %>
			      		<div> : ${fn:escapeXml(quiz_answer3)}<br /></div>
			      		<% } %>
			      		<% if(question.getCorrect() == 4){ %>
			      		<div><b>Correct : ${fn:escapeXml(quiz_answer4)}</b><br /></div>
			      		<% } else { %>
			      		<div>: ${fn:escapeXml(quiz_answer4)}<br /></div>
			      		<% } %>
			      		<div><input style="margin-top: 10px;" type="submit" class="original" value="Home" /></div>
			      		<input type="hidden" name="studentAnswer" value="${fn:escapeXml(answer)}"/>
			   	 	</form>
			   	 	<%
			   	 	index += 1;
				}
		   	 	%>
		 	</div>
		</div>   
  	</body>
<html>