/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parqueocs.modelo.Consultas;
import parqueocs.vista.Bienvenido;
import parqueocs.vista.InicioSesion;
import parqueocs.vista.Registro;

/**
 *
 * @author minio
 */
public class BienvenidoController implements ActionListener{
    private final Bienvenido vista;
    private final Consultas modelo;

    public BienvenidoController(Bienvenido vista, Consultas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnIniciarSesion.addActionListener(this);
        this.vista.btnRegistrarse.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
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

    
    private void abrirRegistro(){
        // Abre ls vista Registro
        Registro vistaRegistro = new Registro();
        Consultas modeloUsuario = new Consultas();
        new RegistroController(vistaRegistro, modeloUsuario);
        
        vistaRegistro.setVisible(true);
    }
    
    private void abrirInicioSesion(){
        // Abre ls vista Registro
        InicioSesion vistaInicioSesion = new InicioSesion();
        Consultas modeloUsuario = new Consultas();
        new InicioSesionController(vistaInicioSesion, modeloUsuario);
        
        vistaInicioSesion.setVisible(true);
    }
    
    private void exit(){
        // Cierra ls vista Bienvenido
        vista.dispose();
    }



    
}
