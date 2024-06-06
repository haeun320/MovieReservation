package Model;

import java.sql.*;
import java.util.*;

public class MemberSearchResultModel {
    private Connection con;
    private List<Integer> movieIds;

    public MemberSearchResultModel(Connection con, List<Integer> movieIds) {
        this.con = con;
        this.movieIds = movieIds;
    }

    public List<Map<String, String>> getMovieDetails() {
        List<Map<String, String>> movieDetailsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT movie_id, movie_name, release_date, film_rates FROM Movie WHERE movie_id = ?";
            preparedStatement = con.prepareStatement(sql);

            for (int movieId : movieIds) {
                preparedStatement.setInt(1, movieId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Map<String, String> movieDetails = new HashMap<>();
                    movieDetails.put("movie_id", String.valueOf(resultSet.getInt("movie_id")));
                    movieDetails.put("movie_name", resultSet.getString("movie_name"));
                    movieDetails.put("release_date", resultSet.getString("release_date"));
                    movieDetails.put("film_rates", resultSet.getString("film_rates"));
                    movieDetailsList.add(movieDetails);
                }
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

        return movieDetailsList;
    }
    
    public Connection getConnection() {
    	return this.con;
    }
}
