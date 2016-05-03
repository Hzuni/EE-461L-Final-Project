 package smartclicker;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.api.server.spi.HttpProxyServlet;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class SmartClickerServlet extends HttpProxyServlet {    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
    	ObjectifyService.register(SmartUser.class);
    	resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
