/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueocs;

import parqueocs.vista.Bienvenido;

import parqueocs.controlador.BienvenidoController;

/**
 *
 * @author Eddy Mena Lopez
 */
public class ParqueoCS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bienvenido vista = new Bienvenido();
        BienvenidoController controlador = new BienvenidoController(vista);
        vista.setVisible(true);
                
    }
    
}
