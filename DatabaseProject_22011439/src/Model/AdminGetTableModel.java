package Model;

import java.sql.*;
import java.util.*;

public class AdminGetTableModel {
	private Connection con;
	
	public AdminGetTableModel(Connection con) {
		this.con = con;
	}

    public String[] getColumnNames(String tableName) throws SQLException {
        try (
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            return columnNames;
        }
    }

    public Object[][] getData(String tableName) throws SQLException {
        try (
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<Object[]> data = new ArrayList<>();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                data.add(row);
            }

            return data.toArray(new Object[0][]);
        }
    }
}
