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
    private String placaVehiculo;
    private int idParqueo;

    
    public EspacioParqueo() {
        this.idParqueo = 0;
    }
    
    public EspacioParqueo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
        this.idParqueo = 0;
    }

    public EspacioParqueo(int id, String placaVehiculo, int idParqueo) {
        this.id = id;
        this.placaVehiculo = placaVehiculo;
        this.idParqueo = idParqueo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParqueo() {
        return idParqueo;
    }

    public void setIdParqueo(int idParqueo) {
        this.idParqueo = idParqueo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    
    
}
