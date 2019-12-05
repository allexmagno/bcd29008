package example01;

import java.sql.*;
import java.util.Scanner;

import static java.lang.Class.forName;

public class Principal {

    private final String pathDB = "src/main/resources/lab01.db";

    public void insert() throws Exception {

        // Não é a melhor form
        Class.forName("org.sqlite.JDBC");

        // No caso do mysql passaria o usuario/senha + IP :port
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+pathDB);

        // Pronto para usar a tabela
        Statement statement = connection.createStatement();

        String nome = "José";
        double peso = 65;
        int altura = 160;
        String email = "jose@email.com";
        String query = "INSERT INTO Pessoa " +
                "(nome, peso, altura, email) " +
                "VALUES ('" + nome + "'," + peso + ","+ altura + ",'" + email + "')";

        statement.executeUpdate(query);
        statement.close();
        connection.close();
    }

    public void buscarPessoa(String nome) throws Exception {
        // Não é a melhor form
        Class.forName("org.sqlite.JDBC");

        // No caso do mysql passaria o usuario/senha + IP :port
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+pathDB);

        // Pronto para usar a tabela
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Pessoa WHERE Nome = '" + nome + "'";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.println("Nome: " + resultSet.getString("Nome"));
            System.out.println("Peso: " + resultSet.getDouble("Peso"));
            System.out.println("Altura: " + resultSet.getInt("Altura"));
            System.out.println("Email: " + resultSet.getString("Email"));
            System.out.println("------------------------");
        }

    }
    public void remove() throws Exception{
        //DELETE FROM "Pessoa" WHERE "idPessoa" = 5

    }

    public void list() throws Exception {
        // Não é a melhor form
        Class.forName("org.sqlite.JDBC");

        // No caso do mysql passaria o usuario/senha + IP :port
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+pathDB);

        // Pronto para usar a tabela
        Statement statement = connection.createStatement();

        String query = "SELECT * FROM Pessoa";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            System.out.println("Nome: " + resultSet.getString("Nome"));
            System.out.println("Peso: " + resultSet.getDouble("Peso"));
            System.out.println("Altura: " + resultSet.getInt("Altura"));
            System.out.println("Email: " + resultSet.getString("Email"));
            System.out.println("------------------------");
        }
        statement.close();
        connection.close();
    }


    public static void main(String[] args) throws Exception {


        Principal p = new Principal();

        //p.insert();
        //p.list();
        System.out.print("Nome: ");
        Scanner scanner = new Scanner(System.in);
        String pessoa = scanner.nextLine();
        //p.buscarPessoa("José' OR '1' = '1");
        p.buscarPessoa(pessoa);


    }
}

