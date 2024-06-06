package Controller;

import Model.AdminInsertDataModel;
import View.AdminInsertDataView;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminInsertDataController {
    private AdminInsertDataModel model;
    private AdminInsertDataView view;

    public AdminInsertDataController(AdminInsertDataView view, AdminInsertDataModel model) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    public void initView() {
        // Initially display the data of the first table in the dropdown
        String initialTable = (String) view.getTableDropdown().getSelectedItem();
        updateTableData(initialTable);
    }

    public void initController() {
        view.setVisible(true);

        view.getTableDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) view.getTableDropdown().getSelectedItem();
                updateTableData(selectedTable);
            }
        });

        view.getAddRowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddRow();
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSave();
            }
        });
    }

    private void updateTableData(String tableName) {
        try {
            String[] columnNames = model.getColumnNames(tableName);
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setColumnIdentifiers(columnNames);
            tableModel.setRowCount(0); // Clear existing rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void onAddRow() {
        view.getTableModel().addRow(new Object[view.getTableModel().getColumnCount()]);
    }

    private void onSave() {
        String selectedTable = (String) view.getTableDropdown().getSelectedItem();
        DefaultTableModel tableModel = view.getTableModel();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();

        ArrayList<Object[]> data = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
            	Object value = tableModel.getValueAt(i, j);
                if (value == null || value.toString().trim().isEmpty()) {
                    row[j] = null; // Save empty values as NULL
                } else {
                    row[j] = value;
                }            }
            data.add(row);
        }

        try {
            model.saveData(selectedTable, data);
            JOptionPane.showMessageDialog(view, "Data saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
