package Controller;

import Model.AdminAlterDataModel;
import View.AdminAlterDataView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AdminAlterDataController {
    private AdminAlterDataModel model;
    private AdminAlterDataView view;

    public AdminAlterDataController(AdminAlterDataView view, AdminAlterDataModel model) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    private void initView() {
        // Initialize the view with the first table's column info
        String initialTable = (String) view.getTableDropdown().getSelectedItem();
        updateColumnInfo(initialTable);
    }

    private void initController() {
        view.setVisible(true);

        view.getTableDropdown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) view.getTableDropdown().getSelectedItem();
                updateColumnInfo(selectedTable);
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = view.getQueryTextArea().getText();
                executeQuery(query);
            }
        });
    }

    private void updateColumnInfo(String tableName) {
        try {
            List<String> columnInfo = model.getColumnInfo(tableName);
            StringBuilder formattedColumnInfo = new StringBuilder();
            for (int i = 0; i < columnInfo.size(); i++) {
                formattedColumnInfo.append(columnInfo.get(i));
                if (i % 2 == 1 || i == columnInfo.size() - 1) {
                    formattedColumnInfo.append("\n");
                } else {
                    formattedColumnInfo.append("\t\t");
                }
            }
            view.getColumnInfoTextArea().setText(formattedColumnInfo.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error fetching column info: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void executeQuery(String query) {
        try {
            model.executeCustomQuery(query);
            JOptionPane.showMessageDialog(view, "Query executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error executing query: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
