package smartclicker;

public class SmartAnswer {
	
	/**NOT CURRENTLY USING THIS CLASS. SEEMS UNNECESSARY NOW IN IMPLEMENTATION. USED JUST SIMLE STRINGS IN QUESTION CLASS**/
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
