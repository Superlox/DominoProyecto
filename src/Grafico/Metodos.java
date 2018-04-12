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
        int cont=0; /*Va a contar cada ficha agregada*/
        while (cont<8) {
            Ficha aux=inicioF;
            int pos = 0;
            int numero = random.nextInt(this.lenF(inicioF)-1); /*Genera aleatoriamenete entre 0 a total de fichas*/
            System.out.println(numero);
            while (aux!=null) {
                if(pos==numero){
                    U.setInicioF(aux); /*agrega ficha a lista de fichas de aux*/
                    if (aux.antF==null){ /*En caso de ser el primero*/
                        aux=aux.sigF;
                        aux.antF=null;
                        inicioF=aux;
                        break;
                    }
                    else if (aux.sigF==null){ /*En caso de ser el ultimo*/
                        aux=aux.antF;
                        aux.sigF=null;
                        aux=finF;
                        break;
                    }
                    aux.antF.sigF=aux.sigF; /*Elmino el aux de la lista de Fichas generadas*/
                    aux.sigF.antF=aux.antF; /**/
                    break; 
                }
                aux=aux.sigF;
                pos+=1;
            }
            cont+=1;
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
