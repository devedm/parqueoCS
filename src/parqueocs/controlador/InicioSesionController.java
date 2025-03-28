/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.vista.InicioSesion;
import parqueocs.vista.Principal;

/**
 *
 * @author minio
 */
public class InicioSesionController extends Controller{
    InicioSesion vista;

    public InicioSesionController(InicioSesion vista, Consultas modelo) {
        this.vista = vista;
        initController();
    }


    
    @Override
    void initController(){
        // Metodos de la vista
        // Iniciar Sesion
        vista.getBtnIniciarSesion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                iniciarSesion();
            }
        });
    }
    
    public void iniciarSesion(){
        // Validar espacios
        if(!(vista.getFieldUsuario().getText().isBlank() || vista.getFieldContrasenia().getText().isBlank())){
            // si no estan vacios busco en la DB
            Usuario usuarioExistente = new Usuario(Integer.parseInt(vista.getFieldUsuario().getText()));
            usuarioExistente = modelo.buscarUsuario(usuarioExistente);
            if(usuarioExistente != null){
                if(vista.getFieldUsuario().getText().contentEquals(String.valueOf(usuarioExistente.getCedula()))){
                    if(vista.getFieldContrasenia().getText().contentEquals(usuarioExistente.getContrasenia())){
                        // inicio correcto
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Se ha iniciado sesion correctamente");
                        abrirPrincipal(); // iniciar sesion
                        exit();
                        
                    } else {
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Error: contrasenia incorrecta");
                    }
                    } else {
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el usuario no existe");
                    }
            }
            
        } else {
            if(vista.getFieldUsuario().getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de cedula esta vacio");
            } else if(vista.getFieldContrasenia().getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Contrasenia esta vacio");
            }
        }
    }
    
    public void abrirPrincipal(){
        // Abre ls vista Principal
        Principal vistaPrincipal = new Principal();
        Consultas modelo = new Consultas();
        new PrincipalController(vistaPrincipal, modelo);
        vistaPrincipal.setVisible(true);
    }
    
    private void exit(){
        // Cierra la vista
        vista.dispose();
    }
}
