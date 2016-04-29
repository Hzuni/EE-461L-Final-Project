package smartclicker;

import java.util.ArrayList;

public class SmartQuiz {
	
	private String userId;
	private static final int MIN_QUESTION_NUMBER = 5;
	private String title;
	private ArrayList<SmartQuestion> questions;
	private String quizID;

	public SmartQuiz(){
		
	}
	
	
	
	public String getQuizID() {
		return quizID;
	}
	public ArrayList<SmartQuestion> getQuestions() {
		return questions;
	}
	public static int getMinimumQuestions(){
		return MIN_QUESTION_NUMBER;
	}
	
	public void setQuestions(ArrayList<SmartQuestion> questions) {
		this.questions = questions;
	}
	public void setQuestionNumber(int q_num, SmartQuestion q){
		questions.set(q_num, q);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUserID() {
		return userId;
	}
	public void setUserID(String uID) {
		userId = uID;
	}
	
}
