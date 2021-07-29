import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.sql.*;
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String type=req.getParameter("type");
		String username=req.getParameter("username");
		String userpass=req.getParameter("userpass");
		if(type.equals("User") && username.equals("servlet") && userpass.equals("servlet"))
		{
			out.print("Welcome " + "" +username);
			out.print("<br>");
			RequestDispatcher rd=req.getRequestDispatcher("/BS_page5.html");
			rd.include(req,res);
		}
		else
		{
			out.print("Sorry, Username or Password Error!");
			RequestDispatcher rd=req.getRequestDispatcher("/BS_page2-Login.html");
			rd.include(req,res);
		}
		out.close();
	}
}