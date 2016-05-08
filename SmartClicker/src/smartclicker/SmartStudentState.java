package smartclicker;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmartStudentState extends HttpServlet implements SmartUserState{
    Logger logger = Logger.getLogger("MyLogger"); 

	
	
	void takeQuiz(String classId){
		
	}
	void joinClass(String classID){
		
	}
	
	/*Used for taking Quizzes*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String QuizID = req.getParameter("inputID").trim();
		
		if(QuizID.equals(null) || QuizID.equals("")) {
			logger.info("Checking NLL: " + QuizID);
			resp.sendRedirect("/home.jsp");
		}
		else {
			logger.info("Checking: " + QuizID);
			
			SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
			SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
			
			if(studentQuiz == null) {
				logger.info("Take Quiz null check: " + QuizID);
			
				resp.sendRedirect("/home.jsp");
			}
			else {
				logger.info("Take Quiz non-null check: " + QuizID);
				req.setAttribute("smartQuiz", studentQuiz);
			}
			try {
				logger.info("Redirect to takeQuiz: " + QuizID);
				req.getRequestDispatcher("/takequiz.jsp").forward(req, resp);
			} catch (ServletException e) {
				resp.sendRedirect("/home.jsp");
			}
		}
	}
}
