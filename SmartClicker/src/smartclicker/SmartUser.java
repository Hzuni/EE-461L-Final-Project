package smartclicker;

import java.util.ArrayList;

public class SmartUser {

	private String userId;
	
	private ArrayList<String> createdQuizIds; 
	private ArrayList<String> courseIds;
	
	SmartUser(String userId){
		this.userId=userId;
		
	}
	
	public String getGoogleId() {
		return userId;
	}
	

}
