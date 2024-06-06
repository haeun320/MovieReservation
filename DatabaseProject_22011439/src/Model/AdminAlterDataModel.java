package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminAlterDataModel {
    private Connection con;

    public AdminAlterDataModel(Connection con) {
        this.con = con;
    }

    public List<String> getColumnInfo(String tableName) throws SQLException {
        List<String> columnInfo = new ArrayList<>();
        String query = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, tableName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    String dataType = rs.getString("DATA_TYPE");
                    columnInfo.add(columnName + " (" + dataType + ")");
                }
            }
        }
        return columnInfo;
    }

    public void executeCustomQuery(String query) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        }
    }
}
