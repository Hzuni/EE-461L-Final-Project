package smartclicker;

import java.util.ArrayList;

public class SmartQuestions {


	String questionContents; 
	String title;
	
	public SmartQuestions(String questionContents, String title,
			ArrayList<SmartAnswer> answers, int correctNumber) {
		super();
		this.questionContents = questionContents;
		this.title = title;
		this.answers = answers;
		this.correctNumber = correctNumber;
	}

	ArrayList<SmartAnswer> answers; 
	public ArrayList<SmartAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<SmartAnswer> answers) {
		this.answers = answers;
	}

	int correctNumber; 
	
	public SmartQuestions(){
		
	}

	public boolean isAnswer(SmartAnswer A){
		return false;
	}
}