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
    public int numero=7;
    Fichacolocada sigFc;
    
    public Fichacolocada(int iax, int iay, int ibx, int iby, int dax, int day) {
        this.iax = iax;
        this.iay = iay;
        this.ibx = ibx;
        this.iby = iby;
        this.dax = dax;
        this.day = day;
        this.dbx = iby;
        this.dby = dax;
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
    
}
