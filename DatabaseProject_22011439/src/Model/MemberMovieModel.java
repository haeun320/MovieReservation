package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberMovieModel {
    private Connection con;
    private int movieId;

    public MemberMovieModel(Connection con, int movieId) {
        this.con = con;
        this.movieId = movieId;
    }

    public Movie getMovieDetails() {
        Movie movie = null;
        String movieQuery = "SELECT movie_name, running_time, film_rates, director_name, movie_info, release_date, grade " +
                            "FROM Movie WHERE movie_id = ?";

        try (PreparedStatement movieStmt = con.prepareStatement(movieQuery)) {
            movieStmt.setInt(1, movieId);
            ResultSet movieRs = movieStmt.executeQuery();

            if (movieRs.next()) {
                movie = new Movie();
                movie.setMovieId(movieId);
                movie.setMovieName(movieRs.getString("movie_name"));
                movie.setRunningTime(movieRs.getInt("running_time"));
                movie.setFilmRates(movieRs.getString("film_rates"));
                movie.setDirectorName(movieRs.getString("director_name"));
                movie.setMovieInfo(movieRs.getString("movie_info"));
                movie.setReleaseDate(movieRs.getDate("release_date"));
                movie.setGrade(movieRs.getFloat("grade"));

                // Fetch actors
                String actorQuery = "SELECT actor_name FROM Actor WHERE movie_id = ?";
                try (PreparedStatement actorStmt = con.prepareStatement(actorQuery)) {
                    actorStmt.setInt(1, movieId);
                    ResultSet actorRs = actorStmt.executeQuery();
                    List<String> actors = new ArrayList<>();
                    while (actorRs.next()) {
                        actors.add(actorRs.getString("actor_name"));
                    }
                    movie.setActorNames(actors.toArray(new String[0]));
                }

                // Fetch genres
                String genreQuery = "SELECT genre_name FROM Genre WHERE movie_id = ?";
                try (PreparedStatement genreStmt = con.prepareStatement(genreQuery)) {
                    genreStmt.setInt(1, movieId);
                    ResultSet genreRs = genreStmt.executeQuery();
                    List<String> genres = new ArrayList<>();
                    while (genreRs.next()) {
                        genres.add(genreRs.getString("genre_name"));
                    }
                    movie.setGenres(genres.toArray(new String[0]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }
}
