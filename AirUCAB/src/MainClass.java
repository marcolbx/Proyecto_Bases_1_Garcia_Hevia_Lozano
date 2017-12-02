
import Adaptadores.ConectorDB;
import Dominio.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexd
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConectorDB c = new ConectorDB();
        c.conectar();
        Lugar l = new Lugar(1000000,"mi casa","Pais");
        //l.agregarADB(c);
        //l.setLug_nombre("Mi casa 2");
        //l.modificarEnDB(c);
        //Lugar.obtenerTodos(c);
        Lugar.buscarPorCodigo(c, 0);
        c.desconectar();
        // TODO code application logic here
    }
    
}
