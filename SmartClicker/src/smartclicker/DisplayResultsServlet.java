package smartclicker;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayResultsServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.sendRedirect("/results.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.sendRedirect("/home.jsp");
	}

}
