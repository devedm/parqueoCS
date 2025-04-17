/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import parqueocs.modelo.EspacioParqueo;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.PagarYSalir;

/**
 *
 * @author Eddy Mena Lopez
 */
public class PagarYSalirController extends Controller implements ActionListener{
    PagarYSalir vista;
    Usuario usuario;
    Vehiculo vehiculo;

    public PagarYSalirController(PagarYSalir vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
        // Elementos
        this.vista.btnPagar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.comboAuto.addActionListener(this);
        // Metodos
        cargarVehiculosCombo(usuario);
        mostrarInformacionVehiculo();
        vista.setVisible(true);
    }

    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnPagar) {
            terminarParqueo();
        } else if (e.getSource() == vista.comboAuto) {
            mostrarInformacionVehiculo();
        }
        if (e.getSource() == vista.btnCancelar) {
            exit();
        } 
        
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
    
    public Duration validarTiempo(Vehiculo vehiculo){
        LocalDateTime entrada = LocalDateTime.of(vehiculo.getFechaEntrada(), vehiculo.getEntradaHora());
        LocalDateTime salida = LocalDateTime.of(vehiculo.getFechaSalida(), vehiculo.getSalidaHora());
        
        Duration duracion = Duration.between(entrada, salida);
        return duracion;
    }
    
    public Vehiculo modificarSalidaVehiculo(Vehiculo vehiculo){
     
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime entrada = LocalDateTime.of(vehiculo.getFechaEntrada(), vehiculo.getEntradaHora());
        LocalDateTime salida = LocalDateTime.of(vehiculo.getFechaSalida(), vehiculo.getSalidaHora());
        
        if(salida.isAfter(ahora) && entrada.isBefore(ahora)){
            // Se calcula la salida a ahora porque estabamos parqueados desde hace x tiempo y estamos saliendo antes 
            vehiculo.setFechaSalida(ahora.toLocalDate());
            vehiculo.setSalidaHora(ahora.toLocalTime());
            vehiculo.setDuracionMinutos((int) validarTiempo(vehiculo).toMinutes());
            System.out.println("Salida antes de la hora");
        } else if (salida.isBefore(ahora) && entrada.isBefore(ahora)){
            // se calcula la salida a ahora estamos saliendo despues 
            vehiculo.setFechaSalida(ahora.toLocalDate());
            vehiculo.setSalidaHora(ahora.toLocalTime());
            vehiculo.setDuracionMinutos((int) validarTiempo(vehiculo).toMinutes());
            System.out.println("Salida despues de la hora");
            
        } else if (salida.isAfter(ahora) && entrada.isAfter(ahora)) {
            // Se libera el espacio solamente se esta saliendo antes de entrar a parquear
            System.out.println("Salida sin parquear");
            
        } else {
            // esta a tiempo y no se cambia la hora de salida 
            System.out.println("Salida a tiempo");
        }
        
        return vehiculo;
    }
    
    public void mostrarInformacionVehiculo(){
        String placaSeleccionada = (String) vista.comboAuto.getSelectedItem();
        if (placaSeleccionada == null) return;

        vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placaSeleccionada));
        vehiculo = modificarSalidaVehiculo(vehiculo);
        LocalDateTime entrada = LocalDateTime.of(vehiculo.getFechaEntrada(), vehiculo.getEntradaHora());
        LocalDateTime salida = LocalDateTime.of(vehiculo.getFechaSalida(), vehiculo.getSalidaHora());

        Duration duracion = Duration.between(entrada, salida);
        long minutos = duracion.toMinutes();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        vista.labelPlaca.setText("Placa: " + vehiculo.getPlaca());
        vista.labelEntrada.setText("Entrada: " + entrada.format(formatter));
        vista.labelSalida.setText("Salida: " + salida.format(formatter));
        vista.labelTiempoParqueado.setText("Duración total: " + minutos + " minutos");
    }
      
    
    public void terminarParqueo(){
        String placaSeleccionada = (String) vista.comboAuto.getSelectedItem();
        if (placaSeleccionada == null) return;
        
        vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placaSeleccionada));
        vehiculo = modificarSalidaVehiculo(vehiculo);
        vehiculo.setPorPagar(false);
        
        EspacioParqueo espacio = getModelo().buscarEspacioParqueoPorVehiculo(vehiculo);
        espacio.setPlacaVehiculo(null);
        
        if(getModelo().modificarVehiculo(vehiculo)){
            System.out.println("Vehiculo " + placaSeleccionada + " modificado, pago por parqueo " + vehiculo.getDuracionMinutos() + " minutos.");
            if(getModelo().modificarEspacioParqueo(espacio)){
                System.out.println("Espacio " + espacio.getId() + " liberado, pago por parqueo " + vehiculo.getDuracionMinutos() + " minutos.");
            }
        }
        exit();
    }
    
    public void exit(){
        // Cierra la vista
        this.vista.dispose();
    }
}
