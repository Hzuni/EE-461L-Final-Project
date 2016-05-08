package smartclicker;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


public class RegisterIdServlet extends HttpServlet  {

	public String makeClass() {
		return null;

	}

	public String makeQuiz() {
		return null;

	}

	/* Used for quiz creation */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.sendRedirect("/registerId.jsp");
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		/*Code to store the class Id in the user Id and put back into objectify x*/
		SmartClickerObjectify objectify = SmartClickerObjectify.getInstance();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String classId = req.getParameter("classId");
		objectify.registerUser(user.getUserId(), classId);
		resp.sendRedirect("/home.jsp");
	}
	

	

}
