/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.controlador;

import parqueocs.modelo.Consultas;

/**
 *
 * @author minio
 */
public abstract class Controller {
    Consultas modelo = new Consultas();
    
    

    abstract void initController();
}
