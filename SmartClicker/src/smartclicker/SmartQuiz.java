package smartclicker;

import java.util.ArrayList;

public class SmartQuiz {
	
	private static final int MIN_QUESTION_NUMBER = 5;
	/*Currently all questions must have 5 answers*/
	
	private ArrayList<SmartQuestion> questions;
	private String quizID;
	
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
	
	
	
}
