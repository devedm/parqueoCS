/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.modelo;

import java.util.*;

/**
 *
 * @author Eddy Mena Lopez
 */
public class Usuario {
    private int cedula;
    private String nombre;
    private String contrasenia;
    private ArrayList<Vehiculo> listaVehiculos;
    
    public Usuario() {
        this.cedula = 000000000;
        this.nombre = "";
        this.contrasenia = "";
        this.listaVehiculos = new ArrayList<Vehiculo>();
    }
    
    public Usuario(int cedula, String nombre, String contrasenia, ArrayList<Vehiculo> listaVehiculos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.listaVehiculos = listaVehiculos;
    }
    
    public Usuario(int cedula, String nombre, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }
            
    public Usuario(int cedula) {
        this.cedula = cedula;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }
  
    
}
