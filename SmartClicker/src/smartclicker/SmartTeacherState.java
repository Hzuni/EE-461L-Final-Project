package smartclicker;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.security.SecureRandom;


public class SmartTeacherState extends HttpServlet implements SmartUserState{

	
	public String makeClass(){
		return null;
		
	} 
	public String makeQuiz(){
		return null;
		
	}
	
	/*Used for Class creation*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
        resp.sendRedirect("/quiz.jsp");
	}
	
	/*Used for quiz creation*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String userID = req.getParameter("userId");
		
		SmartQuiz newQuiz = new SmartQuiz();	
		
		String quizID = randomString(4);
		System.out.println("Quiz ID: " + quizID);
		
		//newQuiz.setQuizID(quizID);
		String quizTitle = req.getParameter("title");
		if(quizTitle.equals("")){
			quizTitle = "Quiz";
		}
		newQuiz.setTitle(quizTitle);
		newQuiz.setUserID(userID);
		ArrayList<SmartQuestion> new_quiz_questions = new ArrayList<SmartQuestion>();		
		
		/*Build questions and store them in new_quiz_questions*/
		for(int i = 1; i <=1; i++)
		{
			String question_text = req.getParameter("question" + Integer.toString(i));
			if(!question_text.equals("")){
				System.out.println("\n" + "Checking Here: " + question_text);
				ArrayList<String> answerChoices = new ArrayList<String>();		
				int correct_answer_choice;
				for(int j = 1; j<= 4; j++)
				{
					String parsedInAnswerChoice = req.getParameter("answer" + Integer.toString(i) + "_" + Integer.toString(j));
					answerChoices.add(parsedInAnswerChoice);
					System.out.println("Answers: " + parsedInAnswerChoice);
				}
				String correctAnswer = req.getParameter("correct" + Integer.toString(i));
				System.out.println("Before:" + correctAnswer);
				if(correctAnswer.equals("")){
					correctAnswer = "1";
				}
				System.out.println("After:" + correctAnswer);
				correct_answer_choice =  Integer.parseInt(correctAnswer);

				SmartQuestion new_quiz_question = new SmartQuestion(question_text,answerChoices,correct_answer_choice);		
				new_quiz_questions.add(new_quiz_question);
				
			}
			
		}
		if(!new_quiz_questions.isEmpty()){
			newQuiz.setQuestions(new_quiz_questions);
			SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
			objectify.newQuizManagment(newQuiz);
			
		}
		resp.sendRedirect("/home.jsp");
		
	}
	static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";	
	public String randomString(int len) {
		StringBuilder idGen = new StringBuilder(len);
		
		for(int i = 0; i < len; i += 1) {
			idGen.append(chars.charAt(new SecureRandom().nextInt(chars.length())));
		}
		
		return idGen.toString();
	}
}
