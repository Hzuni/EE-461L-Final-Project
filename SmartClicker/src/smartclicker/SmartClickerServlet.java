package smartclicker;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.api.server.spi.HttpProxyServlet;

@SuppressWarnings("serial")
public class SmartClickerServlet extends HttpProxyServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
}
