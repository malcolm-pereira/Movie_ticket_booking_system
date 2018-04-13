package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
 public static Connection connectdb()
 {
	 try{
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket_booking?autoReconnect=true&useSSL=false","root","malcolmp123");
		 System.out.println("Connection Success");
		 return conn;
	 }catch(Exception e)
	 {  e.printStackTrace();
		 return null;
	 }
 }
}
