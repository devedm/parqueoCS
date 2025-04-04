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
import javax.swing.DefaultComboBoxModel;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Calendario;
import parqueocs.vista.Parquear;

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
        this.vista.btnCancelar.addActionListener(this);
        this.vista.comboHoraInicio.addActionListener(this);
        this.vista.comboMinutosInicio.addActionListener(this);
        this.vista.comboAMPMInicio.addActionListener(this);
        this.vista.comboHoraSalida.addActionListener(this);
        this.vista.comboMinutosSalida.addActionListener(this);
        this.vista.comboAMPMSalida.addActionListener(this);
        
        cargarVehiculosCombo(usuario);
        cargarFecha();
        cargarMinutosYHoras();
        cargarHora();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnParquear){
            parquearVehiculo();
        }
        if(e.getSource() == vista.btnFecha){
            abrirCalendario();
        }
        if(e.getSource() == vista.btnCancelar){
            exit();
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
    
    public void cargarHora(){
        LocalTime ahora = LocalTime.now();
        int hora = ahora.getHour();
        String ampm = "AM";
        
        if(hora == 0){
            hora = 12;
        } else if (hora == 12){
            ampm = "PM";
        } else if (hora > 12){
            hora -= 12;
            ampm = "PM";
        }
        
        
        
        int minuto = ahora.getMinute();
        int[] minutos = {0,15,30,45};
        int minutoMasCercano = minutos[0];

        for (int i = 1; i < minutos.length; i++) {
            if (Math.abs(minutos[i] - minuto) < Math.abs(minutoMasCercano - minuto)) {
                minutoMasCercano = minutos[i];
            }
        }
        vista.comboHoraInicio.setSelectedItem(String.valueOf(hora));
        vista.comboMinutosInicio.setSelectedItem(String.format("%02d", minutoMasCercano));
        vista.comboAMPMInicio.setSelectedItem(ampm);
        
        vista.comboHoraSalida.setSelectedItem(String.valueOf(hora));
        vista.comboMinutosSalida.setSelectedItem(String.format("%02d", minutoMasCercano));
        vista.comboAMPMSalida.setSelectedItem(ampm);
    }
    
    public void cargarMinutosYHoras(){
        // crear Modelo Hora Inicio
        DefaultComboBoxModel<String> modeloHorasInicio = new DefaultComboBoxModel<>();
        for(int i = 1; i <= 12; i++){
            modeloHorasInicio.addElement(String.valueOf(i));
        }
        vista.comboHoraInicio.setModel(modeloHorasInicio);
        
        // crear Modelo Hora Salida
        DefaultComboBoxModel<String> modeloHorasSalida = new DefaultComboBoxModel<>();;
        for(int i = 1; i <= 12; i++){
            modeloHorasSalida.addElement(String.valueOf(i));
        }
        vista.comboHoraSalida.setModel(modeloHorasSalida);
        
        // crear Modelo Minuto Inicio
        DefaultComboBoxModel<String> modeloMinutosInicio = new DefaultComboBoxModel<>();
        int[] minutos = {0,15,30,45};
        for (int minuto : minutos) {
            modeloMinutosInicio.addElement(String.format("%02d", minuto));
        }
        vista.comboMinutosInicio.setModel(modeloMinutosInicio);
        
        // crear Modelo Minuto Salida
        DefaultComboBoxModel<String> modeloMinutosSalida = new DefaultComboBoxModel<>();
        for (int minuto : minutos) {
            modeloMinutosSalida.addElement(String.format("%02d", minuto));
        }
        vista.comboMinutosSalida.setModel(modeloMinutosSalida);
        
        // crear Modelo Minuto Inicio
        DefaultComboBoxModel<String> modeloAMPMInicio = new DefaultComboBoxModel<>();
        modeloAMPMInicio.addElement("AM");
        modeloAMPMInicio.addElement("PM");
        vista.comboAMPMInicio.setModel(modeloAMPMInicio);
        
        // crear Modelo AMPM Salida
        DefaultComboBoxModel<String> modeloAMPMSalida = new DefaultComboBoxModel<>();
        modeloAMPMSalida.addElement("AM");
        modeloAMPMSalida.addElement("PM");
        vista.comboAMPMSalida.setModel(modeloAMPMSalida);
    }
    
    public void validarHora(){
        // validar que la hora de inicio sea antes que la hora de salida
        // validar que la hora de inicio y la hora de salida no sean iguales
    }
    
    public void exit(){
        // Cierra la vista
        vista.dispose();
    }
}
