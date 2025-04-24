/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Eddy Mena Lopez
 */
public class Vehiculo {
    
    private String placa;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private LocalTime entradaHora;
    private LocalTime salidaHora;
    private int duracionMinutos;
    private boolean porPagar;

    public Vehiculo(String placa) {
        this.placa = placa.toUpperCase();
        this.fechaEntrada = LocalDate.now();
        this.fechaSalida = LocalDate.now();
        this.entradaHora = LocalTime.now();
        this.salidaHora = LocalTime.now();
        this.duracionMinutos = 0;
        this.porPagar = false;
    }

    public Vehiculo(String placa, LocalDate fechaEntrada, LocalDate fechaSalida, LocalTime entradaHora, LocalTime salidaHora, int duracionMinutos, boolean porPagar) {
        this.placa = placa.toUpperCase();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.entradaHora = entradaHora;
        this.salidaHora = salidaHora;
        this.duracionMinutos = duracionMinutos;
        this.porPagar = porPagar;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public boolean isPorPagar() {
        return porPagar;
    }

    public void setPorPagar(boolean porPagar) {
        this.porPagar = porPagar;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
}
