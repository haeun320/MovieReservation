package View;

import javax.swing.*;
import java.awt.*;
import Utils.AdminMenuBar;
import javax.swing.table.DefaultTableModel;

public class AdminInsertDataView extends JFrame {
    private AdminMenuBar menuBar;
    private JComboBox<String> tableDropdown;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JButton addRowButton;
    private JButton saveButton;
    private JButton cancelButton;

    private String[] tables = {"Actor", "Genre", "Member", "Movie", "Reservation", "ScreeningSchedule", "Seat", "Theater", "Ticket"};

    public AdminInsertDataView() {
        // Set up the frame
        setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create menu bar
        menuBar = new AdminMenuBar(this);
        setJMenuBar(menuBar);

        // Create components
        JLabel titleLabel = new JLabel("데이터 입력");
        titleLabel.setFont(new Font("Times", Font.BOLD, 24));

        tableDropdown = new JComboBox<>(tables);

        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setGridColor(Color.BLACK); // Set grid color
        dataTable.setShowGrid(true); // Enable grid lines
        JScrollPane scrollPane = new JScrollPane(dataTable);

        addRowButton = new JButton("+ 새 행 삽입");
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
        panel.add(new JLabel("입력할 테이블"), gc);

        gc.gridx++;
        panel.add(tableDropdown, gc);

        // Data table
        gc.gridx = 0;
        gc.gridy++;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        panel.add(scrollPane, gc);

        // Add row button
        gc.gridy++;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0;
        gc.weighty = 0;
        panel.add(addRowButton, gc);

        // Save and cancel buttons
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

    public JTable getDataTable() {
        return dataTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAddRowButton() {
        return addRowButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
