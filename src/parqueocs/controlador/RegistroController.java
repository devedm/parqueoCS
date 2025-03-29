/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.vista.Bienvenido;
import parqueocs.vista.Registro;

/**
 *
 * @author minio
 */
public class RegistroController implements ActionListener{
    Registro vista;
    Consultas modelo;

    public RegistroController(Registro vista, Consultas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.btnRegistrarse.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrarse){
            registrarUsuario();
        }
        if(e.getSource() == vista.btnAtras){
            volver();
        }
    }
    
    private void registrarUsuario(){
        if(!(vista.getFieldCedula().getText().isBlank()|| vista.getFieldUsuario().getText().isBlank() || vista.getFieldContrasenia().getPassword().length < 8 || vista.getFieldConfContrasenia().getPassword().length < 8)) {
            if(Arrays.equals(vista.getFieldContrasenia().getPassword(), vista.getFieldConfContrasenia().getPassword())){
                Usuario nuevoUsuario = new Usuario(Integer.parseInt(vista.getFieldCedula().getText()), vista.getFieldUsuario().getText(), vista.getFieldContrasenia().getText());
                if(modelo.registrarUsuario(nuevoUsuario)){
                    JOptionPane.showMessageDialog(null, "Usuario " + nuevoUsuario.getNombre() + " registrado con exito!"); // manejar los mensajes en el modelo para tener los datos a mano;
                } // guardar en la base de datos
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: las contrasenias no coinciden");
            }
        } else {
            if(vista.getFieldCedula().getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Cedula esta vacio");
            } else if(vista.getFieldUsuario().getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Nombre esta vacio");
            } else if(vista.getFieldContrasenia().getPassword().length < 8){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Contrasenia esta vacio");
            } else if(vista.getFieldConfContrasenia().getPassword().length < 8){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Confirmar Contrasenia esta vacio");
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: desconocido vuelva a intentarlo");
            }
        }
    }
    
    public void volver(){
        vista.dispose();
    }
}
