package smartclicker;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.HashMap;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;

public class SmartClickerObjectify {
	private  boolean registered =false;	
    private static SmartClickerObjectify instance = new SmartClickerObjectify();
	
    
    private SmartClickerObjectify()
    {
    	/*Private constructor makes making multiple instances impossible.
    	 This is done to keep all Objectify loads and stores in one class 
    	 and to stay true to the Singleton Design Pattern*/
    
    }
	public static SmartClickerObjectify getInstance()
    {
    	return instance;
    }
	
    public void register()
    {
    	ObjectifyService.register(SmartUser.class);	
    	ObjectifyService.register(SmartQuiz.class);
    }

	public SmartUser retrieveUser(String googleId)
	{		
		/*By the google generated Id determines weather user has used our service before 
		 and will retrieve their information if they have*/
		
		if(!registered){
			register();
		}
		Ref<SmartUser> result = ofy().load().type(SmartUser.class).filter("userId",googleId).first();
		SmartUser retrieved = result.get();		
		if(retrieved != null)
		{
			System.out.println("We have a returning user");
			//TODO need to add code to retrive user quizes via google Id and display this all on home.jsp
			
			return retrieved;
		}
		else
		{
			SmartUser newUser = new SmartUser(googleId);
			ofy().save().entity(newUser).now();
			System.out.println("New User Just added to the Objectify");
			return newUser;
		}
		
	}
	
	public SmartQuiz retrieveQuiz(String quizID) {
		
		Ref<SmartQuiz> result = ofy().load().type(SmartQuiz.class).filter("quizID", quizID).first();
		SmartQuiz retrieved = result.get();
		if(retrieved != null) {
			return retrieved;
		}
		else {
			return null;
		}
	}
	
	public  String newQuizManagment(SmartQuiz newQuiz)
	{
		
		if(!registered){
			register();
		}	
		SmartQuiz inObjectify = new SmartQuiz();		
		String generatedQuizId = null;
		while(inObjectify != null){
			generatedQuizId = SmartQuiz.generateQuizId(5);
			Ref<SmartQuiz> result = ofy().load().type(SmartQuiz.class).filter("quizId",generatedQuizId).first();
			inObjectify = result.get();
		}
		newQuiz.setQuizID(generatedQuizId);
		ofy().save().entity(newQuiz).now();
		System.out.print("New Quiz Added \t");
		System.out.println(generatedQuizId);
		return generatedQuizId;
	
	}
	public HashMap<String,String> retriveCreatedQuizes(String userId)
	{
		Iterable<SmartQuiz> userCreatedQuizes = ofy().load().type(SmartQuiz.class).filter("userID", userId).iterable();
		return null;
	}

}
