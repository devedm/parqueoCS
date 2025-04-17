/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parqueocs.vista.Bienvenido;
import parqueocs.vista.InicioSesion;
import parqueocs.vista.Registro;

/**
 *
 * @author Eddy Mena Lopez
 */
public class BienvenidoController extends Controller implements ActionListener{
    private Bienvenido vista;

    public BienvenidoController(Bienvenido vista) {
        this.vista = vista;
        this.vista.btnIniciarSesion.addActionListener(this);
        this.vista.btnRegistrarse.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnIniciarSesion){
            abrirInicioSesion();
        }
        
        if (e.getSource() == vista.btnRegistrarse){
            abrirRegistro();
        }
        if (e.getSource() == vista.btnSalir){
            exit();
        }
    }   

    
    public void abrirRegistro(){
        // Abre ls vista Registro
        Registro vistaRegistro = new Registro();
        new RegistroController(vistaRegistro);
    }
    
    public void abrirInicioSesion(){
        // Abre ls vista Registro
        InicioSesion vistaInicioSesion = new InicioSesion();
        new InicioSesionController(vistaInicioSesion);
    }
    
    public void exit(){
        // Cierra ls vista Bienvenido
        System.exit(0);
    }



    
}
