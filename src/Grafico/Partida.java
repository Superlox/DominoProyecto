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
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
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
    boolean sodf = true; /*para saber si dibuja o seleciona ficha*/
    boolean jugadarealizada=false;/*para saber cuando el jugador jugo*/
    Ficha fichadibujar;
    int rotacion = 0;
    int cantplayers=4;/*cantidad de jugadores en partida*/
    BufferedImage imagen;
    AffineTransform AT;
    
    public Partida(){
        
        this.setBackground(Color.orange);
        /*Inicializo las funciones del mouse*/
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        met.cargarFichas();

        
        
       met.setFichas(u1);
       met.setFichas(u2);
       met.setFichas(u3);
       met.setFichas(u4);
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
            draw.setColor(Color.GREEN); /*Dibuja donde debe poner la ficha*/
            draw.fillRect(660,310,30,60);
            
            imagen=Loader.ImageLoader("/Imagenes/siguiente.png");
            draw.drawImage(imagen,null, 1050, 10);
            imagen=Loader.ImageLoader("/Imagenes/salir.png"); /*Dibuja los botones salir y siguiente*/
            draw.drawImage(imagen,null,10,20);
            imagen=Loader.ImageLoader("/Imagenes/selecione.png");
            draw.drawImage(imagen, null,880,0);
            
            imagen=Loader.ImageLoader("/Imagenes/guardar.png");
            draw.drawImage(imagen, null,200,20);
            
        /*Quemar datos*/
        u4.sig=inicio;
        u3.sig=u4;
        u2.sig=u3;
        inicio.sig=u2;
        

       
       dominosjugadores(cantplayers,inicio,draw);
      
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mposx=me.getX();
        mposy=me.getY();
        
        Graphics g=getGraphics();
        Graphics2D draw=(Graphics2D) g;
        int ificha=met.vselectf(mposx,mposy);/*da un indice para buscar la ficha*/
        
        if (MouseEvent.BUTTON3==me.getButton()){
                sodf=true;
                this.fichadibujar=null;
                imagen=Loader.ImageLoader("/Imagenes/selecione.png");
                draw.drawImage(imagen, null,880,0);
            
            }

        if (MouseEvent.BUTTON1==me.getButton()){
            if (sodf){/*seleccionar ficha*/        
                }
                if (ificha>0 && ificha<9 && this.jugadarealizada==false){

                    this.fichadibujar=met.inicioF;/*dato quemado, cambiar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!AGREGAR FICHAS DE USUARIO INICIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
                    int cont=1;
                    if (ificha<=met.lenF(this.fichadibujar)){

                        while (this.fichadibujar!=null){/*le da fichadibujar la ficha a dibujar*/
                            if (cont==ificha){
                                break;
                            }

                            this.fichadibujar=this.fichadibujar.sigF;
                            cont+=1;
                        }
                        imagen=Loader.ImageLoader("/Imagenes/coloque.png");
                        draw.drawImage(imagen, null,880,0);
                        
                        sodf=false; /*para que pase a dibujar*/

                    }
                }

                if (ificha==9){/*pasar de jugador*/
                    inicio=inicio.sig;
                    dominosjugadores(cantplayers, inicio, draw);
                    this.jugadarealizada=false;
                    imagen=Loader.ImageLoader("/Imagenes/selecione.png");
                    draw.drawImage(imagen, null,880,0);    
                }

                if (ificha==10){/*Salir del juego*/

                }
                
                if (ificha==11){/*Guardar el juego*/
                    
                }


                /*aqui iria la trampa*/


            else{/*Colocar FICHA o trampa*/
                Fichacolocada aux =met.iniciocolocar;
                while (aux!=null){/*Recorre los cuadros para ubicar el domino*/
                    /*para probar el primero, ya que trabajamos con un nodo simple*/
                    if(aux.iax<=mposx && aux.dax>=mposx && aux.iay<=mposy && aux.iby>=mposy ){ /*verifica donde quiere colocar la ficha*/

                        band=met.direccion(aux,ificha,draw,this.fichadibujar,this,AT);

                        if (band){
                            this.fichadibujar=null;
                            sodf=true; /*ya termino de colocar la ficha*/
                            this.jugadarealizada=true; /*hace que vean  que ya jugo*/
                            imagen=Loader.ImageLoader("/Imagenes/jugo.png");
                            draw.drawImage(imagen, null,880,0);
                            break;
                        }


                    }

                    aux=aux.sigFc;
                }

                }
        }
    }
        
            
    
        

            
    
    
    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
          }
        
    public void dominosjugadores(int cantplayers,Usuario Inicio,Graphics2D draw){/*Dibuja los cuadros y las fichas de cada jugador incluyendo el del turno*/
        draw.setColor(Color.yellow);
        draw.fillRect(1261,100,104,400); /*Cuadro del U1*/
        draw.fillRect(430,0,450,100);  /*Cuadro del U2*/
        draw.fillRect(0,100,91,400);  /*Cuadro del U3*/
        draw.fillRect(430,580,600,120);
        

        
        if (cantplayers>=1){
            draw.setColor(Color.BLACK);
            draw.drawString(Inicio.getNombre(), 650, 595);
            imprimirfichas(inicio.sigFicha,draw);
            /*Falta meter lo de imprimir las fichas...*/
        
        }
        if (cantplayers>=2){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.getNombre(), 1280, 115); /*Escribe el nombre*/
        this.fichasnegras(1265,140,70,30,0,40,draw,Inicio.sig.sig.sigFicha);/*Dibuja las fichas*/
                         /*x,y,ancho,largo,incx,incy,draw*/
        }
        if (cantplayers>=3){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.sig.getNombre(), 650, 22);
        this.fichasnegras(500,30, 30, 70, 40, 0, draw,Inicio.sig.sig.sigFicha);
        }
        if (cantplayers==4){
        draw.setColor(Color.BLACK);
        draw.drawString(Inicio.sig.sig.sig.getNombre(), 25, 115);
        this.fichasnegras(20,140,70,30,0,40,draw,Inicio.sig.sig.sig.sigFicha);
        }
    }
    
    public void fichasnegras(int posx, int posy, int ancho, int largo,int incx,int incy,Graphics2D draw,Ficha f) {
        int cont=0;
        draw.setColor(Color.BLACK);
        while(cont<8){
            if(cont>=met.lenFU(f))
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
            ficha=ficha.sigFichaUsuario;
            cont+=1;
        }
        while (cont<8){ /*Dibuja grises las faltantes.*/
            draw.drawRect(posx, posy, 27, 58);
            cont+=1;
        }
    }
}
