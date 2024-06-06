package Controller;

import Model.MemberSeatModel;
import View.MemberSeatView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class MemberSeatController {
    private MemberSeatView view;
    private MemberSeatModel model;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private int theaterId;

    public MemberSeatController(MemberSeatView view, MemberSeatModel model, int scheduleId) {
        this.view = view;
        this.model = model;
        
        theaterId = model.getTheaterId(scheduleId);
        
        Map<String, Integer> theaterInfo = model.getTheaterInfo(theaterId);
        int horizontalSeats = theaterInfo.get("horizontal_seats");
        int verticalSeats = theaterInfo.get("vertical_seats");

        List<Map<String, Object>> seats = model.getSeatInfo(theaterId);

        for (Map<String, Object> seat : seats) {
            int seatNum = (int)seat.get("seat_num");
            boolean isInUse = (boolean) seat.get("is_in_use");

            int row = (seatNum - 1) / horizontalSeats;
            int col = (seatNum - 1) % horizontalSeats;

            view.setSeatStatus(row, col, isInUse);
        }
        
        JButton[][] seatButtons = view.getSeatButtons();

        view.addSeatSelectionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                for (int row = 0; row < verticalSeats; row++) {
                    for (int col = 0; col < horizontalSeats; col++) {
                        if (seatButtons[row][col] == source) {
                            selectedRow = row;
                            selectedCol = col;
                            view.setSelectedSeat("Row " + (row + 1) + ", Col " + (col + 1));
                            return;
                        }
                    }
                }
            }
        });

        view.addProceedButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProceed();
            }
        });
    }

    private void handleProceed() {
        if (selectedRow != -1 && selectedCol != -1) {
            String paymentMethod = view.getSelectedPaymentMethod();
            if (paymentMethod != null) {
                // 결제 로직 구현
                System.out.println("좌석: Row " + (selectedRow + 1) + ", Col " + (selectedCol + 1));
                System.out.println("결제 수단: " + paymentMethod);
            } else {
                JOptionPane.showMessageDialog(view, "결제 수단을 선택하세요.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "좌석을 선택하세요.");
        }
    }
}
