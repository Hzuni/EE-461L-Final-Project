package smartclicker;
import java.security.SecureRandom;
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
	static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";	
	

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
	public static String generateQuizId(int len) {
		StringBuilder idGen = new StringBuilder(len);
		
		for(int i = 0; i < len; i += 1) {
			idGen.append(chars.charAt(new SecureRandom().nextInt(chars.length())));
		}
		
		return idGen.toString();
	}
	
}
