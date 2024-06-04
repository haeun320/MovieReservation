package Model;

import java.sql.*;

import Utils.SQLQuery;

public class AdminHomeModel {
	private Connection con;
	
	public AdminHomeModel(Connection con) {
		this.con = con;
	}
	
	public boolean initializeDatabase() {
		if (!deleteTables()) return false;
		if (!createTables()) return false;
		return true;
	}
	
	public boolean deleteTables() {
		boolean isValid = false;
		String[] tables = {"Actor", "Genre", "Member", "Movie", "Reservation", "ScreeningSchedule", "Seat", "Theater", "Ticket"};
        
		try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            
            for (String table : tables) {
                stmt.executeUpdate("DROP TABLE IF EXISTS " + table);
            }
            
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            isValid = true;
            
        } catch (SQLException e) {
        	System.err.println("SQL Exception occurred:");
            e.printStackTrace(System.err);
        }
        
		return isValid;
	}
	
	public boolean createTables() {
		boolean isValid = true;
        
        String[] createTableQueries = {
            SQLQuery.CREATE_MOVIE_TABLE,
            SQLQuery.CREATE_ACTOR_TABLE,
            SQLQuery.CREATE_GENRE_TABLE,
            SQLQuery.CREATE_THEATER_TABLE,
            SQLQuery.CREATE_SCREENING_SCHEDULE_TABLE,
            SQLQuery.CREATE_SEAT_TABLE,
            SQLQuery.CREATE_MEMBER_TABLE,
            SQLQuery.CREATE_RESERVATION_TABLE,
            SQLQuery.CREATE_TICKET_TABLE
        };
        
        try {
            Statement stmt = con.createStatement();
            
            for (String query : createTableQueries) {
                stmt.executeUpdate(query);
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred:");
            e.printStackTrace(System.err);
            isValid = false;
        }
        
        return isValid;
    }
	
}
