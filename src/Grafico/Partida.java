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
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Partida extends Canvas implements MouseListener, MouseMotionListener {
    Metodos met = new Metodos();
    Usuario u1 = new Usuario("Angel","hola","gg");
    Usuario u2 = new Usuario("Pedro","hola","gg");
    Usuario u3 = new Usuario("Marco","hola","gg");
    Usuario u4 = new Usuario("NUEVO","hola","gg");
    Usuario inicio = u1;
    int mposx,mposy=0;
    boolean band = true;
    boolean sodf = true;
    Ficha fichadibujar;
    
    public Partida(){
        
        this.setBackground(Color.orange);
        /*Inicializo las funciones del mouse*/
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D draw = (Graphics2D) g;
        super.paint(g);
        /*draw.setColor(Color.GREEN);
        draw.drawRect(30,10,40,100);/*Solo dibuja la linea del cuadrado*/
        /*   
        draw.setColor(Color.orange);
        draw.drawString("Hola Mundo", 200, 100);/*Imprime hola mundo esa pos*/
        
            draw.setColor(Color.white); /*Elige un color */
            draw.fillRect(91,100,1170,480);/*Rellena un rectangulo y lo ubica*/

        /*Quemar datos*/
        u4.sig=inicio;
        u3.sig=u4;
        u2.sig=u3;
        inicio.sig=u2;
        met.cargarFichas();

        dominosjugadores(4,inicio,draw);
                
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mposx=me.getX();
        mposy=me.getY();
        
        Graphics g=getGraphics();
        Graphics2D draw=(Graphics2D) g;
        
        if (sodf){/*seleccionar ficha*/
            int ificha=met.vselectf(mposx,mposy);/*da un indice para buscar la ficha*/
            System.out.println(ificha);
            if (ificha==0){/*retornara 0 si seleciona un espacio donde no hay nada*/
                JOptionPane.showMessageDialog(null, "Selecione ficha o trampa");        
            }
            if (ificha>0 && ificha<9){
                this.fichadibujar=met.inicioF;/*dato quemado DEBO HACER UNA FUNCION YA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
                int cont=1;
                while (this.fichadibujar!=null){/*le da fichadibujar la ficha a dibujar*/
                    if (cont==ificha){
                        break;
                    }
                    this.fichadibujar=this.fichadibujar.sigF;
                    cont+=1;
                }
                sodf=false; /*para que pase a dibujar*/
            }
            /*aqui iria la trampa*/
        }
        else{/*Dibujar FICHA*/
            boolean cuadro = met.vcuadro(mposx,mposy);
            if (cuadro){ /*Seleciono dentro del cuadro*/
                draw.drawImage(this.fichadibujar.imagen, null, mposx, mposy);
                this.fichadibujar=null;
                sodf=true;
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Para poner ficha seleciona dentro de la mesa(Cuadro Blanco)");
                
            }
            
        }
        

            
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        
    public void dominosjugadores(int cantplayers,Usuario Inicio,Graphics2D draw){/*Dibuja los cuadros y las fichas de cada jugador incluyendo el del turno*/
        draw.setColor(Color.yellow);
        draw.fillRect(1261,100,104,400); /*Cuadro del U1*/
        draw.fillRect(430,0,450,100);  /*Cuadro del U2*/
        draw.fillRect(0,100,91,400);  /*Cuadro del U3*/
        draw.fillRect(0,580,1365,120);
        
        if (cantplayers>=1){
            draw.setColor(Color.BLACK);
            draw.drawString(Inicio.getNombre(), 650, 595);
            imprimirfichas(met.inicioF,draw);
            /*Falta meter lo de imprimir las fichas...*/
        
        }
        if (cantplayers>=2){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.getNombre(), 1280, 115); /*Escribe el nombre*/
        this.fichasnegras(1265,140,70,30,0,40,draw);/*Dibuja las fichas*/
                         /*x,y,ancho,largo,incx,incy,draw*/
        }
        if (cantplayers>=3){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.sig.getNombre(), 650, 22);
        this.fichasnegras(500,30, 30, 70, 40, 0, draw);
        }
        if (cantplayers==4){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.sig.sig.getNombre(), 25, 115);
        this.fichasnegras(20,140,70,30,0,40,draw);
        }
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
    
    public void imprimirfichas(Ficha ficha,Graphics2D draw){
        
        int posx=470;
        int posy=600;
        int cont=0;
        while (ficha!=null){/*dibuja las fichas*/
            posx+=40;
            draw.drawImage(ficha.imagen, null,posx, posy);
            ficha=ficha.sigF;
            cont+=1;
        }
        while (cont<8){ /*Dibuja grises las faltantes.*/
            draw.drawRect(posx, posy, 27, 58);
        }
    }
}
