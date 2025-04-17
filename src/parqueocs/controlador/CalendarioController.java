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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import parqueocs.vista.Calendario;

/**
 *
 * @author Eddy Mena Lopez
 */
public class CalendarioController implements ActionListener{
    private final Calendario vista;
    private JFormattedTextField field;

    public CalendarioController(Calendario vista, JFormattedTextField field) {
        this.vista = vista;
        this.field = field;
        llenarMes(vista.comboMes);
        llenarAnio(vista.comboAnio);
        llenarDias(vista.tablaDias, vista.comboMes, vista.comboAnio);
        vista.comboMes.addActionListener(this);
        vista.comboAnio.addActionListener(this);
        vista.btnHoy.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        
        vista.tablaDias.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                seleccionarFecha();
            }
        });
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.comboMes || e.getSource() == vista.comboAnio){
            llenarDias(vista.tablaDias, vista.comboMes, vista.comboAnio);
        }
        if(e.getSource() == vista.tablaDias){
            seleccionarFecha();
        }
        if(e.getSource() == vista.btnHoy){
            seleccionarFechaHoy();
        }
        if(e.getSource() == vista.btnCancelar){
            exit();
        }
        
    }
    
    
    
    public void llenarMes(JComboBox combo){
        String[] listaMeses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>(listaMeses);
        combo.setModel(myModel);
        
        LocalDate fechaActual = LocalDate.now();
        combo.setSelectedIndex(fechaActual.getMonthValue() - 1);
    }
    
    public void llenarAnio(JComboBox combo){
        DefaultComboBoxModel<String> myModel = new DefaultComboBoxModel<>();
        for (int i = 2025; i <= 2035; i++) {
            myModel.addElement(String.valueOf(i));
        }
        combo.setModel(myModel);
        
        LocalDate fechaActual = LocalDate.now();
        combo.setSelectedItem(String.valueOf(fechaActual.getYear()));
    }
    
    public void llenarDias(JTable diasTabla, JComboBox mesCombo, JComboBox anioCombo){
        int mes = mesCombo.getSelectedIndex() + 1; // Enero = 1, ... Diciembre = 12
        int anio = Integer.parseInt((String) anioCombo.getSelectedItem());
        
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
        diasTabla.setModel(modelo);
    }
    
    public void seleccionarFecha(){
        int mes = vista.comboMes.getSelectedIndex() + 1; // Enero = 1, ... Diciembre = 12
        int anio = Integer.parseInt((String) vista.comboAnio.getSelectedItem());
        
        int col = vista.tablaDias.getSelectedColumn();
        int row = vista.tablaDias.getSelectedRow();
        int dia = (int) vista.tablaDias.getValueAt(row, col);
        LocalDate fecha = LocalDate.of(anio, mes, dia);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        field.setText(fecha.format(formato));
        exit();
    }   
    
    public void seleccionarFechaHoy(){
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        field.setText(hoy.format(formato));
        exit();
    } 
    
    public void exit(){
        // Cierra la vista
        vista.dispose();
    }
}

