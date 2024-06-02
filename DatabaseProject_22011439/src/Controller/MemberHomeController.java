package Controller;

import Model.MemberHomeModel;
import View.MemberHomeView;
import View.LoginView;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class MemberHomeController {
    private MemberHomeView view;
    private MemberHomeModel model;
    private LoginView loginView;

    public MemberHomeController(MemberHomeView view, MemberHomeModel model, LoginView loginView) {
        this.view = view;
        this.model = model;
        this.loginView = loginView;

        // Add action listeners to the menu items
        view.addMenuMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JMenu source = (JMenu) e.getSource();
                if (source.getText().equals("영화예매")) {
                    handleMovieReservation();
                } else if (source.getText().equals("예매내역")) {
                    handleReservationHistory();
                } else if (source.getText().equals("로그아웃")) {
                    handleLogout();
                }
            }
        });

        // Add action listener to the search button
        view.addSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
    }

    private void handleMovieReservation() {
        // Implement movie reservation logic here
        System.out.println("Movie reservation clicked");
    }

    private void handleReservationHistory() {
        // Implement reservation history logic here
        System.out.println("Reservation history clicked");
    }

    private void handleLogout() {
        // Implement logout logic here
        System.out.println("Logout clicked");
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
