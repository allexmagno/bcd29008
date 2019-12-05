package example05.db;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ConnectionFactory {

    // Conexão com o mySql IP:Porta
    // Qual esquema deve utilizar
    // Usuário e senha (emerson:1234)

    private static Connection connection;

    public static synchronized Connection getConnection(){

        Properties properties = new Properties();
        properties.setProperty("user", "emerson");
        properties.setProperty("password", "1234");
        properties.setProperty("useSSL", "false");

        String host = "ampto.sj.ifsc.edu.br";
        String port = "33060";
        String dbName = "lab01emerson";


        String url = "jdbc:mysql://"+ host + ":" + port + "/" + dbName;

        try {

            connection = DriverManager.getConnection(url, properties);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
