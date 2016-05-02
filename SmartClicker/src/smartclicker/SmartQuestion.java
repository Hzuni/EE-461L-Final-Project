package smartclicker;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SmartQuestion {
	@Id private String questionID;
	private String question;
	private ArrayList<String> answerChoices;
	private int correctAnswerChoice;
	
	private static final int MIN_ANSWER_NUMBER = 4;

	
	public SmartQuestion() {
		this.answerChoices = new ArrayList<String>();
		
	}

	public SmartQuestion(String question,
			ArrayList<String> answers, int correctNumber) {
		super();
		this.question = question;
		this.answerChoices = answers;
		this.correctAnswerChoice = correctNumber;
		this.questionID = "";
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public ArrayList<String> getAnswers() {
		return answerChoices;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answerChoices = answers;
	}
	
	public int getCorrect() {
		return correctAnswerChoice;
	}
	
	public void setCorrect(int correct) {
		this.correctAnswerChoice = correct;
	}	
	public static int getMinimumAnswers(){
		return MIN_ANSWER_NUMBER;
	}

	public void setQuestionID(String id) {
		this.questionID = id;
	}	
	public String getQuestionID(){
		return questionID;
	}
}