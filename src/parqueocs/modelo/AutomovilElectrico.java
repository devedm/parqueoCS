package parqueocs.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class AutomovilElectrico extends Automovil {
    private int capacidadBateria;

    public AutomovilElectrico(String placa, LocalDate fecha, LocalTime entradaHora, LocalTime salidaHora, int duracionMinutos, Propietario propietario, int cantidadPuertas, int capacidadBateria) {
        super(placa, fecha, entradaHora, salidaHora, duracionMinutos, propietario, cantidadPuertas);
        this.capacidadBateria = capacidadBateria;
    }

    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(int capacidadBateria) {
        this.capacidadBateria = capacidadBateria;
    }
}
