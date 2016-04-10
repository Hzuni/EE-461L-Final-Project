package smartclicker;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
        resp.sendRedirect("/class.jsp");
	}
	
	/*Used for quiz creation*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
        resp.sendRedirect("/quiz.jsp");
	}
}
