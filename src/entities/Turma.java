/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author melys
 */
public class Turma {
    private int id;
    private String nometurma;
    private Professor prof_id;
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNometurma() {
        return nometurma;
    }

    public void setNometurma(String nometurma) {
        this.nometurma = nometurma;
    }

    public Professor getProf_id() {
        return prof_id;
    }

    public void setProf_id(Professor prof_id) {
        this.prof_id = prof_id;
    }
    public String toString(){
        return this.nometurma;
    }
    
   public boolean equals(Object obj){
       Turma t = (Turma)  obj;
       if(this.id == t.getId()){
           return true;
       }
       else{
           return false;
       }
    
}
}
