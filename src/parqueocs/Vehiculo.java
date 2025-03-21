/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author minio
 */
public class Vehiculo {
    
    private String placa;
    private String tipo;
    private LocalDate fecha;
    private LocalTime entradaHora;
    private LocalTime salidaHora;
    private long duracionMinutos;
    private boolean estaParqueado;

    public Vehiculo(String placa, String tipo) {
        this.placa = placa;
        this.tipo = tipo;
        this.fecha = null;
        this.entradaHora = null;
        this.salidaHora = null;
        this.duracionMinutos = 0;
        this.estaParqueado = false;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public boolean isEstaParqueado() {
        return estaParqueado;
    }

    public void setEstaParqueado(boolean estaParqueado) {
        this.estaParqueado = estaParqueado;
    }

    void calcularTiempo(){
        if(fecha != null & entradaHora != null & salidaHora != null){
            
        }
    }
    
}
