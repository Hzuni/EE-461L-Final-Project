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
	
	/*Used for quiz creation*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		
        resp.sendRedirect("/quiz.jsp");
	}
}
