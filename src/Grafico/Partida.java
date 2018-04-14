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
    Fichacolocada iniciocolocar= new Fichacolocada(660,310,660,368,687,310);
    int rotacion = 0;
    BufferedImage imagen;
    
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
            draw.setColor(Color.GREEN); /*Dibuja donde debe poner la ficha*/
            draw.fillRect(660,310,27,58);
            
            imagen=Loader.ImageLoader("/Imagenes/siguiente.png");
            draw.drawImage(imagen,null, 1050, 10);
            imagen=Loader.ImageLoader("/Imagenes/salir.png"); /*Dibuja los botones salir y siguiente*/
            draw.drawImage(imagen,null,10,20);
        
        /*Quemar datos*/
        u4.sig=inicio;
        u3.sig=u4;
        u2.sig=u3;
        inicio.sig=u2;
        
        met.cargarFichas();
        JOptionPane.showMessageDialog(null, "Es el turno del jugador: "+inicio.nombre);
        dominosjugadores(4,inicio,draw);
                
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        mposx=me.getX();
        mposy=me.getY();
        
        Graphics g=getGraphics();
        Graphics2D draw=(Graphics2D) g;
        int ificha=met.vselectf(mposx,mposy);/*da un indice para buscar la ficha*/
        
        if (sodf){/*seleccionar ficha*/
            if (ificha==0 && this.jugadarealizada==false){/*retornara 0 si seleciona un espacio donde no hay nada*/
                JOptionPane.showMessageDialog(null, "Selecione ficha o trampa");        
            }
            if (ificha>0 && ificha<9 && this.jugadarealizada==false){
                this.fichadibujar=met.inicioF;/*dato quemado, cambiar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
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
            if (ificha==9){/*pasar de jugador*/
                inicio=inicio.sig;
                this.jugadarealizada=false;
                JOptionPane.showMessageDialog(null, "Es el turno del jugador: "+inicio.nombre);
                
                
                
            }
            if (ificha==10){/*Salir del juego*/
                
            }
            
            
            /*aqui iria la trampa*/
            if (this.jugadarealizada){
                JOptionPane.showMessageDialog(null, "Ya realizó su jugada. Dele a Pasar turno para continuar...");
            }
        }
        else{/*Colocar FICHA o trampa*/
            boolean cuadro=true;
            while (iniciocolocar!=null){/*Recorre los cuadros para ubicar el domino*/
                if(iniciocolocar.iax<=mposx && iniciocolocar.dax>=mposx && iniciocolocar.iay<=mposy && iniciocolocar.iby>=mposy){ /*verifica donde quiere colocar la ficha*/
                        if(iniciocolocar.numero==7){
                            draw.drawImage(this.fichadibujar.imagen,null,iniciocolocar.iax,iniciocolocar.iay);
                            Fichacolocada aux2 = new Fichacolocada(iniciocolocar.iax,iniciocolocar.iay-60,iniciocolocar.ibx,iniciocolocar.iby-60,iniciocolocar.dax,iniciocolocar.day-60);
                            Fichacolocada aux1 = new Fichacolocada(iniciocolocar.iax,iniciocolocar.iay+60,iniciocolocar.ibx,iniciocolocar.iby+60,iniciocolocar.dax,iniciocolocar.day+60);
                            aux1.setNumero(this.fichadibujar.valor1);
                            aux2.setNumero(this.fichadibujar.valor2);
                            aux1.sigFc=aux2;
                            iniciocolocar=aux1;
                            draw.setColor(Color.GREEN);
                            draw.fillRect(aux1.iax, aux1.iay, 27, 58);
                            draw.fillRect(aux2.iax, aux2.iay, 27, 58); /*coloca los cuadros verdes para selecionar*/
                            
                            this.fichadibujar=null;
                            sodf=true; /*ya termino de colocar la ficha*/
                            this.jugadarealizada=true; /*hace que vean  que ya jugo*/
                            cuadro=false; /*para dar por hecho que coloco en el espacio verde*/
                            break;
                        }
                        /*Este sera cuando son iguales crear 2 caminos y girar la ficha*/
                        if (iniciocolocar.numero==this.fichadibujar.valor1 && iniciocolocar.numero==this.fichadibujar.valor2){
                            draw.rotate(Math.toRadians(rotacion));
                            draw.drawImage(this.fichadibujar.imagen,null,iniciocolocar.ibx+4,iniciocolocar.iby);
                            
                            break;
                        }
                        if (iniciocolocar.numero==this.fichadibujar.valor2 ){
                            
                        }
                        
                }
                iniciocolocar=iniciocolocar.sigFc;
            }
            if (cuadro)
                JOptionPane.showMessageDialog(null, "Coloca la ficha en el espacio verde correcto");
           
                
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
