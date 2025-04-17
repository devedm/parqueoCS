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
    
    public void mostrarInformacionVehiculo(){
        String placaSeleccionada = (String) vista.comboAuto.getSelectedItem();
        if (placaSeleccionada == null) return;

        vehiculo = getModelo().buscarVehiculosPorPlaca(new Vehiculo(placaSeleccionada));
        
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
        String placa = (String) vista.comboAuto.getSelectedItem();
        vehiculo = new Vehiculo(placa);
        vehiculo = getModelo().buscarVehiculosPorPlaca(vehiculo);
        vehiculo.setPorPagar(false);
        
        EspacioParqueo espacio = getModelo().buscarEspacioParqueoPorVehiculo(vehiculo);
        espacio.setPlacaVehiculo(null);
        
        if(getModelo().modificarVehiculo(vehiculo)){
            System.out.println("Vehiculo " + placa + " modificado, pago por parqueo " + vehiculo.getDuracionMinutos() + " minutos.");
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
