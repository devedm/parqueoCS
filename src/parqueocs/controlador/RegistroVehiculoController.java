/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parqueocs.modelo.Consultas;
import parqueocs.vista.RegistroVehiculo;

/**
 *
 * @author minio
 */
public class RegistroVehiculoController {
    RegistroVehiculo vista;
    Consultas modelo;

    public RegistroVehiculoController(RegistroVehiculo vista, Consultas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initController();
    }
    
    private void initController(){
        // Metodos de la vista
        vista.getBtnRegistrarVehiculo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                registrarVehiculo();
            }
        });
    }
    
    public void registrarVehiculo(){
        // Abre ls vista Registrar Vehiculo
        
    }
}
