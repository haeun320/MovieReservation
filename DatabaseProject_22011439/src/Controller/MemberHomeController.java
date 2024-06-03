package Controller;

import Model.MemberHomeModel;
import View.MemberHomeView;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class MemberHomeController {
    private MemberHomeView view;
    private MemberHomeModel model;

    public MemberHomeController(MemberHomeView view, MemberHomeModel model) {
        this.view = view;
        this.model = model;
        
        // Add action listener to the search button
        view.addSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
    }

    private void handleSearch() {
        String movieName = view.getMovieName();
        String directorName = view.getDirectorName();
        String actorName = view.getActorName();
        String genre = view.getGenre();

        List<String> movies = model.searchMovies(movieName, directorName, actorName, genre);
        if (!movies.isEmpty()) {
            String message = "Movies found: " + String.join(", ", movies);
            JOptionPane.showMessageDialog(view, message);
        } else {
            JOptionPane.showMessageDialog(view, "No movies found");
        }
    }
}
