/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author minio
 */
public class Vehiculo {
    
    private String placa;
    private LocalDate fecha;
    private LocalTime entradaHora;
    private LocalTime salidaHora;
    private int duracionMinutos;

    public Vehiculo(String placa) {
        this.placa = placa;
        this.fecha = LocalDate.now();
        this.entradaHora = LocalTime.now();
        this.salidaHora = LocalTime.now();
        this.duracionMinutos = 0;
    }

    public Vehiculo(String placa, LocalDate fecha, LocalTime entradaHora, LocalTime salidaHora, int duracionMinutos) {
        this.placa = placa;
        this.fecha = fecha;
        this.entradaHora = entradaHora;
        this.salidaHora = salidaHora;
        this.duracionMinutos = duracionMinutos;
    }

    void calcularTiempo(){
        if(fecha != null & entradaHora != null & salidaHora != null){
            
        }
    }
    
//    public Date asDate(LocalDate localDate) {
//        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    public Date asDate(LocalDateTime localDateTime) {
//        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    public LocalDate asLocalDate(Date date) {
//        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//    }
//
//    public LocalDateTime asLocalDateTime(Date date) {
//        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
//    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getEntradaHora() {
        return entradaHora;
    }

    public void setEntradaHora(LocalTime entradaHora) {
        this.entradaHora = entradaHora;
    }

    public LocalTime getSalidaHora() {
        return salidaHora;
    }

    public void setSalidaHora(LocalTime salidaHora) {
        this.salidaHora = salidaHora;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    
}
