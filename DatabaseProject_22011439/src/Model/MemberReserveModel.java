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
}
