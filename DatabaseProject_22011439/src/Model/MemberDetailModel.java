package Model;

import java.sql.*;

public class MemberDetailModel {
	private Reservation info;
	private int reservationId;
	private Connection con;
	
	public MemberDetailModel(Connection con, int reservationId) {
		this.con = con;
		this.reservationId = reservationId;
		this.info = fetchReservationInfo();
	}
	
	private Reservation fetchReservationInfo() {
        Reservation reservation = null;
        String query = "SELECT r.reservation_id, m.movie_name, ss.screening_start_date, ss.screening_day, ss.screening_start_time, " +
                       "ss.screening_round, t.theater_name, s.seat_num, r.payment_amount, r.payment_method, r.payment_status, r.payment_date " +
                       "FROM Reservation r " +
                       "JOIN ScreeningSchedule ss ON r.reservation_id = ss.screening_schedule_id " +
                       "JOIN Movie m ON ss.movie_id = m.movie_id " +
                       "JOIN Theater t ON ss.theater_id = t.theater_id " +
                       "JOIN Seat s ON ss.theater_id = s.theater_id " +
                       "WHERE r.reservation_id = ?";

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
}
