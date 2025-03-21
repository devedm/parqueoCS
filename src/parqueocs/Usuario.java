/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs;

/**
 *
 * @author minio
 */
public class Usuario {
    private int cedula;
    private String nombre;
    private String contrasenia;
    private Vehiculo[] listaVehiculos;

    public Usuario(int cedula, String nombre, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.listaVehiculos = new Vehiculo[10];
    }

    public Vehiculo[] getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(Vehiculo[] listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
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
    
    
}
