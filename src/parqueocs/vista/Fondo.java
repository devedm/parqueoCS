/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author minio
 */
public class Fondo extends JPanel{

    private Image imagen;

    public Fondo() {
        // Cambia la ruta según la ubicación real de la imagen
        imagen = new ImageIcon(getClass().getResource("/parqueocs/resources/Images/fondo.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            int x = (getWidth() - imagen.getWidth(this)) / 2;
            int y = (getHeight() - imagen.getHeight(this)) / 2;
            g.drawImage(imagen, x, y, this);
        }
    }
}
