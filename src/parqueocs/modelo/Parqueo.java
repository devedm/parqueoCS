/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.util.*;

/**
 *
 * @author minio
 */
public class Parqueo {
    private int id;
    private String nombreParqueo;

    public Parqueo(String nombreParqueo) {
        this.nombreParqueo = nombreParqueo;
    }
    
    public Parqueo(int id, String nombreParqueo) {
        this.id = id;
        this.nombreParqueo = nombreParqueo;
    }

    public String getNombreParqueo() {
        return nombreParqueo;
    }

    public void setNombreParqueo(String nombreParqueo) {
        this.nombreParqueo = nombreParqueo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
