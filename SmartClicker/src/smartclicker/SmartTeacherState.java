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

	public static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
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
		SmartQuestion question = new SmartQuestion();
		
		String title = req.getParameter("title");
		String questionText = req.getParameter("question");
		String answer1 = req.getParameter("answer1");
		String answer2 = req.getParameter("answer2");
		String answer3 = req.getParameter("answer3");
		String answer4 = req.getParameter("answer4");
		
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
		
		/*Need to figure out how to get an integer*/
		//question.setCorrect(req.getParameter("correct"));
		if(title != null && question != null) {
			Key key = KeyFactory.createKey("Title", title);
			Entity Question = new Entity("Question", key);
			Question.setProperty("question_title", title);
			Question.setProperty("question_content", questionText);
			datastore.put(Question);
		}
		
		
		resp.sendRedirect("/home.jsp");
		
	}
}
