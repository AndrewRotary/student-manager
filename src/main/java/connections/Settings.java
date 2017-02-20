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
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-195-248-0.eu-west-1.compute.amazonaws.com:5432/ddtemravmfggd0?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
                    "lsolmhwrrmrtrf", "d22b4084b63e6bda2b69f3ccf32cc74ec3d0ecb9eca952511cec343561a57ee5");
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
