package smartclicker;

import java.util.HashMap;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SmartUser {
	@Id private String userId;
	private String classId;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
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
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		objectify.saveQuizzes(userId, quizId, title);
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	

}
