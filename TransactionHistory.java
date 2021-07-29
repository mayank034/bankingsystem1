import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
public class TransactionHistory extends HttpServlet
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
			
			
			ResultSet rs=s.executeQuery("SELECT * FROM TransactionHistory");
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>SER NO.</th> <th>NAME</th> <th>SENDER</th> <th>RECIEVER</th> <th>AMOUNT</th> <th>DATE</th>");
			out.print("</tr>");
			//int serno=1;
			while(rs.next())
			{
				out.print("<tr>");
				out.print("<td>"+rs.getInt(1));
				out.print("</td>");
				out.print("<td>"+rs.getString(2));
				out.print("</td>");
				out.print("<td>"+rs.getString(3));
				out.print("</td>");
				out.print("<td>"+rs.getString(4));
				out.print("</td>");
				out.print("<td>"+rs.getInt(5));
				out.print("</td>");
				out.print("<td>"+rs.getString(6));
				out.print("</td>");
				out.print("</tr>");
				//serno++;
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
