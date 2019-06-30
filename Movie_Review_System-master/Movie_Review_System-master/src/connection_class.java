import java.sql.*;
import javax.swing.*;
public class connection_class
{
	//Connection conn=null;
	public static Connection dbConnector() 
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\vinsh\\OneDrive\\Documents\\Freemake\\EmployeeData.sqlite");
			JOptionPane.showMessageDialog(null,"Connection successfull");
			return conn;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Exception found "+e);
			return null;
		}
	}
}
