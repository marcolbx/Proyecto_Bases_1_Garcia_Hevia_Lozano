/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adaptadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author LAB_L11
 */
public class ConectorDB {
    Connection conexion;
    
    public void conectar(){
            Connection conexion = null;
            String urlDatabase =  "jdbc:postgresql://localhost:0000/sample_db"; 
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(urlDatabase,  "alex", "123456");
                System.out.println("La conexión se realizo sin problemas! =) ");
            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            
    }
    
    public void ejecutarInsert(String insert){
        Statement s = null;
         try
        {
            s = conexion.createStatement();
            s.execute(insert);
        }catch (Exception e)
        {
            System.out.println("Problema al consultar la base de datos 1 ");
        }
        
    }
}
