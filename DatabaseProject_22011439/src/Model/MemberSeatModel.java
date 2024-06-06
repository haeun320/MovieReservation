package Model;

import java.sql.*;
import java.util.*;

public class MemberSeatModel {
    private Connection con;
    private int scheduleId;
    private int movieId;
    
    public MemberSeatModel(Connection con, int scheduleId) {
        this.con = con;
        this.scheduleId = scheduleId;
    }
    
    public int getMovieId() {
    	int id = -1;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT s.movie_id " +
                         "FROM ScreeningSchedule s " +
                         "WHERE s.screening_schedule_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, scheduleId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("movie_id");
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
    
    public int getSelectedSeatId(int seatNum, int theaterId) {
    	int id = -1;
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT seat_id FROM Seat WHERE seat_num = ? AND theater_id = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, seatNum);
            preparedStatement.setInt(2, theaterId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("seat_id");
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
    
    public boolean insertReserveInfo(List<Object> info) {
        boolean isValid = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con.setAutoCommit(false);

            // Reservation
            String sql = "INSERT INTO Reservation (payment_method, payment_status, payment_amount, member_id, payment_date) VALUES (?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, (String) info.get(0));
            preparedStatement.setBoolean(2, (Boolean) info.get(1));
            preparedStatement.setInt(3, (Integer) info.get(2));
            preparedStatement.setString(4, (String) info.get(3));
            preparedStatement.setDate(5, java.sql.Date.valueOf((String) info.get(4))); 

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int reservationId = resultSet.getInt(1);

                    // Ticket
                    sql = "INSERT INTO Ticket (screening_schedule_id, theater_id, seat_id, reservation_id, is_ticketed, standard_price, sale_price) VALUES (?,?,?,?,?,?,?)";
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setInt(1, scheduleId);
                    preparedStatement.setInt(2, (Integer) info.get(5));
                    preparedStatement.setInt(3, (Integer) info.get(6));
                    preparedStatement.setInt(4, reservationId);
                    preparedStatement.setBoolean(5, (Boolean) info.get(1)); // payment_status
                    preparedStatement.setInt(6, (int) ((Integer) info.get(2) * 1.1)); // standard_price 계산
                    preparedStatement.setInt(7, (Integer) info.get(2)); // sale_price

                    rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        isValid = true;
                    }
                }
            }

            con.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
    
    public Connection getConnection() {
    	return this.con;
    }
}
