/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueocs.vista;

import java.awt.Color;

/**
 *
 * @author minio
 */
public class ColoresInterfaz {
    private Color colorPrimario;
    private Color colorSecundario;
    private Color colorAcento;
    private Color colorFondo;
    private Color colorTexto;
    private Color colorBordes;
    public ColoresInterfaz() {
        this.colorPrimario = Color.decode("#F2CB05");
        this.colorSecundario = Color.decode("#2C3E50");
        this.colorAcento = Color.decode("#27AE60");
        this.colorFondo = Color.decode("#F8F9F9");
        this.colorTexto = Color.decode("#2C3E50");
        this.colorBordes = Color.decode("#BDC3C7");
    }

    public Color getColorPrimario() {
        return colorPrimario;
    }

    public void setColorPrimario(Color colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public Color getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(Color colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    public Color getColorAcento() {
        return colorAcento;
    }

    public void setColorAcento(Color colorAcento) {
        this.colorAcento = colorAcento;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

    public Color getColorBordes() {
        return colorBordes;
    }

    public void setColorBordes(Color colorBordes) {
        this.colorBordes = colorBordes;
    }
    
    
}
