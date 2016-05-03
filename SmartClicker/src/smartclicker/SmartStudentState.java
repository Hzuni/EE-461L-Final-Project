package smartclicker;

import java.io.IOException;

import javax.servlet.ServletException;
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
		String QuizID = req.getParameter("inputID").trim();
		
		if(QuizID.equals(null) || QuizID.equals("")) {
			System.out.println("Checking NLL: " + QuizID);
			resp.sendRedirect("/home.jsp");
		}
		else {
			System.out.println("Checking: " + QuizID);
			
			SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
			SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
			
			if(studentQuiz == null) {
				System.out.println("Take Quiz null check: " + QuizID);
			
				resp.sendRedirect("/home.jsp");
			}
			else {
				System.out.println("Take Quiz non-null check: " + QuizID);
				req.setAttribute("smartQuiz", studentQuiz);
			}
			try {
				System.out.println("Redirect to takeQuiz: " + QuizID);
				req.getRequestDispatcher("/takequiz.jsp").forward(req, resp);
			} catch (ServletException e) {
				resp.sendRedirect("/home.jsp");
			}
		}
	}
}
