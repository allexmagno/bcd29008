package example02.db;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

    private static final String pathDB = "src/main/resources/lab01.db";
    private static Connection connection;

    public static synchronized Connection getConnection(){

        try {

            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection("jdbc:sqlite: " + pathDB);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
