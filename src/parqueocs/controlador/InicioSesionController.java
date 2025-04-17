/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import parqueocs.modelo.Usuario;
import parqueocs.vista.InicioSesion;
import parqueocs.vista.Principal;

/**
 *
 * @author Eddy Mena Lopez
 */
public class InicioSesionController extends Controller implements ActionListener{
    private final InicioSesion vista;

    public InicioSesionController(InicioSesion vista) {
        this.vista = vista;
        this.vista.btnIniciarSesion.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnIniciarSesion){
            iniciarSesion();
        }
        if(e.getSource() == vista.btnAtras){
            exit();
        }
    }
    
    public void iniciarSesion(){
        // Validar espacios
        if(!(vista.fieldUsuario.getText().isBlank() || vista.fieldContrasenia.getText().isBlank())){
            // si no estan vacios busco en la DB
            Usuario usuarioExistente = new Usuario(Integer.parseInt(vista.fieldUsuario.getText()));
            usuarioExistente = getModelo().buscarUsuario(usuarioExistente);
            if(usuarioExistente != null){
                if(vista.fieldUsuario.getText().contentEquals(String.valueOf(usuarioExistente.getCedula()))){
                    if(vista.fieldContrasenia.getText().contentEquals(usuarioExistente.getContrasenia())){
                        // inicio correcto
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Se ha iniciado sesion correctamente");
                        usuarioExistente = getModelo().buscarUsuario(usuarioExistente);
                        abrirPrincipal(usuarioExistente); // iniciar sesion
                        
                    } else {
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Error: contrasenia incorrecta");
                    }
                    } else {
                        JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el usuario no existe");
                    }
            }
            
        } else {
            if(vista.fieldUsuario.getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de cedula esta vacio");
            } else if(vista.fieldContrasenia.getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Contrasenia esta vacio");
            }
        }
    }
    
    public void abrirPrincipal(Usuario usuario){
        // Abre ls vista Principal
        Principal vistaPrincipal = new Principal();
        new PrincipalController(vistaPrincipal, usuario);
        exit();

    }
    
    public void exit(){
        vista.dispose();
    }
}
