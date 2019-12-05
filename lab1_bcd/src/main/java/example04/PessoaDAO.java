package example04;


import example04.entities.Pessoa;
import example02.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// DATA ACCESS OBJECT
public class PessoaDAO {


    public boolean inserir(Pessoa pessoa){

        String query = "INSERT INTO Pessoa (nome, peso, altura, email) " +
                "VALUES (?,?,?,?)";


        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            // String para a primeira variável que é o nome
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setDouble(2, pessoa.getPeso());
            preparedStatement.setInt(3, pessoa.getAltura());
            preparedStatement.setString(4, pessoa.getEmail());

            int linhas = preparedStatement.executeUpdate(query);


        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
            return false;
        }
        return true;
    }


    public Pessoa obterPessoa(int idPessoa){

        String query = "SELECT * FROM Pessoa " +
                "WHERE idPessoa = ?";

        Pessoa pessoa = new Pessoa();
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, idPessoa);

            ResultSet resultSet = preparedStatement.executeQuery();
            pessoa.setNome(resultSet.getString("Nome"));
            pessoa.setEmail(resultSet.getString("Email"));
            pessoa.setAltura(resultSet.getInt("Altura"));
            pessoa.setPeso(resultSet.getDouble("Peso"));

            resultSet.close();

        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }

        return pessoa;
    }

    public List<Pessoa> listarTodasPessoas() {

        String query = "SELECT * FROM Pessoa ";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pessoa p = new Pessoa(resultSet.getString("Nome"), resultSet.getString("Email"),
                        resultSet.getDouble("Peso"), resultSet.getInt("Altura"));
                pessoas.add(p);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }


        public int atualizarPessoa(Pessoa pessoa){

            String query = "UPDATE Pessoa SET Nome = ?, Peso = ?, Altura = ?, Email = ?" +
                "WHERE idPessoa = ?";
            int linhas = 0;

            try(Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
                // String para a primeira variável que é o nome
                preparedStatement.setString(1, pessoa.getNome());
                preparedStatement.setDouble(2, pessoa.getPeso());
                preparedStatement.setInt(3, pessoa.getAltura());
                preparedStatement.setString(4, pessoa.getEmail());
                preparedStatement.setInt(5, pessoa.getIdPessoa());

                linhas = preparedStatement.executeUpdate(query);


            }catch(Exception e){
                System.err.println("Erro: " + e.toString());
            }

            return linhas;

    }

    public int atualizarVariasPessoas(List<Pessoa> pessoas){
        // Exemplo WHERE estado = 'São Paulo'

        String query = "UPDATE Pessoa SET Nome = ?, Peso = ?, Altura = ?, Email = ?" +
                "WHERE idPessoa = ?";

        int linhas = 0;

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            Iterator<Pessoa> iterator = pessoas.iterator();
            while (iterator.hasNext()){
                Pessoa pessoa = iterator.next();
                preparedStatement.setString(1, pessoa.getNome());
                preparedStatement.setDouble(2, pessoa.getPeso());
                preparedStatement.setInt(3, pessoa.getAltura());
                preparedStatement.setString(4, pessoa.getEmail());
                preparedStatement.setInt(5, pessoa.getIdPessoa());

                linhas = linhas + preparedStatement.executeUpdate(query);
            }
        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }

        return linhas;
    }

    public int apagarPessoa(int idPessoa){

        String query = "DELETE ? FROM Pessoa";
        int linhas = 0;

        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, idPessoa);
            linhas = preparedStatement.executeUpdate(query);

        }catch(Exception e){
            System.err.println("Erro: " + e.toString());
        }

        return linhas;
    }
}
