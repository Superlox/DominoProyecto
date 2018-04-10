/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.Canvas;
import static Grafico.Assets.List;
import static Grafico.Assets.imp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import Main.Usuario;
import Main.fichaUser;

/**
 *
 * @author marco
 */
public class Metodos {
    Ficha inicioF,finF;
    
    
    public void cargarFichas(){
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
    public void setFichas(){
        Main.Usuario aux= Main.Login.auxUser;
        int cont=0;
        Ficha aux2=inicioF;
        fichaUser aux3;
        while(cont <=6){
            if(aux.sigFU!=null){
                aux3=new fichaUser(aux2.getValor1(),aux2.getValor2(),aux2.pertenece,aux2.trampa,aux2.imagen);
                aux.sigFU=aux3;
                aux2=aux2.sigF;
            }
            else{
                aux3=new fichaUser(aux2.getValor1(),aux2.getValor2(),aux2.pertenece,aux2.trampa,aux2.imagen);
                aux3.sigFI=aux.sigFU;
                aux.sigFU=aux3;
                aux2=aux2.sigF;
            }
            cont++;
            }
            fichaUser auximp=aux.sigFU;
            imprimirFichaUsuario(auximp);
            
        }
    public void imprimirFichaUsuario(fichaUser aux){
        while(aux!=null){
            System.out.println(aux.valor1+"--"+aux.valor2);
            aux=aux.sigFI;
        }
    }
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
    


