/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueocs;

import parqueocs.vista.Bienvenido;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import parqueocs.controlador.BienvenidoController;

/**
 *
 * @author minio
 */
public class ParqueoCS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Bienvenido vistaBienvenido= new Bienvenido();
        new BienvenidoController(vistaBienvenido);
        vistaBienvenido.setVisible(true);
                

        
//        // crear 2 horas
//        LocalTime horaActual = LocalTime.now();
//        LocalTime horaDespues = LocalTime.of(19, 1, 0);
//        
//        // comparar 2 horas
//        Duration diferencia = Duration.between(horaActual, horaDespues);
//        
//        System.out.println("Hora actual: " + horaActual.getHour() + ":" + horaActual.getMinute());
//        System.out.println("Hora despues: " + horaDespues);
//        System.out.println("Minutos diferencia: " + (diferencia.getSeconds() / 60));
    }
    
}
