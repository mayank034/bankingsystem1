import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class SignUp extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String username=req.getParameter("yourname");
		String useremail=req.getParameter("youremail");
		String userpass=req.getParameter("yourpass");
		String country=req.getParameter("UserCountry");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","SYSTEM");
			PreparedStatement ps=con.prepareStatement("insert into BankEmployee values(?,?,?,?)");
			ps.setString(1,username);
			ps.setString(2,useremail);
			ps.setString(3,userpass);
			ps.setString(4,country);
			int i=ps.executeUpdate();
			if(i>0)
			{
				out.print("Successfully added record to database");
				out.print("<br>");
				RequestDispatcher rd=req.getRequestDispatcher("/BS_page2-Login.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			out.print(e);
		}
		out.close();
	}
}