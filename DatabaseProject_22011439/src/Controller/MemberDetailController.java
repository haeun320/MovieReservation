package Controller;

import Model.*;
import Utils.Constants;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberDetailController {
    private MemberDetailView view;
    private MemberDetailModel model;
    private MemberHistoryView hView;
    
    public MemberDetailController(MemberDetailView view, MemberDetailModel model, MemberHistoryView hView) {
        this.view = view;
        this.model = model;
        this.hView = hView;

        // Initialize view with model data
        updateView();

        // Add listeners
        view.addPayButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayButton();
            }
        });

        view.addChangeMovieButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangeMovieButton();
            }
        });

        view.addChangeScheduleButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleChangeScheduleButton();
            }
        });
    }

    private void updateView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        Reservation reservation = model.getInfo();
        String[] details = new String[11];
        
        details[0] = reservation.getMovieName();
        details[1] = dateFormat.format(reservation.getScreeningDate());
        details[2] = reservation.getScreeningDay();
        details[3] = timeFormat.format(reservation.getScreeningTime());
        details[4] = String.valueOf(reservation.getScreeningRound());
        details[5] = reservation.getTheaterName();
        details[6] = String.valueOf(reservation.getSeatNumber());
        details[7] = String.valueOf(reservation.getPrice());
        details[8] = reservation.getPaymentMethod();
        details[9] = reservation.getPaymentStatus() ? "완료" : "미완료";
        details[10] = reservation.getPaymentDate() != null ? dateFormat.format(reservation.getPaymentDate()) : "N/A";
        
        view.setReservationDetails(details, reservation.getPaymentStatus());
    }

    private void handlePayButton() {
        // Implement pay button logic here
        System.out.println("결제하기 버튼 클릭됨");
        
        if (processPayment()) {
            model.updatePaymentStatus(true, "2024-06-01");
            updateView();
        }
    }

    private boolean processPayment() {
        // Simulate payment processing
        // In a real-world scenario, integrate with a payment gateway here
        return true;
    }

    private void handleChangeMovieButton() {
        // TODO: 예매 영화 변경 
        System.out.println("예매 영화 변경 버튼 클릭됨");
        Constants constants = Constants.getInstance();
        constants.setIsEdit(true);
        constants.setReservationId(model.reservationId);
        
        MemberHomeView mView = new MemberHomeView();
        MemberHomeModel mModel = new MemberHomeModel(model.getConnection());
        MemberHomeController controller = new MemberHomeController(mView, mModel);
        mView.setVisible(true);
        view.dispose();
        hView.dispose();
    }

    private void handleChangeScheduleButton() {
        // TODO: 예매 일정 변경  
        System.out.println("예매 일정 변경 버튼 클릭됨");
        Constants constants = Constants.getInstance();
        constants.setIsEdit(true);
        constants.setReservationId(model.reservationId);
        int movieId = model.getMovieId();
        
        MemberReserveView mView = new MemberReserveView();
        MemberReserveModel mModel = new MemberReserveModel(model.getConnection());
        MemberReserveController controller = new MemberReserveController(mView, mModel, movieId);
        mView.setVisible(true);
        view.dispose();
        hView.dispose();
    }
}
