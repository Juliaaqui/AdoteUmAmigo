package adoteumamigo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/adote_um_amigo";
    private static final String USUARIO = "root";
    private static final String SENHA = "#admin301606";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
        return conexao;
    }

    public static void main(String[] args) {
        conectar();
    }
}