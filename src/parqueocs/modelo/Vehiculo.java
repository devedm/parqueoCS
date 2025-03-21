/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private long duracionMinutos;
    private int espacioParqueo;

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.fecha = null;
        this.entradaHora = null;
        this.salidaHora = null;
        this.duracionMinutos = 0;
        this.espacioParqueo = 0;
    }

    void calcularTiempo(){
        if(fecha != null & entradaHora != null & salidaHora != null){
            
        }
    }

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

    public long getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(long duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public int getEspacioParqueo() {
        return espacioParqueo;
    }

    public void setEspacioParqueo(int espacioParqueo) {
        this.espacioParqueo = espacioParqueo;
    }
    
}
