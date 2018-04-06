/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import math.Vectores;

/**
 *
 * @author marco
 */
public abstract class Jugador extends gameObject {

    public Jugador(BufferedImage textura, Vectores posicion) {
        super(textura, posicion);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void Dibujar(Graphics g) {
        g.drawImage(textura, (int)posicion.getX(),(int)posicion.getY(),null);
    } 
}
