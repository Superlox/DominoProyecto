/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

/**
 *
 * @author ADMIN
 */
public class Fichacolocada {
    public int iax,iay,ibx,iby,dax,day,dbx,dby;
    public int trampa;
    public int numero;
    public Fichacolocada sigFc,antFc;
    public int rotacion;
    public boolean sinusar;
    public Fichacolocada() {
    }
    
    public Fichacolocada(int iax, int iay, int ibx, int iby, int dax, int day,int rotacion) {
        this.iax = iax;
        this.iay = iay;
        this.ibx = ibx;
        this.iby = iby;
        this.dax = dax;
        this.day = day;
        this.dbx = dax;
        this.dby = iby;
        this.rotacion=rotacion;
        this.sinusar=true;
    }
    public Fichacolocada(int iax, int iay, int ibx, int iby, int dax, int day,int rotacion, int numero) {
        this.iax = iax;
        this.iay = iay;
        this.ibx = ibx;
        this.iby = iby;
        this.dax = dax;
        this.day = day;
        this.dbx = dax;
        this.dby = iby;
        this.rotacion=rotacion;
        this.numero=numero;
        this.sinusar=true;
    }    


    public void setTrampa(int trampa) {
        this.trampa = trampa;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIax() {
        return iax;
    }

    public int getIay() {
        return iay;
    }

    public void setSinusar(boolean sinusar) {
        this.sinusar = sinusar;
    }
    
    

 }


