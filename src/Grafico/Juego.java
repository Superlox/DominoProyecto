/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author ADMIN
 */
public class Juego extends JFrame {
    
    Metodos met=new Metodos(); /*Todos los metods*/
    static final int width=1365,height=720; /*Tamano de la ventana*/
    /*Canvas y los grafics para dibujar*/
    Partida canvas = new Partida(); 

    
    public Juego(){
        setTitle("DOMINO");
        setSize(width,height); /*Tamano de la ventana*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Permite cerrar la ventana con la X*/
        add(canvas); /*Se agrega el canvas en este caso DIBUJO*/
        setVisible(true); /*Hace Visible la Ventana*/
        
        
    }

    
   
    

    
}
