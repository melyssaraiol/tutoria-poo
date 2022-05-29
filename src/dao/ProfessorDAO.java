/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DataBase;
import entities.Aluno;
import entities.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melys
 */
public class ProfessorDAO {
    private DataBase db;
    private Connection conn;
    
    public  ProfessorDAO(){
        this.db = new DataBase();
        this.conn = this.db.getConexao();
    }
    
    public void cadastrarProfessor(Professor professor){
        String sql = "INSERT INTO professor(nomeprofessor) VALUES(?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, professor.getNomeprofessor());
            stmt.execute();
        }
        catch(Exception e){
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }
    public void editarProfessor(Professor professor){
        String sql = "UPDATE professor SET nomeprofessor=? WHERE id=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, professor.getNomeprofessor());
            stmt.setInt(2, professor.getId());
            stmt.execute();
            
        }
        catch(Exception e){
            System.out.println("Erro ao atualizar professor." + e.getMessage());
            
        }
    }
    public Professor pesquisarProfessor(int id){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM professor WHERE id = ?", 
                  ResultSet.TYPE_SCROLL_SENSITIVE, 
                  ResultSet.CONCUR_UPDATABLE );
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Professor professor = new Professor();
           
            rs.first();
            professor.setId(id);
            professor.setNomeprofessor(rs.getString("nomeprofessor"));
            return professor;
    }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void excluirProfessor(int id){
        String sql = "DELETE FROM professor WHERE id=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }
        catch(Exception e){
            System.out.println("Erro ao excluir professor: " + e.getMessage());
            
        }
    }
    public List<Professor> pesquisarProfessores(String nomeprofessor){
        String sql = "SELECT * FROM professor WHERE nomeprofessor LIKE ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeprofessor + "%");
            ResultSet rs = stmt.executeQuery();
            List<Professor>  listaProfessores = new ArrayList<>();
            while(rs.next()){
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNomeprofessor(rs.getString("nomeprofessor"));
                listaProfessores.add(professor);
            }
            return listaProfessores;
            
            
            
        }
        catch(Exception e){
            return null;
        }
    }
}
