package Controller;

import Model.MemberHistoryModel;
import Model.Reservation;
import View.MemberHistoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemberHistoryController {
    private MemberHistoryView view;
    private MemberHistoryModel model;
    private String userId;

    public MemberHistoryController(MemberHistoryView view, MemberHistoryModel model) {
        this.view = view;
        this.model = model;
        this.userId = "user1";

        // Add action listener to the delete button
        view.addDeleteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteSelected();
            }
        });

        // Load reservation history
        loadReservationHistory();
    }

    private void handleDeleteSelected() {
        // Implement delete selected logic here
        System.out.println("Delete selected clicked");
        List<Integer> selectedReservations = view.getSelectedReservations();
        if (selectedReservations.isEmpty()) {
            JOptionPane.showMessageDialog(view, "삭제할 예매 내역을 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean isValid = true;
        for (int reservationId : selectedReservations) {
            if (!model.deleteReservation(reservationId)){
            	isValid = false;
            }
        }
        
        if (!isValid) {
            JOptionPane.showMessageDialog(view, "삭제 실패");
        }
        // 삭제 후 갱신된 예매 내역 다시 불러오기
        view.clearReservations();
        loadReservationHistory();
    }

    private void loadReservationHistory() {
        List<Reservation> reservations = model.getReservationHistory(userId);
        
        for (Reservation reservation : reservations) {
        	JPanel panel = new JPanel(new BorderLayout());
        	
            JPanel reservationDetail = new JPanel(new BorderLayout());

            JCheckBox checkBox = new JCheckBox();
            checkBox.putClientProperty("reservationId", reservation.getReservationId()); // 예매 ID 저장
            
            JLabel infoLabel = new JLabel("<html>영화명: " + reservation.getMovieName() + "&nbsp;&nbsp;|&nbsp;&nbsp;상영일: " + reservation.getScreeningDate() + " (" + reservation.getScreeningRound() + "회차)" + 
            							  "<br>상영관: " + reservation.getTheaterName() + "&nbsp;&nbsp;|&nbsp;&nbsp;좌석 번호: " + reservation.getSeatNumber() + "&nbsp;&nbsp;|&nbsp;&nbsp;판매 가격: " + reservation.getPrice() + "</html>");
            
            JButton detailButton = new JButton("상세보기");
            
            panel.add(checkBox, BorderLayout.WEST);
            reservationDetail.add(infoLabel, BorderLayout.WEST);
            
            panel.add(reservationDetail);
            panel.add(detailButton, BorderLayout.EAST);
            
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            panel.add(separator, BorderLayout.SOUTH);
            view.addReservationDetail(panel);
        }
    }
}
