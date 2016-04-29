package smartclicker;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.api.server.spi.HttpProxyServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.Result;

@SuppressWarnings("serial")
public class SmartClickerServlet extends HttpProxyServlet {    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
    	ObjectifyService.register(SmartUser.class);
    	resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
    

	
}
