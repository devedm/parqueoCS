/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import parqueocs.modelo.Consultas;
import parqueocs.modelo.Vehiculo;
import parqueocs.vista.Bienvenido;
import parqueocs.vista.Calendario;
import parqueocs.vista.ModificarParqueo;
import parqueocs.vista.Parquear;

/**
 *
 * @author minio
 */
public class CalendarioController implements ActionListener{
    private final Calendario vista;
    private Parquear vistaParquear;
    private ModificarParqueo vistaModificarParqueo;

    public CalendarioController(Calendario vista, Parquear vistaParquear) {
        this.vista = vista;
        this.vistaParquear = vistaParquear;
        llenarMes();
        llenarAnio();
        llenarDias();
        vista.comboMes.addActionListener(this);
        vista.comboAnio.addActionListener(this);
        vista.tablaDias.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                seleccionarFechaParquear();
            }
        });
        
    }
    
    public CalendarioController(Calendario vista, ModificarParqueo vistaModificarParqueo) {
        this.vista = vista;
        this.vistaModificarParqueo = vistaModificarParqueo;
        llenarMes();
        llenarAnio();
        llenarDias();
        vista.comboMes.addActionListener(this);
        vista.comboAnio.addActionListener(this);
        vista.tablaDias.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                seleccionarFechaModificar();
            }
        });
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.comboMes || e.getSource() == vista.comboAnio){
            llenarDias();
        }
        if(e.getSource() == vista.tablaDias){
            if(vistaModificarParqueo == null){
                seleccionarFechaParquear();
            } else {
                seleccionarFechaModificar();
            }
            
        }
    }
    
    
    
    public void llenarMes(){
        String[] listaMeses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>(listaMeses);
        vista.comboMes.setModel(myModel);
        
        LocalDate fechaActual = LocalDate.now();
        vista.comboMes.setSelectedIndex(fechaActual.getMonthValue() - 1);
    }
    
    public void llenarAnio(){
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>();
        for (int i = 2025; i <= 2035; i++) {
            myModel.addElement(String.valueOf(i));
        }
        vista.comboAnio.setModel(myModel);
        
        LocalDate fechaActual = LocalDate.now();
        vista.comboAnio.setSelectedItem(String.valueOf(fechaActual.getYear()));
    }
    
    public void llenarDias(){
        int mes = vista.comboMes.getSelectedIndex() + 1; // Enero = 1, ... Diciembre = 12
        int anio = Integer.parseInt((String) vista.comboAnio.getSelectedItem());
        
        // Informacion del mes
        LocalDate fecha = LocalDate.of(anio, mes, 1);
        int diasDelMes = fecha.lengthOfMonth();
        int primerDiaDeLaSemana = fecha.getDayOfWeek().getValue() % 7;
        
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"D", "L", "M", "X", "J", "V", "S"},0);
        int dia = 1;
        for (int fila = 0; fila < 6; fila++){ // maximo 6 semanas
            Object[] semana = new Object[7];
            for(int col = 0; col < 7; col++){
                if( fila == 0 && col < primerDiaDeLaSemana){
                    semana[col] = ""; // espacios antes del 0
                } else if (dia <= diasDelMes) {
                    semana[col] = dia; // dias del mes
                    dia++;
                } else {
                    semana[col] = ""; // espacios vacios despes del mes
                }
            }
            modelo.addRow(semana);
        }
        vista.tablaDias.setModel(modelo);
    }
    
    public void seleccionarFechaParquear(){
        int mes = vista.comboMes.getSelectedIndex() + 1; // Enero = 1, ... Diciembre = 12
        int anio = Integer.parseInt((String) vista.comboAnio.getSelectedItem());
        
        int col = vista.tablaDias.getSelectedColumn();
        int row = vista.tablaDias.getSelectedRow();
        int dia = (int) vista.tablaDias.getValueAt(row, col);
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vistaParquear.fieldFecha.setText(fecha.format(formato));
        vista.dispose();
    }   
    
    public void seleccionarFechaModificar(){
        int mes = vista.comboMes.getSelectedIndex() + 1; // Enero = 1, ... Diciembre = 12
        int anio = Integer.parseInt((String) vista.comboAnio.getSelectedItem());
        
        int col = vista.tablaDias.getSelectedColumn();
        int row = vista.tablaDias.getSelectedRow();
        int dia = (int) vista.tablaDias.getValueAt(row, col);
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vistaModificarParqueo.fieldFecha.setText(fecha.format(formato));
        vista.dispose();
    } 
}

