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
		
		SmartQuiz newQuiz = new SmartQuiz();		
		newQuiz.setTitle(req.getParameter("title"));
		ArrayList<SmartQuestion> new_quiz_questions = new ArrayList<SmartQuestion>();		
		
		/*Build questions and store them in new_quiz_questions*/
		for(int i = 1; i <=5; i++)
		{
			String question_text = req.getParameter("quetion" + Integer.toString(i));			
			ArrayList<String> answerChoices = new ArrayList<String>();		
			int correct_answer_choice;
			for(int j = 1; j<= 4; j++)
			{
				String parsedInAnswerChoice = req.getParameter("answer" + Integer.toString(i) + "_" + Integer.toString(j));
				answerChoices.add(parsedInAnswerChoice);
			}
			correct_answer_choice =  Integer.parseInt(req.getParameter("correct" + Integer.toString(i)));
			SmartQuestion new_quiz_question = new SmartQuestion(question_text,answerChoices,correct_answer_choice);			
		}
		
		
		
		/*String title = req.getParameter("title");
		String questionText = req.getParameter("question");
		String answer1 = req.getParameter("answer1");
		String answer2 = req.getParameter("answer2");
		String answer3 = req.getParameter("answer3");
		String answer4 = req.getParameter("answer4");
		String correctAnswer =req.getParameter("correct");
		if(title != null)
			question.setTitle(title);
		if(question != null)
			question.setQuestion(questionText);
		if(answer1 != null)
			question.addAnswers(answer1);
		if(answer2 != null)
			question.addAnswers(answer2);
		if(answer3 != null)
			question.addAnswers(answer3);
		if(answer4 != null)
			question.addAnswers(answer4);
		if(correctAnswer != null){
			question.setCorrect(Integer.parseInt(correctAnswer));
		}
		if(title != null && question != null) {
			Key key = KeyFactory.createKey("Title", title);
			Entity Question = new Entity("Question", key);
			Question.setProperty("question_title", title);
			Question.setProperty("question_content", questionText);
			datastore.put(Question);
		}*/
		
		
		resp.sendRedirect("/home.jsp");
		
	}
	
	
	
}
