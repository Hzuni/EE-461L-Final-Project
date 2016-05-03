package smartclicker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayResultsServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String QuizID = req.getParameter("quizID").trim();
		String Answer = req.getParameter("answers");
		
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		SmartQuiz studentQuiz = objectify.retrieveQuiz(QuizID);
		
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
		resp.sendRedirect("/home.jsp");
	}

}
