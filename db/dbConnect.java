package thicki.DataBase_QLNV.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnect {
    private static final String URL = "jdbc:sqlserver://HOANGDEV21\\SQLEXPRESS:1433;databaseName=NguyenNgocHoang; encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    public static Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                return connection;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
