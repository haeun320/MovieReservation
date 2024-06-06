package View;

import javax.swing.*;
import java.awt.*;
import Utils.AdminMenuBar;

public class AdminAlterDataView extends JFrame {
    private AdminMenuBar menuBar;
    private JComboBox<String> tableDropdown;
    private JTextArea queryTextArea;
    private JTextArea columnInfoTextArea;
    private JButton saveButton;
    private JButton cancelButton;

    private String[] tables = {"Actor", "Genre", "Member", "Movie", "Reservation", "ScreeningSchedule", "Seat", "Theater", "Ticket"};

    public AdminAlterDataView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new AdminMenuBar(this);
        setJMenuBar(menuBar);

        // Create components
        JLabel titleLabel = new JLabel("데이터 삭제 및 변경");
        titleLabel.setFont(new Font("Times", Font.BOLD, 24));

        tableDropdown = new JComboBox<>(tables);

        columnInfoTextArea = new JTextArea(5, 40);
        columnInfoTextArea.setEditable(false);

        queryTextArea = new JTextArea(10, 50);

        saveButton = new JButton("저장");
        cancelButton = new JButton("취소");

        // Layout components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);
        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.fill = GridBagConstraints.NONE;

        // Title
        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(titleLabel, gc);

        // Table dropdown
        gc.gridy++;
        panel.add(new JLabel("테이블 속성 정보 보기"), gc);

        gc.gridx++;
        panel.add(tableDropdown, gc);

        // Column info
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(columnInfoTextArea), gc);

        // Query text area
        gc.gridy++;
        panel.add(new JLabel("쿼리 입력"), gc);

        gc.gridy++;
        panel.add(new JScrollPane(queryTextArea), gc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        gc.gridy++;
        panel.add(buttonPanel, gc);

        add(panel);
    }

    public JComboBox<String> getTableDropdown() {
        return tableDropdown;
    }

    public JTextArea getQueryTextArea() {
        return queryTextArea;
    }

    public JTextArea getColumnInfoTextArea() {
        return columnInfoTextArea;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
