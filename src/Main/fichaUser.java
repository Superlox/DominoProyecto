/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.image.BufferedImage;

/**
 *
 * @author marco
 */
public class fichaUser extends Grafico.Ficha {
    public fichaUser sigFI;
    
    public fichaUser(int valor1, int valor2, int pertenece, int trampa, BufferedImage imagen) {
        super(valor1, valor2,pertenece, trampa, imagen);
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    public int getPertenece() {
        return pertenece;
    }

    public void setPertenece(int pertenece) {
        this.pertenece = pertenece;
    }
    
}
