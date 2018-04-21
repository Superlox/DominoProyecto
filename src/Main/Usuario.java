package Main;

import Grafico.Ficha;
import Grafico.Trampa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class Usuario {
    public String nombre;
    public String Contraseña;
    public String control;
    public Usuario sig,ant,sigP,antP;
    public Ficha sigFicha;
    public Trampa trampa;
    public boolean pasar;
    
    public Usuario(String Nombre, String Contraseña,String control) {
        this.nombre = Nombre;
        this.Contraseña = Contraseña;
        this.control=control;
        this.sig=this.ant=this.sigP=this.antP=null;
        this.pasar=false;

    }


    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public Trampa getTrampa() {
        return trampa;
    }

    public void setTrampa(Trampa trampa) {
        this.trampa = trampa;
    }
    

    
}
