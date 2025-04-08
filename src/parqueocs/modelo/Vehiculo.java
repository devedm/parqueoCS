package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vehiculo {
    
    private String placa;
    private LocalDate fecha;
    private LocalTime entradaHora;
    private LocalTime salidaHora;
    private int duracionMinutos;
    private Propietario propietario;

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

    public Vehiculo(String placa, LocalDate fecha, LocalTime entradaHora, LocalTime salidaHora, int duracionMinutos, Propietario propietario) {
        this.placa = placa;
        this.fecha = fecha;
        this.entradaHora = entradaHora;
        this.salidaHora = salidaHora;
        this.duracionMinutos = duracionMinutos;
        this.propietario = propietario;
    }

    void calcularTiempo() {
        if (fecha != null & entradaHora != null & salidaHora != null) {
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

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}

