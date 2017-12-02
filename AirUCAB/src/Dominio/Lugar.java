/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.ConectorDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

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
    
    public static List<Lugar> obtenerTodos(ConectorDB conector){
        List<Lugar> lugares = new ArrayList<Lugar>();
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Lugar l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
                lugares.add(l);
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return lugares;
    }
    
    public static Lugar buscarPorCodigo(ConectorDB conector, int codigo){
        Lugar l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar WHERE lug_codigo=?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
    }
    
    public static Lugar buscarPorNombre(ConectorDB conector, String nombre){
        Lugar l = null;
        try {
            PreparedStatement pst = conector.conexion.prepareStatement("SELECT lug_codigo, lug_nombre , lug_tipo, fk_lug_codigo FROM lugar WHERE lug_nombre=?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                l = new Lugar(rs.getInt("lug_codigo"),rs.getString("lug_nombre"),rs.getString("lug_tipo"),rs.getInt("fk_lug_codigo"));
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        }
        return l;
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
