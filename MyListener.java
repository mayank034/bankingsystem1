import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class MyListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent e)
	{
		try
		{
			ServletContext ctx = e.getServletContext();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			ctx.setAttribute("con",c);
			System.out.println("context created");
		}	
		catch(Exception e1)
		{
			System.out.println("Enter valid account number");
		}
	}
	public void contextDestroyed(ServletContextEvent e)
	{
	}
}
