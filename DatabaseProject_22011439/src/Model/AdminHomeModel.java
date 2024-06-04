package Model;

import java.sql.*;

import Utils.SQLQuery;

public class AdminHomeModel {
	private Connection con;
	
	public AdminHomeModel(Connection con) {
		this.con = con;
	}
	
	public boolean initializeDatabase() {
		System.out.println("Init Database...");
		
		if (!deleteTables()) return false;
		System.out.println("Delete tables success");
		
		if (!createTables()) return false;
		System.out.println("Create tables success");
		
		if (!insertSampleData()) return false;
		System.out.println("insert sample data successs");
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
	
	public boolean insertSampleData() {
		boolean isValid = true;
		
		String[] insertSampleDataQueries = {
            SQLQuery.INSERT_SAMPLE_DATA_MOVIE,
            SQLQuery.INSERT_SAMPLE_DATA_ACTOR,
            SQLQuery.INSERT_SAMPLE_DATA_GENRE,
            SQLQuery.INSERT_SAMPLE_DATA_THEATER,
            SQLQuery.INSERT_SAMPLE_DATA_SCREENING_SCHEDULE,
            SQLQuery.INSERT_SAMPLE_DATA_SEAT,
            SQLQuery.INSERT_SAMPLE_DATA_MEMBER,
            SQLQuery.INSERT_SAMPLE_DATA_RESERVATION,
            SQLQuery.INSERT_SAMPLE_DATA_TICKET
        };
        
        try {
            Statement stmt = con.createStatement();
            
            for (String query : insertSampleDataQueries) {
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
