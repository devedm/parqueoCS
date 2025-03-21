/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parqueocs;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

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
        Bienvenido principal= new Bienvenido();
//        Principal principal= new Principal();
        principal.setVisible(true);
                

        
        // crear 2 horas
        LocalTime horaActual = LocalTime.now();
        LocalTime horaDespues = LocalTime.of(19, 1, 0);
        
        // comparar 2 horas
        Duration diferencia = Duration.between(horaActual, horaDespues);
        
        System.out.println("Hora actual: " + horaActual.getHour() + ":" + horaActual.getMinute());
        System.out.println("Hora despues: " + horaDespues);
        System.out.println("Minutos diferencia: " + (diferencia.getSeconds() / 60));
    }
    
}
