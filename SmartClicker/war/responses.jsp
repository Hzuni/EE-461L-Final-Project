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
				System.out.println("/*********************************************************************************************/");
				System.out.println("Entered responses");
				System.out.println("");
				System.out.println("");
				System.out.println("Response Check 1");
				String identification = (String)request.getAttribute("quizId");
				SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
				System.out.println("Response Check 2: " + identification);
				SmartQuiz quiz = objectify.retrieveQuiz(identification);
				
				System.out.println("Response Checking Quiz: " + quiz);
				
				String title = quiz.getTitle();
				ArrayList<String> IDs = quiz.getQuestionIds();
				
				System.out.println("Response Checking Take Quiz Title: " + title);
				%><form action="home.jsp" style="padding: 20px;"> <%
				    int index = 1;
					for(String ID : IDs) {
						SmartQuestion question = objectify.retrieveQuestion(ID);
						
						pageContext.setAttribute("quiz_content", question.getQuestion());
						
						ArrayList<String> answers = question.getAnswers();
						
						ArrayList<Integer> responses = question.getStudentResponse();
						
						pageContext.setAttribute("quizID", quiz.getQuizID());
						
						double a0 = responses.get(0); int ia0 = responses.get(0);
						double a1 = responses.get(1); int ia1 = responses.get(1);
						double a2 = responses.get(2); int ia2 = responses.get(2);
						double a3 = responses.get(3); int ia3 = responses.get(3);
						System.out.println("Value Check: a0 = " + a0 + " a1 = " + a1 + " a2 = " + a2 + " a3 = " + a3);
						
						double total = a0 + a1 + a2 + a3; int itotal = ia0 + ia1 + ia2 + ia3;
						System.out.println("Total Check: Total = a1 + a2 + a3 + a4 = " + total);
						
						double out0 = (a0 / total) * 100; 
						double out1 = (a1 / total) * 100;
						double out2 = (a2 / total) * 100;
						double out3 = (a3 / total) * 100;
						System.out.println("Value Check: out0 = " + out0 + " out1 = " + out1 + " out2 = " + out2 + " out3 = " + out3);
						
						System.out.println("");
						System.out.println("");
						System.out.println("Exited results");
						System.out.println("/*********************************************************************************************/");
						System.out.println("");
						System.out.println("");
						
						String output1 = out0 + "% Of Students Chose: " + answers.get(0);
						String output2 = out1 + "% Of Students Chose: " + answers.get(1);
						String output3 = out2 + "% Of Students Chose: " + answers.get(2);
						String output4 = out3 + "% Of Students Chose: " + answers.get(3);
						
						
						String outputTotal = itotal + " Students Have answered Question " + index;
						
						pageContext.setAttribute("quiz_total", outputTotal);
						pageContext.setAttribute("quiz_responses1", output1);
						pageContext.setAttribute("quiz_responses2", output2);
						pageContext.setAttribute("quiz_responses3", output3);
						pageContext.setAttribute("quiz_responses4", output4);
						
						%>
							<div><b><u> ${fn:escapeXml(quiz_total)}</u></b></div>
				      		<div>${fn:escapeXml(quiz_content)}</div>
				      		<h4>Answer choices:</h4>
				      		<div> ${fn:escapeXml(quiz_responses1)}</div>
				      		<div> ${fn:escapeXml(quiz_responses2)}</div>
				      		<div> ${fn:escapeXml(quiz_responses3)}</div>
				      		<div> ${fn:escapeXml(quiz_responses4)}</div>
				      		<input type="hidden" name="quizID" value="${fn:escapeXml(quizID)}"/>
				      		<input type="hidden" name="studentAnswer" value="${fn:escapeXml(answer)}"/>
					<%
					index += 1;
					}%>
			   	 	<div><input style="margin-top: 10px;" type="submit" class="original" value="Home" /></div>
		   	 	</form>
		 	</div>
		</div>   
  	</body>
<html>