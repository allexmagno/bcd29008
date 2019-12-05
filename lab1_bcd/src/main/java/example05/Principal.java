package example05;

import example05.db.ConnectionFactory;

import java.sql.*;
import java.util.Scanner;

public class Principal {

    String query = "SELECT * FROM Aluno";

    public void list_all(){
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("idAluno: " + resultSet.getInt("idAluno"));
                System.out.println("Nome: " + resultSet.getString("Nome"));
                System.out.println("Telefone: " + resultSet.getInt("Telefone"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("------------------------");
            }

            resultSet.close();


        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }

    }

    // SEM SQL injection
    public void insert(String nome, double peso, int altura, String email) throws Exception {

        String query = "INSERT INTO Pessoa (nome, peso, altura, email) " +
                "VALUES (?,?,?,?)";
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            // String para a primeira variável que é o nome
            preparedStatement.setString(1, nome);
            preparedStatement.setDouble(2, peso);
            preparedStatement.setInt(3, altura);
            preparedStatement.setString(4, email);

            int linhas = preparedStatement.executeUpdate(query);

        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }


    }

    // SQL injection
    public void buscarPessoa(String nome) throws Exception {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement()){
            String query = "SELECT * FROM Pessoa WHERE Nome = '" + nome + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println("Nome: " + resultSet.getString("Nome"));
                System.out.println("Peso: " + resultSet.getDouble("Peso"));
                System.out.println("Altura: " + resultSet.getInt("Altura"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("------------------------");
            }

        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }

    }
    public void remove() throws Exception{
        //DELETE FROM "Pessoa" WHERE "idPessoa" = 5

    }

    public void list() throws Exception {
        // Não é a melhor form
        Class.forName("org.sqlite.JDBC");

        // No caso do mysql passaria o usuario/senha + IP :port
        String pathDB = "src/main/resources/lab01.db";
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
        p.list_all();
        //System.out.print("Nome: ");
        //Scanner scanner = new Scanner(System.in);
        //String pessoa = scanner.nextLine();
        //p.buscarPessoa("José' OR '1' = '1");
        //p.buscarPessoa(pessoa);
    }
}
