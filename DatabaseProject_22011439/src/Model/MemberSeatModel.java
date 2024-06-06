package Model;

import java.sql.*;
import java.util.*;

public class MemberSeatModel {
    private Connection con;

    public MemberSeatModel(Connection con) {
        this.con = con;
    }
    
    public int getTheaterId(int scheduleId) {
    	int id = -1;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT s.theater_id " +
                         "FROM ScreeningSchedule s " +
                         "WHERE s.screening_schedule_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, scheduleId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("theater_id");
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

    public List<Map<String, Object>> getSeatInfo(int theaterId) {
        List<Map<String, Object>> seats = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT seat_num, is_in_use FROM Seat WHERE theater_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, theaterId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> seat = new HashMap<>();
                seat.put("seat_num", resultSet.getInt("seat_num"));
                seat.put("is_in_use", resultSet.getBoolean("is_in_use"));
                seats.add(seat);
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

        return seats;
    }

    public Map<String, Integer> getTheaterInfo(int theaterId) {
        Map<String, Integer> theaterInfo = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT horizontal_seats, vertical_seats FROM Theater WHERE theater_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, theaterId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                theaterInfo.put("horizontal_seats", resultSet.getInt("horizontal_seats"));
                theaterInfo.put("vertical_seats", resultSet.getInt("vertical_seats"));
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

        return theaterInfo;
    }
}
