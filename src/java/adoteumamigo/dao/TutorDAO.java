/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adoteumamigo.dao;

import adoteumamigo.model.Tutor;
import adoteumamigo.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TutorDAO {

    public void cadastrar(Tutor tutor) {
        String sql = "INSERT INTO tutor (nome, telefone, email, cpf, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getTelefone());
            stmt.setString(3, tutor.getEmail());
            stmt.setString(4, tutor.getCpf());
            stmt.setString(5, tutor.getEstado());

            stmt.executeUpdate();
            System.out.println("Tutor cadastrado com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar tutor: " + e.getMessage());
        }
    }
}