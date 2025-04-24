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
import parqueocs.vista.Parquear;

/**
 *
 * @author Eddy Mena Lopez
 */
public class ParquearController extends Controller implements ActionListener{
    private final Parquear vista;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private boolean vistaInicializada = false;

    public ParquearController(Parquear vista, Usuario usuario) {
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
        
    }
    
    public void abrirCalendario(JFormattedTextField field){
        Calendario vistaCalendario = new Calendario();
        new CalendarioController(vistaCalendario, field);
        vistaCalendario.setVisible(true);
    }
    
    public void cargarVehiculosCombo(Usuario usuario){
        ArrayList<Vehiculo> listaVehiculos = getModelo().buscarVehiculosUsuario(usuario);
        DefaultComboBoxModel<String> modelCombo =new DefaultComboBoxModel<>();
        for(Vehiculo auto : listaVehiculos){
            modelCombo.addElement(auto.getPlaca());
        }
        vista.comboAuto.setModel(modelCombo);
    }
    
    public void cargarEspaciosTiempo(){
        cargarFecha(vista.fieldFechaEntrada);
        cargarFecha(vista.fieldFechaSalida);
        cargarMinutosYHoras();
        cargarHora();
    }
    
    public void cargarFecha(JFormattedTextField field){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        field.setText(hoy.format(formato));
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
    
    public Duration validarHora(){
        try {
            // Parse fechas
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaEntrada = LocalDate.parse(vista.fieldFechaEntrada.getText(), formatoFecha);
            LocalDate fechaSalida = LocalDate.parse(vista.fieldFechaSalida.getText(), formatoFecha);

            // Parse horas
            LocalTime horaInicio = sacarHoras(vista.comboHoraInicio, vista.comboMinutosInicio, vista.comboAMPMInicio);
            LocalTime horaSalida = sacarHoras(vista.comboHoraSalida, vista.comboMinutosSalida, vista.comboAMPMSalida);

            // Unificar en LocalDateTime
            var dateTimeEntrada = fechaEntrada.atTime(horaInicio);
            var dateTimeSalida = fechaSalida.atTime(horaSalida);

            // Validación
            if (dateTimeEntrada.isBefore(dateTimeSalida)) {
                return Duration.between(dateTimeEntrada, dateTimeSalida);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Fecha/hora de inicio debe ser anterior a la de salida.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR al validar fechas u horas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    
    public void cargarTiempo(){
        Duration duracion = validarHora();
        if (duracion != null) {
            long minutos = duracion.toMinutes();
            vista.labelTiempo.setText("Tiempo = " + minutos + " min");
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
    
    public void cargarEspaciosParqueo(){
        ArrayList<EspacioParqueo> listaEspacios = getModelo().buscarEspacioParqueoPorParqueo(new Parqueo(1));
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>();
        
        for (EspacioParqueo espacio : listaEspacios) {
            if(espacio.getPlacaVehiculo() == null){
                myModel.addElement(String.valueOf("Plaza #" + espacio.getId()));
            }
        }
        vista.listEspacios.setModel(myModel);
        
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
