/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.crypto.AEADBadTagException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import parqueocs.modelo.EspacioParqueo;
import parqueocs.modelo.Usuario;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.ModificarParqueo;
import parqueocs.vista.PagarYSalir;

/**
 *
 * @author minio
 */
public class PagarYSalirController extends Controller implements ActionListener{
    PagarYSalir vista;
    Usuario usuario;
    Vehiculo vehiculo;
    private boolean vistaInicializada = false;

    public PagarYSalirController(PagarYSalir vista, Usuario usuario) {
        this.vista = vista;
        this.usuario = usuario;
        // Elementos
        this.vista.btnPagar.addActionListener(this);
        // Metodos
        cargarVehiculosCombo(usuario);
    }

    @Override
     public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnPagar){
            terminarParqueo();
        }
        
    }
     
    public void cargarVehiculosCombo(Usuario usuario){
       ArrayList<Vehiculo> listaVehiculos = getModelo().buscarVehiculosUsuario(usuario);
       DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<>();
       for(Vehiculo auto : listaVehiculos){
           modelCombo.addElement(auto.getPlaca());
       }
       vista.comboAuto.setModel(modelCombo);
   }
      
    
    public void terminarParqueo(){
        String placa = (String) vista.comboAuto.getSelectedItem();
        vehiculo = new Vehiculo(placa);
        vehiculo = getModelo().buscarVehiculosPorPlaca(vehiculo);
        vehiculo.setPorPagar(false);
        
        EspacioParqueo espacio = getModelo().buscarEspacioParqueoPorVehiculo(vehiculo);
        espacio.setPlacaVehiculo(null);
        
        if(getModelo().modificarVehiculo(vehiculo)){
            System.out.println("Vehiculo " + placa + "modificado, pago por parqueo " + vehiculo.getDuracionMinutos() + " minutos.");
            if(getModelo().modificarEspacioParqueo(espacio)){
                System.out.println("Espacio " + espacio.getId() + " liberado, pago por parqueo " + vehiculo.getDuracionMinutos() + " minutos.");
            }
        }
        
    }
}
