package smartclicker;

import java.util.ArrayList;

public class SmartQuestion {

	private String questionContents;
	private String title;
	private ArrayList<SmartAnswer> answers;
	private int correctNumber;
	
	private static final int MIN_ANSWER_NUMBER = 5;

	
	public SmartQuestion() {

	}

	public SmartQuestion(String questionContents, String title,
			ArrayList<SmartAnswer> answers, int correctNumber) {
		super();
		this.questionContents = questionContents;
		this.title = title;
		this.answers = answers;
		this.correctNumber = correctNumber;
	}

	public ArrayList<SmartAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<SmartAnswer> answers) {
		this.answers = answers;
	}

	
	public boolean isAnswer(SmartAnswer A) {
		return false;
	}
	public static int getMinimumAnswers(){
		return MIN_ANSWER_NUMBER;
	}

}