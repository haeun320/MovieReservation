package Model;

import java.sql.*;
import java.util.*;

public class MemberReserveModel {
    private Connection con;

    public MemberReserveModel(Connection con) {
        this.con = con;
    }

    public List<Map<String, String>> getScreeningSchedules(int movieId) {
        List<Map<String, String>> schedules = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT t.theater_name, s.screening_start_date, s.screening_start_time " +
                         "FROM ScreeningSchedule s, Theater t " +
                         "WHERE s.theater_id = t.theater_id and s.movie_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, movieId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, String> schedule = new HashMap<>();
                schedule.put("theater_name", resultSet.getString("theater_name"));
                schedule.put("screening_start_date", resultSet.getString("screening_start_date"));
                schedule.put("screening_start_time", resultSet.getString("screening_start_time"));
                schedules.add(schedule);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return schedules;
    }
    
    public int getSelectedScheduleID(List<String> selectedSchedule) {
    	int id = -1;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT s.screening_schedule_id " +
                         "FROM ScreeningSchedule s, Theater t " +
                         "WHERE s.theater_id = t.theater_id AND t.theater_name = ? " +
                         "AND s.screening_start_date = ? AND s.screening_start_time = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, selectedSchedule.get(0)); // theater_name
            preparedStatement.setString(2, selectedSchedule.get(1)); // screening_start_date
            preparedStatement.setString(3, selectedSchedule.get(2)); // screening_start_time
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("screening_schedule_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return id;
    }
    
    public List<Integer> getSeatInfo(int scheduleId){
    	List<Integer> seat = new ArrayList<>();
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	String sql = "SELECT t.horizontal_seats, t.vertical_seats " +
	        			 "FROM ScreeningSchedule s, Theater t " +
	                     "WHERE s.theater_id = t.theater_id AND s.screening_schedule_id = ?";
	       preparedStatement = con.prepareStatement(sql);
	       preparedStatement.setInt(1, scheduleId);
	       resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                seat.add(resultSet.getInt("horizontal_seats")); 
                seat.add(resultSet.getInt("vertical_seats")); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    	return seat;
    }
    
    public Connection getConnection() {
    	return this.con;
    }
}
