import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.util.Random;
public class NewAccount extends HttpServlet
{
	public void doPost(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
	    	String user=req.getParameter("yourname");
		String adhar=req.getParameter("youradharnumber");
		String mobile=req.getParameter("yourmobilenumber");
		String email=req.getParameter("youremail");
		String father=req.getParameter("yourfathername");
		String accountype=req.getParameter("youraccounttype");
		String balance=req.getParameter("yourbalancenumber");
		String gender=req.getParameter("yourgender");
		String update="";
		
		
        	Random random=new Random();		
		String s="1234567890";
		char[] otp=new char[10];
		for(int i=0;i<10;i++){
			otp[i]=s.charAt(random.nextInt(s.length()));//random.nextInt(s.length()) will return a random number from 0 th s.length() i.e from 0(inclusive) to 10(exclusive)
			}
			
			String strArray[]=new String[otp.length];
			for(int i=0;i<otp.length;i++){
				strArray[i]=String.valueOf(otp[i]);//converts otp[i] i.e say (char)5 into (String)5
			}
			
			String res1="";
			for(String num:strArray){
				res1+=num;
			}
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			PreparedStatement ps=con.prepareStatement("insert into AccountData values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,user);
			ps.setString(2,res1);
			ps.setString(3,adhar);
			ps.setString(4,mobile);
			ps.setString(5,email);
			ps.setString(6,father);
			ps.setString(7,accountype);
			ps.setString(8,balance);
			ps.setString(9,gender);
			ps.executeUpdate();
			PreparedStatement ps1=con.prepareStatement("select * from AccountData where ACCOUNTNUMBER='"+res1+"'");
			ResultSet rs=ps1.executeQuery();
				while(rs.next()){
					out.println("<br>Name= "+rs.getString(1));
					out.println("<br>Account Number= "+rs.getString(2));
					out.println("<br>Adhar Number= "+rs.getString(3));
					out.println("<br>Mobile Number= "+rs.getString(4));
					out.println("<br>Email Id= "+rs.getString(5));
					out.println("<br>Father's Name= "+rs.getString(6));
					out.println("<br>Accountype= "+rs.getString(7));
					out.println("<br>Balance= "+rs.getString(8));
					out.println("<br>Gender= "+rs.getString(9));
				}
			RequestDispatcher disp=req.getRequestDispatcher("/BS_page2-Login.html");
				disp.include(req,res);
			
				out.println("<br>Registered Successfully");
			
		
			
			
			}
			catch(Exception e){out.println(e);}
			out.close();
	}
}
			