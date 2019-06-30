package dbproject;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class database 
{
	
	public static Connection getConnection()
	{
		try
		{
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/movie";
			String username="root";
			String password="Dharmin@5663";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			//JOptionPane.showMessageDialog(null, "Connected");
			return conn;
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
}
