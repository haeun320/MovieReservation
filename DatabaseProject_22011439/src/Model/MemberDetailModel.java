package Model;

import java.sql.*;

public class MemberDetailModel {
    private Reservation info;
    public int reservationId;
    private Connection con;
    
    public MemberDetailModel(Connection con, int reservationId) {
        this.con = con;
        this.reservationId = reservationId;
        this.info = fetchReservationInfo();
    }
    
    private Reservation fetchReservationInfo() {
        Reservation reservation = null;
        String query = "SELECT \n"
        		+ "    r.reservation_id,\n"
        		+ "    m.movie_name,\n"
        		+ "    ss.screening_start_date,\n"
        		+ "    ss.screening_day,\n"
        		+ "    ss.screening_start_time,\n"
        		+ "    ss.screening_round,\n"
        		+ "    t.theater_name,\n"
        		+ "    s.seat_num,\n"
        		+ "    r.payment_amount,\n"
        		+ "    r.payment_method,\n"
        		+ "    r.payment_status,\n"
        		+ "    r.payment_date\n"
        		+ "FROM \n"
        		+ "    Reservation r\n"
        		+ "JOIN \n"
        		+ "    Ticket tk ON r.reservation_id = tk.reservation_id\n"
        		+ "JOIN \n"
        		+ "    ScreeningSchedule ss ON tk.screening_schedule_id = ss.screening_schedule_id\n"
        		+ "JOIN \n"
        		+ "    Movie m ON ss.movie_id = m.movie_id\n"
        		+ "JOIN \n"
        		+ "    Theater t ON ss.theater_id = t.theater_id\n"
        		+ "JOIN \n"
        		+ "    Seat s ON tk.seat_id = s.seat_id\n"
        		+ "WHERE \n"
        		+ "    r.reservation_id = ?;";

        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setMovieName(rs.getString("movie_name"));
                reservation.setScreeningDate(rs.getDate("screening_start_date"));
                reservation.setScreeningDay(rs.getString("screening_day"));
                reservation.setScreeningTime(rs.getTime("screening_start_time"));
                reservation.setScreeningRound(rs.getInt("screening_round"));
                reservation.setTheaterName(rs.getString("theater_name"));
                reservation.setSeatNumber(rs.getInt("seat_num"));
                reservation.setPrice(rs.getInt("payment_amount"));
                reservation.setPaymentMethod(rs.getString("payment_method"));
                reservation.setPaymentStatus(rs.getBoolean("payment_status"));
                reservation.setPaymentDate(rs.getDate("payment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
    }
    
    public Reservation getInfo() {
        return info;
    }

    public void updatePaymentStatus(boolean paymentStatus, String paymentDate) {
        String updateQuery = "UPDATE Reservation SET payment_status = ?, payment_date = ? WHERE reservation_id = ?";
        String ticketUpdateQuery = "UPDATE Ticket SET is_ticketed = ? WHERE reservation_id = ?";

        try (
             PreparedStatement stmt = con.prepareStatement(updateQuery);
             PreparedStatement ticketStmt = con.prepareStatement(ticketUpdateQuery)) {
            
            stmt.setBoolean(1, paymentStatus);
            stmt.setDate(2, Date.valueOf(paymentDate));
            stmt.setInt(3, reservationId);
            stmt.executeUpdate();
            
            ticketStmt.setBoolean(1, true);
            ticketStmt.setInt(2, reservationId);
            ticketStmt.executeUpdate();
            
            // Refresh the reservation info
            this.info = fetchReservationInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getMovieId() {
    	int movieId = -1;
        String query = "select s.movie_id\n"
        		+ "from Ticket t, ScreeningSchedule s\n"
        		+ "where t.screening_schedule_id = s.screening_schedule_id and t.reservation_id = ?;";

        try (
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	movieId = rs.getInt("movie_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movieId;
    }
    
    public Connection getConnection() {
    	return this.con;
    }
}
