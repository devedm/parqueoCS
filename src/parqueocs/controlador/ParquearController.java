/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Parquear;

/**
 *
 * @author minio
 */
public class ParquearController implements ActionListener{
    private final Parquear vista;
    private final Consultas modelo;
    private Usuario usuario;

    public ParquearController(Parquear vista, Consultas modelo, Usuario usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
        this.vista.btnParquear.addActionListener(this);
        cargarVehiculosCombo(usuario);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnParquear){
            parquearVehiculo();
        }
    }
    
    public void parquearVehiculo(){
        
    }
    
    public void cargarVehiculosCombo(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = modelo.buscarVehiculosUsuario(usuario);
        DefaultComboBoxModel<String> modelCombo =new DefaultComboBoxModel<>();
        for(Vehiculo auto : listaVehiculos){
            modelCombo.addElement(auto.getPlaca());
        }
        vista.comboAuto.setModel(modelCombo);
    }
}
