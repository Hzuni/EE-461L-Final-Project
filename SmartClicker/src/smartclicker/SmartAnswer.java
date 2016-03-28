package smartclicker;

public class SmartAnswer {
	
	
	private String answerChoiceContents; 
	
	public SmartAnswer(String answerChoice) {
		super();
		this.answerChoiceContents = answerChoice;
	}

	
	public String getAnswerChoice() {
		return answerChoiceContents;
	}

	public void setAnswerChoice(String answerChoice) {
		this.answerChoiceContents = answerChoice;
	}
	
}
