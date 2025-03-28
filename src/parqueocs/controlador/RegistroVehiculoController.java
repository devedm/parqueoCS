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
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.RegistroVehiculo;

/**
 *
 * @author minio
 */
public class RegistroVehiculoController implements ActionListener{
    RegistroVehiculo vista;
    Consultas modelo;
    Usuario usuario;

    public RegistroVehiculoController(RegistroVehiculo vista, Consultas modelo, Usuario usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
        this.vista.btnRegistrarVehiculo.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.btnRegistrarVehiculo){
            registrarVehiculo();
        }
    }
    
    public void registrarVehiculo(){
        // Abre ls vista Registrar Vehiculo
        if(!(vista.fieldPlaca.getText().isBlank())){
            Vehiculo vehiculo = new Vehiculo(vista.fieldPlaca.getText());
            if (modelo.registrarVehiculo(vehiculo, usuario)){
                JOptionPane.showMessageDialog(null, "Vehiculo con placa " + vehiculo.getPlaca() + " registrado en " + usuario.getNombre() + " con exito!");
                vista.dispose();
            }
        }else {
            if(vista.fieldPlaca.getText().isBlank()){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: el espacio de Placa esta vacio");
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"Error: desconocido vuelva a intentarlo");
            }
        }
    
}
}
