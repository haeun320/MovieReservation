package Controller;

import View.*;

import java.awt.event.*;
import javax.swing.JButton;

import Model.*;

public class MemberSearchResultController {
	private MemberSearchResultView view;
	private MemberSearchResultModel model;
	
	public MemberSearchResultController(MemberSearchResultView view, MemberSearchResultModel model) {
		this.view = view;
		this.model = model;
		
		view.setResults(model.getMovieDetails(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int movieId = Integer.parseInt(source.getActionCommand());
                openReservationView(movieId);
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int movieId = Integer.parseInt(source.getActionCommand());
                openDetailView(movieId);
            }
        });
    }

    private void openReservationView(int movieId) {
        MemberReserveView reserveView = new MemberReserveView();
        MemberReserveModel reserveModel = new MemberReserveModel(model.getConnection());
        new MemberReserveController(reserveView, reserveModel, movieId);
        reserveView.setVisible(true);
    	view.dispose();
    }

    private void openDetailView(int movieId) {
        // Implement the logic to open the detailed view of the selected movie
        MemberMovieView mView = new MemberMovieView();
        MemberMovieModel mModel = new MemberMovieModel(model.getConnection(), movieId);
        new MemberMovieController(mView, mModel);
        mView.setVisible(true);
    }
}
