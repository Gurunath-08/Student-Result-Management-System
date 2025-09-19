import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/student_result";
            String user = "root";
            String password = "08062005";

            con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");
        } catch (Exception e) {
            System.out.println("❌ Database Connection Failed: " + e.getMessage());
        }
        return con;
    }
}
