/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adoteumamigo.dao;

import adoteumamigo.model.Animal;
import adoteumamigo.model.Tutor;
import adoteumamigo.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public void cadastrar(Animal animal) {
        String sql = "INSERT INTO animal (nome, sexo, idade, raca, peso, especie, descricao, status, id_tutor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getSexo());
            stmt.setInt(3, animal.getIdade());
            stmt.setString(4, animal.getRaca());
            stmt.setDouble(5, animal.getPeso());
            stmt.setString(6, animal.getEspecie());
            stmt.setString(7, animal.getDescricao());
            stmt.setString(8, animal.getStatus());
            stmt.setInt(9, animal.getTutor().getId()); 

            stmt.executeUpdate();
            System.out.println("Animal cadastrado com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar animal: " + e.getMessage());
        }
    }

    public List<Animal> listarDisponiveis() {
        String sql = "SELECT * FROM animal WHERE status = 'Disponível'";
        List<Animal> lista = new ArrayList<>();
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Animal a = new Animal();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setSexo(rs.getString("sexo"));
                a.setIdade(rs.getInt("idade"));
                a.setRaca(rs.getString("raca"));
                a.setPeso(rs.getDouble("peso"));
                a.setEspecie(rs.getString("especie"));
                a.setDescricao(rs.getString("descricao"));
                a.setStatus(rs.getString("status"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar animais: " + e.getMessage());
        }
        return lista;
    }

    public Animal buscarDetalhesPorId(int idAnimal) {
        String sql = "SELECT a.*, t.nome AS nome_tutor, t.email AS email_tutor, t.estado AS estado_tutor " +
                     "FROM animal a " +
                     "INNER JOIN tutor t ON a.id_tutor = t.id " +
                     "WHERE a.id = ?";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idAnimal);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setSexo(rs.getString("sexo"));
                    animal.setIdade(rs.getInt("idade"));
                    animal.setRaca(rs.getString("raca"));
                    animal.setPeso(rs.getDouble("peso"));
                    animal.setEspecie(rs.getString("especie"));
                    animal.setDescricao(rs.getString("descricao"));
                    animal.setStatus(rs.getString("status"));
                    
                    Tutor tutor = new Tutor();
                    tutor.setNome(rs.getString("nome_tutor"));
                    tutor.setEmail(rs.getString("email_tutor"));
                    tutor.setEstado(rs.getString("estado_tutor"));
                    
                    animal.setTutor(tutor); 
                    
                    return animal;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar detalhes: " + e.getMessage());
        }
        return null;
    }
}