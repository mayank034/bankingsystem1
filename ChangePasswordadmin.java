import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class ChangePasswordadmin extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//String newpassword=req.getParameter("newpassword");
		//String confirmnewpassword=req.getParameter("confirmnewpassword");
		
		//if(newpassword.equals(confirmnewpassword))
		//{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
		    	s.executeUpdate("update BankEmployee set USERPASS='"+password+"' where USERNAME='"+username+"'");
			out.println("Password changed successfully");
			
			//ResultSet rs=s.executeQuery("select USERPASS from BankEmployee where USERNAME='"+username+"'");
			//while(rs.next())
			//{
				
			      //String userpass=rs.getString(1);
			      //if(userpass.equals(oldpassword))
			      //{
				//s.executeUpdate("update BankEmployee set USERPASS='"+password+"' where USERNAME='"+username+"'");
				//out.println("Password changed successfully");
			      //}
			      /*else
			      {
			      	out.println("Your password dosen't match with old password");
			      }*/
			      
			      /*Statement s1=con.createStatement();
			      s1.executeUpdate("update  AccountData set BALANCE="+sendernewbalance+" where ACCOUNTNUMBER='"+senderaccountnumber+"'");
				  out.println("sender's new balance is" +sendernewbalance);*/
			
				/*ResultSet rs1=s.executeQuery("select BALANCE from AccountData where ACCOUNTNUMBER='"+recieveraccountnumber+"'");
				while(rs1.next())
				{
				
			      		long recieverbalance=Long.parseLong(rs1.getString(1));
			
			      		long recievernewbalance=recieverbalance+transferamount;
			      		Statement s2=con.createStatement();
			      		s2.executeUpdate("update  AccountData set BALANCE="+recievernewbalance+" where ACCOUNTNUMBER='"+recieveraccountnumber+"'");
				 	 out.println("reciever's new balance is" +recievernewbalance);
				}*/
			RequestDispatcher disp=req.getRequestDispatcher("/BS_page-ChangePassword(admin).html");
			disp.include(req,res);
			
			
			
			
		  }
		catch(Exception e){out.print(e);}
		
		//else
		//{
		//out.println("New Password dosen't match Confirm New Password");
		//}
	}
}