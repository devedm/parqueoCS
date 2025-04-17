/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import parqueocs.modelo.Consultas;

/**
 *
 * @author Eddy Mena Lopez
 */
public class Controller {
    private Consultas modelo;

    public Controller() {
        this.modelo = new Consultas();
    }

    public Consultas getModelo() {
        return modelo;
    }

    public void setModelo(Consultas modelo) {
        this.modelo = modelo;
    }
    
}
