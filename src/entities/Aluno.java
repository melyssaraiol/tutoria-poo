/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author melys
 */
public class Aluno {
    private int id;
    private String nome;
    private String nome_mae;
    private String nome_pai;
    private String dt_nasc;
    private String dt_cadastro;
    private Turma turma_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Turma turma_id) {
        this.turma_id = turma_id;
    }

    public String getNome_mae() {
        return nome_mae;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }

    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(String dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }
    
    public String toString(){
        return this.nome;
    }
    
   public boolean equals(Object obj){
       Aluno a = (Aluno)  obj;
       if(this.id == a.getId()){
           return true;
       }
       else{
           return false;
       }
}
}
