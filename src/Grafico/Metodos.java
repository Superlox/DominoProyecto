/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;
import Main.Usuario;
import Main.metodos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author marco
 */
public class Metodos {
    public Ficha inicioF,finF;
    public static BufferedImage imp;
    Random random = new Random();
    
    public void darfichas(Usuario U){/*Da 8 Fichas a Usuario*/
        for (int i =0; i<8; i++) {
            System.out.println("pepe");
            Ficha aux=this.inicioF;
            int pos = 0;
            System.out.println("hola");
            int numero = random.nextInt(this.lenF(this.inicioF)); /*Genera aleatoriamenete entre 0 a total de fichas*/
            System.out.println(numero);
            while (pos!=numero){
                pos+=1;
                aux=aux.sigF;
            }
            U.setInicioF(aux);
            if(numero==0){
                this.inicioF=this.inicioF.sigF;
                this.inicioF.antF=null;
            }
            if (numero==this.lenF(this.inicioF)-1){
                this.finF=this.finF.antF;
                this.finF.sigF=null;
            }
            if (numero!=0 || numero !=this.lenF(this.inicioF)-1){
                aux.sigF.antF=aux.antF;
                aux.antF.sigF=aux.sigF;
            }
            

        }
    }
    
    public void cargarFichas(){ /*crea todas las 28 fichas con su respectiva imagen extrayendola.*/
        int cont1=0;
        int cont2=0;
        for(int i=0; i<28;i++){
            if (cont2<6){
                if(inicioF==null){
                    imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                    Ficha f = new Ficha(cont1,cont2,0,0,imp);
                    cont2+=1;
                    inicioF=f;
                    finF=f;
                    System.out.println(inicioF.getValor1() +"-"+ inicioF.getValor2());
                }
                else{
                    imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                    Ficha f = new Ficha(cont1,cont2,0,0,imp);
                    cont2+=1;
                    f.sigF=inicioF;
                    inicioF.antF=f;
                    inicioF=f;
                    System.out.println(inicioF.getValor1()+"-"+ inicioF.getValor2());
                }

                 
            }
            else{
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont2,0,0,imp);
                cont1+=1;
                cont2=cont1;
                f.sigF=inicioF;
                inicioF.antF=f;
                inicioF=f;
                System.out.println(inicioF.getValor1() +"-"+ inicioF.getValor2());
            }
            
       }
    
    }
    
    public int lenF(Ficha ficha){ /*retorna el tamano de la lista*/
        Ficha aux= ficha;
        int cont=0;
        while (aux!=null){
            aux=aux.sigF;
            cont+=1;
        }
        return cont;
    }
    public void selecionarficha(int x ,int y){/*estaran todas las funciones de verificancion y turno*/

    }
    public boolean vcuadro(int x,int y){/*Verifica si seleciona dentro del cuadro a dibujar*/
        if(x<1261 && x>91 && y<580 && y>100){
            return true;
        }
        return false;
    }
    
    public int vselectf(int x,int y){/*verifica si seleciona ficha*/
        int x2=510;
        int y2=600;

        int indice=1;
        while (indice<=8){
            if (x2<=x && x2+27>=x && y2<=y && y2+58>=y) {
            return indice;
            }
            indice+=1;
            x2+=40; /*espacio entre cada ficha*/
        }
        /*aqui va la condiccion para la trampa*/
        return 0;
    }
    
   /*public void cargarFichas2(){
        int cont1=0;
        int cont2=0;
        int cont3=1;
        int cont4=2;
        int cont5=3;
        int cont6=4;
        int cont7=5;
        for(int i=0; i<28;i++){
            if(i<7){
                
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont2,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont2+=1;
                
            }
            else if (i<13){
                cont1=1;
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont3,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont3+=1;
        }
            else if(i<18){
                cont1=2;
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont4,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont4+=1;
            }
            else if(i<22){
                cont1=3;
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont5,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont5+=1;
            }
            else if(i<25){
                cont1=4;
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont6,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont6+=1;
            }
            else if(i<27){
                cont1=5;
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont7,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
                cont7+=1;
            }
            else{
                cont1=6;
                imp=Loader.ImageLoader("/Imagenes/"+(27)+".png");
                Ficha f = new Ficha(cont1,cont1,0,0,imp);
                System.out.println(f.getValor1()+"-"+f.getValor2());
                agregarFichas(f);
            }
            
        }}
    public void agregarFichas(Ficha f){
        Ficha aux=inicioF;
        if(aux==null){
            inicioF=finF=f;
        }
        else{
            f.sigF=inicioF;
            inicioF.antF=f;
            inicioF=f;
        }
    }*/
    

}
