import java.io.*;
import java.sql.*;

public class MySQLConnector {
	Connection con;
	
	public MySQLConnector() {
		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/db1";
		String userid = "root";
		String pwd = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Load Driver success");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Ready to connect DB...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("Database connection success");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
