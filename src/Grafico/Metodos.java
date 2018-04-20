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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class Metodos {
    public Ficha inicioF,finF=null;
    public static BufferedImage imp;
    Random random = new Random();
    public Fichacolocada iniciocolocar= new Fichacolocada(660,310,660,368,687,310,0);

    

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
                    inicioF.antF=f;
                    f.sigF=inicioF;
                    inicioF=f;
                    System.out.println(inicioF.getValor1()+"-"+ inicioF.getValor2());
                }

                 
            }
            else{
                imp=Loader.ImageLoader("/Imagenes/"+(i)+".png");
                Ficha f = new Ficha(cont1,cont2,0,0,imp);
                cont1+=1;
                cont2=cont1;
                inicioF.antF=f;
                f.sigF=inicioF;
                inicioF=f;
                System.out.println(inicioF.getValor1() +"-"+ inicioF.getValor2());
            }
            
       }
    
    }
    public int lenFU(Ficha ficha){ /*retorna el tamano de la lista*/
        Ficha aux= ficha;
        int cont=0;
        while (aux!=null){
            aux=aux.sigFichaUsuario;
            cont+=1;
        }
        return cont;
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


    
    public int vselectf(int x,int y){/*verifica si seleciona ficha*/
        int x2=510;
        int y2=600;

        if (1050<=x && 1300>=x && 10<=y && 90>=y){/*Si selecionase SIGUIENTE*/
            return 9;
        }
        if (10<=x && 160>=x && 20<=y && 70>=y){/*Si fuese Salir*/
            return 10;
        }
        if(x>=200 && x<=350 && y>=20 && y<=70){//si fuese guardar
            return 11;
        }
        
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
public void setFichas(Usuario u){//Empieza parte para agregar fichas al usuario
Ficha aux=inicioF;
int cont=0;
while(cont <=6){
     
    if(u.sigFicha==null){
       int numero=RandomFicha(lenF(aux)-1);
       Ficha fichaEncontrada=BuscarFicha(numero);
       u.sigFicha=fichaEncontrada;
       eliminarFicha(fichaEncontrada);
       System.out.println("el random:"+numero);
    }
    else{
        int numero=RandomFicha(lenF(aux)-1);
        Ficha fichaEncontrada=BuscarFicha(numero);
        fichaEncontrada.sigFichaUsuario=u.sigFicha;
        u.sigFicha.antFichaUsuario=fichaEncontrada;
        u.sigFicha=fichaEncontrada;
        eliminarFicha(fichaEncontrada);
        System.out.println("el random:"+numero);
    }

    System.out.println("Tamano de fichas:"+lenF(inicioF));
    cont++;
}
imprimir(u);
}
public int RandomFicha(int hasta){//numero random para la ficha
  int randomInt = random.nextInt(hasta);
  return randomInt;
}
public Ficha BuscarFicha(int Indice){//buscar la ficha ya despues haber obtenido el ramdom
    Ficha aux=inicioF;
    int cont=0;
    if (inicioF.sigF==null){
        return inicioF;
    }
    if (Indice==0){
        return inicioF;
    }
    while(cont!=Indice){
       aux=aux.sigF;
       cont++;
    }
    return aux;
}
public Ficha eliminarFicha(Ficha f){//eliminar la ficha de la lista principal
    if (inicioF.sigF==null){
        inicioF=null;
        return null;
    }
    else if(inicioF.equals(f)){
        inicioF=inicioF.sigF;
        inicioF.antF=null;
    }
    else if(finF.equals(f)){
        finF=finF.antF;
        finF.sigF=null;
    }
    Ficha aux=inicioF;
    while(aux!=finF){
        if(aux.equals(f)){
            aux.sigF.antF=aux.antF;
            aux.antF.sigF=aux.sigF;
            return aux;
        }
        aux=aux.sigF;
    }
    return null;

}
    
public void imprimir(Usuario u){
        Ficha aux=u.sigFicha;
        System.out.println("-----------------------------------------------------");
        while(aux!=null){
            
            System.out.println(aux.valor1+"-+"+aux.valor2);
            
            aux=aux.sigFichaUsuario;
        }
        
}

public boolean direccion(Fichacolocada aux,int ificha,Graphics2D draw,Ficha fichadibujar,Partida partida,AffineTransform AT){
    Fichacolocada nuevo1= new Fichacolocada();
    Fichacolocada nuevo2= new Fichacolocada();
    if(aux.numero==7){ /*primera ficha*/
        draw.drawImage(fichadibujar.imagen,null,aux.iax,aux.iay);
        nuevo1 = new Fichacolocada(aux.iax,aux.iay+60,aux.ibx,aux.iby+60,aux.dax,aux.day+60,0);
        nuevo2 = new Fichacolocada(aux.iax,aux.iay-60,aux.ibx,aux.iby-60,aux.dax,aux.day-60,180);
        nuevo1.setNumero(fichadibujar.valor2);
        nuevo2.setNumero(fichadibujar.valor1);
       
        nuevo1.sigFc=nuevo2;
        nuevo2.antFc=nuevo1;
        iniciocolocar=nuevo1;
        aux=iniciocolocar;
        while (aux!=null){
            System.out.println("numero: "+aux.numero+ "  x= "+aux.iax+" y= "+aux.iay);
            aux=aux.sigFc;
        }
        draw.setColor(Color.GREEN);
        draw.fillRect(nuevo1.iax, nuevo1.iay, 30, 60);
        draw.fillRect(nuevo2.iax, nuevo2.iay, 30, 60); /*coloca los cuadros verdes para selecionar*/
        return true;
        
    }
        /*Este sera cuando son iguales crear 2 caminos y girar la ficha cuando tiene igual numero*/
 /*   if (fichadibujar.valor2==fichadibujar.valor1 && aux.numero == fichadibujar.valor2){ /*fichas igual numero*/
        /*INCOMPLETO*/
   /*     AT=AffineTransform.getRotateInstance(aux.ibx+15,aux.iby+30);
        draw.drawImage(fichadibujar.imagen,AT,partida);
        aux2 = new Fichacolocada(aux.iax,aux.iay,aux.ibx,aux.iby,aux.dax,aux.day,aux.rotacion+90);
        aux1 = new Fichacolocada(aux.iax,aux.iay,aux.ibx,aux.iby,aux.dax,aux.day,aux.rotacion+270);
        draw.setColor(Color.GREEN);
        draw.fillRect(aux.ibx-75,aux.iby,60,30);
        draw.fillRect(aux.dbx+75,aux.iby,60,30);
        return true;
    }*/
    
    if (aux.numero == fichadibujar.valor2){ /*Si la parte baja de la ficha es igual*/
        nuevo1.setNumero(fichadibujar.valor2);
        System.out.println("baja");
        if (aux.rotacion==0){ /*para abajo*/
            
            AT = AffineTransform.getTranslateInstance(aux.dbx,aux.dby+60);
            AT.rotate(Math.toRadians(180));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax,aux.iay+60,aux.ibx,aux.iby+60,aux.dax,aux.day+60,0);
            draw.setColor(Color.GREEN);

            if (nuevo1.iby>= 550){/*dibuja verda par ala izquierda*/
                nuevo1= new Fichacolocada(aux.iax-60,aux.iay+30,aux.ibx-60, aux.iby,aux.dax-30,aux.day+30,90);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
            }

            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==90){/*para la izquierda*/
            AT = AffineTransform.getTranslateInstance(aux.ibx,aux.iby);
            AT.rotate(Math.toRadians(270));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax-60,aux.iay,aux.ibx-60,aux.iby,aux.dax-60,aux.day,90);
            draw.setColor(Color.GREEN);
            if (nuevo1.iax<=100){/*dibuja verde para arriba*/
                nuevo1 = new Fichacolocada(aux.iax,aux.iay-60,aux.ibx,aux.iby-30,aux.dax-30,aux.day-60,180);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==180){/*para arriba*/
            AT = AffineTransform.getTranslateInstance(aux.iax,aux.iay);
            AT.rotate(Math.toRadians(0));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax,aux.iay-60,aux.ibx,aux.iby-60,aux.dax,aux.day-60,180);
            draw.setColor(Color.GREEN);
            if (nuevo1.iay<=120){/*Dibuja verde para derecha*/
                nuevo1 = new Fichacolocada(aux.iax+30,aux.iay,aux.ibx+30,aux.iby-30,aux.dax+60,aux.day,270);
                draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
                iniciocolocar=addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
            iniciocolocar=addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==270){/*para derecha*/
            AT = AffineTransform.getTranslateInstance(aux.dax,aux.day);
            AT.rotate(Math.toRadians(90));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax+60,aux.iay,aux.ibx+60,aux.iby,aux.dax+60,aux.day,270);
            draw.setColor(Color.GREEN);
            if(nuevo1.dax>=1230){/*dibuja verde para abajo*/
                nuevo1 = new Fichacolocada(aux.iax+30,aux.iay+30,aux.ibx+30,aux.iby+60,aux.dax,aux.day+30,0);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        
        return true;

    }

    
    if (aux.numero == fichadibujar.valor1){ /*Si la parte alta de la ficha es igual al numero*/
        nuevo1.setNumero(fichadibujar.valor1);
        System.out.println("parte alta");
        if (aux.rotacion==0){ /*para abajo*/
            AT = AffineTransform.getTranslateInstance(aux.iax,aux.iay);
            AT.rotate(Math.toRadians(0));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax,aux.iay+60,aux.ibx,aux.iby+60,aux.dax,aux.day+60,0);
            draw.setColor(Color.GREEN);

            if (nuevo1.iby>= 550){/*dibuja verda par ala izquierda*/
                nuevo1= new Fichacolocada(aux.iax-60,aux.iay+30,aux.ibx-60, aux.iby,aux.dax-30,aux.day+30,90);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }

            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==90){/*para la izquierda*/
            AT = AffineTransform.getTranslateInstance(aux.dax,aux.day);
            AT.rotate(Math.toRadians(90));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax-60,aux.iay,aux.ibx-60,aux.iby,aux.dax-60,aux.day,90);
            draw.setColor(Color.GREEN);
            if (nuevo1.iax<=100){/*dibuja verde para arriba*/
                nuevo1 = new Fichacolocada(aux.iax,aux.iay-60,aux.ibx,aux.iby-30,aux.dax-30,aux.day-60,180);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==180){/*para arriba*/
            AT = AffineTransform.getTranslateInstance(aux.dbx,aux.dby);
            AT.rotate(Math.toRadians(180));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax,aux.iay-60,aux.ibx,aux.iby-60,aux.dax,aux.day-60,180);
            draw.setColor(Color.GREEN);
            if (nuevo1.iay<=120){/*Dibuja verde para derecha*/
                nuevo1 = new Fichacolocada(aux.iax+30,aux.iay,aux.ibx+30,aux.iby-30,aux.dax+60,aux.day,270);
                draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        if (aux.rotacion==270){/*para derecha*/
            AT = AffineTransform.getTranslateInstance(aux.ibx,aux.iby);
            AT.rotate(Math.toRadians(270));
            draw.drawImage(fichadibujar.imagen,AT,null);
            nuevo1 = new Fichacolocada(aux.iax+60,aux.iay,aux.ibx+60,aux.iby,aux.dax+60,aux.day,270);
            draw.setColor(Color.GREEN);
            if(nuevo1.dax>=1230){/*dibuja verde para abajo*/
                nuevo1 = new Fichacolocada(aux.iax+30,aux.iay+30,aux.ibx+30,aux.iby+60,aux.dax,aux.day+30,0);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, aux, iniciocolocar);
                return true;
            }
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, aux, iniciocolocar);
            return true;
        }
        
    return true;

    }
    return false;
}
public Fichacolocada addfichacol(Fichacolocada nuevo,Fichacolocada aux,Fichacolocada iniciocolocar){
    if(aux==iniciocolocar){
        nuevo.sigFc=iniciocolocar.sigFc;
        iniciocolocar.sigFc.antFc=nuevo;
        iniciocolocar=nuevo;
        
    }
    else{
        aux.antFc.sigFc=nuevo;
        aux.sigFc.antFc=nuevo;
    }
    return iniciocolocar;
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
