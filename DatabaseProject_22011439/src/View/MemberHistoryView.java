package View;

import javax.swing.*;

import Utils.MemberMenuBar;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MemberHistoryView extends JFrame {
    private MemberMenuBar menuBar;
    private JButton deleteSelectedButton;
    private JPanel reservationsPanel;
    private JScrollPane scrollPane;

    public MemberHistoryView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new MemberMenuBar(this);
        setJMenuBar(menuBar);

        // Create components
        deleteSelectedButton = new JButton("선택 내역 삭제");
        reservationsPanel = new JPanel();
        reservationsPanel.setLayout(new BoxLayout(reservationsPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(reservationsPanel);

        JLabel titleLabel = new JLabel("나의 예매 내역", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Times", Font.BOLD, 24));

        // Layout components
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(deleteSelectedButton, BorderLayout.EAST);

        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteSelectedButton.addActionListener(listener);
    }

    public void addReservationDetail(JPanel reservationDetail) {
        reservationsPanel.add(reservationDetail);
        reservationsPanel.revalidate();
        reservationsPanel.repaint();
    }

    public void clearReservations() {
        reservationsPanel.removeAll();
        reservationsPanel.revalidate();
        reservationsPanel.repaint();
    }
    
    public List<Integer> getSelectedReservations() {
        List<Integer> selectedReservations = new ArrayList<>();
        Component[] components = reservationsPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] panelComponents = panel.getComponents();
                for (Component panelComponent : panelComponents) {
                    if (panelComponent instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) panelComponent;
                        if (checkBox.isSelected()) {
                            // 예매 ID를 체크박스에 저장된 클라이언트 속성에서 가져오
                            selectedReservations.add((Integer) checkBox.getClientProperty("reservationId"));
                        }
                    }
                }
            }
        }
        return selectedReservations;
    }
}
