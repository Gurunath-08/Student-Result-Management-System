package srs;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String password = "08062005"; 

        return DriverManager.getConnection(url, user, password);
    }
}

