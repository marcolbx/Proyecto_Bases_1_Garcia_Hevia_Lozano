/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.ConectorDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LAB_L11
 */
public class Lugar {
    
    int lug_codigo;
    String lug_nombre;
    String lug_tipo;
    int fk_lug_codigo;

    public Lugar(int lug_codigo, String lug_nombre, String lug_tipo, int fk_lug_codigo) {
        this.lug_codigo = lug_codigo;
        this.lug_nombre = lug_nombre;
        this.lug_tipo = lug_tipo;
        this.fk_lug_codigo = fk_lug_codigo;
    }

    public Lugar(int lug_codigo, String lug_nombre, String lug_tipo) {
        this.lug_codigo = lug_codigo;
        this.lug_nombre = lug_nombre;
        this.lug_tipo = lug_tipo;
    }
    
    public void agregarADB(ConectorDB conector){
        try{
            String stm = "INSERT INTO Lugar(lug_codigo,lug_nombre,lug_tipo) VALUES(?,?,?)";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lug_codigo);
            pst.setString(2, lug_nombre);
            pst.setString(3,lug_tipo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void modificarEnDB(ConectorDB conector){
        try{
            String stm = "UPDATE Lugar SET lug_nombre=?,lug_tipo=? WHERE lug_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setString(1, lug_nombre);
            pst.setString(2,lug_tipo);
            pst.setInt(3, lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }
    
    public void eliminarDeDB(ConectorDB conector){
        try{
            String stm = "Delete from Lugar where lug_codigo=?";
            PreparedStatement pst = conector.conexion.prepareStatement(stm);
            pst.setInt(1, lug_codigo);
            pst.executeUpdate();
            pst.close();
        }catch (SQLException ex){
           System.out.print(ex.toString());
        }
    }

    public int getLug_codigo() {
        return lug_codigo;
    }

    public void setLug_codigo(int lug_codigo) {
        this.lug_codigo = lug_codigo;
    }

    public String getLug_nombre() {
        return lug_nombre;
    }

    public void setLug_nombre(String lug_nombre) {
        this.lug_nombre = lug_nombre;
    }

    public String getLug_tipo() {
        return lug_tipo;
    }

    public void setLug_tipo(String lug_tipo) {
        this.lug_tipo = lug_tipo;
    }

    public int getFk_lug_codigo() {
        return fk_lug_codigo;
    }

    public void setFk_lug_codigo(int fk_lug_codigo) {
        this.fk_lug_codigo = fk_lug_codigo;
    }
    
    
    
    
}
