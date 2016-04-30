package smartclicker;
import java.util.ArrayList;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SmartQuiz {
	
	@Id private String quizID;
	private String userID;
	private static final int MIN_QUESTION_NUMBER = 5;
	private String title;
	private ArrayList<SmartQuestion> questions;
	

	public SmartQuiz(){
		
	}
	
	public SmartQuiz(String qID, String uID, String t, ArrayList<SmartQuestion> qs) {
		quizID = qID;
		userID = uID;
		title = t;
		questions = qs;
	}
	
	public void setQuizID(String sID) {
		quizID = sID;
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
		return userID;
	}
	public void setUserID(String uID) {
		userID = uID;
	}
	
}
