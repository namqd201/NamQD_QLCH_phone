package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL =
            "jdbc:postgresql://localhost:5432/phone_store_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123";
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy PostgreSQL JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Kết nối DB thất bại!");
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Connection conn = getConnection();
//        if (conn != null) {
//            System.out.println("Kết nối PostgreSQL thành công!");
//        } else {
//            System.out.println("Kết nối thất bại!");
//        }
//    }
}
