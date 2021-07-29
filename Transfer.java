import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Transfer extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String senderaccountnumber=req.getParameter("senderaccountnumber");
		String recieveraccountnumber=req.getParameter("recieveraccountnumber");
		long transferamount=Long.parseLong(req.getParameter("transferamount"));
		
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();
		    	ResultSet rs=s.executeQuery("select BALANCE from AccountData where ACCOUNTNUMBER='"+senderaccountnumber+"'");
			while(rs.next()){
				
			      long senderbalance=Long.parseLong(rs.getString(1));
			
			      long sendernewbalance=senderbalance-transferamount;
			      Statement s1=con.createStatement();
			      s1.executeUpdate("update  AccountData set BALANCE="+sendernewbalance+" where ACCOUNTNUMBER='"+senderaccountnumber+"'");
				  out.println("sender's new balance is" +sendernewbalance);
			}
			ResultSet rs1=s.executeQuery("select BALANCE from AccountData where ACCOUNTNUMBER='"+recieveraccountnumber+"'");
			while(rs1.next()){
				
			      long recieverbalance=Long.parseLong(rs1.getString(1));
			
			      long recievernewbalance=recieverbalance+transferamount;
			      Statement s2=con.createStatement();
			      s2.executeUpdate("update  AccountData set BALANCE="+recievernewbalance+" where ACCOUNTNUMBER='"+recieveraccountnumber+"'");
				  out.println("reciever's new balance is" +recievernewbalance);
			}
			RequestDispatcher disp=req.getRequestDispatcher("/BS_page-Transfer.html");
				disp.include(req,res);
			
			
			
			
		 }
		catch(Exception e){out.print(e);}
	}
}