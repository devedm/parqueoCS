/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import parqueocs.modelo.Vehiculo;

/**
 *
 * @author minio
 */
public class EspacioParqueo {
    private int id;
    private Vehiculo vehiculoParqueado;
    
    public EspacioParqueo(int id) {
        this.id = id;
        this.vehiculoParqueado = null;
    }

    public EspacioParqueo(int id, Vehiculo vehiculoParqueado) {
        this.id = id;
        this.vehiculoParqueado = vehiculoParqueado;
    }

    public Vehiculo getVehiculoParqueado() {
        return vehiculoParqueado;
    }

    public void setVehiculoParqueado(Vehiculo vehiculoParqueado) {
        this.vehiculoParqueado = vehiculoParqueado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
