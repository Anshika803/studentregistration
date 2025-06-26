package studentregistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",
                "acer"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            System.out.println("Connected to Oracle DB!");
        } else {
            System.out.println("Connection Failed.");
        }
    }
}
