import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Debit extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String accountnumber=req.getParameter("accountnumber");
		long debitamount=Long.parseLong(req.getParameter("debitamount"));
		
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s1=con.createStatement();
		    ResultSet rs=s1.executeQuery("select BALANCE from AccountData where ACCOUNTNUMBER='"+accountnumber+"'");
			while(rs.next()){
				
			      long amount=Long.parseLong(rs.getString(1));
			
			      long newbalance=amount-debitamount;
			      Statement s=con.createStatement();
			      s.executeUpdate("update  AccountData set BALANCE="+newbalance+" where ACCOUNTNUMBER='"+accountnumber+"'");
				  out.println("new balance is" +newbalance);
			}
			RequestDispatcher disp=req.getRequestDispatcher("/BS_page-Debit.html");
				disp.include(req,res);
			
			
			
			
		}
		catch(Exception e){System.out.println(e);}
	}
}