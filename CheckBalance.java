import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
public class CheckBalance extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String accountnumber=req.getParameter("accountnumber");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery("select BALANCE from AccountData where ACCOUNTNUMBER='"+accountnumber+"'");
			while(rs.next())
			{
				out.println(rs.getString(1)+"Rs.");
			}
				RequestDispatcher disp=req.getRequestDispatcher("/BS_page-CheckBalance.html");
				disp.include(req,res);
		}
		catch(Exception e)
		{
			out.println("Enter valid account number");
		}
	}
}
