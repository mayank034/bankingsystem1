import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
//import java.sql.*;
public class RequestDispatcherServlet2 extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String n=req.getParameter("yourname");
		out.print("Welcome " +n);
		
		out.close();
	}
}