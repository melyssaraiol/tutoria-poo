/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author melys
 */
public class DataBase {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/escola?serverTimezone=UTC",
             "root",
             ""
            );
            return conn;
        }
        catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;            
        }
        
        
    }
    
}
