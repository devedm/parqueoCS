/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import parqueocs.modelo.EspacioParqueo;
import parqueocs.modelo.Parqueo;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Calendario;
import parqueocs.vista.ModificarParqueo;

/**
 *
 * @author Eddy Mena Lopez
 */
public class ModificarParqueoController extends Controller implements ActionListener{

    ModificarParqueo vista;
    Usuario usuario;
    Vehiculo vehiculo;
    private boolean vistaInicializada = false;
    
    public ModificarParqueoController(ModificarParqueo vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
        this.vista.btnParquear.addActionListener(this);
        this.vista.btnCalendarioEntrada.addActionListener(this);
        this.vista.btnCalendarioSalida.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.comboHoraInicio.addActionListener(this);
        this.vista.comboMinutosInicio.addActionListener(this);
        this.vista.comboAMPMInicio.addActionListener(this);
        this.vista.comboHoraSalida.addActionListener(this);
        this.vista.comboMinutosSalida.addActionListener(this);
        this.vista.comboAMPMSalida.addActionListener(this);
        this.vista.comboAuto.addActionListener(this);
        
        cargarVehiculosCombo(usuario);
        cargarEspaciosTiempo();
        cargarEspaciosParqueo();
        
        vista.setVisible(true);

        vistaInicializada = true;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnParquear){
            parquearVehiculo();
        }
        if(e.getSource() == vista.btnCalendarioEntrada){
            abrirCalendario(vista.fieldFechaEntrada);
        }
        if(e.getSource() == vista.btnCalendarioSalida){
            abrirCalendario(vista.fieldFechaSalida);
        }
        if(e.getSource() == vista.btnCancelar){
            exit();
        }
        if(vistaInicializada && (
            e.getSource() == vista.comboHoraInicio ||
            e.getSource() == vista.comboMinutosInicio ||
            e.getSource() == vista.comboAMPMInicio ||
            e.getSource() == vista.comboHoraSalida || 
            e.getSource() == vista.comboMinutosSalida ||
            e.getSource() == vista.comboAMPMSalida)) {
            
            cargarTiempo();
        }
        if(e.getSource() == vista.comboAuto){
            cargarEspaciosParqueo();
        }
        
        
    }
    
    public void abrirCalendario(JFormattedTextField field){
        Calendario vistaCalendario = new Calendario();
        new CalendarioController(vistaCalendario, field);
        vistaCalendario.setVisible(true);
    }
    
    public void cargarVehiculosCombo(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = getModelo().buscarVehiculosUsuarioFull(usuario);
        DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<>();
        for(Vehiculo auto : listaVehiculos){
            if (auto.isPorPagar()) {
                modelCombo.addElement(auto.getPlaca());
            }
        }
        vista.comboAuto.setModel(modelCombo);
    }
    
    public void cargarEspaciosTiempo(){
        cargarFechaExistente(vista.fieldFechaEntrada, vista.fieldFechaSalida);
        cargarMinutosYHoras();
        cargarHoraExistente(true, vista.comboHoraInicio, vista.comboMinutosInicio, vista.comboAMPMInicio);
        cargarHoraExistente(false, vista.comboHoraSalida, vista.comboMinutosSalida, vista.comboAMPMSalida);
    }
    
    public void cargarFecha(JFormattedTextField field){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        field.setText(hoy.format(formato));
    }
    
    public void cargarFechaExistente(JFormattedTextField fieldEntrada, JFormattedTextField fieldSalida){
        String placa = (String) vista.comboAuto.getSelectedItem();
        vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placa));
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        fieldEntrada.setText(vehiculo.getFechaEntrada().format(formato));
        fieldSalida.setText(vehiculo.getFechaSalida().format(formato));
        
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
    
    public void cargarHoraExistente(boolean esEntrada, JComboBox comboHora, JComboBox comboMinuto, JComboBox comboAMPM){
        String placa = (String) vista.comboAuto.getSelectedItem();
        vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placa));
        
        int hora;
        int minuto;
        String ampm = "AM";
        
        int[] minutos = {0,15,30,45};
        int minutoMasCercano = minutos[0];
        
        if(esEntrada){
            hora = vehiculo.getEntradaHora().getHour();
            minuto = vehiculo.getEntradaHora().getMinute();
            
        } else {
            hora = vehiculo.getSalidaHora().getHour();
            minuto = vehiculo.getSalidaHora().getMinute();
        }
        
        if(hora == 0){
            hora = 12;
        } else if (hora == 12){
            ampm = "PM";
        } else if (hora > 12){
            hora -= 12;
            ampm = "PM";
        }

        for (int i = 1; i < minutos.length; i++) {
            if (Math.abs(minutos[i] - minuto) < Math.abs(minutoMasCercano - minuto)) {
                minutoMasCercano = minutos[i];
            }
        }
        
        comboHora.setSelectedItem(String.valueOf(hora));
        comboMinuto.setSelectedItem(String.format("%02d", minutoMasCercano));
        comboAMPM.setSelectedItem(ampm);
        
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
    
    public Duration validarHora(){
        // validar que la hora de inicio sea antes que la hora de salida
        LocalTime horaInicio = sacarHoras(vista.comboHoraInicio, vista.comboMinutosInicio, vista.comboAMPMInicio);
        LocalTime horaSalida = sacarHoras(vista.comboHoraSalida, vista.comboMinutosSalida, vista.comboAMPMSalida);
        if(horaInicio.isBefore(horaSalida)){
            if(!(horaInicio.equals(horaSalida))){
                return Duration.between(horaInicio, horaSalida);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: hora salida y hora entrada son iguales","Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: hora de inicio no es antes de la hora de salida","Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public void cargarTiempo(){
        Duration duracion = validarHora();
        if(duracion != null){
            vista.labelTiempo.setText("Tiempo = " + duracion.toMinutes() + "min");
        }

    }
    
    public LocalTime sacarHoras(JComboBox<String> comboHora, JComboBox<String> comboMinuto, JComboBox<String> comboAMPM){
        int hora = Integer.parseInt((String) comboHora.getSelectedItem());
        int minuto = Integer.parseInt((String) comboMinuto.getSelectedItem());
        String ampm = (String) comboAMPM.getSelectedItem();

        // Convertir a formato 24 horas
        if (ampm.equalsIgnoreCase("PM") && hora != 12) {
            hora += 12;
        } else if (ampm.equalsIgnoreCase("AM") && hora == 12) {
            hora = 0;
        }
        return LocalTime.of(hora, minuto);
    }
    
    public boolean cargarEspaciosParqueo(){
        ArrayList<EspacioParqueo> listaEspacios = getModelo().buscarEspacioParqueoPorParqueo(new Parqueo(1));
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>();
        String placa;
        try {
            placa = (String) vista.comboAuto.getSelectedItem();
            vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placa));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "No se pueden cargar los espacios sin seleccionar un vehiculo");
            return false;
        }
        
        for (EspacioParqueo espacio : listaEspacios) {
            if(espacio.getPlacaVehiculo() == null || espacio.getPlacaVehiculo().contentEquals(placa)){
                myModel.addElement(String.valueOf("Plaza #" + espacio.getId()));
            }
        }
        vista.listEspacios.setModel(myModel);
        return true;
    }

    
    public void parquearVehiculo(){
        // Variables
        String placaAuto;
        LocalDate fechaEntrada;
        LocalDate fechaSalida;
        LocalTime horaInicio;
        LocalTime horaSalida;
        int tiempoParqueado;
        int idEspacioParqueo;
        
        // Obtener Valores
        
        // Auto
        placaAuto = vista.comboAuto.getSelectedItem().toString();
        System.out.println("Auto seleccionado: " + placaAuto);

        // Fechas
        String[] fechaListEntrada = vista.fieldFechaEntrada.getText().split("/");
        fechaEntrada = LocalDate.of(Integer.valueOf(fechaListEntrada[2]), Integer.valueOf(fechaListEntrada[1]), Integer.valueOf(fechaListEntrada[0]));
        System.out.println(fechaEntrada.format(DateTimeFormatter.ISO_DATE));
        
        String[] fechaListSalida = vista.fieldFechaSalida.getText().split("/");
        fechaSalida = LocalDate.of(Integer.valueOf(fechaListSalida[2]), Integer.valueOf(fechaListSalida[1]), Integer.valueOf(fechaListSalida[0]));
        System.out.println(fechaSalida.format(DateTimeFormatter.ISO_DATE));
        

        // Horas
        horaInicio = sacarHoras(vista.comboHoraInicio, vista.comboMinutosInicio, vista.comboAMPMInicio);
        horaSalida = sacarHoras(vista.comboHoraSalida, vista.comboMinutosSalida, vista.comboAMPMSalida);
        System.out.println("Hora Inicio: " + horaInicio.format(DateTimeFormatter.ISO_TIME) + ", Hora Salida: " + horaSalida.format(DateTimeFormatter.ISO_TIME));

        // Tiempo
        tiempoParqueado = (int) validarHora().toMinutes();
        System.out.println("Minutos a parquear: " + tiempoParqueado);

        // Espacio
        idEspacioParqueo = Integer.parseInt(vista.listEspacios.getSelectedValue().replace("Plaza #", ""));
        System.out.println("Espacio de parqueo: " + idEspacioParqueo);
            
        
        // Crear modelo vehiculo
        vehiculo = new Vehiculo(placaAuto, fechaEntrada, fechaSalida, horaInicio, horaSalida, tiempoParqueado, true);
        
        // Crear modelo espacioParqueo
        
        EspacioParqueo espacio = new EspacioParqueo(idEspacioParqueo, placaAuto, 1);
        
        
        if(getModelo().modificarVehiculo(vehiculo)){
            if(getModelo().modificarEspacioParqueo(espacio)){
                JOptionPane.showMessageDialog(vista.getRootPane(),"Se ha parqueado el vehiculo " + vehiculo.getPlaca() + " en el espacio #" + espacio.getId() + " correctamente!");
                exit();
            } else {
                JOptionPane.showMessageDialog(vista.getRootPane(),"No se ha podido actualizar el espacio #" + espacio.getId() + " con el vehiculo " + vehiculo.getPlaca());
            }
        } else {
            JOptionPane.showMessageDialog(vista.getRootPane(),"no se ha podido actualizar el vehiculo " + vehiculo.getPlaca());
        }
    }
    
    
    
    
    public void exit(){
        // Cierra la vista
        vista.dispose();
    }
}
