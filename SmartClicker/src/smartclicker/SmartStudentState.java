package smartclicker;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmartStudentState extends HttpServlet implements SmartUserState{
	void takeQuiz(String classId){
		
	}
	void joinClass(String classID){
		
	}
	
	/*Used to take quizzes*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		System.out.println("Student taking a quiz");
        resp.sendRedirect("/takequiz.jsp");
	}
}
