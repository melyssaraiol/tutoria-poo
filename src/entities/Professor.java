/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author melys
 */
public class Professor {
    private int id;
    private String nomeprofessor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeprofessor() {
        return nomeprofessor;
    }

    public void setNomeprofessor(String nomeprofessor) {
        this.nomeprofessor = nomeprofessor;
    }
    
    public String toString(){
        return this.nomeprofessor;
    }
    
   public boolean equals(Object obj){
       Professor p = (Professor)  obj;
       if(this.id == p.getId()){
           return true;
       }
       else{
           return false;
       }
    
   }
}
