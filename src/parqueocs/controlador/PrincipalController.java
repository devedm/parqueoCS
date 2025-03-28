/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Parquear;
import parqueocs.vista.Principal;
import parqueocs.vista.RegistroVehiculo;

/**
 *
 * @author minio
 */
public class PrincipalController implements ActionListener{
    private final Principal vista;
    private final Consultas modelo;
    private Usuario usuario;

    public PrincipalController(Principal vista, Consultas modelo, Usuario usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
        this.vista.btnRegistrarVehiculo.addActionListener(this);
        this.vista.btnEliminarVehiculo.addActionListener(this);
        this.vista.btnParquear.addActionListener(this);
        llenarListaVehiculos(this.usuario);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnRegistrarVehiculo){
            abrirRegistrarVehiculo(usuario);
        }
        if(e.getSource() == vista.btnEliminarVehiculo){
            eliminarVehiculo();
        }
        if(e.getSource() == vista.btnParquear){
            abrirParquear(usuario);
        }
        if(e.getSource() == vista.listParqueados){
            llenarListaVehiculos(usuario);
        }
    }
    
    public void abrirRegistrarVehiculo(Usuario usuario){
        // Abre ls vista Registrar Vehiculo
        RegistroVehiculo vistaRegistroVehiculo = new RegistroVehiculo();
        Consultas modelo = new Consultas();
        new RegistroVehiculoController(vistaRegistroVehiculo, modelo, usuario);
        vistaRegistroVehiculo.setVisible(true);
    }
    
    public void eliminarVehiculo(){
        // Eliminar el vehiculo seleccionado en la lista
        System.out.println(vista.listVehiculos.getSelectedValue());
        if(JOptionPane.showConfirmDialog(vista.getRootPane(),"Esta seguro que desea Eliminar el Vehiculo" + vista.listVehiculos.getSelectedValue(),"Confirmacion Eliminar", JOptionPane.YES_NO_OPTION) == 0){
            Vehiculo vehiculoEliminado = new Vehiculo(vista.listVehiculos.getSelectedValue());
            if(modelo.eliminarVehiculo(vehiculoEliminado)){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Se ha eliminado el vehiculo " + vehiculoEliminado.getPlaca() + " correctamente!");
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"no se ha podido eliminar el vehiculo " + vehiculoEliminado.getPlaca());
            }
        }
        
    }
    
    public void llenarListaVehiculos(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = modelo.buscarVehiculosUsuario(usuario);
        DefaultListModel<String> myModel = new DefaultListModel<>();
        for (Vehiculo auto : listaVehiculos){
            myModel.addElement(auto.getPlaca());
        }
        vista.listVehiculos.setModel(myModel);
    }

    public void abrirParquear(Usuario usuario){
        Parquear vistaParquear = new Parquear();
        Consultas modelo = new Consultas();
        new ParquearController(vistaParquear, modelo, usuario);
        vistaParquear.setVisible(true);
    }
}
