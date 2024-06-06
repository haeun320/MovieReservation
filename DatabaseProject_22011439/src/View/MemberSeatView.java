package View;

import javax.swing.*;

import Utils.MemberMenuBar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class MemberSeatView extends JFrame {
    private MemberMenuBar menuBar;
    private JPanel seatPanel;
    private JButton[][] seatButtons;
    private JButton proceedButton;
    private JLabel selectedSeatLabel;
    private ButtonGroup paymentMethodsGroup;

    public MemberSeatView(int horizontalSeats, int verticalSeats) {
        // Set up the frame
        setTitle("Seat Selection");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create menu bar
        menuBar = new MemberMenuBar(this);
        setJMenuBar(menuBar);
        
        seatPanel = new JPanel(new GridLayout(verticalSeats, horizontalSeats));
        seatButtons = new JButton[verticalSeats][horizontalSeats];

        for (int row = 0; row < verticalSeats; row++) {
            for (int col = 0; col < horizontalSeats; col++) {
                seatButtons[row][col] = new JButton();
                seatPanel.add(seatButtons[row][col]);
            }
        }

        proceedButton = new JButton("결제하기");
        proceedButton.setEnabled(false);
        selectedSeatLabel = new JLabel("선택된 좌석: 없음");

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentPanel.setBorder(BorderFactory.createTitledBorder("결제수단"));

        JRadioButton creditCardOption = new JRadioButton("신용카드");
        JRadioButton mobilePaymentOption = new JRadioButton("휴대폰결제");
        JRadioButton bankTransferOption = new JRadioButton("무통장입금");

        paymentMethodsGroup = new ButtonGroup();
        paymentMethodsGroup.add(creditCardOption);
        paymentMethodsGroup.add(mobilePaymentOption);
        paymentMethodsGroup.add(bankTransferOption);

        paymentPanel.add(creditCardOption);
        paymentPanel.add(mobilePaymentOption);
        paymentPanel.add(bankTransferOption);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(selectedSeatLabel, BorderLayout.WEST);
        bottomPanel.add(paymentPanel, BorderLayout.CENTER);
        bottomPanel.add(proceedButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(seatPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton[][] getSeatButtons() {
        return seatButtons;
    }

    public void setSeatStatus(int row, int col, boolean isInUse) {
        if (isInUse) {
        	seatButtons[row][col].setBackground(Color.GREEN);
            seatButtons[row][col].setEnabled(true);
        } else {
            seatButtons[row][col].setBackground(Color.RED);
            seatButtons[row][col].setEnabled(false);
        }
    }

    public void addSeatSelectionListener(ActionListener listener) {
        for (int row = 0; row < seatButtons.length; row++) {
            for (int col = 0; col < seatButtons[row].length; col++) {
                seatButtons[row][col].addActionListener(listener);
            }
        }
    }

    public void addProceedButtonListener(ActionListener listener) {
        proceedButton.addActionListener(listener);
    }

    public void setSelectedSeat(String seat) {
        selectedSeatLabel.setText("선택된 좌석: " + seat);
        proceedButton.setEnabled(true);
    }

    public String getSelectedPaymentMethod() {
        for (Enumeration<AbstractButton> buttons = paymentMethodsGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
