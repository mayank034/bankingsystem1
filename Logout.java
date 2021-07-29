import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class Logout extends HttpServlet
{
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
		{
			res.setContentType("text/html");
			PrintWriter out=res.getWriter();
			RequestDispatcher disp=req.getRequestDispatcher("/BS_page1.html");
			disp.include(req,res);
			HttpSession session=req.getSession(false);// will return current session if current session exists. If not, it will not create a new session
			session.invalidate();
			out.println("Successfully logged out");	
		}
}
