/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parqueocs.ConsultasUsuario;
import parqueocs.vista.Bienvenido;
import parqueocs.vista.Registro;

/**
 *
 * @author minio
 */
public class BienvenidoController {
    private Bienvenido vista;

    public BienvenidoController(Bienvenido vista) {
        this.vista = vista;
        initController();
    }
    
    private void initController(){
        // abrir Registro
        vista.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });
        
        // abrir Inicio Sesion
        vista.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInicioSesion();
            }
        });
        
        // abrir Inicio Sesion
        vista.getBtnSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }
    
    
    private void abrirRegistro(){
        // Abre ls vista Registro
        Registro vistaRegistro = new Registro();
        ConsultasUsuario modeloUsuario = new ConsultasUsuario();
        new RegistroController(vistaRegistro, modeloUsuario);
        vistaRegistro.setVisible(true);
    }
    
    private void abrirInicioSesion(){
        // Abre ls vista Registro
        Registro registro = new Registro();
        registro.setVisible(true);
    }
    
    private void exit(){
        // Cierra ls vista Bienvenido
        vista.dispose();
    }
    
}
