/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import parqueocs.modelo.Usuario;
import parqueocs.vista.Registro;

/**
 *
 * @author Eddy Mena Lopez
 */
public class RegistroController extends Controller implements ActionListener{
    Registro vista;

    public RegistroController(Registro vista) {
        this.vista = vista;
        this.vista.btnRegistrarse.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        vista.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrarse){
            registrarUsuario();
        }
        if(e.getSource() == vista.btnAtras){
            exit();
        }
    }
    
    private void registrarUsuario(){
        if(!(vista.getFieldCedula().getText().isBlank()|| vista.getFieldUsuario().getText().isBlank() || vista.getFieldContrasenia().getPassword().length < 8 || vista.getFieldConfContrasenia().getPassword().length < 8)) {
            if(Arrays.equals(vista.getFieldContrasenia().getPassword(), vista.getFieldConfContrasenia().getPassword())){
                Usuario nuevoUsuario = new Usuario(Integer.parseInt(vista.getFieldCedula().getText()), vista.getFieldUsuario().getText(), vista.getFieldContrasenia().getText());
                if(getModelo().registrarUsuario(nuevoUsuario)){
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
    
    public void exit(){
        this.vista.dispose();
    }
}
