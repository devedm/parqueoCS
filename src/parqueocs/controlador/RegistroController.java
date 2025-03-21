/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import parqueocs.ConsultasUsuario;
import parqueocs.modelo.Usuario;
import parqueocs.vista.Registro;

/**
 *
 * @author minio
 */
public class RegistroController {
    Registro vista;
    ConsultasUsuario modelo;

    public RegistroController(Registro vista, ConsultasUsuario modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initController();
    }
    
    private void initController(){
        // registrar Usuario
        vista.getBtnRegistrarse().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                registrarUsuario();
            }
        });
    }
    
    private void registrarUsuario(){
        if(!(vista.getFieldCedula().getText().isBlank()|| vista.getFieldUsuario().getText().isBlank() || vista.getFieldContrasenia().getPassword().length < 8 || vista.getFieldConfContrasenia().getPassword().length < 8)) {
            if(Arrays.equals(vista.getFieldContrasenia().getPassword(), vista.getFieldConfContrasenia().getPassword())){
                Usuario nuevoUsuario = new Usuario(Integer.parseInt(vista.getFieldCedula().getText()), vista.getFieldUsuario().getText(), vista.getFieldContrasenia().getText());
                modelo.registrarUsuario(nuevoUsuario);
                // insertar metodo para guardar usuario en base de datos
                JOptionPane.showMessageDialog(vista.getRootPane(), "El usuario ha sido registrado exitosamente");
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
}
