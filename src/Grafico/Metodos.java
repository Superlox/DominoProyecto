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
    public Fichacolocada iniciocolocar= new Fichacolocada(660,310,660,370,690,310,0,7);
    
    

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
            if (x2<=x && x2+30>=x && y2<=y && y2+60>=y) {
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



public boolean direccion(Fichacolocada selecionada,int ificha,Graphics2D draw,Ficha fichadibujar,Partida partida,AffineTransform AT){
    if(selecionada.numero==7){ /*primera ficha*/
        Fichacolocada nuevo1;
        Fichacolocada nuevo2;
        draw.drawImage(fichadibujar.imagen,null,selecionada.iax,selecionada.iay);
        
        nuevo1 = new Fichacolocada(selecionada.iax,selecionada.iay-60,selecionada.ibx,selecionada.iby-60,selecionada.dax,selecionada.day-60,180);//arriba
        nuevo1.setNumero(fichadibujar.valor1);//arriba
        
        nuevo2 = new Fichacolocada(selecionada.iax,selecionada.iay+60,selecionada.ibx,selecionada.iby+60,selecionada.dax,selecionada.day+60,0); //va para abajo        
        nuevo2.setNumero(fichadibujar.valor2);//abajo
       
        iniciocolocar.setSinusar(false); //para decir que ya no se puede dibujar en esta.
        iniciocolocar.antFc=nuevo2;
        nuevo2.sigFc=iniciocolocar;//inserto al inicio
        nuevo2.antFc=nuevo1;
        nuevo1.sigFc=nuevo2;
        iniciocolocar=nuevo1;
        
        draw.setColor(Color.GREEN);
        draw.fillRect(nuevo1.iax, nuevo1.iay, 30, 60);
        draw.fillRect(nuevo2.iax, nuevo2.iay, 30, 60); /*coloca los cuadros verdes para selecionar*/
        return true;
        
    }
        //Este sera cuando son iguales crear 2 caminos y girar la ficha cuando tiene igual numero*/
    if (fichadibujar.valor2==fichadibujar.valor1 && selecionada.numero == fichadibujar.valor2){ //fichas igual numero
        Fichacolocada nuevo1;
        Fichacolocada nuevo2;
        
        if (selecionada.rotacion==0){ //para abajo
            AT = AffineTransform.getTranslateInstance(selecionada.dax+15,selecionada.day);
            AT.rotate(Math.toRadians(90));
            draw.drawImage(fichadibujar.imagen,AT,null);//coloco la ficha horizontal
            
            draw.setColor(Color.WHITE);//borra el cuadro verde que sobresale
            draw.fillRect(selecionada.iax, selecionada.iay+30, 30, 30);
            
            nuevo1 = new Fichacolocada(selecionada.iax-75,selecionada.iay,selecionada.ibx-75,selecionada.iby-30,selecionada.dax-45,selecionada.day,90);
            nuevo2 = new Fichacolocada(selecionada.iax+45,selecionada.iay,selecionada.ibx+45,selecionada.iby-30,selecionada.dax+75,selecionada.day,270);
            draw.setColor(Color.GREEN);
            draw.fillRect(nuevo1.iax, nuevo1.iay, 60, 30);
            draw.fillRect(nuevo2.iax, nuevo2.iay, 60, 30);
            addfichacol(nuevo1, selecionada, fichadibujar);
            addfichacol(nuevo2, selecionada, fichadibujar);    
        }
        
        if (selecionada.rotacion==90){//para la izquierda
            AT = AffineTransform.getTranslateInstance(selecionada.dbx,selecionada.dby+15);
            AT.rotate(Math.toRadians(180));
            draw.drawImage(fichadibujar.imagen,AT,null);//coloco la ficha horizontal
            
            draw.setColor(Color.WHITE);//borra el cuadro verde que sobresale
            draw.fillRect(selecionada.iax, selecionada.iay, 30, 30);
            
            nuevo1 = new Fichacolocada(selecionada.iax+30,selecionada.iay-75,selecionada.ibx+30,selecionada.iby-45,selecionada.dax,selecionada.day-75,180);
            nuevo2 = new Fichacolocada(selecionada.iax+30,selecionada.iay+45,selecionada.ibx+30,selecionada.iby+75,selecionada.dax,selecionada.day+45,0);
            draw.setColor(Color.GREEN);
            draw.fillRect(nuevo1.iax, nuevo1.iay, 30, 60);
            draw.fillRect(nuevo2.iax, nuevo2.iay, 30, 60);
            addfichacol(nuevo1, selecionada, fichadibujar);
            addfichacol(nuevo2, selecionada, fichadibujar);    
        }
        
        if (selecionada.rotacion==180){ //para arriba
            AT = AffineTransform.getTranslateInstance(selecionada.ibx-15,selecionada.iby);
            AT.rotate(Math.toRadians(270));
            draw.drawImage(fichadibujar.imagen,AT,null);//coloco la ficha horizontal
            
            draw.setColor(Color.WHITE);//borra el cuadro verde que sobresale
            draw.fillRect(selecionada.iax, selecionada.iay, 30, 30);
            
            nuevo1 = new Fichacolocada(selecionada.iax-75,selecionada.iay+30,selecionada.ibx-75,selecionada.iby,selecionada.dax-45,selecionada.day+30,90);//izquierda
            nuevo2 = new Fichacolocada(selecionada.iax+45,selecionada.iay+30,selecionada.ibx+45,selecionada.iby,selecionada.dax+75,selecionada.day+30,270);//derecha
            draw.setColor(Color.GREEN);
            draw.fillRect(nuevo1.iax, nuevo1.iay, 60, 30);
            draw.fillRect(nuevo2.iax, nuevo2.iay, 60, 30);
            addfichacol(nuevo1, selecionada, fichadibujar);
            addfichacol(nuevo2, selecionada, fichadibujar);    
        }
        
        if (selecionada.rotacion==270){//para derecha
            AT = AffineTransform.getTranslateInstance(selecionada.iax,selecionada.iay-15);
            AT.rotate(Math.toRadians(0));
            draw.drawImage(fichadibujar.imagen,AT,null);//coloco la ficha horizontal
            
            draw.setColor(Color.WHITE);//borra el cuadro verde que sobresale
            draw.fillRect(selecionada.dax-30, selecionada.day, 30, 30);
            
            nuevo1 = new Fichacolocada(selecionada.iax,selecionada.iay-75,selecionada.ibx,selecionada.iby-45,selecionada.dax-30,selecionada.day-75,180);//arriba
            nuevo2 = new Fichacolocada(selecionada.iax,selecionada.iay+45,selecionada.ibx,selecionada.iby+75,selecionada.dax-30,selecionada.day+45,0);//abajo
            draw.setColor(Color.GREEN);
            draw.fillRect(nuevo1.iax, nuevo1.iay, 30, 60);
            draw.fillRect(nuevo2.iax, nuevo2.iay, 30, 60);
            addfichacol(nuevo1, selecionada, fichadibujar);
            addfichacol(nuevo2, selecionada, fichadibujar);    
        }
        return true;
    }
    //colocacion normal y con cambio de direecion en caso de chocar pared
    if (selecionada.numero == fichadibujar.valor1 || selecionada.numero==fichadibujar.valor2){//Si cualquiera de los 2 valores son iguales;
        Fichacolocada nuevo1= new Fichacolocada();
        if (selecionada.rotacion==0){ //para abajo
            System.out.println("0");
            //SE COLOCA LA FICHA QUE QUEREMOS UBICAR
            if (selecionada.numero==fichadibujar.valor1){//si la parte alta de la ficha es igual a la ficha ubicada arriba
                draw.drawImage(fichadibujar.imagen,null,selecionada.iax,selecionada.iay); 
            }
            if(selecionada.numero==fichadibujar.valor2){//la parte baja de la ficha es igual a la ficha ubicada arriba
                AT = AffineTransform.getTranslateInstance(selecionada.dbx,selecionada.dby);
                AT.rotate(Math.toRadians(180));
                draw.drawImage(fichadibujar.imagen,AT,null);

            }
            //se dibuja los cuadros verdes//////////////////////////////
            
            nuevo1 = new Fichacolocada(selecionada.iax,selecionada.iay+60,selecionada.ibx,selecionada.iby+60,selecionada.dax,selecionada.day+60,0);
            //da las posicione normal
            draw.setColor(Color.GREEN);            
            if (nuevo1.iby>= 530){//Si choca  o se pasa con pared CAMBIAMOS la posicion normal
                nuevo1= new Fichacolocada(selecionada.iax-60,selecionada.iay+30,selecionada.ibx-60, selecionada.iby,selecionada.dax-30,selecionada.day+30,90);
                draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
                addfichacol(nuevo1, selecionada,fichadibujar);
                return true;
            }
            //lo dibuja con posicion normal
            
            draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
            addfichacol(nuevo1, selecionada,fichadibujar);
            return true;
            
            }

        if (selecionada.rotacion==90){ //para la izquierda
            //SE COLOCA LA FICHA QUE QUEREMOS UBICAR
            System.out.println("90");
            if (selecionada.numero==fichadibujar.valor1){//si la parte alta de la ficha es igual a la ficha ubicada derecha
                AT = AffineTransform.getTranslateInstance(selecionada.dax,selecionada.day);
                AT.rotate(Math.toRadians(90));
                draw.drawImage(fichadibujar.imagen,AT,null); 
            }
            if(selecionada.numero==fichadibujar.valor2){//la parte baja de la ficha es igual a la ficha ubicada derecha
                AT = AffineTransform.getTranslateInstance(selecionada.ibx,selecionada.iby);
                AT.rotate(Math.toRadians(270));
                draw.drawImage(fichadibujar.imagen,AT,null);
            }
            //se dibuja los cuadros verdes///////////////////////////////////
            
            nuevo1 = new Fichacolocada(selecionada.iax-60,selecionada.iay,selecionada.ibx-60,selecionada.iby,selecionada.dax-60,selecionada.day,90);
            //da las posicione normal
            draw.setColor(Color.GREEN);            
            if (nuevo1.ibx<= 141){//Si choca  o se pasa con pared CAMBIAMOS la posicion normal
                nuevo1= new Fichacolocada(selecionada.iax,selecionada.iay-60,selecionada.ibx, selecionada.iby-30,selecionada.dax-30,selecionada.day-60,180);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, selecionada,fichadibujar);
                return true;
            }
            //lo dibuja con posicion normal
            
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, selecionada,fichadibujar);
            return true;
           
            }
        if (selecionada.rotacion==180){ //para la arriba
            //SE COLOCA LA FICHA QUE QUEREMOS UBICAR
            System.out.println("180");
            if (selecionada.numero==fichadibujar.valor1){//si la parte alta de la ficha es igual a la ficha ubicada abajo
                AT = AffineTransform.getTranslateInstance(selecionada.dbx,selecionada.dby);
                AT.rotate(Math.toRadians(180));
                draw.drawImage(fichadibujar.imagen,AT,null); 
            }
            if(selecionada.numero==fichadibujar.valor2){//la parte baja de la ficha es igual a la ficha ubicada abajo
                draw.drawImage(fichadibujar.imagen,null,selecionada.iax,selecionada.iay);
            }
            //se dibuja los cuadros verdes///////////////////////////////////
            
            nuevo1 = new Fichacolocada(selecionada.iax,selecionada.iay-60,selecionada.ibx,selecionada.iby-60,selecionada.dax,selecionada.day-60,180);
            //da las posicione normal
            draw.setColor(Color.GREEN);            
            if (nuevo1.iay<= 150){//Si choca  o se pasa con pared CAMBIAMOS la posicion normal
                nuevo1 = new Fichacolocada(selecionada.iax+30,selecionada.iay,selecionada.ibx+30,selecionada.iby-30,selecionada.dax+60,selecionada.day,270);
                draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
                addfichacol(nuevo1, selecionada,fichadibujar);
                return true;
            }
            //lo dibuja con posicion normal
            
            draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
            addfichacol(nuevo1, selecionada,fichadibujar);
            return true;
           
            }
        if (selecionada.rotacion==270){ //para la derecha
            //SE COLOCA LA FICHA QUE QUEREMOS UBICAR
            System.out.println("270");
            if (selecionada.numero==fichadibujar.valor1){//si la parte alta de la ficha es igual a la ficha ubicada izquierda
                AT = AffineTransform.getTranslateInstance(selecionada.ibx,selecionada.iby);
                AT.rotate(Math.toRadians(270));
                draw.drawImage(fichadibujar.imagen,AT,null); 
            }
            if(selecionada.numero==fichadibujar.valor2){//la parte baja de la ficha es igual a la ficha ubicada izquierda
                AT = AffineTransform.getTranslateInstance(selecionada.dax,selecionada.day);
                AT.rotate(Math.toRadians(90));
                draw.drawImage(fichadibujar.imagen,AT,null);
            }
            //se dibuja los cuadros verdes///////////////////////////////////
            
            nuevo1 = new Fichacolocada(selecionada.iax+60,selecionada.iay,selecionada.ibx+60,selecionada.iby,selecionada.dax+60,selecionada.day,270);
            //da las posicione normal
            draw.setColor(Color.GREEN);            
            if (nuevo1.dax>= 1211){//Si choca  o se pasa con pared CAMBIAMOS la posicion normal
                nuevo1 = new Fichacolocada(selecionada.iax+30,selecionada.iay+30,selecionada.ibx+30,selecionada.iby+60,selecionada.dax,selecionada.day+30,0);
                draw.fillRect(nuevo1.iax,nuevo1.iay,30,60);
                addfichacol(nuevo1, selecionada,fichadibujar);
                return true;
            }
            //lo dibuja con posicion normal
            
            draw.fillRect(nuevo1.iax,nuevo1.iay,60,30);
            addfichacol(nuevo1, selecionada,fichadibujar);
            return true;
           
            }

    }
    return false;
}

public void addfichacol(Fichacolocada nuevo,Fichacolocada selecionada,Ficha fichadibujar){
    if(fichadibujar.valor1==selecionada.numero)
        nuevo.setNumero(fichadibujar.valor2);
    else
        nuevo.setNumero(fichadibujar.valor1);
    selecionada.setSinusar(false);//para mostrar que ya se uso
    nuevo.sigFc=iniciocolocar;//enlaza el nuevo en el inicio
    iniciocolocar.antFc=nuevo;
    iniciocolocar=nuevo;
}
public void print(Fichacolocada inicio,Ficha fichadibujar){
    while (inicio!=null){
        
        System.out.println("Fichascolocadas\n"+inicio.iax+"-"+inicio.iay+"-"+inicio.numero);
        inicio=inicio.sigFc;
    }
    System.out.println("Ficha a dibujar:"+fichadibujar.valor1+"-"+fichadibujar.valor2);
}
public int lenP(Usuario x){
      Usuario aux=x.sigP;
        int cont=1;
        while (aux!=x){
            aux=aux.sigP;
            
            cont+=1;
            
        }
        System.out.println(cont);
        return cont;
}

}
