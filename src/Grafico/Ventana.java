/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author marco
 */
public class Ventana extends JFrame implements Runnable{
    Metodos met=new Metodos();
    static final int width=1365,height=720;
    Canvas canvas;
    BufferStrategy bS;
    Graphics g;
    private Thread thread;
    private boolean running = false;
    
    public Ventana(){
        setTitle("Que mira compa");
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        canvas=new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(true);
        add(canvas);
    
        setVisible(true);
    
        met.cargarFichas();
        start();
        
    
    }
    public static void main(String[] args){
        
        new Ventana().start();
        
    }
    
    public void update(){
        
    }
    
    private void draw(){
        bS = canvas.getBufferStrategy();
        
        if (bS == null){
            canvas.createBufferStrategy(3);
            return;
        }
        
        g = bS.getDrawGraphics();
        //Aqui para abajo se dibuja
       Ficha aux=met.inicioF;
       int cont=0;
       int cont2=0;
       while(aux!=null){
       
           g.drawImage(aux.imagen,cont,cont2,null);
               cont+=29;
               aux=aux.sigF;
       }
       ///Termina Dibujo        
        g.dispose();
        bS.show();
    }
    
    private void start(){
        thread = new Thread(this);
        thread.start();
        running=true;
    }
   
    private void stop(){
        try {
            thread.join();
            running=false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(running){
            //lo que hara la ventana
            update();
            draw();
        }
        stop();
    }
    
    
}
