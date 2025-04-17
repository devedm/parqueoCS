/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.EspacioParqueo;
import parqueocs.modelo.Parqueo;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.ModificarParqueo;
import parqueocs.vista.PagarYSalir;
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
        this.vista.btnRefrescar.addActionListener(this);
        this.vista.btnRefrescarParqueados.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnPagar.addActionListener(this);
        this.vista.btnCerrarSesion.addActionListener(this);
        
        
        llenarListaVehiculos(this.usuario);
        llenarListaParqueados(this.usuario);
//        limpiarEspacios();
        
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
        if(e.getSource() == vista.btnRefrescar){
            llenarListaVehiculos(usuario);
        }
        if(e.getSource() == vista.btnRefrescarParqueados){
            llenarListaParqueados(usuario);
//            limpiarEspacios();
        }
        if(e.getSource() == vista.btnModificar){
            abrirModificarParqueado(usuario);
        }
        if(e.getSource() == vista.btnPagar){
            abrirPagarYSalir(usuario);
        }
        if(e.getSource() == vista.btnCerrarSesion){
            exit();
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
    
    public void llenarListaParqueados(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = modelo.buscarVehiculosUsuarioFull(usuario);
        DefaultListModel<String> myModel = new DefaultListModel<>();
        
        for (Vehiculo vehiculo : listaVehiculos ) {
            if(estaParqueado(vehiculo)){
                if(LocalTime.now().isBefore(vehiculo.getEntradaHora())){
                    myModel.addElement("Pendiente - " + vehiculo.getPlaca() + " - Entrada: " + vehiculo.getEntradaHora().format(DateTimeFormatter.ISO_TIME) + " - Salida: " + vehiculo.getSalidaHora().format(DateTimeFormatter.ISO_TIME)); 
                } else if(LocalTime.now().isAfter(vehiculo.getSalidaHora())){
                    myModel.addElement("Vencido - " + vehiculo.getPlaca() + " - Entrada: " + vehiculo.getEntradaHora().format(DateTimeFormatter.ISO_TIME) + " - Salida: " + vehiculo.getSalidaHora().format(DateTimeFormatter.ISO_TIME)); 
                }else if (LocalTime.now().isBefore(vehiculo.getSalidaHora())){
                    myModel.addElement("Vencido - " + vehiculo.getPlaca() + " - Entrada: " + vehiculo.getEntradaHora().format(DateTimeFormatter.ISO_TIME) + " - Salida: " + vehiculo.getSalidaHora().format(DateTimeFormatter.ISO_TIME)); 
                }
            }
        }
                
        vista.listParqueados.setModel(myModel);
    }
    
    public boolean estaParqueado(Vehiculo vehiculoActual){
        
//        LocalTime horaActual = LocalTime.now();
//        System.out.println("Hora Actual: " + horaActual.format(DateTimeFormatter.ISO_TIME));
//        System.out.println("Hora Salida Vehiculo: " + vehiculoActual.getSalidaHora());
        
//        LocalDate fechaActual = LocalDate.now();
//        System.out.println("Fecha Actual" + fechaActual);
//        System.out.println("Fecha Vehiculo" + vehiculoActual.getFecha());
//        if(vehiculoActual.getFecha().equals(fechaActual)){
//                if(horaActual.isAfter(vehiculoActual.getEntradaHora())){
//                    if(!horaActual.isAfter(vehiculoActual.getSalidaHora())){
//                        System.out.println(vehiculoActual.getPlaca() + " esta parqueado actualmente!");
//                        return true;
//                    }
//                }
//            }

        return vehiculoActual.isPorPagar();
    }
    
    public void limpiarEspacios(){
        ArrayList<EspacioParqueo> listaEspacios = modelo.buscarEspacioParqueoPorParqueo(new Parqueo(1));

        for (EspacioParqueo espacio : listaEspacios) {
            if(espacio.getPlacaVehiculo() != null){
                Vehiculo auto = new Vehiculo(espacio.getPlacaVehiculo());
                Vehiculo autoActual = modelo.buscarVehiculosPorPlaca(auto);
                System.out.println("Vehiculo traido de consulta en Limpiar: " + autoActual.getSalidaHora());
                if(!estaParqueado(autoActual)){
                    EspacioParqueo espacioNuevo = new EspacioParqueo(espacio.getId(), null, 1);
                    modelo.modificarEspacioParqueo(espacioNuevo);
                }

            }
        }
    }

    public void abrirParquear(Usuario usuario){
        Parquear vistaParquear = new Parquear();
        Consultas modelo = new Consultas();
        new ParquearController(vistaParquear, modelo, usuario);
        vistaParquear.setVisible(true);
    }
    
    public void abrirModificarParqueado(Usuario usuario){
        ModificarParqueo vistaModificarParqueo = new ModificarParqueo();
        new ModificarParqueoController(vistaModificarParqueo, usuario);
        vistaModificarParqueo.setVisible(true);
    }
    
    public void abrirPagarYSalir(Usuario usuario){
        PagarYSalir vistaPagarYSalir = new PagarYSalir();
        new PagarYSalirController(vistaPagarYSalir, usuario);
        vistaPagarYSalir.setVisible(true);
    }
    
    public void exit(){
        // Cierra la vista
        vista.dispose();
    }
    
}
