package Controller;

import View.*;
import Model.*;

public class MemberMovieController {
    private MemberMovieView view;
    private MemberMovieModel model;

    public MemberMovieController(MemberMovieView view, MemberMovieModel model) {
        this.view = view;
        this.model = model;

        // Initialize view with model data
        updateView();
    }

    private void updateView() {
        Movie movie = model.getMovieDetails();
        String[] details = {
            movie.getMovieName(),
            String.valueOf(movie.getRunningTime()),
            movie.getFilmRates(),
            movie.getDirectorName(),
            movie.getMovieInfo(),
            movie.getReleaseDate().toString(),
            String.valueOf(movie.getGrade()),
            String.join(", ", movie.getActorNames()),
            String.join(", ", movie.getGenres())
        };
        view.setMovieDetails(details);
    }
}
