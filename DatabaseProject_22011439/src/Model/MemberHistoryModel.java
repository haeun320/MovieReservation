package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberHistoryModel {
    private Connection con;

    public MemberHistoryModel(Connection con) {
        this.con = con;
    }

    public List<Reservation> getReservationHistory(String userId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT r.reservation_id, m.movie_name, ss.screening_start_date, ss.screening_start_time, th.theater_name, ss.screening_round, s.seat_num, ti.standard_price " +
                "FROM Reservation r " +
                "JOIN Ticket ti ON r.reservation_id = ti.reservation_id " +
                "JOIN ScreeningSchedule ss ON ti.screening_schedule_id = ss.screening_schedule_id " +
                "JOIN Movie m ON ss.movie_id = m.movie_id " +
                "JOIN Theater th ON ss.theater_id = th.theater_id " +
                "JOIN Seat s ON ti.seat_id = s.seat_id " +
                "WHERE r.member_id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setMovieName(rs.getString("movie_name"));
                reservation.setScreeningDate(rs.getDate("screening_start_date"));
                reservation.setScreeningTime(rs.getTime("screening_start_time"));
                reservation.setTheaterName(rs.getString("theater_name"));
                reservation.setScreeningRound(rs.getInt("screening_round"));
                reservation.setSeatNumber(rs.getInt("seat_num"));
                reservation.setPrice(rs.getInt("standard_price"));

                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservations;
    }
    
    public boolean deleteReservation(int reservationId) {
    	boolean isValid = false;
    	String deleteTicketSql = "DELETE FROM Ticket WHERE reservation_id = ?";
        String deleteReservationSql = "DELETE FROM Reservation WHERE reservation_id = ?";

        try {
            con.setAutoCommit(false);

            try (PreparedStatement deleteTicketStmt = con.prepareStatement(deleteTicketSql);
                 PreparedStatement deleteReservationStmt = con.prepareStatement(deleteReservationSql)) {
                 
                // Ticket 테이블에서 삭제
                deleteTicketStmt.setInt(1, reservationId);
                deleteTicketStmt.executeUpdate();

                // Reservation 테이블에서 삭제
                deleteReservationStmt.setInt(1, reservationId);
                deleteReservationStmt.executeUpdate();

                con.commit(); 
                isValid = true;
            } catch (SQLException ex) {
                con.rollback(); // 트랜잭션 롤백
                ex.printStackTrace();
            } finally {
                con.setAutoCommit(true); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }
    
    public Connection getConnection() {
    	return this.con;
    }
}
