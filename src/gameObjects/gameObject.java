/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.image.BufferedImage;
import math.Vectores;

/**
 *
 * @author marco
 */
public abstract class gameObject {
    protected BufferedImage textura;
    protected Vectores posicion;
    
    public gameObject(BufferedImage textura,Vectores posicion){
        this.textura=textura;
        this.posicion=posicion;
    }
    
    public abstract void update();
    
    public abstract void Dibujar();

    public Vectores getPosicion() {
        return posicion;
    }

    public void setPosicion(Vectores posicion) {
        this.posicion = posicion;
    }
    
    
}
