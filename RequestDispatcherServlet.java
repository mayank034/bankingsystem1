import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.sql.*;
public class RequestDispatcherServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String username=req.getParameter("yourname");
		String userpass=req.getParameter("yourpass");
		if(username.equals("servlet") && userpass.equals("servlet"))
		{
			RequestDispatcher rd=req.getRequestDispatcher("/servlet2");
			rd.forward(req,res);
		}
		else
		{
			out.print("Sorry, Username or Password Error!");
			RequestDispatcher rd=req.getRequestDispatcher("/welcome.html");
			rd.include(req,res);
		}
		out.close();
	}
}