package smartclicker;

import java.util.HashMap;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SmartUser {
	@Id private String userId;
	
	private HashMap<String,String> createdQuizIds; 
	
	public HashMap<String, String> displayCreatedQuizes() {
		/*To be used by home.jsp*/
		return createdQuizIds;
	}
	public SmartUser(){
		this.userId ="";
		createdQuizIds = new HashMap<String,String>();
	}
	public SmartUser(String userId){
		this();
		this.userId=userId;		
	}
	public void addCreatedQuiz(String quizId,String title)
	{
		/*quizId is the key which is guaranteed to be unique*/
		createdQuizIds.put(quizId,title);
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	

}
