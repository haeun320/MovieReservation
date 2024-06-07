package Controller;

import Model.*;
import View.*;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class MemberHomeController {
    private MemberHomeView view;
    private MemberHomeModel model;

    public MemberHomeController(MemberHomeView view, MemberHomeModel model) {
        this.view = view;
        this.model = model;
        
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
        
        if (movieName.isEmpty() && directorName.isEmpty() && actorName.isEmpty() && genre.isEmpty()) {
            JOptionPane.showMessageDialog(view, "하나 이상의 조건을 입력하세요.");
            return;
        }

        List<Integer> movies = model.searchMovies(movieName, directorName, actorName, genre);
        if (!movies.isEmpty()) {
        	MemberSearchResultView Mview = new MemberSearchResultView();
        	MemberSearchResultModel Mmodel = new MemberSearchResultModel(model.getConnection(), movies);
        	MemberSearchResultController controller = new MemberSearchResultController(Mview, Mmodel);
        	Mview.setVisible(true);
        	view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "No movies found");
        }
    }
}
