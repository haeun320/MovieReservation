package Model;

import java.sql.*;
import java.util.*;

public class AdminInsertDataModel {
    private Connection con;
    
    public AdminInsertDataModel(Connection con) {
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

    public void saveData(String tableName, ArrayList<Object[]> data) throws SQLException {
        con.setAutoCommit(false); 

        String[] columnNames = getColumnNames(tableName);
        String columnNamesQuery = String.join(", ", columnNames);
        String[] placeholders = new String[columnNames.length];
        Arrays.fill(placeholders, "?");
        String placeholdersString = String.join(", ", placeholders);

        String query = "INSERT INTO " + tableName + " (" + columnNamesQuery + ") VALUES (" + placeholdersString + ")";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            for (Object[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    preparedStatement.setObject(i + 1, row[i]);
                }
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            con.commit();
        } catch (SQLException e) {
            con.rollback(); 
            throw e;
        }
    }
}
