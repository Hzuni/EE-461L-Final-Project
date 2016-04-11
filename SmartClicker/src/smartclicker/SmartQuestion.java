package smartclicker;

import java.util.ArrayList;

public class SmartQuestion {

	private String question;
	private String title;
	private ArrayList<String> answers;
	private int correctNumber;
	
	private static final int MIN_ANSWER_NUMBER = 4;

	
	public SmartQuestion() {
		answers = new ArrayList<String>();
	}

	public SmartQuestion(String question, String title,
			ArrayList<String> answers, int correctNumber) {
		super();
		this.question = question;
		this.title = title;
		this.answers = answers;
		this.correctNumber = correctNumber;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public ArrayList<String> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	public void addAnswers(String answer) {
		answers.add(answer);
	}
	
	
	public int getCorrect() {
		return correctNumber;
	}
	public void setCorrect(int correct) {
		this.correctNumber = correct;
	}

	
	public boolean isAnswer(SmartAnswer A) {
		return false;
	}
	public static int getMinimumAnswers(){
		return MIN_ANSWER_NUMBER;
	}

}