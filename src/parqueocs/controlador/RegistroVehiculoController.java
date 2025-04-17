/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.RegistroVehiculo;

/**
 *
 * @author Eddy Mena Lopez
 */
public class RegistroVehiculoController extends Controller implements ActionListener{
    RegistroVehiculo vista;
    Usuario usuario;

    public RegistroVehiculoController(RegistroVehiculo vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
        this.vista.btnRegistrarVehiculo.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.btnRegistrarVehiculo){
            registrarVehiculo();
        }
        if(e.getSource() == vista.btnCancelar){
            exit();
        }
    }
    
    public void registrarVehiculo(){
        // Abre ls vista Registrar Vehiculo
        if(!(vista.fieldPlaca.getText().isBlank())){
            Vehiculo vehiculo = new Vehiculo(vista.fieldPlaca.getText());
            if (getModelo().registrarVehiculo(vehiculo, usuario)){
                JOptionPane.showMessageDialog(null, "Vehiculo con placa " + vehiculo.getPlaca() + " registrado en " + usuario.getNombre() + " con exito!");
                exit();
            }
        }else {
            if(vista.fieldPlaca.getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Placa esta vacio");
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: desconocido vuelva a intentarlo");
            }
        }
    
    }
    
    public void exit(){
        this.vista.dispose();
    }
}
