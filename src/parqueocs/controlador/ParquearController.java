/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.DateFormatter;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Calendario;
import parqueocs.vista.Parquear;
import parqueocs.vista.Principal;

/**
 *
 * @author minio
 */
public class ParquearController implements ActionListener{
    private final Parquear vista;
    private final Consultas modelo;
    private Usuario usuario;
    private Vehiculo vehiculo;

    public ParquearController(Parquear vista, Consultas modelo, Usuario usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
        this.vista.btnParquear.addActionListener(this);
        this.vista.btnFecha.addActionListener(this);
        cargarVehiculosCombo(usuario);
        cargarFecha();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnParquear){
            parquearVehiculo();
        }
        if(e.getSource() == vista.btnFecha){
            abrirCalendario();
        }
    }
    
    public void abrirCalendario(){
        Calendario vistaCalendario = new Calendario();
        new CalendarioController(vistaCalendario, vista);
        vistaCalendario.setVisible(true);
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
    
    public void cargarFecha(){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vista.fieldFecha.setText(hoy.format(formato));
    }
}
