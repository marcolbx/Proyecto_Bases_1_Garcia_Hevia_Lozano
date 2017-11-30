/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Adaptadores.ConectorDB;

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
    
    public void agregarADB(ConectorDB conexion){
        conexion.ejecutarInsert("insert into Lugar values ("+lug_codigo+",'"+lug_nombre+"','"+lug_tipo+"');");
    }
    
    
}
