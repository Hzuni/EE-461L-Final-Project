package smartclicker;
import static com.googlecode.objectify.ObjectifyService.ofy;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.logging.Logger;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;


public class SmartClickerObjectify {
	private  boolean registered =false;	
    private static SmartClickerObjectify instance = new SmartClickerObjectify();    
    static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Logger logger = Logger.getLogger("MyLogger"); 
    
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
    	ObjectifyService.register(SmartQuestion.class);
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
			logger.info("We have a returning user");
			return retrieved;
		}
		else
		{
			SmartUser newUser = new SmartUser(googleId);
			ofy().save().entity(newUser).now();
			logger.info("New User Just added to the Objectify");
			return newUser;
		}
		
	}
	
	public void saveQuizzes(String googleId, String qId, String title)
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
			
			logger.info("We hav a returning user");
			HashMap<String,String> updateStorage = retrieved.displayCreatedQuizes();
			updateStorage.put(qId, title);
			
			ofy().save().entity(retrieved).now();
		}
	}
	
	public SmartQuiz retrieveQuiz(String IDs) {
		
		logger.info("ID Loading: " + IDs);
		if(!registered){
			register();
		}
		Ref<SmartQuiz> result = ofy().load().type(SmartQuiz.class).filter("quizID",IDs).first();
		logger.info("Loading Quiz 1: " + result);
		SmartQuiz retrieved = result.get();
		logger.info("What is retrieved: " + retrieved);
		return retrieved;
	}
	public String addNewQuestion(SmartQuestion newQuestion)
	{
		
		//TODO will be written to add a new quiz question to the Objectify
		// Returns the Question id which'll be stored within a questionIds list in SmartQuiz
		if(!registered){
			register();
		}
		SmartQuestion inObjectify = new SmartQuestion();		
		String generatedQuizId = null;
		while(inObjectify != null){
			generatedQuizId = generateId(8);
			Ref<SmartQuestion> result = ofy().load().type(SmartQuestion.class).filter("questionID",generatedQuizId).first();
			/*If the Id isn't in the Objectify then the Id will be null*/
			inObjectify = result.get();
		}
		newQuestion.setQuestionID(generatedQuizId);
		ofy().save().entity(newQuestion).now();
		logger.info("New Question Added \t");
		logger.info(generatedQuizId);
		return generatedQuizId;
	}
	
	public SmartQuestion retrieveQuestion(String ID) {
			
		if(!registered){
			register();
		}
		Ref<SmartQuestion> result = ofy().load().type(SmartQuestion.class).filter("questionID", ID).first();
		SmartQuestion retrieved = result.get();
		if(retrieved != null) {
			return retrieved;
		}
		else {
				return null;
		}
	}
	public void updateQuestion(String ID, int index) {
		
		if(!registered){
			register();
		}
		Ref<SmartQuestion> result = ofy().load().type(SmartQuestion.class).filter("questionID", ID).first();
		SmartQuestion retrieved = result.get();
		if(retrieved != null) {
			retrieved.addStudentResponse(index);
			ofy().save().entity(retrieved).now();
		}
	}
	
	public  String addNewQuiz(SmartQuiz newQuiz)
	{
		
		if(!registered){
			register();
		}	
		SmartQuiz inObjectify = new SmartQuiz();		
		String generatedQuizId = "";
		while(inObjectify != null){
			generatedQuizId = generateId(5);
			Ref<SmartQuiz> result = ofy().load().type(SmartQuiz.class).filter("quizID",generatedQuizId).first();
			inObjectify = result.get();
		}
		newQuiz.setQuizID(generatedQuizId);
		ofy().save().entity(newQuiz).now();
		logger.info("New Quiz Added \t");
		logger.info(generatedQuizId);
		return generatedQuizId;
	
	}
	
	public static String generateId(int len) {
		StringBuilder idGen = new StringBuilder(len);
		
		for(int i = 0; i < len; i += 1) {
			idGen.append(chars.charAt(new SecureRandom().nextInt(chars.length())));
		}
		
		return idGen.toString();
	}
}
