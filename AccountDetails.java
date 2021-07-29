import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
public class AccountDetails extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.print("<html><head><style>table, th, td { border: 1px solid black;}</style></head>");
		out.print("<body>");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
			
			
			ResultSet rs=s.executeQuery("SELECT * FROM AccountData");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>SER NO.</th> <th>NAME</th> <th>ACCOUNTNUMBER</th> <th>ADHARNUMBER</th> <th>MOBILENUMBER</th> <th>EMAIL</th> <th>FATHERNAME</th> <th>ACCOUNTTYPE</th> <th>BALANCE</th> <th>GENDER</th> <th>UPDATE</th>");
			out.print("</tr>");
			int serno=1;
			while(rs.next())
			{
				out.print("<tr>");
				out.print("<td>"+serno);
				out.print("</td>");
				out.print("<td>"+rs.getString(1));
				out.print("</td>");
				out.print("<td>"+rs.getLong(2));
				out.print("</td>");
				out.print("<td>"+rs.getLong(3));
				out.print("</td>");
				out.print("<td>"+rs.getLong(4));
				out.print("</td>");
				out.print("<td>"+rs.getString(5));
				out.print("</td>");
				out.print("<td>"+rs.getString(6));
				out.print("</td>");
				out.print("<td>"+rs.getString(7));
				out.print("</td>");
				out.print("<td>"+rs.getLong(8));
				out.print("</td>");
				out.print("<td>"+rs.getString(9));
				out.print("</td>");
				out.print("</td>");
				out.print("<td>");
				out.print("<a href='Update.html'>");
				out.print("Update &nbsp;");
				out.print("</a>");
				out.print("</td>");
				out.print("</tr>");
				serno++;
			}
			out.print("</table>");
			
			
		}
		catch(Exception e)
		{
			out.print(e);
		}
		out.print("</body>");
		out.print("</html>");
		out.close();
	}
}
