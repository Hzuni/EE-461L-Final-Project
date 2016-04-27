package smartclicker;

import java.util.ArrayList;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class SmartUser {
	@Id private String userId;
	
	private ArrayList<String> createdQuizIds; 
	private ArrayList<String> courseIds;
	
	public SmartUser(){
		this.userId ="00000000";
	}
	public SmartUser(String userId){
		this.userId=userId;
		
	}
	
	public String getGoogleId() {
		return userId;
	}
	

}
