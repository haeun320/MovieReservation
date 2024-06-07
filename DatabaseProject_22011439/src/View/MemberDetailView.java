package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberDetailView extends JFrame {
    private JLabel[] labels;
    private JButton payButton;
    private JButton changeMovieButton;
    private JButton changeScheduleButton;

    public MemberDetailView() {
        // Set up the frame
        setTitle("Reservation Detail");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        labels = new JLabel[11];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
        }

        payButton = new JButton("결제하기");
        changeMovieButton = new JButton("예매 영화 변경");
        changeScheduleButton = new JButton("예매 일정 변경");

        // Layout components
        setLayout(new GridLayout(0, 1));
        for (JLabel label : labels) {
            add(label);
        }
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(payButton, BorderLayout.WEST);
        buttonPanel.add(changeMovieButton, BorderLayout.CENTER);
        buttonPanel.add(changeScheduleButton, BorderLayout.EAST);

        add(buttonPanel);
    }

    public void setReservationDetails(String[] details, boolean paymentStatus) {
        String[] labelsText = {
            "영화명: ", "상영일: ", "상영 요일: ", "상영 시간: ", "상영 회차: ", "상영관: ", 
            "좌석 번호: ", "가격: ", "결제 방법: ", "결제 상태: ", "결제 날짜: "
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(labelsText[i] + details[i]);
        }
        
        // Enable or disable the pay button based on payment status
        payButton.setEnabled(!paymentStatus);
    }

    public void addPayButtonListener(ActionListener listener) {
        payButton.addActionListener(listener);
    }

    public void addChangeMovieButtonListener(ActionListener listener) {
        changeMovieButton.addActionListener(listener);
    }

    public void addChangeScheduleButtonListener(ActionListener listener) {
        changeScheduleButton.addActionListener(listener);
    }
}
