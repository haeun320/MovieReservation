package Model;

import java.sql.*;

public class AdminHomeModel {
	private Connection con;
	
	public AdminHomeModel(Connection con) {
		this.con = con;
	}
	
	public boolean initializeDatabase() {
		boolean isValid = false;
		// TODO: 데이터베이스 초기화 구현하기 
		
		return isValid;
	}
}
