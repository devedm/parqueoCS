package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Automovil extends Vehiculo {
    private int cantidadPuertas;

    public Automovil(String placa, LocalDate fecha, LocalTime entradaHora, LocalTime salidaHora, int duracionMinutos, Propietario propietario, int cantidadPuertas) {
        super(placa, fecha, entradaHora, salidaHora, duracionMinutos, propietario);
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }
}
