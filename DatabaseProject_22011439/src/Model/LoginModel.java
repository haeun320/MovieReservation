package Model;

import java.sql.*;
import Utils.MySQLConnector;

public class LoginModel {
    Connection con;
    
    private MySQLConnector db;
    private String id;
    private String password;
    private String userType;
    
    public LoginModel() {
    	this.db = new MySQLConnector();
    }

    public String getID() {
        return id;
    }

    public void setID(String username) {
        this.id = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserType() {
    	return userType;
    }
    
    public void setUserType(String userType) {
    	this.userType = userType;
    }
    

    public boolean validateUser() {
        boolean isValid = false;
        
        if (userType.equals("admin") && id.equals("root") && password.equals("1234")) {
            isValid = db.connectToDatabase(id, password);
        }
        else if (userType.equals("member") && id.equals("user1") && password.equals("user1")) {
            isValid = db.connectToDatabase(id, password);
        }
        
        return isValid;
    }
    
    public Connection getConnection() {
    	con = db.getConnection();
    	return con;
    }
}
