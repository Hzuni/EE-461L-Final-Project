package smartclicker;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmartStudentState extends HttpServlet implements SmartUserState{
	void takeQuiz(String classId){
		
	}
	void joinClass(String classID){
		
	}
	
	/*Used for taking Quizzes*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String QuizID = req.getParameter("inputID");
		
		System.out.println("Checking: " + QuizID);
		
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
		
		if(studentQuiz == null) {
			String emptyString = "empty";
			ArrayList<String> blank = new ArrayList<String>();
			
			System.out.println("Take Quiz null check: " + QuizID);
		
			SmartQuiz empty = new SmartQuiz(emptyString, emptyString, emptyString, blank);
			req.setAttribute("smartQuiz", empty);
		}
		else {
			System.out.println("Take Quiz non-null check: " + QuizID);
			req.setAttribute("smartQuiz", studentQuiz);
		}
        resp.sendRedirect("/takequiz.jsp");
	}
}
