/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import parqueocs.modelo.Consultas;
import parqueocs.vista.Principal;
import parqueocs.vista.RegistroVehiculo;

/**
 *
 * @author minio
 */
public class PrincipalController {
    Principal vista;
    Consultas modelo;

    public PrincipalController(Principal vista, Consultas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initController();
    }

    private void initController(){
        // Metodos de la vista
        vista.getBtnRegistrarVehiculo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                abrirRegistrarVehiculo();
            }
        });
    }
    
    public void abrirRegistrarVehiculo(){
        // Abre ls vista Registrar Vehiculo
        RegistroVehiculo vistaRegistroVehiculo = new RegistroVehiculo();
        Consultas modelo = new Consultas();
        new RegistroVehiculoController(vistaRegistroVehiculo, modelo);
        vistaRegistroVehiculo.setVisible(true);
    }
}
