package Model;

import java.sql.*;
import java.util.*;

public class MemberHomeModel {
    private Connection con;

    public MemberHomeModel(Connection con) {
        this.con = con;
    }

    public List<String> searchMovies(String movieName, String directorName, String actorName, String genre) {
        List<String> movies = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Build the SQL query dynamically
            StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT m.movie_name FROM Movie m " +
                "LEFT JOIN Actor a ON m.movie_id = a.movie_id " +
                "LEFT JOIN Genre g ON m.movie_id = g.movie_id WHERE 1=1"
            );
            List<String> parameters = new ArrayList<>();

            if (movieName != null && !movieName.isEmpty()) {
                sql.append(" AND m.movie_name LIKE ?");
                parameters.add("%" + movieName + "%");
            }
            if (directorName != null && !directorName.isEmpty()) {
                sql.append(" AND m.director_name LIKE ?");
                parameters.add("%" + directorName + "%");
            }
            if (actorName != null && !actorName.isEmpty()) {
                sql.append(" AND a.actor_name LIKE ?");
                parameters.add("%" + actorName + "%");
            }
            if (genre != null && !genre.isEmpty()) {
                sql.append(" AND g.genre_name LIKE ?");
                parameters.add("%" + genre + "%");
            }

            preparedStatement = con.prepareStatement(sql.toString());

            // Set the parameters for the prepared statement
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setString(i + 1, parameters.get(i));
            }

            // Execute query
            resultSet = preparedStatement.executeQuery();

            // Process results
            while (resultSet.next()) {
                movies.add(resultSet.getString("movie_name"));
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

        return movies;
    }
}
