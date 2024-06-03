package Utils;
import java.io.*;
import java.sql.*;

public class MySQLConnector {
	Connection con;
	
	public boolean connectToDatabase(String userid, String pwd) {
        boolean isValid = false;

		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/db1";
		
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
			isValid = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public boolean disconnectToDatabase() {
		boolean isValid = false;
		try {
            if (con != null && !con.isClosed()) {
                con.close();
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return isValid;
	}
	
	public Connection getConnection() {
        return con;
    }
}
