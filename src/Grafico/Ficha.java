/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.image.BufferedImage;

/**
 *
 * @author marco
 */
public class Ficha {
    private int valor1;
    private int valor2;
    private int pertenece;
    private int trampa;
    BufferedImage imagen;
    Ficha sigF,antF;

    public Ficha(int valor1, int valor2, int pertenece, int trampa, BufferedImage imagen) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.pertenece = pertenece;
        this.trampa = trampa;
        this.imagen = imagen;
        this.sigF = null;
        this.antF = null;
    }

    public int getValor1() {
        return valor1;
    }

    public int getValor2() {
        return valor2;
    }
    
    
}
