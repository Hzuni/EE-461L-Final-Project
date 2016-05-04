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
		int correctIndex = 0;
		
		ArrayList<Integer> check = new ArrayList<Integer>();
		
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
		
		
		System.out.println("/*********************************************************************************************/");
		System.out.println("Entered DisplayResults");
		System.out.println("");
		System.out.println("");
		
		
		int index = 1;
		ArrayList<String> IDs = studentQuiz.getQuestionIds();
		for(String ID : IDs) {
			SmartQuestion question = objectify.retrieveQuestion(ID);
			ArrayList<String> answers = question.getAnswers();
			
			String requestIndex = "answers" + index;
			System.out.println("Check for index: " + requestIndex);
			String Answer = req.getParameter(requestIndex);
			
			System.out.println("Check for Answer: " + Answer);
			
			if(Answer == null) { Answer = "incorrect";}
			
			if(Answer.equals("answer1")) {
				correctIndex = 0;
				objectify.updateQuestion(ID, correctIndex);
			}
			else if(Answer.equals("answer2")) {
				correctIndex = 1;
				objectify.updateQuestion(ID, correctIndex);
			}
			else if(Answer.equals("answer3")) {
				correctIndex = 2;
				objectify.updateQuestion(ID, correctIndex);
			}
			else if(Answer.equals("answer4")) {
				correctIndex = 3;
				objectify.updateQuestion(ID, correctIndex);
			}
			else {
				
			}
			
			req.setAttribute(requestIndex, Answer);
			index += 1;
		}
		
		System.out.println("Results for: " + QuizID);
		
		req.setAttribute("smartQuiz", studentQuiz);
		
		try {
			System.out.println("Redirect to results: " + QuizID);
			
			System.out.println("");
			System.out.println("");
			System.out.println("Exited takequiz");
			System.out.println("/*********************************************************************************************/");
			System.out.println("");
			System.out.println("");
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
