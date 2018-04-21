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
public class Trampa {
    public String Nombre;
    public boolean sinusar;

    public Trampa() {
    }

    
    public Trampa(String Nombre) {
        this.Nombre = Nombre;
        this.sinusar = true;
    }

    public boolean isSinusar() {
        return sinusar;
    }

    public void setSinusar(boolean sinusar) {
        this.sinusar = sinusar;
    }
    
    
    
}
