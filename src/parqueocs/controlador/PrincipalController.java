/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
 * @author Eddy Mena Lopez
 */
public class PrincipalController extends Controller implements ActionListener{
    private Principal vista;
    private Usuario usuario;

    public PrincipalController(Principal vista, Usuario usuario) {
        this.vista = vista;
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
        
        vista.setVisible(true);
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
        new RegistroVehiculoController(vistaRegistroVehiculo, usuario);
    }
    
    public void eliminarVehiculo(){
        // Eliminar el vehiculo seleccionado en la lista
        System.out.println(vista.listVehiculos.getSelectedValue());
        if(JOptionPane.showConfirmDialog(vista.getRootPane(),"Esta seguro que desea Eliminar el Vehiculo" + vista.listVehiculos.getSelectedValue(),"Confirmacion Eliminar", JOptionPane.YES_NO_OPTION) == 0){
            Vehiculo vehiculoEliminado = new Vehiculo(vista.listVehiculos.getSelectedValue());
            if(getModelo().eliminarVehiculo(vehiculoEliminado)){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Se ha eliminado el vehiculo " + vehiculoEliminado.getPlaca() + " correctamente!");
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"no se ha podido eliminar el vehiculo " + vehiculoEliminado.getPlaca());
            }
        }
        
    }
    
    public void llenarListaVehiculos(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = getModelo().buscarVehiculosUsuario(usuario);
        DefaultListModel<String> myModel = new DefaultListModel<>();
        for (Vehiculo auto : listaVehiculos){
            myModel.addElement(auto.getPlaca());
        }
        vista.listVehiculos.setModel(myModel);
    }
    
    public void llenarListaParqueados(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = getModelo().buscarVehiculosUsuarioFull(usuario);
        DefaultListModel<String> myModel = new DefaultListModel<>();
        LocalDateTime ahora = LocalDateTime.now();
        
        for (Vehiculo vehiculo : listaVehiculos ) {
            if (vehiculo.isPorPagar()) {
            LocalDateTime entrada = LocalDateTime.of(vehiculo.getFechaEntrada(), vehiculo.getEntradaHora());
            LocalDateTime salida = LocalDateTime.of(vehiculo.getFechaSalida(), vehiculo.getSalidaHora());

            String estado;
            if (ahora.isBefore(entrada)) {
                estado = "Pendiente";
            } else if (ahora.isAfter(salida)) {
                estado = "Vencido";
            } else {
                estado = "En curso";
            }

            String texto = String.format(
                "%s - %s - Entrada: %s %s - Salida: %s %s",
                estado,
                vehiculo.getPlaca(),
                vehiculo.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                vehiculo.getEntradaHora().format(DateTimeFormatter.ofPattern("HH:mm")),
                vehiculo.getFechaSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                vehiculo.getSalidaHora().format(DateTimeFormatter.ofPattern("HH:mm"))
            );
            myModel.addElement(texto);
            }
        }
                
        vista.listParqueados.setModel(myModel);
    }
    
    public boolean estaParqueado(Vehiculo vehiculoActual) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime entrada = LocalDateTime.of(vehiculoActual.getFechaEntrada(), vehiculoActual.getEntradaHora());
        LocalDateTime salida = LocalDateTime.of(vehiculoActual.getFechaSalida(), vehiculoActual.getSalidaHora());

        return ahora.isAfter(entrada) && ahora.isBefore(salida) && vehiculoActual.isPorPagar();
    }
    
    public void limpiarEspacios(){
        ArrayList<EspacioParqueo> listaEspacios = getModelo().buscarEspacioParqueoPorParqueo(new Parqueo(1));

        for (EspacioParqueo espacio : listaEspacios) {
            if(espacio.getPlacaVehiculo() != null){
                Vehiculo auto = new Vehiculo(espacio.getPlacaVehiculo());
                Vehiculo autoActual = getModelo().buscarVehiculosPorPlaca(auto);
                System.out.println("Vehiculo traido de consulta en Limpiar: " + autoActual.getSalidaHora());
                if(!estaParqueado(autoActual)){
                    EspacioParqueo espacioNuevo = new EspacioParqueo(espacio.getId(), null, 1);
                    getModelo().modificarEspacioParqueo(espacioNuevo);
                }

            }
        }
    }
    
    public boolean validarHayItemsEnLista(JList lista){
        return lista.getModel().getSize() != 0;
    }

    public void abrirParquear(Usuario usuario){
        if (validarHayItemsEnLista(vista.listVehiculos)) {
            Parquear vistaParquear = new Parquear();
            new ParquearController(vistaParquear, usuario);
        } else {
            JOptionPane.showMessageDialog(vista.getRootPane(),"Error: No hay vehiculos registrados");
        }
        
    }
    
    public void abrirModificarParqueado(Usuario usuario){
        if (validarHayItemsEnLista(vista.listParqueados)) {
            ModificarParqueo vistaModificarParqueo = new ModificarParqueo();
            new ModificarParqueoController(vistaModificarParqueo, usuario);
        } else {
            JOptionPane.showMessageDialog(vista.getRootPane(),"Error: No hay vehiculos parqueados");
        }
    }
    
    public void abrirPagarYSalir(Usuario usuario){
        if (validarHayItemsEnLista(vista.listParqueados)) {
            PagarYSalir vistaPagarYSalir = new PagarYSalir();
            new PagarYSalirController(vistaPagarYSalir, usuario);
        } else {
            JOptionPane.showMessageDialog(vista.getRootPane(),"Error: No hay vehiculos parqueados");
        }
        
    }
    
    public void exit(){
        // Cierra la vista
        this.vista.dispose();
    }
    
}
