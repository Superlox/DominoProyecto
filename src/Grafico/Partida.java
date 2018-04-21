/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import Main.PreparacionPartida;
import Main.Usuario;
import Main.userUser;
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
    
    int mposx,mposy=0;
    boolean band; 
    boolean sodf = true; /*para saber si dibuja o seleciona ficha*/
    boolean jugadarealizada=false;/*para saber cuando el jugador jugo*/
    Ficha fichadibujar;
    int rotacion = 0;
    int cantplayers;/*cantidad de jugadores en partida*/
    BufferedImage imagen;
    AffineTransform AT;
    Usuario Lista,Fin;//AQUI ESTA LA LISTA PARA LOS USUARIOS DEL JUEGO!!!!!!!
    Juego game;
    
    public Partida(Usuario Lista,Usuario Fin){
        
        this.setBackground(Color.orange);
        /*Inicializo las funciones del mouse*/
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.Lista=Lista;
        this.Fin=Fin;
        this.cantplayers=met.lenP(Lista);
        met.cargarFichas();
        
        Usuario aux=Lista;
        while(aux!=Fin){
            met.setFichas(aux);
            aux=aux.sigP;
        }
        aux=Fin;
        met.setFichas(Fin);
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

        

       
       dominosjugadores(cantplayers,Lista,draw);
      
        
        
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

                    this.fichadibujar=Lista.sigFicha;
                    int pos=1; //pos
                    if (ificha<=met.lenFU(this.fichadibujar)){//para que no sobrepase la cantidad de fichas que tiene
                        while (this.fichadibujar!=null){/*le da fichadibujar la ficha a dibujar*/
                            if (pos==ificha){
                                break;//se detiene cuando la posicion de la ficha es igual a ificha(posicion de ficha dibujada)
                            }

                            this.fichadibujar=this.fichadibujar.sigFichaUsuario;//contador y ficha se aumentan
                            pos+=1;
                        }
                        imagen=Loader.ImageLoader("/Imagenes/coloque.png");//le pone que colque la ficha porque ya seleciono
                        draw.drawImage(imagen, null,880,0);
                        
                        sodf=false; /*para que pase a dibujar*/

                    }
                    
                }

                if (ificha==9){/*pasar de jugador*/
                    Lista=Lista.sigP;
                    dominosjugadores(cantplayers,Lista, draw);
                    this.jugadarealizada=false;
                    imagen=Loader.ImageLoader("/Imagenes/selecione.png");
                    draw.drawImage(imagen, null,880,0);    //para que el siguiente jugador selecione
                }

                if (ificha==10){/*Salir del juego*/
                    
                }
                
                if (ificha==11){/*Guardar el juego*/
                    
                }


                /*aqui iria la trampa*/


            else{/*Colocar FICHA o trampa*/
                Fichacolocada aux = met.iniciocolocar;
                while (aux!=null){/*Recorre los cuadros para ubicar el domino*/
                    /*para probar el primero, ya que trabajamos con un nodo simple*/
                    if(aux.iax<=mposx && aux.dax>=mposx && aux.iay<=mposy && aux.iby>=mposy && aux.sinusar==true){ //verifica donde quiere colocar la ficha y que no se haya usado
                        band=false;
                        band=met.direccion(aux,ificha,draw,this.fichadibujar,this,AT);//regresa true si si corresponde

                        if (band){
                            Ficha Iniciofu= Lista.sigFicha;//borra la ficha que dibujo del usuario
                            if (Iniciofu==this.fichadibujar){//primera ficha
                                Iniciofu=Iniciofu.sigFichaUsuario;
                                Iniciofu.antFichaUsuario=null;
                            }
                            Ficha ficha=Iniciofu.sigFichaUsuario;
                            while(ficha!=null){

                                if(ficha.sigFichaUsuario==null && ficha.equals(this.fichadibujar)){ //significa que es la ultima ficha
                                    ficha=null;
                                    break;
                                }
                                if(ficha==this.fichadibujar){//borrar ficha del enmedio
                                    ficha.antFichaUsuario.sigFichaUsuario=ficha.sigFichaUsuario;
                                    ficha.sigFichaUsuario.antFichaUsuario=ficha.antFichaUsuario;
                                    break;
                                }
                                ficha=ficha.sigFichaUsuario;
                            }
                            met.print(met.iniciocolocar,this.fichadibujar);
                            this.fichadibujar=null;
                            this.sodf=true; /*ya termino de colocar la ficha*/
                            this.jugadarealizada=true; /*hace que vean  que ya jugo*/
                            imagen=Loader.ImageLoader("/Imagenes/jugo.png");
                            draw.drawImage(imagen, null,880,0);//muestra que ya realizo la jugada.
                            dominosjugadores(cantplayers, Lista, draw);
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
        
    public void dominosjugadores(int cantplayers,Usuario Lista,Graphics2D draw){/*Dibuja los cuadros y las fichas de cada jugador incluyendo el del turno*/
        draw.setColor(Color.yellow);
        draw.fillRect(1261,100,104,400); /*Cuadro del U1*/
        draw.fillRect(430,0,450,100);  /*Cuadro del U2*/
        draw.fillRect(0,100,91,400);  /*Cuadro del U3*/
        draw.fillRect(430,580,600,120);
        

        
        if (cantplayers>=1){
            draw.setColor(Color.BLACK);
            draw.drawString(Lista.getNombre(), 650, 595);
            imprimirfichas(Lista.sigFicha,draw);
            /*Falta meter lo de imprimir las fichas...*/
        
        }
        if (cantplayers>=2){
        draw.setColor(Color.BLACK);
        draw.drawString(Lista.sigP.getNombre(), 1280, 115); /*Escribe el nombre*/
        this.fichasnegras(1265,140,70,30,0,40,draw,Lista.sigP.sigFicha);/*Dibuja las fichas*/
                         /*x,y,ancho,largo,incx,incy,draw*/
        }
        if (cantplayers>=3){
        draw.setColor(Color.BLACK);
        draw.drawString(Lista.sigP.sigP.getNombre(), 650, 22);
        this.fichasnegras(500,30, 30, 70, 40, 0, draw,Lista.sigP.sigP.sigFicha);
        }
        if (cantplayers==4){
        draw.setColor(Color.BLACK);
        draw.drawString(Lista.sigP.sigP.sigP.getNombre(), 25, 115);
        this.fichasnegras(20,140,70,30,0,40,draw,Lista.sigP.sigP.sigP.sigFicha);
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
        while (cont<9){ /*Dibuja grises las faltantes.*/
            draw.setColor(Color.GRAY);
            draw.drawRect(posx, posy, 30, 60);
            posx+=40;
            cont+=1;
        }
    }
}
