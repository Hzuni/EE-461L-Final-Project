package smartclicker;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayResultsServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String QuizID = req.getParameter("quizID").trim();
		String Answer = req.getParameter("answers");
		int correctIndex = 0;
		
		ArrayList<Integer> check = new ArrayList<Integer>();
		
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
		
		ArrayList<String> IDs = studentQuiz.getQuestionIds();
		for(String ID : IDs) {
			SmartQuestion question = objectify.retrieveQuestion(ID);
			ArrayList<String> answers = question.getAnswers();
			if(Answer.equals("answer1")) {
				correctIndex = 0;
				question.addStudentResponse(correctIndex);
			}
			else if(Answer.equals("answer2")) {
				correctIndex = 1;
				question.addStudentResponse(correctIndex);
			}
			else if(Answer.equals("answer3")) {
				correctIndex = 2;
				question.addStudentResponse(correctIndex);
			}
			else if(Answer.equals("answer4")) {
				correctIndex = 3;
				question.addStudentResponse(correctIndex);
			}
			else {
				
			}
			check = question.getStudentResponse();
		}
		System.out.println("Added Answer: " + check);
		System.out.println("Chosen Answer: " + Answer);
		System.out.println("Results for: " + QuizID);
		req.setAttribute("smartQuiz", studentQuiz);
		req.setAttribute("answer", Answer);
		
		try {
			System.out.println("Redirect to results: " + QuizID);
			req.getRequestDispatcher("/results.jsp").forward(req, resp);
		} catch (ServletException e) {
			resp.sendRedirect("/home.jsp");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String identification = req.getParameter("quizID");
		
		System.out.println("Results Check: ID = " + identification);
		
		req.setAttribute("quizId", identification);
		try {
			req.getRequestDispatcher("/responses.jsp").forward(req, resp);
		} catch (ServletException e) {
			resp.sendRedirect("/home.jsp");
		}
	}

}
