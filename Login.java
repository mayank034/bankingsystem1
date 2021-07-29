import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String type=req.getParameter("type");
		String username=req.getParameter("username");
		String userpass=req.getParameter("userpass");
		HttpSession session=req.getSession();
		session.setAttribute("name",username);
		try
		{
			int flag=0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=conn.createStatement();
			if(type.equals("User"))
			{
				ResultSet rs=s.executeQuery("select USERNAME,USERPASS from BankEmployee");
				while(rs.next())
				{
				         if(username.equals(rs.getString(1))&& userpass.equals(rs.getString(2)))
					 {				
					  	out.print("Welcome " + "" +username);
						out.print("<br>");
						RequestDispatcher rd=req.getRequestDispatcher("/BS_page5.html");
						rd.include(req,res);
						flag=1;
						break;
					  }
				 }
				 
				if(flag==0)
				{
					JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("/BS_page2-Login.html");
					dispatcher.include(req,res);
				 }
			}	
			else
			{
				
				        if(username.equals("simran")&& userpass.equals("admin"))
					{
					     out.println("welcome " +""+ username);
					     RequestDispatcher dispatcher1=req.getRequestDispatcher("/BS_page5(admin).html");
					     dispatcher1.forward(req,res);
					}
					else
					{
					    JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					    RequestDispatcher dispatcher=req.getRequestDispatcher("index.html");
					    dispatcher.include(req,res);
					}  
			}
		}
		catch(Exception e)
		{
			out.print(e);
		}
		out.close();
	}
}