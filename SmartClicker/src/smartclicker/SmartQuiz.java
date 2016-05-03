package smartclicker;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class SmartQuiz {
	
	@Id private String quizID;
	private String userId;
	private static final int MIN_QUESTION_NUMBER = 5;
	private String title;
	private ArrayList<String> questionIds; 		

	public SmartQuiz(){
		this.quizID ="";
		this.userId = "";
		this.title = "";
		this.questionIds = new ArrayList<String>();
		
	}
	
	public SmartQuiz(String qID, String uID, String t, ArrayList<String> qs) {
		this.quizID = qID;
		this.userId = uID;
		this.title = t;
		this.questionIds=qs;
	}
	public static int getMinimumQuestions(){
		return MIN_QUESTION_NUMBER;
	}
	
	public void setQuizID(String sID) {
		quizID = sID;
	}
	public String getQuizID() {
		return quizID;
	}
	public ArrayList<String> getQuestionIds() {
		return questionIds;
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

	public void addQuestion(String questionId) {
		questionIds.add(questionId);
		return;
		
	}
}
