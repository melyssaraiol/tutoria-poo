/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DataBase;
import entities.Professor;
import entities.Turma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melys
 */
public class TurmaDAO {
    private DataBase db;
    private Connection conn;
    
    public TurmaDAO(){
        this.db = new DataBase();
        this.conn = this.db.getConexao();
    }
    
    public void cadastrarTurma(Turma turma){
        String sql = "INSERT INTO turma(nometurma, prof_id) VALUES (?, ?)";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, turma.getNometurma());
            stmt.setInt(2, turma.getProf_id().getId());
            stmt.execute();
            
        }
        catch(Exception e){
            System.out.println("Erro ao cadastrar turma: " + e.getMessage());
        }
       
    }
    public void editar(Turma turma) {
        String sql = "UPDATE turma SET nometurma=?, prof_id=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, turma.getNometurma());
            stmt.setInt(2, turma.getProf_id().getId());
            stmt.setInt(3, turma.getId());
            stmt.execute();
            
        }
        catch (Exception e) {
            System.out.println("Erro ao editar turma: " + e.getMessage());
        }
    }
    
   
    public Turma pesquisarTurma(int id){
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM turma WHERE id = ?", 
                  ResultSet.TYPE_SCROLL_SENSITIVE, 
                  ResultSet.CONCUR_UPDATABLE );
        
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Turma turma = new Turma();
            rs.first();
            turma.setId(id);
            turma.setNometurma(rs.getString("nometurma"));
            Professor prof_id = new Professor();
            prof_id.setId(rs.getInt("prof_id"));
            turma.setProf_id(prof_id);
            return turma;
        }
        catch (SQLException ex) {
            System.out.println("Erro ao pesquisar turma: " + ex.getMessage());
            return null;
        }
    
    }

    public List<Turma> pesquisarTurmas(){
        String sql  = "SELECT t.id, t.nometurma, t.prof_id, p.nomeprofessor FROM"
                + " turma t INNER JOIN professor p ON t.prof_id = p.id;";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Turma> lista = new ArrayList<>();
            while(rs.next()){
                Turma turma = new Turma();
                Professor professor = new Professor();
                turma.setId(rs.getInt("id"));
                turma.setNometurma(rs.getString("nometurma"));
                professor.setId(rs.getInt("prof_id"));
                professor.setNomeprofessor(rs.getString("nomeprofessor"));
                turma.setProf_id(professor);
                
                lista.add(turma);
                
            }
            return lista;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
       
    }
       public List<Turma> pesquisarTurmas2(String nometurma){
        String sql  = "SELECT * FROM turma WHERE nometurma LIKE ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nometurma + "%");
            ResultSet rs = stmt.executeQuery();
            List<Turma> lista = new ArrayList<>();
            while(rs.next()){
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                turma.setNometurma(rs.getString("nometurma"));             
                lista.add(turma);
                
            }
            return lista;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
       }
}
