package connections;

/**
 * Created by student on 2/8/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Settings {
    private static Connection connection;

    public Settings() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/practica",
                    "postgres", "andrei123");
        }
        return connection;
    }

    public static void resetAll() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
