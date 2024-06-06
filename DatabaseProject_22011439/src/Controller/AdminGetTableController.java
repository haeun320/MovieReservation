package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.AdminGetTableModel;
import View.AdminGetTableView;

public class AdminGetTableController {
    private AdminGetTableModel model;
    private AdminGetTableView view;

    public AdminGetTableController(AdminGetTableView view, AdminGetTableModel model) {
        this.model = model;
        this.view = view;
        initView();
        initController();
    }

    public void initView() {
        // Initially display the data of the first table in the list
        String initialTable = view.getTableList().getModel().getElementAt(0);
        updateTableData(initialTable);
    }

    public void initController() {
        view.setVisible(true);
        view.getTableList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedTable = view.getTableList().getSelectedValue();
                    updateTableData(selectedTable);
                }
            }
        });
    }

    private void updateTableData(String tableName) {
        try {
            String[] columnNames = model.getColumnNames(tableName);
            Object[][] data = model.getData(tableName);
            view.setTableData(data, columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
