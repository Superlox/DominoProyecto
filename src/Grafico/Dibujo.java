/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import Main.Usuario;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;

/**
 *
 * @author ADMIN
 */
public class Dibujo extends Canvas implements MouseListener, MouseMotionListener {
    private Path2D fichaj;
    Metodos met = new Metodos();
    Usuario u1 = new Usuario("Angel","hola","gg");
    Usuario u2 = new Usuario("Pedro","hola","gg");
    Usuario u3 = new Usuario("Marco","hola","gg");
    
    public Dibujo(){
        
        this.setBackground(Color.orange);
        
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D draw = (Graphics2D) g;
        super.paint(g);
        
        /*draw.setColor(Color.GREEN);
        draw.drawRect(30,10,40,100);/*Solo dibuja la linea del cuadrado*/
           
        draw.setColor(Color.orange);
        draw.drawString("Hola Mundo", 200, 100);/*Imprime hola mundo esa pos*/
        
        draw.setColor(Color.white); /*Elige un color */
        draw.fillRect(91,100,1170,500);/*Rellena un rectangulo y lo ubica*/
        /*draw.setStroke(new BasicStroke(5));
        draw.setColor(Color.black);
        draw.draw(this.fichaj);*/
        dominosjugadores(u1,u2,u3,draw);
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    public void dominosjugadores(Usuario u1,Usuario u2, Usuario u3,Graphics2D draw){
        draw.setColor(Color.yellow);
        draw.fillRect(1260,125,90,450); /*Cuadro del U1*/
        draw.fillRect(430,0,450,100);  /*Cuadro del U2*/
        draw.fillRect(0,125,90,450);  /*Cuadro del U3*/
        draw.setColor(Color.BLACK);
        met.cargarFichas();

        draw.drawString(u1.getNombre(), 1280, 155); /*Escribe el nombre*/
        this.fichasnegras(1265,200,70,30,0,40,draw);/*Dibuja las fichas*/
                         /*x,y,ancho,largo,incx,incy,draw*/
        draw.drawString(u2.getNombre(), 650, 22);
        this.fichasnegras(500,30, 30, 70, 40, 0, draw);
        
        draw.drawString(u3.getNombre(), 25, 155);
        this.fichasnegras(20,200,70,30,0,40,draw);
    }
    
    public void fichasnegras(int posx, int posy, int ancho, int largo,int incx,int incy,Graphics2D draw) {
        int cont=0;
        draw.setColor(Color.BLACK);
        while(cont<8){
            if(cont>=met.lenF(u1.getSigF()))
                draw.setColor(Color.GRAY);
            draw.fillRect(posx,posy,ancho,largo);
            posx+=incx;
            posy+=incy;
            cont+=1;
        }
        
    }
}
