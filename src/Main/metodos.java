package Main;

import java.io.*;
import java.io.Serializable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class metodos implements Serializable{
public Usuario inicio,fin;
public String insertarinicio(String nombre, String contraseña){//Funcion para insertar un usuario nuevo a la lista
        Usuario nuevo= new Usuario(nombre,contraseña,"0");
        if(inicio==null){
            fin=inicio=nuevo;
            inicio.sig=inicio;
            inicio.ant=inicio;
            return "Insertado";
        }
   
        nuevo.sig=inicio;
        inicio.ant=nuevo;
        fin.sig=nuevo;
        nuevo.ant=fin;
        inicio=nuevo;
        return "Insertado";
    }
    
 public String insertardelArchivo(Usuario u){//Funcion que inserta los usuarios guardados en el archivo
        if(inicio==null){
            fin=inicio=u;
            inicio.sig=inicio;
            inicio.ant=inicio;
            return "Insertado";
        }
        Usuario aux= buscarN(u.nombre);
        if (aux==null){
            u.sig=inicio;
            inicio.ant=u;
            fin.sig=u;
            u.ant=fin;
            inicio=u;
            imprimir();
            return "Insertado";
        }
        return "no insertado";
    }
     
public Usuario buscarN(String nombre){//Funcion para verificar si un usuario ya existe o no.
        Usuario aux=inicio;
        while(aux!=fin){
            if(aux.nombre.equals(nombre)){
                return aux;
            }
       aux=aux.sig;
        }
        if(fin.nombre.equals(nombre)){
            return aux;
        }
        return null;
    }
public Usuario buscarLog(String nombre,String contra){//Funcion para la hora de log, esta busca que el nombre de usuario y contraseña sean correctos.
        Usuario aux=inicio;
        while(aux!=fin){
            if(aux.nombre.equals(nombre)&aux.Contraseña.equals(contra)){
                return aux;
            }
        aux=aux.sig;
        }
        if(fin.nombre.equals(nombre)&fin.Contraseña.equals(contra)){
            return aux;}
        return null;
}
        
        
public  void escribirArchivo() {//Funcion para poder escribir en el archivo y tambien por si acaso el archivo no existe, crear uno desde cero.
        FileWriter fichero = null;
        PrintWriter pw = null;
        Usuario aux=inicio;
        
        try {
                fichero = new FileWriter("archivo.txt");
                pw = new PrintWriter(fichero);
                while(aux!=fin){
                    pw.println(aux.nombre);
                    pw.println(aux.Contraseña);
                    pw.println(aux.control);
                    aux=aux.sig;
            }
                pw.println(fin.nombre);
                pw.println(fin.Contraseña);
                pw.println(fin.control);    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
               if (null != fichero) {
                    fichero.close();
               }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }

public void leerUsuarios() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            
            archivo = new File("archivo.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            
            String linea;
            String nombre;
            String contra;
            String control;

            while ((linea = br.readLine()) != null) {
                    nombre=linea;
                    contra = br.readLine();
                    control=br.readLine();
                    Usuario nuevo=new Usuario(nombre,contra,control);
                    insertardelArchivo(nuevo);
                    System.out.println(nombre);
                    System.out.println(contra);
            }
        }
       catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
public void imprimir(){
        Usuario aux=inicio;
        while(aux!=null&aux.sig!=inicio){
            System.out.println(aux.nombre+"\t"+aux.Contraseña+"\t"+aux.control);
            aux=aux.sig;
        }
        aux=fin;
        System.out.println(aux.nombre+"\t"+aux.Contraseña+"\t"+aux.control);
}
}       

    
        
        

