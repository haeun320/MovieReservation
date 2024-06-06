package Controller;

import Model.MemberSeatModel;
import View.MemberSeatView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class MemberSeatController {
    private MemberSeatView view;
    private MemberSeatModel model;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private int theaterId;
    int horizontalSeats;
    int verticalSeats;
    private String today = "2024-06-01";

    public MemberSeatController(MemberSeatView view, MemberSeatModel model, int scheduleId) {
        this.view = view;
        this.model = model;
        
        theaterId = model.getTheaterId(scheduleId);
        
        Map<String, Integer> theaterInfo = model.getTheaterInfo(theaterId);
        horizontalSeats = theaterInfo.get("horizontal_seats");
        verticalSeats = theaterInfo.get("vertical_seats");

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
    
    public int getSelectedSeatId() {
    	int id = -1;
    	int seatNum = selectedRow * horizontalSeats + selectedCol+1;
    	id = model.getSelectedSeatId(seatNum, theaterId);
    	return id;
    }
    
    public List<Object> reserveInfo(){
    	List<Object> info = new ArrayList<>();
    	
    	String paymentMethod = view.getSelectedPaymentMethod();
    	Random random = new Random();
        int min = 8000;
        int max = 15000;
    	int paymentAmount = min + (random.nextInt((max - min) / 1000 + 1) * 1000);
    	
    	info.add(paymentMethod);
    	info.add(paymentMethod.equals("무통장입금") ? false : true);
    	info.add(paymentAmount);
    	info.add("user1");
    	info.add(today);
    	
    	info.add(theaterId);
    	info.add(getSelectedSeatId());
    	
    	return info;
    }

    private void handleProceed() {
        if (selectedRow != -1 && selectedCol != -1) {
            String paymentMethod = view.getSelectedPaymentMethod();
            if (paymentMethod != null) {
                System.out.println("좌석: Row " + (selectedRow + 1) + ", Col " + (selectedCol + 1));
                System.out.println("결제 수단: " + paymentMethod);
                System.out.println("seat_id: " + getSelectedSeatId());
                // TODO: 티켓, 예매 정보 저장 
                boolean isValid = model.insertReserveInfo(reserveInfo());
                if (isValid) {
                    JOptionPane.showMessageDialog(view, "예매 성공");
                }
                else {
                    JOptionPane.showMessageDialog(view, "예매 실패");
                }
            } else {
                JOptionPane.showMessageDialog(view, "결제 수단을 선택하세요.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "좌석을 선택하세요.");
        }
    }
}
