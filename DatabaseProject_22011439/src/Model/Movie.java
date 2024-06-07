package Model;

import java.util.Date;

public class Movie {
    private int movieId;
    private String movieName;
    private int runningTime;
    private String filmRates;
    private String directorName;
    private String movieInfo;
    private Date releaseDate;
    private float grade;
    private String[] actorNames;
    private String[] genres;

    // Getters and setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getFilmRates() {
        return filmRates;
    }

    public void setFilmRates(String filmRates) {
        this.filmRates = filmRates;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(String movieInfo) {
        this.movieInfo = movieInfo;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String[] getActorNames() {
        return actorNames;
    }

    public void setActorNames(String[] actorNames) {
        this.actorNames = actorNames;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
