package smartclicker;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.security.SecureRandom;

public class SmartTeacherState extends HttpServlet implements SmartUserState {

	public String makeClass() {
		return null;

	}

	public String makeQuiz() {
		return null;

	}

	/* Used for quiz creation */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.sendRedirect("/quiz.jsp");
	}

	/* Used for quiz creation */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		SmartUser quizCreator;
		SmartQuiz newQuiz;
		ArrayList<SmartQuestion> newQuestions;
		
		newQuiz = new SmartQuiz();
		String quizTitle = req.getParameter("title");		
		if (quizTitle.equals("")) {
			quizTitle = "Quiz";
		}
		newQuiz.setTitle(quizTitle);
		newQuiz.setUserID(user.getUserId());
		
		
		/*Adding newQuestions to newQuiz*/
		newQuestions = new ArrayList<SmartQuestion>();
		for (int i = 1; i <= 1; i++) {
			String question_text = req.getParameter("question"+ Integer.toString(i));
			
			if (!question_text.equals("")) {
				System.out.println("\n" + "Checking Here: " + question_text);
				ArrayList<String> answerChoices = new ArrayList<String>();
				int correct_answer_choice;
				
				for (int j = 1; j <= 4; j++) {
					String parsedInAnswerChoice = req.getParameter("answer"
							+ Integer.toString(i) + "_" + Integer.toString(j));
					answerChoices.add(parsedInAnswerChoice);
					System.out.println("Answers: " + parsedInAnswerChoice);
				}
				String correctAnswer = req.getParameter("correct" + Integer.toString(i));
				System.out.println("Before:" + correctAnswer);
				
				if (correctAnswer.equals("")) {
					correctAnswer = "1";
				}
				
				System.out.println("After:" + correctAnswer);
				correct_answer_choice = Integer.parseInt(correctAnswer);
				
				SmartQuestion new_quiz_question = new SmartQuestion(question_text, answerChoices, correct_answer_choice);
				newQuestions.add(new_quiz_question);
				
			}
		}
		
		/*Add new questions to objectify and store their Ids within questionIds*/
		for(SmartQuestion q: newQuestions)
			{
				String questionId = objectify.addNewQuestion(q);
				newQuiz.addQuestion(questionId);			
			}
		

		
		if (!newQuestions.isEmpty()) 
		{
			objectify.addNewQuiz(newQuiz);
			quizCreator = objectify.retrieveUser(user.getUserId());
			/*Finally we associate the quiz with the creator*/
			quizCreator.addCreatedQuiz(newQuiz.getQuizID(), newQuiz.getTitle());
		}
		
		resp.sendRedirect("/home.jsp");
	}

}
