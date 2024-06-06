package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Utils.AdminMenuBar;


public class AdminGetTableView extends JFrame {
	private AdminMenuBar menuBar;
	private JList<String> tableList;
    private JTable table;
    private DefaultListModel<String> listModel;
	String[] tableNames = {"Actor", "Genre", "Member", "Movie", "Reservation", "ScreeningSchedule", "Seat", "Theater", "Ticket"};

	public AdminGetTableView() {
		// Set up the frame
		setTitle("Movie Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		        
        // Create menu bar
        menuBar = new AdminMenuBar(this);
        setJMenuBar(menuBar);
        
        // Create the list of tables
        listModel = new DefaultListModel<>();
        for (String tableName : tableNames) {
            listModel.addElement(tableName);
        }
        tableList = new JList<>(listModel);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(tableList);
        listScrollPane.setPreferredSize(new Dimension(150, 600));

        // Create the table to display data
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Add components to the frame
        add(listScrollPane, BorderLayout.WEST);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public JList<String> getTableList() {
        return tableList;
    }

    public void setTableData(Object[][] data, String[] columnNames) {
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

}
