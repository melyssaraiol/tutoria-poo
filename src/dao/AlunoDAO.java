/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import config.DataBase;
import entities.Aluno;
import entities.Turma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melys
 */
public class AlunoDAO {
    private DataBase db;
    private Connection conn;
    
    public AlunoDAO() {
    this.db = new DataBase();
    this.conn = this.db.getConexao();
    }
    
    public void adicionarAluno(Aluno aluno){
       String sql = "INSERT INTO aluno(nome, nome_mae, nome_pai, dt_nasc, dt_cadastro, turma_id)"
               + "VALUES (?, ?, ?, ?, ?, ?) ";
       try {
       PreparedStatement stmt = this.conn.prepareStatement(sql);
       stmt.setString(1, aluno.getNome());
       stmt.setString(2, aluno.getNome_mae());
       stmt.setString(3, aluno.getNome_pai());
       stmt.setString(4, aluno.getDt_nasc());
       stmt.setString(5, aluno.getDt_cadastro());
       stmt.setInt(6, aluno.getTurma_id().getId());
       stmt.execute();
       
       }
       catch (Exception e) {
           System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
       }
       
    }
        
    public Aluno pesquisaAluno(int id){
            try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM aluno WHERE id = ?",
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            Aluno aluno = new Aluno();
            rs.first();
            aluno.setId(id);
            aluno.setNome(rs.getString("nome"));
            aluno.setNome_mae(rs.getString("nome_mae"));
            aluno.setNome_pai(rs.getString("nome_pai"));
            aluno.setDt_nasc(rs.getString("dt_nasc"));
            aluno.setDt_cadastro(rs.getString("dt_cadastro"));
            Turma turma_id = new Turma();
            turma_id.setId(rs.getInt("turma_id"));
            aluno.setTurma_id(turma_id);
            return aluno;
          
            
        }
        catch(Exception e){
            return null;
        }
    } 
        public void atualizarAluno(Aluno aluno) {
            String sql = "UPDATE aluno SET nome = ?, nome_mae = ?, nome_pai = ?, "
                    + " dt_nasc = ?, dt_cadastro = ?, turma_id = ? WHERE id = ?";
                    
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getNome_mae());
                stmt.setString(3, aluno.getNome_pai());
                stmt.setString(4, aluno.getDt_nasc());
                stmt.setString(5, aluno.getDt_cadastro());
                stmt.setInt(6, aluno.getTurma_id().getId());
                stmt.setInt(7, aluno.getId());
                stmt.execute();
            }
            catch (Exception e) {
                System.out.println("Erro ao atualizar aluno: " + e.getMessage());           
             }
        }
        
        public void deletarAluno(int id){
            String sql = "DELETE FROM aluno WHERE id = ?";
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
            }
            catch (Exception e) {
                System.out.println("Erro ao excluir aluno: " + e.getMessage());
            }
        }
        
        public List<Aluno> pesquisarAlunos(String nome){
            String sql = "SELECT * FROM aluno a JOIN turma ON a.turma_id = turma.id WHERE a.nome LIKE ?";
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, "%" + nome + "%");
                ResultSet rs = stmt.executeQuery();
                List<Aluno> listaAlunos = new ArrayList<>();
                while(rs.next()){
                    Aluno aluno = new Aluno();
                    Turma turma = new Turma();
                    
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setNome_mae(rs.getString("nome_mae"));
                    aluno.setNome_pai(rs.getString("nome_pai"));
                    aluno.setDt_nasc(rs.getString("dt_nasc"));
                    aluno.setDt_cadastro(rs.getString("dt_cadastro"));
                    turma.setId(rs.getInt("turma_id"));
                    turma.setNometurma(rs.getString("nometurma"));
                    aluno.setTurma_id(turma);
                    listaAlunos.add(aluno);
                }
                return listaAlunos;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        public List<Aluno> AlunoTurma(int id){
            String sql = "SELECT a.id, a.nome FROM aluno a JOIN turma ON a.turma_id = turma.id WHERE turma.id = ?";
            try{
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                List<Aluno> listaAlunos = new ArrayList<>();
                while(rs.next()){
                    Aluno aluno = new Aluno();
                    Turma turma = new Turma();
                    
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    listaAlunos.add(aluno);
                }
                return listaAlunos;
            }
            catch (Exception e) {
                System.out.println("erro: "  + e.getMessage());
                return null;
            }
        }
}
         