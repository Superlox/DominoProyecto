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
    Dibujo canvas = new Dibujo(); 
    BufferStrategy bS;
    Graphics g;
    /*CANVAS*/
    
    public Juego(){
        setTitle("DOMINO");
        setSize(width,height); /*Tamano de la ventana*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Permite cerrar la ventana con la X*/
        add(canvas); /*Se agrega el canvas en este caso DIBUJO*/
        setVisible(true); /*Hace Visible la Ventana*/
        
        
    }

    
    public void update(){
        
    }
    
    public void draw(){ /*Pinta*/
        /*crea el buffer*/
        bS = canvas.getBufferStrategy();
        
        if (bS == null){/*Le da un valor a buffer*/
            canvas.createBufferStrategy(3);
            return;
        }
        g = bS.getDrawGraphics();/*Le da el buffer a graphics*/
        

        //Aqui para abajo se dibuja
       Ficha aux=met.inicioF;
       int x=0;
       int y=0;
       while(aux!=null){ /*dibuja todas las fichas*/
           g.drawImage(aux.imagen,x,y,null);
               x+=29;
               aux=aux.sigF;
       }
       
       
       
       ///Termina Dibujo        
        g.dispose();
        bS.show();
    }
    

    
}
